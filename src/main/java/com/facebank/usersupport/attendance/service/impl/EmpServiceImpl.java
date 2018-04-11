package com.facebank.usersupport.attendance.service.impl;

import com.facebank.usersupport.attendance.mapper.EmpMapper;
import com.facebank.usersupport.attendance.model.EmpModel;
import com.facebank.usersupport.attendance.service.IEmpService;
import com.facebank.usersupport.model.RestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HuBiao
 * @date 2018/3/28 0028 15:39
 **/
@Service
public class EmpServiceImpl implements IEmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * 根据workNumber查询员工信息
     * @param workNumber
     * @return
     */
    @Override
    public EmpModel getEmpByWorkNumber(Integer workNumber) {
        return empMapper.getEmpByWorkNumber(workNumber);
    }

    /**
     * 根据部门号查询该部门下的员工
     * @param deptNumber
     * @return
     */
    @Override
    public List<EmpModel> getEmpListByDeptNumber(Integer deptNumber) {
        return empMapper.getEmpListByDeptNumber(deptNumber);
    }

    /**
     * 根据父级number获取信息
     * @param empModel
     * @return
     */
    @Override
    public EmpModel getEmpByParentNumber(EmpModel empModel) {
        return empMapper.getEmpByParentNumber(empModel);
    }

    /**
     * 更新时长
     * @param empModel
     * @return
     */
    @Override
    public RestModel updateEmpInfo(EmpModel empModel) {
        int is_update = empMapper.updateEmpInfo(empModel);
        if (is_update == 1) {
            return new RestModel(RestModel.CODE_SUCCESS,RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

}
