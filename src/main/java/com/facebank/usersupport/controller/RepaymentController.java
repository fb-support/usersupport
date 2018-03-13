package com.facebank.usersupport.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.reqDto.RepaymentForm;
import com.facebank.usersupport.model.RepaymentModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.IRepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 还款查询业务Controller
 * @author NingKui
 * @date 2018/3/9 10:42
 **/
@RestController
public class RepaymentController extends BaseController {

    @Autowired
    private IRepaymentService repaymentService;

    /**
     * 根据手机号、用户名、还款日期组合条件查询还款信息
     * @param repaymentForm
     * @return
     */
     @PostMapping("/service/repayment")
     public RestModel repaymentSearch(RepaymentForm repaymentForm){
        try{
            List<RepaymentModel> repaymentModels = repaymentService.getRepaymentModelByRepaymenyForm(repaymentForm);
            return this.success(repaymentModels);
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

}
