package com.facebank.usersupport.mapper.usersupport.p2p;

import com.facebank.usersupport.dto.reqDto.RepaymentForm;
import com.facebank.usersupport.mapper.usersupport.base.BaseReadMapper;
import com.facebank.usersupport.model.RepaymentModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 还款查询业务Mapper
 * @author HuBiao
 * @date 2018/3/9 0009 13:07
 **/
@Repository
public interface RepaymentMapper extends BaseReadMapper<RepaymentModel,Long> {

    /**
     * 根据手机号、用户名、还款日期组合条件查询债券还款相关信息
     * @return
     */
    List<RepaymentModel> getCreditInfoByRepaymentForm(RepaymentForm repaymentForm);
    List<RepaymentModel> getRedPackageInfoByRepaymentForm(RepaymentForm repaymentForm);
    List<RepaymentModel> getVipInfoByRepaymentForm(RepaymentForm repaymentForm);
    List<RepaymentModel> getPfInterestInfoByRepaymentForm(RepaymentForm repaymentForm);
    // List<RepaymentModel> getRepaymentModelListByRepaymentForm(RepaymentForm repaymentForm);

    /**
     * 根据手机号、用户名、还款日期组合条件查询订单还款相关信息
     * @return
     */
    List<RepaymentModel> getOrderCreditInfoByRepaymentForm(RepaymentForm repaymentForm);
    List<RepaymentModel> getOrderRedPackageInfoByRepaymentForm(RepaymentForm repaymentForm);
    List<RepaymentModel> getOrderVipInfoByRepaymentForm(RepaymentForm repaymentForm);
    List<RepaymentModel> getOrderPfInterestInfoByRepaymentForm(RepaymentForm repaymentForm);
    // List<RepaymentModel> getOrderRepaymentModelListByRepaymentForm(RepaymentForm repaymentForm);

}
