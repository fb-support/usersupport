package com.facebank.usersupport.attendance.service;

import com.facebank.usersupport.attendance.model.EmpModel;

import java.util.List;

/**
 * @author HuBiao
 * @date 2018/3/28 0028 15:38
 **/
public interface IEmpService {

    EmpModel getEmpByWorkNumber(Integer workNumber);

    List<EmpModel> getEmpListByDeptNumber(Integer deptNumber);

}
