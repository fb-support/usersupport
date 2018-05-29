package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.attendance.model.EmpAttendanceModel;
import com.facebank.usersupport.dto.reqDto.RepaymentForm;
import com.facebank.usersupport.mapper.usersupport.p2p.RepaymentMapper;
import com.facebank.usersupport.model.RepaymentModel;
import com.facebank.usersupport.service.IRepaymentService;
import com.facebank.usersupport.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
     * 根据手机号、用户名、还款日期组合条件查询债权还款信息
     *
     * @return
     */
    @Cacheable(value="repaymentCache",key="'repayment'+#userId")
    @Override
    public PageInfo<RepaymentModel> getRepaymentModelByRepaymentForm(RepaymentForm repaymentForm,Long userId) {

        List<RepaymentModel> repaymentModelList1 = repaymentMapper.getCreditInfoByRepaymentForm(repaymentForm);
        List<RepaymentModel> repaymentModelList2 = repaymentMapper.getRedPackageInfoByRepaymentForm(repaymentForm);
        List<RepaymentModel> repaymentModelList3 = repaymentMapper.getVipInfoByRepaymentForm(repaymentForm);
        List<RepaymentModel> repaymentModelList4 = repaymentMapper.getPfInterestInfoByRepaymentForm(repaymentForm);
        // 根据第一个list构建一个Map<String,RepaymentModel>
        Map<String, RepaymentModel> repaymentModelMap = new HashMap<>();
        for (RepaymentModel repaymentModel1 : repaymentModelList1) {
            String key = repaymentModel1.getCreditId() + ":" + repaymentModel1.getBizStatus();
            repaymentModelMap.put(key, repaymentModel1);
        }
        // 根据key设置RepaymentModel中的剩余属性
        for (RepaymentModel repaymentModel2 : repaymentModelList2) {
            String key = repaymentModel2.getCreditId() + ":" + repaymentModel2.getBizStatus();
            if (repaymentModelMap.containsKey(key)) {
                repaymentModelMap.get(key).setRedPlanAmount(repaymentModel2.getRedPlanAmount());
                repaymentModelMap.get(key).setRedRealAmount(repaymentModel2.getRedRealAmount());
                repaymentModelMap.get(key).setRedLocalInfo(repaymentModel2.getRedLocalInfo());
                repaymentModelMap.get(key).setRedPackageType(repaymentModel2.getRedPackageType());
                repaymentModelMap.get(key).setRedTermNum(repaymentModel2.getRedTermNum());
            }
        }
        for (RepaymentModel repaymentModel3 : repaymentModelList3) {
            String key = repaymentModel3.getCreditId() + ":" + repaymentModel3.getBizStatus();
            if (repaymentModelMap.containsKey(key)) {
                repaymentModelMap.get(key).setVipPlanAmount(repaymentModel3.getVipPlanAmount());
                repaymentModelMap.get(key).setVipRealAmount(repaymentModel3.getVipRealAmount());
                repaymentModelMap.get(key).setVipRate(repaymentModel3.getVipRate());
                repaymentModelMap.get(key).setVipTermNum(repaymentModel3.getVipTermNum());
            }
        }
        for (RepaymentModel repaymentModel4 : repaymentModelList4) {
            String key = repaymentModel4.getCreditId() + ":" + repaymentModel4.getBizStatus();
            if (repaymentModelMap.containsKey(key)) {
                repaymentModelMap.get(key).setPfPlanAmount(repaymentModel4.getPfPlanAmount());
                repaymentModelMap.get(key).setPfRealAmount(repaymentModel4.getPfRealAmount());
                repaymentModelMap.get(key).setPfTermNum(repaymentModel4.getPfTermNum());
                repaymentModelMap.get(key).setPfType(repaymentModel4.getPfType());
            }
        }
        // 将Map<creditId,RepaymentModel>转换为List<RepaymentModel>
        Set<Map.Entry<String, RepaymentModel>> entrySet = repaymentModelMap.entrySet();
        List<RepaymentModel> repaymentModels = new ArrayList<>();
        for (Map.Entry<String, RepaymentModel> RepaymentModelEntry : entrySet) {
            repaymentModels.add(RepaymentModelEntry.getValue());
        }
        System.err.println("缓存里没有"+repaymentForm+",所以这边没有走缓存，从数据库拿数据");
        PageInfo<RepaymentModel> pageInfo =new PageInfo<>(repaymentModels);
        return pageInfo;
    }

    /**
     * 多条件组合查询债权还款明细信息
     *
     * @param repaymentForm
     * @return
     */
    @Override
    public List<RepaymentModel> getRepaymentDetailByRepaymentForm(RepaymentForm repaymentForm) {
        // List<RepaymentModel> repaymentModels = repaymentMapper.getRepaymentDetailByRepaymentForm(repaymentForm);

        List<RepaymentModel> repaymentModelList1 = repaymentMapper.getCreditDetailByRepaymentForm(repaymentForm);
        List<RepaymentModel> repaymentModelList2 = repaymentMapper.getRedPackageDetailByRepaymentForm(repaymentForm);
        List<RepaymentModel> repaymentModelList3 = repaymentMapper.getVipDetailByRepaymentForm(repaymentForm);
        List<RepaymentModel> repaymentModelList4 = repaymentMapper.getPfInterestDetailByRepaymentForm(repaymentForm);
        // 根据第一个list构建一个Map<String,RepaymentModel>
        Map<String, RepaymentModel> repaymentModelMap = new HashMap<>();
        for (RepaymentModel repaymentModel1 : repaymentModelList1) {
            String key = repaymentModel1.getCreditId() + ":" + repaymentModel1.getCredTermNum();
            repaymentModelMap.put(key, repaymentModel1);
        }
        // 根据key设置RepaymentModel中的剩余属性
        for (RepaymentModel repaymentModel2 : repaymentModelList2) {
            String key = repaymentModel2.getCreditId() + ":" + repaymentModel2.getRedTermNum();
            if (repaymentModelMap.containsKey(key)) {
                repaymentModelMap.get(key).setRedPlanAmount(repaymentModel2.getRedPlanAmount());
                repaymentModelMap.get(key).setRedRealAmount(repaymentModel2.getRedRealAmount());
                repaymentModelMap.get(key).setRedLocalInfo(repaymentModel2.getRedLocalInfo());
                repaymentModelMap.get(key).setRedPackageType(repaymentModel2.getRedPackageType());
            }
        }
        for (RepaymentModel repaymentModel3 : repaymentModelList3) {
            String key = repaymentModel3.getCreditId() + ":" + repaymentModel3.getVipTermNum();
            if (repaymentModelMap.containsKey(key)) {
                repaymentModelMap.get(key).setVipPlanAmount(repaymentModel3.getVipPlanAmount());
                repaymentModelMap.get(key).setVipRealAmount(repaymentModel3.getVipRealAmount());
                repaymentModelMap.get(key).setVipRate(repaymentModel3.getVipRate());
            }
        }
        for (RepaymentModel repaymentModel4 : repaymentModelList4) {
            String key = repaymentModel4.getCreditId() + ":" + repaymentModel4.getPfTermNum();
            if (repaymentModelMap.containsKey(key)) {
                repaymentModelMap.get(key).setPfPlanAmount(repaymentModel4.getPfPlanAmount());
                repaymentModelMap.get(key).setPfRealAmount(repaymentModel4.getPfRealAmount());
                repaymentModelMap.get(key).setPfType(repaymentModel4.getPfType());
            }
        }
        // 将Map<creditId,RepaymentModel>转换为List<RepaymentModel>
        Set<Map.Entry<String, RepaymentModel>> entrySet = repaymentModelMap.entrySet();
        List<RepaymentModel> repaymentModels = new ArrayList<>();
        for (Map.Entry<String, RepaymentModel> RepaymentModelEntry : entrySet) {
            repaymentModels.add(RepaymentModelEntry.getValue());
        }

        return repaymentModels;
    }

    /**
     * 根据手机号、用户名、还款日期组合条件查询订单还款信息
     *
     * @return
     */
    @Override
    public List<RepaymentModel> getRepaymentOrderByRepaymentForm(RepaymentForm repaymentForm) {
        // List<RepaymentModel> repaymentModels = repaymentMapper.getOrderRepaymentModelListByRepaymentForm(repaymentForm);

        List<RepaymentModel> repaymentModelList1 = repaymentMapper.getOrderCreditInfoByRepaymentForm(repaymentForm);
        List<RepaymentModel> repaymentModelList2 = repaymentMapper.getOrderRedPackageInfoByRepaymentForm(repaymentForm);
        List<RepaymentModel> repaymentModelList3 = repaymentMapper.getOrderVipInfoByRepaymentForm(repaymentForm);
        List<RepaymentModel> repaymentModelList4 = repaymentMapper.getOrderPfInterestInfoByRepaymentForm(repaymentForm);
        // 根据第一个list构建一个Map<String,RepaymentModel>
        Map<String, RepaymentModel> repaymentModelMap = new HashMap<>();
        for (RepaymentModel repaymentModel1 : repaymentModelList1) {
            String key = repaymentModel1.getOrderId() + ":" + repaymentModel1.getBizStatus();
            repaymentModelMap.put(key, repaymentModel1);
        }
        // 根据key设置RepaymentModel中的剩余属性
        for (RepaymentModel repaymentModel2 : repaymentModelList2) {
            String key = repaymentModel2.getOrderId() + ":" + repaymentModel2.getBizStatus();
            if (repaymentModelMap.containsKey(key)) {
                repaymentModelMap.get(key).setRedPlanAmount(repaymentModel2.getRedPlanAmount());
                repaymentModelMap.get(key).setRedRealAmount(repaymentModel2.getRedRealAmount());
            }
        }
        for (RepaymentModel repaymentModel3 : repaymentModelList3) {
            String key = repaymentModel3.getOrderId() + ":" + repaymentModel3.getBizStatus();
            if (repaymentModelMap.containsKey(key)) {
                repaymentModelMap.get(key).setVipPlanAmount(repaymentModel3.getVipPlanAmount());
                repaymentModelMap.get(key).setVipRealAmount(repaymentModel3.getVipRealAmount());
            }
        }
        for (RepaymentModel repaymentModel4 : repaymentModelList4) {
            String key = repaymentModel4.getOrderId() + ":" + repaymentModel4.getBizStatus();
            if (repaymentModelMap.containsKey(key)) {
                repaymentModelMap.get(key).setPfPlanAmount(repaymentModel4.getPfPlanAmount());
                repaymentModelMap.get(key).setPfRealAmount(repaymentModel4.getPfRealAmount());
            }
        }
        // 将Map<String,RepaymentModel>转换为List<RepaymentModel>
        Set<Map.Entry<String, RepaymentModel>> entrySet = repaymentModelMap.entrySet();
        List<RepaymentModel> repaymentModels = new ArrayList<>();
        for (Map.Entry<String, RepaymentModel> RepaymentModelEntry : entrySet) {
            repaymentModels.add(RepaymentModelEntry.getValue());
        }

        return repaymentModels;
    }

}
