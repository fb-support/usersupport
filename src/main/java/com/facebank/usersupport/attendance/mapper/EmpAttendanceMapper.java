package com.facebank.usersupport.attendance.mapper;

import com.facebank.usersupport.attendance.dto.EmpUserDto;
import com.facebank.usersupport.attendance.dto.reqDto.GetAttendanceForm;
import com.facebank.usersupport.attendance.model.EmpAttendanceModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HuBiao
 * @date 2018/4/4 0004 11:18
 **/
@Repository
public interface EmpAttendanceMapper {

    void saveEmpAttendanceList(List<EmpAttendanceModel> list);

    List<EmpAttendanceModel> selectAttendanceRecordByForm(GetAttendanceForm attendanceForm);

    List<EmpAttendanceModel> selectAttendanceRecordByAttendanceDate(String attendanceDate);

    List<EmpUserDto> listEmpNames(String deptName);

}
