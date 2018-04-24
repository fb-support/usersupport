package com.facebank.usersupport.attendance.mapper;

import com.facebank.usersupport.attendance.model.EmpModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HuBiao
 * @date 2018/3/28 0028 15:42
 **/
@Repository
public interface EmpMapper {

    EmpModel getEmpByWorkNumber(Integer workNumber);

    Integer getParentNumberByWorkNumber(Integer workNumber);

    List<EmpModel> getEmpListByDeptNumber(Integer deptNumber);

    /**
     * 根据父级员工号
     * @param empModel
     * @return
     */
    EmpModel getEmpByParentNumber(EmpModel empModel);

    /**
     * 修改员工时长
     * @param empModel
     * @return
     */
    Integer updateEmpInfo(EmpModel empModel);

}
