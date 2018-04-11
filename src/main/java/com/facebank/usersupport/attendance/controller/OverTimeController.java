package com.facebank.usersupport.attendance.controller;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.attendance.model.DealRecordModel;
import com.facebank.usersupport.attendance.model.EmpAttendanceModel;
import com.facebank.usersupport.attendance.model.EmpModel;
import com.facebank.usersupport.attendance.service.IDealRecordService;
import com.facebank.usersupport.attendance.service.IEmpService;
import com.facebank.usersupport.attendance.service.IOverTimeService;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 加班申请
 * Created by wz on 2018/3/27.
 */
@RestController
public class OverTimeController extends BaseController {
    /**
     * 添加加班申请
     */
    @Autowired
    private IOverTimeService overTimeService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IEmpService empService;
    @Autowired
    private IDealRecordService dealRecordService;

    @RequestMapping("/attendance/addOverTime")
    public RestModel addOverTime(ApplyRecordModel applyRecordModel) {
        //获取id
        Long userId = iUserService.getActiveUserId();
        //根据id获取用户信息
        UserModel userModel = iUserService.getByUserId(userId);
        EmpModel empModel = empService.getEmpByWorkNumber(userModel.getWorkNumber());
        applyRecordModel.setWorkNumber(empModel.getWorkNumber());
        applyRecordModel.setEmpName(empModel.getName());
        //因为已经明确是加班申请所以设一个死值
        applyRecordModel.setApplyType(0);
        applyRecordModel.setApplyDate(new Date());
        applyRecordModel.setStatus(0);
        RestModel model = overTimeService.insert(applyRecordModel);
        //如果加班时长小于等于3，就只有一级审核。
        DealRecordModel dealRecordModel = new DealRecordModel();
        if (applyRecordModel.getApplyDuration() <= 3) {
            dealRecordModel.setApplyId(applyRecordModel.getId());
            dealRecordModel.setWorkNumber(empModel.getParentNumber());
            dealRecordModel.setDealLevel(1);
            dealRecordModel.setStatus(1);
            model = dealRecordService.insertApplyDealRecord(dealRecordModel);
        }
        //如果加班时长大于3小于等于5，就两级审核。
        else if (applyRecordModel.getApplyDuration() > 3 && applyRecordModel.getApplyDuration() <= 5) {
            dealRecordModel.setApplyId(applyRecordModel.getId());
            dealRecordModel.setWorkNumber(empModel.getParentNumber());
            dealRecordModel.setDealLevel(1);
            dealRecordModel.setStatus(1);
            model = dealRecordService.insertApplyDealRecord(dealRecordModel);
            //根据父员工号查找员工信息
            EmpModel empModel1 = empService.getEmpByParentNumber(empModel);
            if(empModel1.getParentNumber()!=null){
                dealRecordModel.setWorkNumber(empModel1.getParentNumber());
                dealRecordModel.setDealLevel(2);
                dealRecordModel.setStatus(0);
                model = dealRecordService.insertApplyDealRecord(dealRecordModel);
            }
        }
        //如果加班时长大于5小于等于7.5，就三级审核
        else if (applyRecordModel.getApplyDuration() > 5 && applyRecordModel.getApplyDuration() <= 7.5) {
            //1级
            dealRecordModel.setApplyId(applyRecordModel.getId());
            dealRecordModel.setWorkNumber(empModel.getParentNumber());
            dealRecordModel.setDealLevel(1);
            dealRecordModel.setStatus(1);
            model = dealRecordService.insertApplyDealRecord(dealRecordModel);
            //2级
            EmpModel empModel2 = empService.getEmpByParentNumber(empModel);
            if(empModel2.getParentNumber()!=null){
                dealRecordModel.setWorkNumber(empModel2.getParentNumber());
                dealRecordModel.setDealLevel(2);
                dealRecordModel.setStatus(0);
                model = dealRecordService.insertApplyDealRecord(dealRecordModel);
            }
            //3级
            EmpModel empModel3 = empService.getEmpByParentNumber(empModel2);
            if(empModel3.getParentNumber()!=null){
                dealRecordModel.setWorkNumber(empModel3.getParentNumber());
                dealRecordModel.setDealLevel(3);
                dealRecordModel.setStatus(0);
                model = dealRecordService.insertApplyDealRecord(dealRecordModel);
            }
        }

        if (model.getCode().equals(RestModel.CODE_SUCCESS)) {
            return this.success(model);
        }
        return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);

    }

    /**
     * 查询加班申请
     *
     * @param start
     * @param length
     * @param queryVo
     * @param draw
     * @return
     */
    @PostMapping("/overTime/search")
    public RestModel overTimeSearch(@RequestParam(required = false, defaultValue = "1") int start,
                                    @RequestParam(required = false, defaultValue = "10") int length
            , QueryVo queryVo, String draw) {
        //获取id
        Long userId = iUserService.getActiveUserId();
        //根据id获取用户信息
        UserModel userModel = iUserService.getByUserId(userId);
        queryVo.setWorkNumber(userModel.getWorkNumber());
        int pageNo = start / length + 1;
        PageInfo pageInfo = overTimeService.search(pageNo, length, queryVo);
        PageRestModel pageRestModel = new PageRestModel();
        pageRestModel.setData(pageInfo.getList());
        pageRestModel.setRecordsTotal(pageInfo.getTotal());
        pageRestModel.setRecordsFiltered(pageInfo.getTotal());
        pageRestModel.setDraw(draw);
        return this.success(pageRestModel);
    }

    /**
     * 修改加班申请
     *
     * @param applyRecordModel
     * @return
     */
    @RequestMapping("/attendance/updateOverTime")
    public RestModel updateOverTime(ApplyRecordModel applyRecordModel) {
        applyRecordModel.setApplyDate(new Date());
        RestModel restModel = overTimeService.updateApplyRecord(applyRecordModel);
        if (restModel.getCode().equals(RestModel.CODE_SUCCESS)) {
            return this.success(restModel);
        }
        return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);

    }

}
