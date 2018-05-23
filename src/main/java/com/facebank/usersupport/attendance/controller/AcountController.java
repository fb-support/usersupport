package com.facebank.usersupport.attendance.controller;

import com.facebank.usersupport.attendance.dto.AcountDto;
import com.facebank.usersupport.attendance.dto.reqDto.AcountVo;
import com.facebank.usersupport.attendance.service.IAcountService;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.PageBeanModel;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 考勤统计
 */
@RestController
public class AcountController extends BaseController {

    @Autowired
    private IAcountService acountService;

    /**
     * 考勤统计
     * @param
     * @param acountVo
     * @return
     */
    @RequestMapping("/attendance/getAcountAll")
    public RestModel getAcountByCondition(AcountVo acountVo){
        try{
            PageInfo<AcountDto> pageinfos = acountService.getAcountByPage(acountVo);
            PageBeanModel pageRestModel=new PageBeanModel();
            pageRestModel.setData(pageinfos.getList());
            pageRestModel.setPage(pageinfos.getPageNum());
            pageRestModel.setPageSize(pageinfos.getPageSize());
            pageRestModel.setTotalCount(pageinfos.getTotal());
            pageRestModel.setTotalPage(pageinfos.getPages());
            return success(pageRestModel);

        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }
}
