package com.facebank.usersupport.attendance.mapper;

import com.facebank.usersupport.attendance.model.DeptModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门管理Mapper
 * @author HuBiao
 * @date 2018/4/4 0004 15:27
 **/
@Repository
public interface DeptMapper {

    List<DeptModel> getAllDept();
    DeptModel getWorkNumberByDeptNumber(Integer deptNumber);
}
