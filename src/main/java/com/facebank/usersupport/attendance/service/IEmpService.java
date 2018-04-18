package com.facebank.usersupport.attendance.service;

import com.facebank.usersupport.attendance.model.EmpModel;
import com.facebank.usersupport.model.RestModel;

import java.util.List;

/**
 * @author HuBiao
 * @date 2018/3/28 0028 15:38
 **/
public interface IEmpService {

    EmpModel getEmpByWorkNumber(Integer workNumber);

    List<EmpModel> getEmpListByDeptNumber(Integer deptNumber);

    /**
     * 根据父级number获取信息
     * @param empModel
     * @return
     */
    EmpModel getEmpByParentNumber(EmpModel empModel);

    /**
     * 更新员工时长
     * @param empModel
     * @return
     */
    RestModel updateEmpInfo(EmpModel empModel);

}
