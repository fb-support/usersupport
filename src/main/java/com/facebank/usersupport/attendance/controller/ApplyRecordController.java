package com.facebank.usersupport.attendance.controller;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.attendance.model.DealRecordModel;
import com.facebank.usersupport.attendance.model.EmpModel;
import com.facebank.usersupport.attendance.service.IApplyRecordService;
import com.facebank.usersupport.attendance.service.IDealRecordService;
import com.facebank.usersupport.attendance.service.IDealService;
import com.facebank.usersupport.attendance.service.IEmpService;
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

/**
 * @author zhanguo.huang
 * @date 2018-04-12
 */
@RestController
public class ApplyRecordController extends BaseController {

    @Autowired
    private IApplyRecordService applyRecordService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IEmpService empService;
    @Autowired
    private IDealRecordService dealRecordService;
    @Autowired
    private IDealService dealService;
    /**
     * 提交申请
     * @param applyRecordModel
     * @return
     */
    @PostMapping("/attendance/addApply")
    public RestModel addApply(ApplyRecordModel applyRecordModel, HttpServletRequest request, HttpServletResponse response) {
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
//            applyRecordModel.setApplyType(1);
            RestModel model = new RestModel();

            DealRecordModel dealRecordModel = new DealRecordModel();
            if(applyRecordModel.getApplyType() != 0) {
                if(applyRecordModel1 == null){
                    applyRecordService.addApplyRecord(applyRecordModel);
                }
                //如果申请时长少于8小时，由上级审批
                if (applyRecordModel.getApplyDuration() <= 8) {
                    dealRecordModel.setApplyId(applyRecordModel.getId());
                    dealRecordModel.setWorkNumber(empModel.getParentNumber());
                    dealRecordModel.setDealLevel(1);
                    dealRecordModel.setStatus(1);
                    model = dealRecordService.insertApplyDealRecord(dealRecordModel);
                } else if (applyRecordModel.getApplyDuration() > 8 && applyRecordModel.getApplyDuration() <= 24) {
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
                } else {
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
                    if (empParentModel2.getParentNumber() != null) {
                        dealRecordModel.setDealLevel(3);
                        dealRecordModel.setStatus(0);
                        dealRecordModel.setWorkNumber(empParentModel2.getParentNumber());
                        model = dealRecordService.insertApplyDealRecord(dealRecordModel);
                    }
                }
            }else {
                if(applyRecordModel.getApplyDuration() <= 7.5){
                    if(applyRecordModel1 == null){
                        applyRecordService.addApplyRecord(applyRecordModel);
                    }
                    //如果申请时长少于8小时，由上级审批
                    if (applyRecordModel.getApplyDuration() <= 3) {
                        dealRecordModel.setApplyId(applyRecordModel.getId());
                        dealRecordModel.setWorkNumber(empModel.getParentNumber());
                        dealRecordModel.setDealLevel(1);
                        dealRecordModel.setStatus(1);
                        model = dealRecordService.insertApplyDealRecord(dealRecordModel);
                    } else if (applyRecordModel.getApplyDuration() > 3 && applyRecordModel.getApplyDuration() <= 5) {
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
                    } else if(applyRecordModel.getApplyDuration() > 5 && applyRecordModel.getApplyDuration() <= 7.5){
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
                        if (empParentModel2.getParentNumber() != null) {
                            dealRecordModel.setDealLevel(3);
                            dealRecordModel.setStatus(0);
                            dealRecordModel.setWorkNumber(empParentModel2.getParentNumber());
                            model = dealRecordService.insertApplyDealRecord(dealRecordModel);
                        }
                    }
                } else{
                    return this.excpRestModel("0","申请加班时长不可超过7.5小时");
                }

            }
            if (model.getCode().equals(RestModel.CODE_SUCCESS) && model != null) {
                return this.success(model);
            }else{
                return this.excpRestModel("0","申请加班时长不可超过7.5小时");
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
    @PostMapping("/attendance/getApplyRecordByCondition")
    public RestModel getApplyRecordByCondition(@RequestParam(required = false, defaultValue = "1") int start,
                                               @RequestParam(required = false, defaultValue = "10") int length,
                                               String draw,
                                               QueryVo queryVo){
        try{
            Long userId = userService.getActiveUserId();
            UserModel userModel = userService.getByUserId(userId);
            queryVo.setWorkNumber(userModel.getWorkNumber());
            int pageNo = start / length + 1;
            PageInfo pageInfo = applyRecordService.selectByPage(length,pageNo,queryVo);
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
    @PostMapping ("/attendance/updateApply")
    public RestModel updateApplyRecord(ApplyRecordModel applyRecordModel, HttpServletRequest request, HttpServletResponse response) {
        try {
            RestModel model = new RestModel();
            applyRecordModel.setApplyDate(new Date());
            ApplyRecordModel applyRecordModel2 = applyRecordService.getApplyRecordById(applyRecordModel.getId());
            applyRecordModel.setApplyType(applyRecordModel2.getApplyType());
            if(applyRecordModel.getApplyType() != 0) {
                if (applyRecordModel.getApplyDuration() == applyRecordModel2.getApplyDuration()
                        || (applyRecordModel.getApplyDuration() <= 8 && applyRecordModel2.getApplyDuration() <= 8)
                        || (applyRecordModel.getApplyDuration() > 8 && applyRecordModel.getApplyDuration() <= 24
                        && applyRecordModel2.getApplyDuration() > 8 && applyRecordModel2.getApplyDuration() <= 24)
                        || (applyRecordModel.getApplyDuration() > 24 && applyRecordModel2.getApplyDuration() > 24)) {

                    model = applyRecordService.updateApplyRecord(applyRecordModel);
                } else {
                    model = applyRecordService.updateApplyRecord(applyRecordModel);
                    dealRecordService.deleteApplyDealRecord(applyRecordModel.getId());
                    request.setAttribute("applyRecordModel", applyRecordModel);
                    request.getRequestDispatcher("/attendance/addApply").forward(request, response);
                }
            }else{
                if (applyRecordModel.getApplyDuration() > 7.5){

                    return this.excpRestModel("0","申请加班时长不可超过7.5小时");

                }else if (applyRecordModel.getApplyDuration() == applyRecordModel2.getApplyDuration()
                        || (applyRecordModel.getApplyDuration() <= 3 && applyRecordModel2.getApplyDuration() <= 3)
                        || (applyRecordModel.getApplyDuration() > 3 && applyRecordModel.getApplyDuration() <= 5
                        && applyRecordModel2.getApplyDuration() > 3 && applyRecordModel2.getApplyDuration() <= 5)
                        || (applyRecordModel.getApplyDuration() > 5 && applyRecordModel.getApplyDuration() <= 7.5
                        && applyRecordModel2.getApplyDuration() > 5 && applyRecordModel2.getApplyDuration() <= 7.5)) {

                    model = applyRecordService.updateApplyRecord(applyRecordModel);
                } else {
                    model = applyRecordService.updateApplyRecord(applyRecordModel);
                    dealRecordService.deleteApplyDealRecord(applyRecordModel.getId());
                    request.setAttribute("applyRecordModel", applyRecordModel);
                    request.getRequestDispatcher("/attendance/addApply").forward(request, response);
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
}
