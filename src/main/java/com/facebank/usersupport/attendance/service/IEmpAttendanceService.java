package com.facebank.usersupport.attendance.service;

import com.facebank.usersupport.attendance.dto.EmpUserDto;
import com.facebank.usersupport.attendance.dto.reqDto.GetAttendanceForm;
import com.facebank.usersupport.attendance.model.EmpAttendanceModel;

import java.util.List;

/**
 * @author HuBiao
 * @date 2018/4/4 0004 11:16
 **/
public interface IEmpAttendanceService {

    void importAttendance(List<EmpAttendanceModel> list);

    List<EmpAttendanceModel> getAttendanceRecordByForm(GetAttendanceForm attendanceForm);

    List<EmpAttendanceModel> selectAttendanceRecordByAttendanceDate(String attendanceDate);

    List<EmpUserDto> getAllEmpUsers(String deptName);

}
