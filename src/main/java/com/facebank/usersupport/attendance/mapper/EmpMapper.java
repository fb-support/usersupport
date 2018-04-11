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

}
