package com.facebank.usersupport.service;

import com.facebank.usersupport.dto.reqDto.RepaymentForm;
import com.facebank.usersupport.model.RepaymentModel;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 还款查询业务Service接口
 * @author HuBiao
 * @date 2018/3/9 0009 13:59
 **/
public interface IRepaymentService {

    /**
     * 根据手机号、用户名、还款日期组合条件查询还款信息
     * @return
     */
    PageInfo<RepaymentModel> getRepaymentModelByRepaymenyForm(RepaymentForm repaymentForm);

}
