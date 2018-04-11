package com.facebank.usersupport.attendance.controller;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.attendance.model.DealRecordModel;
import com.facebank.usersupport.attendance.model.EmpModel;
import com.facebank.usersupport.attendance.service.IDealRecordService;
import com.facebank.usersupport.attendance.service.IDealService;
import com.facebank.usersupport.attendance.service.IEmpService;
import com.facebank.usersupport.attendance.service.ILeaveService;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author zhanguo.huang
 * @date  2018-03-27
 */
@RestController
public class LeaveController extends BaseController {

    @Autowired
    private ILeaveService leaveService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IEmpService empService;
    @Autowired
    private IDealRecordService dealRecordService;
    @Autowired
    private IDealService dealService;

    /**
     * 提交请假申请
     * @param applyRecordModel
     * @return
     */
    @PostMapping ("/attendance/addLeaveApply")
    public RestModel addLeaveApply(ApplyRecordModel applyRecordModel, HttpServletRequest request, HttpServletResponse response) {
        ApplyRecordModel applyRecordModel1 = (ApplyRecordModel) request.getAttribute("applyRecordModel");
        if(applyRecordModel1 != null){
            applyRecordModel = applyRecordModel1;
        }
        try {
            Long userId = userService.getActiveUserId();
            UserModel userModel = userService.getByUserId(userId);
            EmpModel empModel = empService.getEmpByWorkNumber(userModel.getWorkNumber());
            applyRecordModel.setWorkNumber(empModel.getWorkNumber());
            applyRecordModel.setEmpName(empModel.getName());
            applyRecordModel.setApplyDate(new Date());
            applyRecordModel.setApplyType(1);
            RestModel model = new RestModel();
            if(applyRecordModel1 == null){
                leaveService.addLeaveApply(applyRecordModel);
            }
            DealRecordModel dealRecordModel = new DealRecordModel();
            //如果申请时长少于8小时，由上级审批
            if(applyRecordModel.getApplyDuration() <= 8){
                dealRecordModel.setApplyId(applyRecordModel.getId());
                dealRecordModel.setWorkNumber(empModel.getParentNumber());
                dealRecordModel.setDealLevel(1);
                dealRecordModel.setStatus(1);
                model = dealRecordService.insertApplyDealRecord(dealRecordModel);
            }else if(applyRecordModel.getApplyDuration() > 8 && applyRecordModel.getApplyDuration() <= 24){
                //如果申请时长大于8小时小于24，由上级和上上级。两级审批
                dealRecordModel.setApplyId(applyRecordModel.getId());
                dealRecordModel.setWorkNumber(empModel.getParentNumber());
                dealRecordModel.setDealLevel(1);
                dealRecordModel.setStatus(1);
                dealRecordService.insertApplyDealRecord(dealRecordModel);
                EmpModel empParentModel = empService.getEmpByParentNumber(empModel);
                dealRecordModel.setDealLevel(2);
                dealRecordModel.setStatus(0);
                dealRecordModel.setWorkNumber(empParentModel.getParentNumber());
                model = dealRecordService.insertApplyDealRecord(dealRecordModel);
            }else{
                //如果申请时长大于24小时，由上级审批，上上级，上上上级，三级审批
                dealRecordModel.setApplyId(applyRecordModel.getId());
                dealRecordModel.setWorkNumber(empModel.getParentNumber());
                dealRecordModel.setDealLevel(1);
                dealRecordModel.setStatus(1);
                dealRecordService.insertApplyDealRecord(dealRecordModel);
                EmpModel empParentModel = empService.getEmpByParentNumber(empModel);
                dealRecordModel.setDealLevel(2);
                dealRecordModel.setStatus(0);
                dealRecordModel.setWorkNumber(empParentModel.getParentNumber());
                model = dealRecordService.insertApplyDealRecord(dealRecordModel);
                EmpModel empParentModel2 = empService.getEmpByParentNumber(empParentModel);
                if(empParentModel2.getParentNumber() != null){
                    dealRecordModel.setDealLevel(3);
                    dealRecordModel.setStatus(0);
                    dealRecordModel.setWorkNumber(empParentModel2.getParentNumber());
                    model = dealRecordService.insertApplyDealRecord(dealRecordModel);
                }
            }
            if (model.getCode().equals(RestModel.CODE_SUCCESS)) {
                return this.success(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.excpRestModel(MessageKeyEnum.REST_SERVICE_ERROR);
    }

    /**
     *分页查询
     * @param start
     * @param length
     * @param draw
     * @param queryVo
     * @return
     */
    @PostMapping("/attendance/getApplyByCondition")
    public RestModel getApplyByCondition(@RequestParam(required = false, defaultValue = "1") int start,
                                         @RequestParam(required = false, defaultValue = "10") int length,
                                         String draw,
                                         QueryVo queryVo){
        try{
            Long userId = userService.getActiveUserId();
            UserModel userModel = userService.getByUserId(userId);
            queryVo.setWorkNumber(userModel.getWorkNumber());
            int pageNo = start / length + 1;
            PageInfo pageInfo = leaveService.selectByPage(length,pageNo,queryVo);
            PageRestModel pageRestModel = new PageRestModel(
                    draw,
                    pageInfo.getTotal(),
                    pageInfo.getTotal(),
                    pageInfo.getList()
            );
            return this.success(pageRestModel);

        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    /**
     * 更新申请信息
     * @param applyRecordModel
     * @return
     */
    @PostMapping ("/attendance/updateApplyRecord")
    public RestModel updateApplyRecord(ApplyRecordModel applyRecordModel, HttpServletRequest request, HttpServletResponse response) {
        try {
            RestModel model = new RestModel();
            applyRecordModel.setApplyDate(new Date());
            ApplyRecordModel applyRecordModel2 = leaveService.getApplyRecordById(applyRecordModel.getId());
            if(applyRecordModel.getApplyDuration() == applyRecordModel2.getApplyDuration()
                    || (applyRecordModel.getApplyDuration() <= 8 && applyRecordModel2.getApplyDuration() <= 8)
                    || (applyRecordModel.getApplyDuration() > 8 && applyRecordModel.getApplyDuration() <= 24
                    && applyRecordModel2.getApplyDuration() > 8 && applyRecordModel2.getApplyDuration() <= 24)
                    || (applyRecordModel.getApplyDuration() > 24 && applyRecordModel2.getApplyDuration() > 24) ){

                model = leaveService.updateLeaveApply(applyRecordModel);
            }else {
                model = leaveService.updateLeaveApply(applyRecordModel);
                dealRecordService.deleteApplyDealRecord(applyRecordModel.getId());
                request.setAttribute("applyRecordModel",applyRecordModel);
                request.getRequestDispatcher("/attendance/addLeaveApply").forward(request,response);
            }
            if (model.getCode().equals(RestModel.CODE_SUCCESS)) {
                return this.success(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.excpRestModel(MessageKeyEnum.REST_SERVICE_ERROR);
    }

    /**
     *审核
     * @param start
     * @param length
     * @param draw
     * @param queryVo
     * @return
     */
    @PostMapping("/attendance/applyDeal")
    public RestModel applyDeal(@RequestParam(required = false, defaultValue = "1") int start,
                               @RequestParam(required = false, defaultValue = "10") int length,
                               String draw,
                               QueryVo queryVo){
        try{
            Long userId = userService.getActiveUserId();
            UserModel userModel = userService.getByUserId(userId);
            queryVo.setWorkNumber(userModel.getWorkNumber());
            int pageNo = start / length + 1;
            PageInfo pageInfo = dealService.selectByPage(length,pageNo,queryVo);
            PageRestModel pageRestModel = new PageRestModel(
                    draw,
                    pageInfo.getTotal(),
                    pageInfo.getTotal(),
                    pageInfo.getList()
            );
            return this.success(pageRestModel);

        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    /**
     *批准申请
     * @param dealRecordModel
     * @return
     */
    @PostMapping("/attendance/passApply")
    public RestModel passApply(DealRecordModel dealRecordModel){
        //更新状态--2待审核
        dealRecordModel.setStatus(2);
        dealRecordModel.setDealTime(new Date());
        RestModel restModel = new RestModel();
        DealRecordModel deal = dealRecordService.findDealRecordById(dealRecordModel);
        List<DealRecordModel> lists = dealRecordService.findDealRecordByApplyId(deal.getApplyId());
        if (lists != null && lists.size() >= 2) {
            lists.get(1).setStatus(1);
            dealRecordService.updateApplyDealRecord(lists.get(1));
            restModel = dealRecordService.updateApplyDealRecord(dealRecordModel);
        }
        if (lists.size() == 1) {

            restModel = dealRecordService.updateApplyDealRecord(dealRecordModel);
            //把申请表字段改为同意
            ApplyRecordModel applyRecordModel = new ApplyRecordModel();
            applyRecordModel.setId(deal.getApplyId());
            //2--申请通过
            applyRecordModel.setStatus(2);
            restModel = leaveService.updateApplyRecordStatus(applyRecordModel);

            if(restModel.getCode().equals(RestModel.CODE_SUCCESS)){
                //获取申请记录
                //获取emp信息
                //修改总时长
                applyRecordModel = leaveService.getApplyRecordById(deal.getApplyId());
                UserModel userModel = new UserModel();
                userModel.setWorkNumber(applyRecordModel.getWorkNumber());
                EmpModel empModel = empService.getEmpByWorkNumber(userModel.getWorkNumber());
                empModel.setLeaveDuration(empModel.getLeaveDuration()+applyRecordModel.getApplyDuration());
                restModel = empService.updateEmpInfo(empModel);
            }


        }
        if (restModel.getCode().equals(RestModel.CODE_SUCCESS)) {
            return this.success(restModel);
        }
        return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
    }

    /**
     * 不通过操作
     */
    @PostMapping("/attendance/rejectApply")
    public RestModel rejectApply(DealRecordModel dealRecordModel) {
        System.out.println(dealRecordModel.getId() + "---" + dealRecordModel.getNotpassCause());
        dealRecordModel.setStatus(2);
        dealRecordModel.setDealTime(new Date());
        RestModel restModel = new RestModel();
        restModel = dealRecordService.updateApplyDealRecord(dealRecordModel);
        //不同意就直接向申请表传入不同意，并传入拒绝原因
        DealRecordModel deal = dealRecordService.findDealRecordById(dealRecordModel);
        ApplyRecordModel applyRecordModel = new ApplyRecordModel();
        //3--不通过
        applyRecordModel.setStatus(3);
        applyRecordModel.setId(deal.getApplyId());
        applyRecordModel.setNotpassCause(dealRecordModel.getNotpassCause());
        restModel = leaveService.updateApplyRecordStatus(applyRecordModel);
        if (restModel.getCode().equals(RestModel.CODE_SUCCESS)) {
            return this.success(restModel);
        }
        return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
    }
}
