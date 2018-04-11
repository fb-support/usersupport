package com.facebank.usersupport.attendance.controller;

import com.facebank.usersupport.attendance.dto.reqDto.AcountVo;
import com.facebank.usersupport.attendance.service.IAcountService;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 考勤统计
 * @author zhanguo.huang
 * @date 2018-04-11
 */
@RestController
public class AcountController extends BaseController {

    @Autowired
    private IAcountService acountService;

    /**
     * 考勤统计
     * @param start
     * @param length
     * @param draw
     * @param acountVo
     * @return
     */
    @PostMapping("/attendance/getAcountAll")
    public RestModel getAcountByCondition(@RequestParam(required = false, defaultValue = "1") int start,
                                          @RequestParam(required = false, defaultValue = "10") int length,
                                          String draw,
                                          AcountVo acountVo){
        try{
//            Long userId = userService.getActiveUserId();
//            UserModel userModel = userService.getByUserId(userId);
//            queryVo.setWorkNumber(userModel.getWorkNumber());
            int pageNo = start / length + 1;
            PageInfo pageInfo = acountService.getAcountByPage(length,pageNo,acountVo);
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
}
