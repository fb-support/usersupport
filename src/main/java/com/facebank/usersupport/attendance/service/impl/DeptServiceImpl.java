package com.facebank.usersupport.attendance.service.impl;

import com.facebank.usersupport.attendance.mapper.DeptMapper;
import com.facebank.usersupport.attendance.model.DeptModel;
import com.facebank.usersupport.attendance.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门管理Service
 * @author HuBiao
 * @date 2018/4/4 0004 15:26
 **/
@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<DeptModel> getAllDept() {
        return deptMapper.getAllDept();
    }

    @Override
    public DeptModel getWorkNumberByDeptNumber(Integer deptNumber) {
        return deptMapper.getWorkNumberByDeptNumber(deptNumber);
    }
}
