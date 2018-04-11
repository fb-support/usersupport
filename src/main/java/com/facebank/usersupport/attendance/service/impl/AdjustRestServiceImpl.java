package com.facebank.usersupport.attendance.service.impl;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.mapper.AdjustRestMapper;
import com.facebank.usersupport.attendance.mapper.DealRecordMapper;
import com.facebank.usersupport.attendance.mapper.EmpMapper;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.attendance.model.DealRecordModel;
import com.facebank.usersupport.attendance.service.IAdjustRestService;
import com.facebank.usersupport.model.RepaymentModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调休管理service
 * @author HuBiao
 * @date 2018/3/28 0028 16:05
 **/
@Service
public class AdjustRestServiceImpl implements IAdjustRestService {

    @Autowired
    private AdjustRestMapper adjustRestMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DealRecordMapper dealRecordMapper;

    /**
     * 添加调休申请记录
     * @param applyRecordModel
     */
    @Override
    public void saveRestApply(ApplyRecordModel applyRecordModel) {
        // 向数据库中插入调休申请记录
        adjustRestMapper.saveRestApply(applyRecordModel);
        // 封装dealRecordModel
        DealRecordModel dealRecordModel = new DealRecordModel();
        dealRecordModel.setApplyId(applyRecordModel.getId());
        Integer parentNumber = empMapper.getParentNumberByWorkNumber(applyRecordModel.getWorkNumber());
        Integer parentNumber2 = null;
        Integer parentNumber3 = null;
        dealRecordModel.setWorkNumber(parentNumber);
        dealRecordModel.setStatus(0);
        dealRecordModel.setDealLevel(1);
        // 保存第一级审批记录
        dealRecordMapper.insertApplyDealRecord(dealRecordModel);
        // 根据申请时长判断需要几级审批
        if(applyRecordModel.getApplyDuration() <= 8.0F){
            return;
        }else{
            parentNumber2 = empMapper.getParentNumberByWorkNumber(parentNumber);
            dealRecordModel.setWorkNumber(parentNumber2);
            dealRecordModel.setDealLevel(2);
            dealRecordMapper.insertApplyDealRecord(dealRecordModel);
            if(applyRecordModel.getApplyDuration() <= 24.0F){
                return;
            }else{
                parentNumber3 = empMapper.getParentNumberByWorkNumber(parentNumber2);
                dealRecordModel.setWorkNumber(parentNumber3);
                dealRecordModel.setDealLevel(3);
                dealRecordMapper.insertApplyDealRecord(dealRecordModel);
            }
        }
    }

    /**
     * 获取当前用户下的所有调休申请记录
     * @param queryVo
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ApplyRecordModel> getRestApplyListByPage(QueryVo queryVo, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ApplyRecordModel> restApplyList = adjustRestMapper.getRestApplyListByQueryVo(queryVo);
        PageInfo<ApplyRecordModel> pageInfo=new PageInfo<ApplyRecordModel>(restApplyList);
        return pageInfo;
    }

    /**
     * 修改调休申请记录
     * @param applyRecordModel
     */
    @Override
    public void updateRestApply(ApplyRecordModel applyRecordModel) {
        adjustRestMapper.updateRestApply(applyRecordModel);
    }
}
