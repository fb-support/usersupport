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
     * 根据手机号、用户名、还款日期组合条件查询债券还款信息
     * @return
     */
    List<RepaymentModel> getRepaymentModelByRepaymentForm(RepaymentForm repaymentForm);

    /**
     * 根据组合条件查询债券还款明细
     * @param repaymentForm
     * @return
     */
    List<RepaymentModel> getRepaymentDetailByRepaymentForm(RepaymentForm repaymentForm);

    /**
     * 根据手机号、用户名、还款日期组合条件查询订单还款信息
     * @return
     */
    List<RepaymentModel> getRepaymentOrderByRepaymentForm(RepaymentForm repaymentForm);

}
