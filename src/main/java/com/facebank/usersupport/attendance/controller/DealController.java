package com.facebank.usersupport.attendance.controller;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.attendance.model.DealRecordModel;
import com.facebank.usersupport.attendance.model.EmpModel;
import com.facebank.usersupport.attendance.service.*;
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

import java.util.Date;
import java.util.List;

/**
 * @author zhanguo.huang
 * @date 2018-04-02
 */
@RestController
public class DealController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IEmpService empService;
    @Autowired
    private IDealService dealService;
    @Autowired
    private IDealRecordService dealRecordService;

    @Autowired
    private IApplyRecordService applyRecordService;

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
        ApplyRecordModel applyRecordModel = new ApplyRecordModel();
        if (lists != null && lists.size() >= 2) {
            //把申请表字段改为同意
            applyRecordModel.setId(deal.getApplyId());
            //2--申请审核
            applyRecordModel.setStatus(1);
            applyRecordService.updateApplyRecordStatus(applyRecordModel);
            lists.get(1).setStatus(1);
            dealRecordService.updateApplyDealRecord(lists.get(1));
            restModel = dealRecordService.updateApplyDealRecord(dealRecordModel);
        }
        if (lists.size() == 1) {

            dealRecordService.updateApplyDealRecord(dealRecordModel);
            //把申请表字段改为同意
            applyRecordModel.setId(deal.getApplyId());
            //2--申请通过
            applyRecordModel.setStatus(2);
            restModel = applyRecordService.updateApplyRecordStatus(applyRecordModel);

            if(restModel.getCode().equals(RestModel.CODE_SUCCESS)){
                //获取申请记录
                //获取emp信息
                //修改总时长
                applyRecordModel = applyRecordService.getApplyRecordById(deal.getApplyId());
                UserModel userModel = new UserModel();
                userModel.setWorkNumber(applyRecordModel.getWorkNumber());
                EmpModel empModel = empService.getEmpByWorkNumber(userModel.getWorkNumber());
                if(applyRecordModel.getApplyType() == 0){
                    //加班
                    empModel.setOvertimeDuration(empModel.getOvertimeDuration()+applyRecordModel.getApplyDuration());
                }else if(applyRecordModel.getApplyType() == 1){
                    //请假
                    empModel.setLeaveDuration(empModel.getLeaveDuration()+applyRecordModel.getApplyDuration());
                }else {
                    //调休
                    empModel.setRestDuration(empModel.getRestDuration()+applyRecordModel.getApplyDuration());
                }
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
        restModel = applyRecordService.updateApplyRecordStatus(applyRecordModel);
        if (restModel.getCode().equals(RestModel.CODE_SUCCESS)) {
            return this.success(restModel);
        }
        return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
    }
}
