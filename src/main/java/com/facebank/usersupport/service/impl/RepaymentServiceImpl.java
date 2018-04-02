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

import java.util.*;

/**
 * 还款查询业务ServiceImpl
 *
 * @author HuBiao
 * @date 2018/3/9 0009 14:00
 **/
@Service
public class RepaymentServiceImpl extends BaseService implements IRepaymentService {

    @Autowired
    private RepaymentMapper repaymentMapper;

    /**
     * 根据手机号、用户名、还款日期组合条件查询还款信息
     *
     * @return
     */
    @Override
    public List<RepaymentModel> getRepaymentModelByRepaymentForm(RepaymentForm repaymentForm) {
        /*Integer page = repaymentForm.getStart() / repaymentForm.getLength() + 1;
        PageHelper.startPage(page, repaymentForm.getLength());
        ...
        PageInfo<RepaymentModel> pageInfo=new PageInfo<RepaymentModel>(repaymentModels);*/
        List<RepaymentModel> repaymentModelList1 = repaymentMapper.getCreditInfoByRepaymentForm(repaymentForm);
        List<RepaymentModel> repaymentModelList2 = repaymentMapper.getRedPackageInfoByRepaymentForm(repaymentForm);
        List<RepaymentModel> repaymentModelList3 = repaymentMapper.getVipInfoByRepaymentForm(repaymentForm);
        List<RepaymentModel> repaymentModelList4 = repaymentMapper.getPfInterestInfoByRepaymentForm(repaymentForm);
        // 根据第一个list构建一个Map<creditId,RepaymentModel>
        Map<Long,RepaymentModel> repaymentModelMap = new HashMap<>();
        for (RepaymentModel repaymentModel1 : repaymentModelList1) {
            repaymentModelMap.put(repaymentModel1.getCreditId(),repaymentModel1);
        }
        // 根据creditId设置RepaymentModel中的剩余属性
        for (RepaymentModel repaymentModel2 : repaymentModelList2) {
            Long creditId = repaymentModel2.getCreditId();
            if(repaymentModelMap.containsKey(creditId)){
                repaymentModelMap.get(creditId).setRedPlanAmount(repaymentModel2.getRedPlanAmount());
                repaymentModelMap.get(creditId).setRedLocalInfo(repaymentModel2.getRedLocalInfo());
                repaymentModelMap.get(creditId).setRedPackageType(repaymentModel2.getRedPackageType());
                repaymentModelMap.get(creditId).setRedTermNum(repaymentModel2.getRedTermNum());
            }
        }
        for (RepaymentModel repaymentModel3 : repaymentModelList3) {
            Long creditId = repaymentModel3.getCreditId();
            if(repaymentModelMap.containsKey(creditId)){
                repaymentModelMap.get(creditId).setVipPlanAmount(repaymentModel3.getVipPlanAmount());
                repaymentModelMap.get(creditId).setVipRate(repaymentModel3.getVipRate());
                repaymentModelMap.get(creditId).setVipTermNum(repaymentModel3.getVipTermNum());
            }
        }
        for (RepaymentModel repaymentModel4 : repaymentModelList4) {
            Long creditId = repaymentModel4.getCreditId();
            if(repaymentModelMap.containsKey(creditId)){
                repaymentModelMap.get(creditId).setPfPlanAmount(repaymentModel4.getPfPlanAmount());
                repaymentModelMap.get(creditId).setPfTermNum(repaymentModel4.getPfTermNum());
                repaymentModelMap.get(creditId).setPfType(repaymentModel4.getPfType());
            }
        }
        // 将Map<creditId,RepaymentModel>转换为List<RepaymentModel>
        Set<Map.Entry<Long, RepaymentModel>> entrySet = repaymentModelMap.entrySet();
        List<RepaymentModel> repaymentModels = new ArrayList<>();
        for (Map.Entry<Long, RepaymentModel> RepaymentModelEntry : entrySet) {
            repaymentModels.add(RepaymentModelEntry.getValue());
        }
        return repaymentModels;
    }

}
