package com.facebank.usersupport.attendance.service;

import com.facebank.usersupport.attendance.model.DeptModel;

import java.util.List;

/**
 * @author HuBiao
 * @date 2018/4/4 0004 15:26
 **/
public interface IDeptService {

    List<DeptModel> getAllDept();
    DeptModel getWorkNumberByDeptNumber(Integer deptNumber);

}
