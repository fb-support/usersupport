package com.facebank.usersupport.attendance.controller;

import com.facebank.usersupport.attendance.dto.reqDto.GetAttendanceForm;
import com.facebank.usersupport.attendance.model.EmpAttendanceModel;
import com.facebank.usersupport.attendance.service.IEmpAttendanceService;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author HuBiao
 * @date 2018/4/4 0004 9:25
 **/
@Controller
public class EmpAttendanceController extends BaseController {

    @Autowired
    private IEmpAttendanceService empAttendanceService;

    /**
     * 从excel导入考勤记录
     * @param file
     * @return
     */
    @PostMapping("/attendance/import")
    @ResponseBody
    public RestModel attendanceImport(MultipartFile file) {
        try {
            // 校验上传的excel文件格式
            String fileName = file.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (!"xls".equals(fileType.toLowerCase()) && !"xlsx".equals(fileType.toLowerCase())) {
                return this.excpRestModel(MessageKeyEnum.FILE_TYPE_ERROR);
            }
            if (file.getSize() > 2097152) {
                return this.excpRestModel(MessageKeyEnum.FILE_SIZE_ERROR);
            }

            // 解析excel文件
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum();
            int year = 0;
            int month = 0;
            int dayInMonth = 0;
            int workNumber = 0;
            String empName = "";
            String deptName = "";
            XSSFRow row = null;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            List<EmpAttendanceModel> list = new ArrayList<>();
            for (int i = 0; i <= lastRowNum; i++) {
                if (i < 2) {
                    continue;
                }
                row = sheet.getRow(i);
                if (i == 2) {
                    // 解析第三行获取年与月
                    String dateStr = row.getCell(2).getStringCellValue();
                    year = Integer.parseInt(dateStr.substring(0, 4));
                    month = Integer.parseInt(dateStr.substring(5, 7));
                } else if (i == 3) {
                    // 解析第四行获取本月一共有多少天
                    dayInMonth = row.getLastCellNum();
                } else {
                    // 剩下的行就是员工本月的考勤记录
                    if (i % 2 == 0) {
                        // 解析偶数行获取员工的工号、姓名、所属部门信息
                        workNumber = Integer.parseInt(row.getCell(2).getStringCellValue());
                        empName = row.getCell(10).getStringCellValue();
                        deptName = row.getCell(20).getStringCellValue();
                    } else {
                        // 解析奇数行获取上下班打卡时间
                        for (int j = 0; j < dayInMonth; j++) {
                            String clockRecord = row.getCell(j).getStringCellValue();
                            if ("".equals(clockRecord)) {
                                continue;
                            } else {
                                // 解析上下班打卡时间
                                String[] clockTimes = StringUtils.split(clockRecord, "\n");
                                Date startTime = null;
                                Date endTime = null;
                                int status = 0;
                                // 标准上下班时间
                                Date start = timeFormat.parse(year + "-" + month + "-" + String.format("%02d", j + 1) + " 09:00");
                                Date end = timeFormat.parse(year + "-" + month + "-" + String.format("%02d", j + 1) + " 18:00");
                                if (clockTimes.length == 2) {
                                    // 上班打卡时间
                                    startTime = timeFormat.parse(year + "-" + month + "-" + String.format("%02d", j + 1) + " " + clockTimes[0]);
                                    // 下班打卡时间
                                    endTime = timeFormat.parse(year + "-" + month + "-" + String.format("%02d", j + 1) + " " + clockTimes[1]);
                                } else if (clockTimes.length == 1) {
                                    startTime = timeFormat.parse(year + "-" + month + "-" + String.format("%02d", j + 1) + " " + clockTimes[0]);
                                    status = 5;
                                } else if (clockTimes.length >= 3) {
                                    startTime = timeFormat.parse(year + "-" + month + "-" + String.format("%02d", j + 1) + " " + clockTimes[0]);
                                    endTime = timeFormat.parse(year + "-" + month + "-" + String.format("%02d", j + 1) + " " + clockTimes[clockTimes.length - 1]);
                                }

                                // 判断是否迟到早退
                                if(clockTimes.length != 1){
                                    if (startTime.getTime() > start.getTime()) {
                                        if (endTime.getTime() < end.getTime()) {
                                            status = 3;
                                        } else {
                                            status = 1;
                                        }
                                    } else {
                                        if (endTime.getTime() < end.getTime()) {
                                            status = 2;
                                        }
                                    }
                                }
                                // 获取这一天的Date类型
                                Date attendanceDate = dateFormat.parse(year + "-" + month + "-" + String.format("%02d", j + 1));
                                // 创建一个Model保存考勤信息
                                EmpAttendanceModel empAttendanceModel = new EmpAttendanceModel();
                                empAttendanceModel.setStartTime(startTime);
                                empAttendanceModel.setEndTime(endTime);
                                empAttendanceModel.setStatus(status);
                                empAttendanceModel.setWorkNumber(workNumber);
                                empAttendanceModel.setEmpName(empName);
                                empAttendanceModel.setDeptName(deptName);
                                empAttendanceModel.setAttendanceDate(attendanceDate);
                                list.add(empAttendanceModel);
                            }
                        }
                    }
                }
            }
            // 解析完成，调用serivice层方法
            empAttendanceService.importAttendance(list);

            return success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    @PostMapping("/attendance/getAttendanceRecord")
    @ResponseBody
    public RestModel getAttendanceRecord(GetAttendanceForm attendanceForm, String draw, HttpServletRequest request){
        try {
            // 根据页面传递参数组合查询考勤信息
            List<EmpAttendanceModel> attendanceRecord = empAttendanceService.getAttendanceRecordByForm(attendanceForm);
            // 将考勤信息存入session中方便导出
            request.getSession().setAttribute("attendanceRecord",attendanceRecord);
            PageRestModel pageRestModel = new PageRestModel(
                    draw,
                    new Long(attendanceRecord.size()+""),
                    new Long(attendanceRecord.size()+""),
                    attendanceRecord
            );
            return success(pageRestModel);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    /**
     * 导出操作
     */
    @PostMapping("/attendance/export")
    public void output(HttpServletRequest request,HttpServletResponse response,GetAttendanceForm attendanceForm) throws Exception {
        // 从session中取出考勤信息
        List<EmpAttendanceModel> list = (List<EmpAttendanceModel>) request.getSession().getAttribute("attendanceRecord");
        // 遍历集合，将集合中的数据根据员工号进行分组，并保持到map中
        Map<Integer,List<EmpAttendanceModel>> map = new HashMap<>();
        for (EmpAttendanceModel empAttendanceModel : list) {
            Integer workNumber = empAttendanceModel.getWorkNumber();
            if(map.containsKey(workNumber)){
                map.get(workNumber).add(empAttendanceModel);
            }else{
                List<EmpAttendanceModel> subList = new ArrayList<>();
                subList.add(empAttendanceModel);
                map.put(workNumber,subList);
            }
        }

        // 生成Excel文件
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet("考勤记录");

        Calendar calendar = Calendar.getInstance();
        Date attenDate = list.get(0).getAttendanceDate();
        calendar.setTime(attenDate);
        // 获取年、月、本月最小天数、本月最大天数
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 创建合并单元格对象 //起始行,结束行,起始列,结束列
        XSSFCell titleCell = sheet.createRow(0).createCell(0);
        XSSFCellStyle style = xssfWorkbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleCell.setCellStyle(style);
        titleCell.setCellValue("刷卡记录表");
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0,1,0,lastDay-1);
        sheet.addMergedRegion(cellRangeAddress);

        // 第三行
        XSSFRow titleRow = sheet.createRow(2);
        titleRow.createCell(0).setCellValue("考勤日期 :");
        CellRangeAddress cellRangeAddress2 = new CellRangeAddress(2,2,0,1);
        sheet.addMergedRegion(cellRangeAddress2);

        titleRow.createCell(2).setCellValue(year + "/" + month + "/" + firstDay + " ~ " + month + "/" + lastDay);
        CellRangeAddress cellRangeAddress3 = new CellRangeAddress(2,2,2,lastDay-7);
        sheet.addMergedRegion(cellRangeAddress3);
        titleRow.createCell(lastDay-6).setCellValue("制表时间 :");
        CellRangeAddress cellRangeAddress4 = new CellRangeAddress(2,2,lastDay-6,lastDay-4);
        sheet.addMergedRegion(cellRangeAddress4);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        titleRow.createCell(lastDay-3).setCellValue(sdf.format(new Date()));
        CellRangeAddress cellRangeAddress5 = new CellRangeAddress(2,2,lastDay-3,lastDay-1);
        sheet.addMergedRegion(cellRangeAddress5);

        // 显示天数那一行（第4行）
        XSSFRow headRow = sheet.createRow(3);
        // 根据当月最大天数确认有多少列
        for(int x = 1; x <= lastDay; x++){
            headRow.createCell(x-1).setCellValue(x);
        }

        int rowNum = 4;

        // 根据map中的数据生成对应的员工考勤信息
        for (Map.Entry<Integer, List<EmpAttendanceModel>> entry : map.entrySet()) {
            List<EmpAttendanceModel> empList = entry.getValue();

            XSSFRow dataRow = sheet.createRow(rowNum++);
            XSSFRow dataRow1 = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue("工 号：");
            dataRow.createCell(2).setCellValue(empList.get(0).getWorkNumber());
            dataRow.createCell(8).setCellValue("姓 名：");
            dataRow.createCell(10).setCellValue(empList.get(0).getEmpName());
            dataRow.createCell(18).setCellValue("部 门：");
            dataRow.createCell(20).setCellValue(empList.get(0).getDeptName());

            // 定义需要的变量
            int index;
            Date startTime;
            Date attendanceDate;
            Date endTime;
            String startTimeStr = null;
            String endTimeStr = null;
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");

            // 设置为自动换行
            XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
            cellStyle.setWrapText(true);

            for(int i=0;i<empList.size();i++){
                startTime = empList.get(i).getStartTime();
                attendanceDate = empList.get(i).getAttendanceDate();
                endTime = empList.get(i).getEndTime();

                // 根据日期中的日确认单元格列的索引
                calendar.setTime(attendanceDate);
                index = calendar.get(Calendar.DAY_OF_MONTH)-1;

                // 将打卡时间转换为:HH:mm格式的字符串
                if (startTime!=null){
                    startTimeStr = df.format(startTime);
                }
                if(endTime!=null){
                    endTimeStr= df.format(endTime);
                }

                if(startTime==null&&endTime!=null){
                    dataRow1.createCell(index).setCellValue(endTimeStr);
                }else if (startTime!=null&&endTime==null){
                    dataRow1.createCell(index).setCellValue(startTimeStr);
                }else {
                    XSSFCell cell = dataRow1.createCell(index);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(new XSSFRichTextString(startTimeStr+" "+endTimeStr));
                }

            }
        }

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=attendance.xls");//默认Excel名称
        response.flushBuffer();
        OutputStream os = response.getOutputStream();
        xssfWorkbook.write(os);
        os.flush();
        os.close();

    }

}