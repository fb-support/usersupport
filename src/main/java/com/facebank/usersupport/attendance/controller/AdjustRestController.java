package com.facebank.usersupport.attendance.controller;

import com.facebank.usersupport.attendance.dto.reqDto.AddApplyForm;
import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.attendance.model.EmpModel;
import com.facebank.usersupport.attendance.service.IAdjustRestService;
import com.facebank.usersupport.attendance.service.IEmpService;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 调休管理controller
 * @author HuBiao
 * @date 2018/3/28 0028 14:32
 **/
@RestController
public class AdjustRestController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IEmpService empService;
    @Autowired
    private IAdjustRestService adjustRestService;

    /**
     * 添加调休申请
     * @param addApplyForm
     * @return
     */
    @PostMapping("/attendance/addRestApply")
    public RestModel addRestApply(AddApplyForm addApplyForm){
        try {
            // 提交参数有一个为空则为true
            boolean anyoneEmpty = StringUtils.isEmpty(addApplyForm.getStartTime()) || StringUtils.isEmpty(addApplyForm.getEndTime()) || StringUtils.isEmpty(addApplyForm.getApplyDuration()) || StringUtils.isEmpty(addApplyForm.getApplyCause());
            // 结束时间大于开始时间
            boolean timeInterval = !StringUtils.isEmpty(addApplyForm.getStartTime()) && !StringUtils.isEmpty(addApplyForm.getEndTime()) && (addApplyForm.getEndTime().getTime() < addApplyForm.getStartTime().getTime());
            if(anyoneEmpty || timeInterval || addApplyForm.getApplyDuration() <= 0F){
                return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
            }

            // 获取当前登录用户信息
            Long userId = userService.getActiveUserId();
            UserModel userModel = userService.getByUserId(userId);
            EmpModel empModel = empService.getEmpByWorkNumber(userModel.getWorkNumber());

            // 封装applyRecordModel
            ApplyRecordModel applyRecordModel = new ApplyRecordModel();
            BeanUtils.copyProperties(addApplyForm,applyRecordModel);
            applyRecordModel.setWorkNumber(empModel.getWorkNumber());
            applyRecordModel.setEmpName(empModel.getName());
            applyRecordModel.setApplyDate(new Date());
            applyRecordModel.setApplyType(2);
            applyRecordModel.setStatus(0);

            // 调用service层方法保存记录
            adjustRestService.saveRestApply(applyRecordModel);

            return this.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    /**
     * 查询当前用户的所有调休申请记录
     * @param start
     * @param length
     * @param draw
     * @param queryVo
     * @return
     */
    @GetMapping("/attendance/getRestApply")
    public RestModel getRestApply(@RequestParam(required = false, defaultValue = "1") int start,
                                  @RequestParam(required = false, defaultValue = "10") int length,
                                  String draw, QueryVo queryVo){
        try {
            Integer page = start / length + 1;
            // 获取当前登录用户信息
            Long userId = userService.getActiveUserId();
            UserModel userModel = userService.getByUserId(userId);
            queryVo.setWorkNumber(userModel.getWorkNumber());

            PageInfo<ApplyRecordModel> pageInfo = adjustRestService.getRestApplyListByPage(queryVo,page,length);
            PageRestModel pageRestModel = new PageRestModel(
                    draw,
                    pageInfo.getTotal(),
                    pageInfo.getTotal(),
                    pageInfo.getList()
            );
            return this.success(pageRestModel);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    @PostMapping("/attendance/updateRestApply")
    public RestModel updateRestApply(ApplyRecordModel applyRecordModel){
        try {
            adjustRestService.updateRestApply(applyRecordModel);
            return this.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

}
