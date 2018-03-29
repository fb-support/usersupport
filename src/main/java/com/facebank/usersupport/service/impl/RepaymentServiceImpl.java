package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.dto.reqDto.RepaymentForm;
import com.facebank.usersupport.mapper.usersupport.p2p.RepaymentMapper;
import com.facebank.usersupport.model.RepaymentModel;
import com.facebank.usersupport.service.IRepaymentService;
import com.facebank.usersupport.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  还款查询业务ServiceImpl
 * @author HuBiao
 * @date 2018/3/9 0009 14:00
 **/
@Service
public class RepaymentServiceImpl extends BaseService implements IRepaymentService {

    @Autowired
    private RepaymentMapper repaymentMapper;

    /**
     * 根据手机号、用户名、还款日期组合条件查询还款信息
     * @return
     */
    @Override
    public List<RepaymentModel> getRepaymentModelByRepaymenyForm(RepaymentForm repaymentForm) {
        /*Integer page = repaymentForm.getStart() / repaymentForm.getLength() + 1;
        PageHelper.startPage(page, repaymentForm.getLength());*/
        List<RepaymentModel> repaymentModels = repaymentMapper.getRepaymentModelByRepaymentForm(repaymentForm);
        /*PageInfo<RepaymentModel> pageInfo=new PageInfo<RepaymentModel>(repaymentModels);*/
        return repaymentModels;
    }

}
