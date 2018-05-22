package com.facebank.usersupport.attendance.service.impl;

import com.facebank.usersupport.attendance.dto.reqDto.GetAttendanceForm;
import com.facebank.usersupport.attendance.mapper.EmpAttendanceMapper;
import com.facebank.usersupport.attendance.model.EmpAttendanceModel;
import com.facebank.usersupport.attendance.service.IEmpAttendanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HuBiao
 * @date 2018/4/4 0004 11:16
 **/
@Service
public class EmpAttendanceServiceImpl implements IEmpAttendanceService {

    @Autowired
    private EmpAttendanceMapper empAttendanceMapper;

    @Override
    public void importAttendance(List<EmpAttendanceModel> list) {
        empAttendanceMapper.saveEmpAttendanceList(list);
    }

    @Override
    public PageInfo<EmpAttendanceModel> getAttendanceRecordByForm(GetAttendanceForm attendanceForm) {
        PageHelper.startPage(attendanceForm.getPageNumber(),attendanceForm.getPageSize());
        List<EmpAttendanceModel> empAttendanceModels = empAttendanceMapper.selectAttendanceRecordByForm(attendanceForm);
        PageInfo<EmpAttendanceModel> pageInfo =new PageInfo<>(empAttendanceModels);
        return pageInfo;
    }

    @Override
    public List<EmpAttendanceModel> getAttendanceRecordByForm1(GetAttendanceForm attendanceForm) {
        return empAttendanceMapper.selectAttendanceRecordByForm(attendanceForm);
    }

    @Override
    public List<EmpAttendanceModel> selectAttendanceRecordByAttendanceDate(String attendanceDate) {
        return empAttendanceMapper.selectAttendanceRecordByAttendanceDate(attendanceDate);
    }
}
