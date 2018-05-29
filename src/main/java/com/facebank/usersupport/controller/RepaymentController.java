package com.facebank.usersupport.controller;

import com.alibaba.fastjson.JSONObject;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.reqDto.RepaymentForm;
import com.facebank.usersupport.mapper.usersupport.p2p.UserMainP2PReadMapper;
import com.facebank.usersupport.model.*;
import com.facebank.usersupport.service.IRepaymentService;
import com.github.pagehelper.PageInfo;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 还款查询业务Controller
 *
 * @author NingKui
 * @date 2018/3/9 10:42
 **/
@Controller
public class RepaymentController extends BaseController {

    @Autowired
    private IRepaymentService repaymentService;

    @Autowired
    private UserMainP2PReadMapper userMainP2PReadMapper;

    /**
     * 根据手机号、用户名、还款日期组合条件查询债权还款信息
     *
     * @param repaymentForm
     * @return
     */
    @PostMapping("/service/repayment")
    @ResponseBody
    public RestModel repaymentSearch(RepaymentForm repaymentForm,HttpServletRequest request,String iframeId) {
        try {
            //拼接ecache的key
            String telephone=repaymentForm.getMobile();
            String orderId=repaymentForm.getOrderId().toString();
            String credidId=repaymentForm.getCreditId().toString();
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
            Long time=repaymentForm.getStartTime();
            Long time1=repaymentForm.getEndTime();
            String d = format.format(time);
            String b=format.format(time1);
            Date date=format.parse(d);
            Date date1=format.parse(b);
            String bizStatus="";
            if(repaymentForm.getBizStatus()!=null) {
                bizStatus = repaymentForm.getBizStatus().toString();
            }
            String UserId=telephone+"/"+orderId+"/"+credidId+"/"+date+"/"+date1+"/"+bizStatus;

            // 手机号和orderId都为空
            boolean isAllEmpty = StringUtils.isEmpty(repaymentForm.getOrderId()) && StringUtils.isEmpty(repaymentForm.getMobile());
            // 开始时间大于结束时间
            boolean timeInterval = !StringUtils.isEmpty(repaymentForm.getStartTime()) && !StringUtils.isEmpty(repaymentForm.getEndTime()) && (repaymentForm.getEndTime() < repaymentForm.getStartTime());
            // 用户ID与订单ID都为空或者开始时间大于结束时间则直接返回参数错误
            if (isAllEmpty || timeInterval) {
                return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
            }
            if (!StringUtils.isEmpty(repaymentForm.getMobile())) {
                Long userId = userMainP2PReadMapper.selectUserIdByMobile(repaymentForm.getMobile());
                repaymentForm.setUserId(userId);
            }
            // 查询还款信息
            PageInfo<RepaymentModel> repaymentModels = repaymentService.getRepaymentModelByRepaymentForm(repaymentForm,UserId);
            // 查询参数保存到session，方便用于导出数据
            request.getSession().setAttribute( "repaymentForm", repaymentForm);

            // 统计用户资产信息
            List<UserCapitalInfoModel> userCapitalInfoModels = new ArrayList<>();
            if (repaymentModels.getList() != null && repaymentModels.getList().size() > 0) {
                // 查询用户资产信息
                UserCapitalInfoModel userCapitalInfoModel = userMainP2PReadMapper.selectUserCapitalByUserId(repaymentModels.getList().get(0).getUserId());

                // 计算债权计划本金总额
                BigDecimal totalCredPlanPrincipal = new BigDecimal(0);
                // 计算债权实收本金总额
                BigDecimal totalCredRealPrincipal = new BigDecimal(0);
                // 计算债权计划利息总额
                BigDecimal totalCredPlanInterest = new BigDecimal(0);
                // 计算债权实收利息总额
                BigDecimal totalCredRealInterest = new BigDecimal(0);
                // 计算应收红包总额
                BigDecimal totalRedPlanAmount = new BigDecimal(0);
                // 计算实收红包总额
                BigDecimal totalRedRealAmount = new BigDecimal(0);
                // 计算应收vip总收益
                BigDecimal totalVipPlanAmount = new BigDecimal(0);
                // 计算实收vip总收益
                BigDecimal totalVipRealAmount = new BigDecimal(0);
                // 计算应收加息总额
                BigDecimal totalPfPlanAmount = new BigDecimal(0);
                // 计算实收加息总额
                BigDecimal totalPfRealAmount = new BigDecimal(0);
                for (RepaymentModel repaymentModel : repaymentModels.getList()) {
                    totalCredPlanPrincipal = totalCredPlanPrincipal.add(repaymentModel.getCredPlanPrincipal());
                    totalCredRealPrincipal = totalCredRealPrincipal.add(repaymentModel.getCredRealPrincipal());
                    totalCredPlanInterest = totalCredPlanInterest.add(repaymentModel.getCredPlanInterest());
                    totalCredRealInterest = totalCredRealInterest.add(repaymentModel.getCredRealInterest());
                    if(repaymentModel.getRedPlanAmount() != null){
                        totalRedPlanAmount = totalRedPlanAmount.add(repaymentModel.getRedPlanAmount());
                    }
                    if(repaymentModel.getRedRealAmount() != null){
                        totalRedRealAmount = totalRedRealAmount.add(repaymentModel.getRedRealAmount());
                    }
                    if(repaymentModel.getVipPlanAmount() != null){
                        totalVipPlanAmount = totalVipPlanAmount.add(repaymentModel.getVipPlanAmount());
                    }
                    if(repaymentModel.getVipRealAmount() != null){
                        totalVipRealAmount = totalVipRealAmount.add(repaymentModel.getVipRealAmount());
                    }
                    if(repaymentModel.getPfPlanAmount() != null){
                        totalPfPlanAmount = totalPfPlanAmount.add(repaymentModel.getPfPlanAmount());
                    }
                    if(repaymentModel.getPfRealAmount() != null){
                        totalPfRealAmount = totalPfRealAmount.add(repaymentModel.getPfRealAmount());
                    }
                }
                userCapitalInfoModel.setTotalCredPlanPrincipal(totalCredPlanPrincipal);
                userCapitalInfoModel.setTotalCredRealPrincipal(totalCredRealPrincipal);
                userCapitalInfoModel.setTotalCredPlanInterest(totalCredPlanInterest);
                userCapitalInfoModel.setTotalCredRealInterest(totalCredRealInterest);
                userCapitalInfoModel.setTotalRedPlanAmount(totalRedPlanAmount);
                userCapitalInfoModel.setTotalRedRealAmount(totalRedRealAmount);
                userCapitalInfoModel.setTotalVipPlanAmount(totalVipPlanAmount);
                userCapitalInfoModel.setTotalVipRealAmount(totalVipRealAmount);
                userCapitalInfoModel.setTotalPfPlanAmount(totalPfPlanAmount);
                userCapitalInfoModel.setTotalPfRealAmount(totalPfRealAmount);

                // 计算总资产
                BigDecimal totalAssets = userCapitalInfoModel.getCash().add(userCapitalInfoModel.getFrozenWithDrawCash()).add(totalCredPlanPrincipal)
                        .add(totalCredPlanInterest).add(totalRedPlanAmount).add(totalVipPlanAmount).add(totalPfPlanAmount);
                userCapitalInfoModel.setTotalAssets(totalAssets);
                userCapitalInfoModels.add(userCapitalInfoModel);
            }

//            PageRestModel pageRestModel = new PageRestModel();
//
//
//            PageRestModel pageRestModel2 = new PageRestModel(
//                    draw,
//                    new Long(userCapitalInfoModels.size() + ""),
//                    new Long(userCapitalInfoModels.size() + ""),
//                    userCapitalInfoModels
//            );
            PageBeanModel pageRestModel=new PageBeanModel();
            pageRestModel.setData(repaymentModels.getList());
            pageRestModel.setPage(repaymentModels.getPageNum());
            pageRestModel.setPageSize(repaymentModels.getPageSize());
            pageRestModel.setTotalCount(repaymentModels.getTotal());
            pageRestModel.setTotalPage(repaymentModels.getPages());

            PageBeanModel pageBeanModel2=new PageBeanModel();
            pageBeanModel2.setData(userCapitalInfoModels);

            PageBeanModel[] pageBeanModels = new PageBeanModel[2];

            pageBeanModels[0] = pageRestModel;
            pageBeanModels[1] = pageBeanModel2;

            return this.success(pageBeanModels);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    /**
     * 根据手机号、用户名、还款日期组合条件查询订单还款信息
     *
     * @param repaymentForm
     * @return
     */
    @PostMapping("/service/repaymentOrder")
    @ResponseBody
    public RestModel repaymentOrderSearch(RepaymentForm repaymentForm,String draw, HttpServletRequest request,String iframeId) {
        try {

            // 手机号和orderId都为空
            boolean isAllEmpty = StringUtils.isEmpty(repaymentForm.getOrderId()) && StringUtils.isEmpty(repaymentForm.getMobile());
            // 开始时间大于结束时间
            boolean timeInterval = !StringUtils.isEmpty(repaymentForm.getStartTime()) && !StringUtils.isEmpty(repaymentForm.getEndTime()) && (repaymentForm.getEndTime() < repaymentForm.getStartTime());
            // 用户ID与订单ID都为空或者开始时间大于结束时间则直接返回参数错误
            if (isAllEmpty || timeInterval) {
                return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
            }
            if (!StringUtils.isEmpty(repaymentForm.getMobile())) {
                Long userId = userMainP2PReadMapper.selectUserIdByMobile(repaymentForm.getMobile());
                repaymentForm.setUserId(userId);
            }
            // 查询还款信息
            List<RepaymentModel> repaymentModels = repaymentService.getRepaymentOrderByRepaymentForm(repaymentForm);
            // 查询参数保存到session，方便用于导出数据
            request.getSession().setAttribute(iframeId + ":repaymentOrderForm", repaymentForm);

            // 统计用户资产信息
            List<UserCapitalInfoModel> userCapitalInfoModels = new ArrayList<>();
            if (repaymentModels != null && repaymentModels.size() > 0) {
                UserCapitalInfoModel userCapitalInfoModel = userMainP2PReadMapper.selectUserCapitalByUserId(repaymentModels.get(0).getUserId());

                // 计算债权计划本金总额
                BigDecimal totalCredPlanPrincipal = new BigDecimal(0);
                // 计算债权实收本金总额
                BigDecimal totalCredRealPrincipal = new BigDecimal(0);
                // 计算债权计划利息总额
                BigDecimal totalCredPlanInterest = new BigDecimal(0);
                // 计算债权实收利息总额
                BigDecimal totalCredRealInterest = new BigDecimal(0);
                // 计算应收红包总额
                BigDecimal totalRedPlanAmount = new BigDecimal(0);
                // 计算实收红包总额
                BigDecimal totalRedRealAmount = new BigDecimal(0);
                // 计算应收vip总收益
                BigDecimal totalVipPlanAmount = new BigDecimal(0);
                // 计算实收vip总收益
                BigDecimal totalVipRealAmount = new BigDecimal(0);
                // 计算应收加息总额
                BigDecimal totalPfPlanAmount = new BigDecimal(0);
                // 计算实收加息总额
                BigDecimal totalPfRealAmount = new BigDecimal(0);
                for (RepaymentModel repaymentModel : repaymentModels) {
                    totalCredPlanPrincipal = totalCredPlanPrincipal.add(repaymentModel.getCredPlanPrincipal());
                    totalCredRealPrincipal = totalCredRealPrincipal.add(repaymentModel.getCredRealPrincipal());
                    totalCredPlanInterest = totalCredPlanInterest.add(repaymentModel.getCredPlanInterest());
                    totalCredRealInterest = totalCredRealInterest.add(repaymentModel.getCredRealInterest());
                    if(repaymentModel.getRedPlanAmount() != null){
                        totalRedPlanAmount = totalRedPlanAmount.add(repaymentModel.getRedPlanAmount());
                    }
                    if(repaymentModel.getRedRealAmount() != null){
                        totalRedRealAmount = totalRedRealAmount.add(repaymentModel.getRedRealAmount());
                    }
                    if(repaymentModel.getVipPlanAmount() != null){
                        totalVipPlanAmount = totalVipPlanAmount.add(repaymentModel.getVipPlanAmount());
                    }
                    if(repaymentModel.getVipRealAmount() != null){
                        totalVipRealAmount = totalVipRealAmount.add(repaymentModel.getVipRealAmount());
                    }
                    if(repaymentModel.getPfPlanAmount() != null){
                        totalPfPlanAmount = totalPfPlanAmount.add(repaymentModel.getPfPlanAmount());
                    }
                    if(repaymentModel.getPfRealAmount() != null){
                        totalPfRealAmount = totalPfRealAmount.add(repaymentModel.getPfRealAmount());
                    }
                }
                userCapitalInfoModel.setTotalCredPlanPrincipal(totalCredPlanPrincipal);
                userCapitalInfoModel.setTotalCredRealPrincipal(totalCredRealPrincipal);
                userCapitalInfoModel.setTotalCredPlanInterest(totalCredPlanInterest);
                userCapitalInfoModel.setTotalCredRealInterest(totalCredRealInterest);
                userCapitalInfoModel.setTotalRedPlanAmount(totalRedPlanAmount);
                userCapitalInfoModel.setTotalRedRealAmount(totalRedRealAmount);
                userCapitalInfoModel.setTotalVipPlanAmount(totalVipPlanAmount);
                userCapitalInfoModel.setTotalVipRealAmount(totalVipRealAmount);
                userCapitalInfoModel.setTotalPfPlanAmount(totalPfPlanAmount);
                userCapitalInfoModel.setTotalPfRealAmount(totalPfRealAmount);

                // 计算总资产
                BigDecimal totalAssets = userCapitalInfoModel.getCash().add(userCapitalInfoModel.getFrozenWithDrawCash()).add(totalCredPlanPrincipal)
                        .add(totalCredPlanInterest).add(totalRedPlanAmount).add(totalVipPlanAmount).add(totalPfPlanAmount);
                userCapitalInfoModel.setTotalAssets(totalAssets);
                userCapitalInfoModels.add(userCapitalInfoModel);
            }

            PageRestModel pageRestModel = new PageRestModel(
                    draw,
                    new Long(repaymentModels.size() + ""),
                    new Long(repaymentModels.size() + ""),
                    repaymentModels
            );
            PageRestModel pageRestModel2 = new PageRestModel(
                    draw,
                    new Long(repaymentModels.size() + ""),
                    new Long(repaymentModels.size() + ""),
                    userCapitalInfoModels
            );
            PageRestModel[] pageRestModels = new PageRestModel[2];
            pageRestModels[0] = pageRestModel;
            pageRestModels[1] = pageRestModel2;

            return this.success(pageRestModels);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    @PostMapping("/service/repaymentDetail")
    @ResponseBody
    public RestModel searchRepaymentDetail(HttpServletRequest request,String draw,String iframeId){
        try {
            // 从session中获取查询参数
            RepaymentForm repaymentForm = (RepaymentForm) request.getSession().getAttribute(iframeId + ":repaymentOrderForm");
            if(repaymentForm.getOrderId() == null || repaymentForm.getOrderId() == 0L){
                return this.excpRestModel(MessageKeyEnum.ORDERID_EMPTY);
            }
            // 查询债权还款明细
            List<RepaymentModel> repaymentModels = repaymentService.getRepaymentDetailByRepaymentForm(repaymentForm);

            PageRestModel pageRestModel = new PageRestModel(
                    draw,
                    new Long(repaymentModels.size() + ""),
                    new Long(repaymentModels.size() + ""),
                    repaymentModels
            );
            return this.success(pageRestModel);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    @GetMapping("/repayment/export")
    public void exportRepayment(HttpServletRequest request, HttpServletResponse response,String iframeId) throws Exception {
        // 从session中获取查询参数
        RepaymentForm repaymentForm = (RepaymentForm) request.getSession().getAttribute( "repaymentForm");
        // 从数据库中查询要导出的数据
        PageInfo<RepaymentModel> repaymentModels = repaymentService.getRepaymentModelByRepaymentForm(repaymentForm,null);
        if (repaymentModels.getList() != null && repaymentModels.getList().size() > 0) {
            // 生成Excel文件
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("债权还款信息");
            // 标题行
            XSSFRow headRow = sheet.createRow(0);
            XSSFCell headCell1 = headRow.createCell(0);
            headCell1.setCellValue("债权ID");
            XSSFCell headCell2 = headRow.createCell(1);
            headCell2.setCellValue("订单ID");
            XSSFCell headCell3 = headRow.createCell(2);
            headCell3.setCellValue("用户ID");
            XSSFCell headCell4 = headRow.createCell(3);
            headCell4.setCellValue("计划还款时间");
            XSSFCell headCell5 = headRow.createCell(4);
            headCell5.setCellValue("是否还款");
            XSSFCell headCell6 = headRow.createCell(5);
            headCell6.setCellValue("债权计划本金");
            XSSFCell headCell7 = headRow.createCell(6);
            headCell7.setCellValue("债权实收本金");
            XSSFCell headCell8 = headRow.createCell(7);
            headCell8.setCellValue("债权计划利息");
            XSSFCell headCell9 = headRow.createCell(8);
            headCell9.setCellValue("债权实收利息");
            XSSFCell headCell11 = headRow.createCell(10);
            headCell11.setCellValue("应收红包金额");
            XSSFCell headCell12 = headRow.createCell(11);
            headCell12.setCellValue("实收红包金额");
            XSSFCell headCell14 = headRow.createCell(13);
            headCell14.setCellValue("vip利率");
            XSSFCell headCell15 = headRow.createCell(14);
            headCell15.setCellValue("应收vip收益");
            XSSFCell headCell16 = headRow.createCell(15);
            headCell16.setCellValue("实收vip收益");
            XSSFCell headCell17 = headRow.createCell(16);
            headCell17.setCellValue("vip期数");
            XSSFCell headCell18 = headRow.createCell(17);
            headCell18.setCellValue("应收加息金额");
            XSSFCell headCell19 = headRow.createCell(18);
            headCell19.setCellValue("实收加息金额");
            XSSFCell headCell20 = headRow.createCell(19);
            headCell20.setCellValue("加息期数");
            XSSFCell headCell21 = headRow.createCell(20);
            headCell21.setCellValue("加息类型");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            for (int i = 0; i < repaymentModels.getList().size(); i++) {
                XSSFRow dateRow = sheet.createRow(i + 1);
                XSSFCell dateCell1 = dateRow.createCell(0);
                dateCell1.setCellValue(repaymentModels.getList().get(i).getCreditId());
                XSSFCell dateCell2 = dateRow.createCell(1);
                dateCell2.setCellValue(repaymentModels.getList().get(i).getOrderId());
                XSSFCell dateCell3 = dateRow.createCell(2);
                dateCell3.setCellValue(repaymentModels.getList().get(i).getUserId());
                XSSFCell dateCell4 = dateRow.createCell(3);
                Date date = new Date(repaymentModels.getList().get(i).getPlanDate());
                dateCell4.setCellValue(dateFormat.format(date));
                XSSFCell dateCell5 = dateRow.createCell(4);
                if(repaymentModels.getList().get(i).getBizStatus() != null){
                    if(repaymentModels.getList().get(i).getBizStatus() == 100){
                        dateCell5.setCellValue("未还");
                    }else if(repaymentModels.getList().get(i).getBizStatus() == 200){
                        dateCell5.setCellValue("已还");
                    }
                }
                XSSFCell dateCell6 = dateRow.createCell(5);
                dateCell6.setCellValue(repaymentModels.getList().get(i).getCredPlanPrincipal().doubleValue());
                XSSFCell dateCell7 = dateRow.createCell(6);
                dateCell7.setCellValue(repaymentModels.getList().get(i).getCredRealPrincipal().doubleValue());
                XSSFCell dateCell8 = dateRow.createCell(7);
                dateCell8.setCellValue(repaymentModels.getList().get(i).getCredPlanInterest().doubleValue());
                XSSFCell dateCell9 = dateRow.createCell(8);
                dateCell9.setCellValue(repaymentModels.getList().get(i).getCredRealInterest().doubleValue());


                /*XSSFCell dateCell10 = dateRow.createCell(9);
                String redLocalInfo = repaymentModels.get(i).getRedLocalInfo();
                if (!StringUtils.isEmpty(redLocalInfo)) {
                    Map<String, Object> parse = (Map<String, Object>) JSONObject.parse(redLocalInfo);
                    dateCell10.setCellValue(parse.get("model_name").toString());
                }*/
                XSSFCell dateCell10 = dateRow.createCell(9);
                if(repaymentModels.getList().get(i).getRedPlanAmount() != null){
                    dateCell10.setCellValue(repaymentModels.getList().get(i).getRedPlanAmount().doubleValue());
                }else {
                    dateCell10.setCellValue(0);
                }
                XSSFCell dateCell11 = dateRow.createCell(10);
                if(repaymentModels.getList().get(i).getRedRealAmount() != null){
                    dateCell11.setCellValue(repaymentModels.getList().get(i).getRedRealAmount().doubleValue());
                }else {
                    dateCell11.setCellValue(0);
                }
                /*XSSFCell dateCell13 = dateRow.createCell(12);
                if(repaymentModels.get(i).getRedPackageType() != null){
                    if(repaymentModels.get(i).getRedPackageType() == 1000){
                        dateCell13.setCellValue("加息红包");
                    }else if(repaymentModels.get(i).getRedPackageType() == 1010){
                        dateCell13.setCellValue("返现红包");
                    }
                }*/


                XSSFCell dateCell12 = dateRow.createCell(11);
                if(repaymentModels.getList().get(i).getVipRate() != null){
                    dateCell12.setCellValue(repaymentModels.getList().get(i).getVipRate().doubleValue());
                }
                XSSFCell dateCell13 = dateRow.createCell(12);
                if(repaymentModels.getList().get(i).getVipPlanAmount() != null){
                    dateCell13.setCellValue(repaymentModels.getList().get(i).getVipPlanAmount().doubleValue());
                }else {
                    dateCell13.setCellValue(0);
                }
                XSSFCell dateCell14 = dateRow.createCell(13);
                if(repaymentModels.getList().get(i).getVipRealAmount() != null){
                    dateCell14.setCellValue(repaymentModels.getList().get(i).getVipRealAmount().doubleValue());
                }else {
                    dateCell14.setCellValue(0);
                }
                XSSFCell dateCell15 = dateRow.createCell(14);
                if(repaymentModels.getList().get(i).getVipTermNum() != null){
                    dateCell15.setCellValue(repaymentModels.getList().get(i).getVipTermNum());
                }


                XSSFCell dateCell16 = dateRow.createCell(15);
                if(repaymentModels.getList().get(i).getPfPlanAmount() != null){
                    dateCell16.setCellValue(repaymentModels.getList().get(i).getPfPlanAmount().doubleValue());
                }else {
                    dateCell16.setCellValue(0);
                }
                XSSFCell dateCell17 = dateRow.createCell(16);
                if(repaymentModels.getList().get(i).getPfRealAmount() != null){
                    dateCell17.setCellValue(repaymentModels.getList().get(i).getPfRealAmount().doubleValue());
                }else {
                    dateCell17.setCellValue(0);
                }
                XSSFCell dateCell18 = dateRow.createCell(17);
                if(repaymentModels.getList().get(i).getPfTermNum() != null){
                    dateCell18.setCellValue(repaymentModels.getList().get(i).getPfTermNum());
                }
                XSSFCell dateCell19 = dateRow.createCell(18);
                if(repaymentModels.getList().get(i).getPfType() != null){
                    if(repaymentModels.getList().get(i).getPfType() == 100){
                        dateCell19.setCellValue("首购加息");
                    }else if(repaymentModels.getList().get(i).getPfType() == 200){
                        dateCell19.setCellValue("限时加息");
                    }else if(repaymentModels.getList().get(i).getPfType() == 300){
                        dateCell19.setCellValue("项目加息");
                    }
                }
            }
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=repayment.xls");//默认Excel名称
            response.flushBuffer();
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
        }
    }

    @PostMapping("/repaymentOrder/export")
    public void exportRepaymentOrder(HttpServletRequest request, HttpServletResponse response,String iframeId) throws Exception {
        // 从session中获取查询参数
        RepaymentForm repaymentForm = (RepaymentForm) request.getSession().getAttribute(iframeId + ":repaymentOrderForm");
        // 从数据库中查询要导出的数据
        List<RepaymentModel> repaymentModels = repaymentService.getRepaymentOrderByRepaymentForm(repaymentForm);
        if (repaymentModels != null && repaymentModels.size() > 0) {
            // 生成Excel文件
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("订单还款信息");
            // 标题行
            XSSFRow headRow = sheet.createRow(0);
            XSSFCell headCell1 = headRow.createCell(0);
            headCell1.setCellValue("订单ID");
            XSSFCell headCell2 = headRow.createCell(1);
            headCell2.setCellValue("用户ID");
            XSSFCell headCell3 = headRow.createCell(2);
            headCell3.setCellValue("计划还款时间");
            XSSFCell headCell4 = headRow.createCell(3);
            headCell4.setCellValue("是否还款");
            XSSFCell headCell5 = headRow.createCell(4);
            headCell5.setCellValue("订单计划本金");
            XSSFCell headCell6 = headRow.createCell(5);
            headCell6.setCellValue("订单实收本金");
            XSSFCell headCell7 = headRow.createCell(6);
            headCell7.setCellValue("订单计划利息");
            XSSFCell headCell8 = headRow.createCell(7);
            headCell8.setCellValue("订单实收利息");
            XSSFCell headCell9 = headRow.createCell(8);
            headCell9.setCellValue("应收红包金额");
            XSSFCell headCell10 = headRow.createCell(9);
            headCell10.setCellValue("实收红包金额");
            XSSFCell headCell11 = headRow.createCell(10);
            headCell11.setCellValue("vip计划收益");
            XSSFCell headCell12 = headRow.createCell(11);
            headCell12.setCellValue("vip实际收益");
            XSSFCell headCell13 = headRow.createCell(12);
            headCell13.setCellValue("应收加息金额");
            XSSFCell headCell14 = headRow.createCell(13);
            headCell14.setCellValue("实收加息金额");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            for (int i = 0; i < repaymentModels.size(); i++) {
                XSSFRow dateRow = sheet.createRow(i + 1);
                XSSFCell dateCell1 = dateRow.createCell(0);
                dateCell1.setCellValue(repaymentModels.get(i).getOrderId());
                XSSFCell dateCell2 = dateRow.createCell(1);
                dateCell2.setCellValue(repaymentModels.get(i).getUserId());
                XSSFCell dateCell3 = dateRow.createCell(2);
                Date date = new Date(repaymentModels.get(i).getPlanDate());
                dateCell3.setCellValue(dateFormat.format(date));
                XSSFCell dateCell4 = dateRow.createCell(3);
                if(repaymentModels.get(i).getBizStatus() != null){
                    if(repaymentModels.get(i).getBizStatus() == 100){
                        dateCell4.setCellValue("未还");
                    }else if(repaymentModels.get(i).getBizStatus() == 200){
                        dateCell4.setCellValue("已还");
                    }
                }
                XSSFCell dateCell5 = dateRow.createCell(4);
                if(repaymentModels.get(i).getCredPlanPrincipal() != null){
                    dateCell5.setCellValue(repaymentModels.get(i).getCredPlanPrincipal().doubleValue());
                }else {
                    dateCell5.setCellValue(0);
                }
                XSSFCell dateCell6 = dateRow.createCell(5);
                if(repaymentModels.get(i).getCredRealPrincipal() != null){
                    dateCell6.setCellValue(repaymentModels.get(i).getCredRealPrincipal().doubleValue());
                }else {
                    dateCell6.setCellValue(0);
                }
                XSSFCell dateCell7 = dateRow.createCell(6);
                if(repaymentModels.get(i).getCredPlanInterest() != null){
                    dateCell7.setCellValue(repaymentModels.get(i).getCredPlanInterest().doubleValue());
                }else {
                    dateCell7.setCellValue(0);
                }
                XSSFCell dateCell8 = dateRow.createCell(7);
                if(repaymentModels.get(i).getCredRealInterest() != null){
                    dateCell8.setCellValue(repaymentModels.get(i).getCredRealInterest().doubleValue());
                }else {
                    dateCell8.setCellValue(0);
                }


                XSSFCell dateCell9 = dateRow.createCell(8);
                if(repaymentModels.get(i).getRedPlanAmount() != null){
                    dateCell9.setCellValue(repaymentModels.get(i).getRedPlanAmount().doubleValue());
                }else {
                    dateCell9.setCellValue(0);
                }
                XSSFCell dateCell10 = dateRow.createCell(9);
                if(repaymentModels.get(i).getRedRealAmount() != null){
                    dateCell10.setCellValue(repaymentModels.get(i).getRedRealAmount().doubleValue());
                }else {
                    dateCell10.setCellValue(0);
                }


                XSSFCell dateCell11 = dateRow.createCell(10);
                if(repaymentModels.get(i).getVipPlanAmount() != null){
                    dateCell11.setCellValue(repaymentModels.get(i).getVipPlanAmount().doubleValue());
                }else {
                    dateCell11.setCellValue(0);
                }
                XSSFCell dateCell12 = dateRow.createCell(11);
                if(repaymentModels.get(i).getVipRealAmount() != null){
                    dateCell12.setCellValue(repaymentModels.get(i).getVipRealAmount().doubleValue());
                }else {
                    dateCell12.setCellValue(0);
                }


                XSSFCell dateCell13 = dateRow.createCell(12);
                if(repaymentModels.get(i).getPfPlanAmount() != null){
                    dateCell13.setCellValue(repaymentModels.get(i).getPfPlanAmount().doubleValue());
                }else {
                    dateCell13.setCellValue(0);
                }
                XSSFCell dateCell14 = dateRow.createCell(13);
                if(repaymentModels.get(i).getPfRealAmount() != null){
                    dateCell14.setCellValue(repaymentModels.get(i).getPfRealAmount().doubleValue());
                }else {
                    dateCell14.setCellValue(0);
                }
            }
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=repaymentOrder.xls");//默认Excel名称
            response.flushBuffer();
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
        }
    }

    @PostMapping("/repaymentDetail/export")
    public void exportRepaymentDetail(HttpServletRequest request, HttpServletResponse response,String iframeId) throws Exception{
        // 从session中获取查询参数
        RepaymentForm repaymentForm = (RepaymentForm) request.getSession().getAttribute(iframeId + ":repaymentOrderForm");
        // 从数据库中查询要导出的数据
        List<RepaymentModel> repaymentModels = repaymentService.getRepaymentDetailByRepaymentForm(repaymentForm);

        if (repaymentModels != null && repaymentModels.size() > 0) {
            // 生成Excel文件
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("债权还款明细");
            // 标题行
            XSSFRow headRow = sheet.createRow(0);
            XSSFCell headCell1 = headRow.createCell(0);
            headCell1.setCellValue("债权ID");
            XSSFCell headCell2 = headRow.createCell(1);
            headCell2.setCellValue("订单ID");
            XSSFCell headCell3 = headRow.createCell(2);
            headCell3.setCellValue("用户ID");
            XSSFCell headCell4 = headRow.createCell(3);
            headCell4.setCellValue("计划还款时间");
            XSSFCell headCell5 = headRow.createCell(4);
            headCell5.setCellValue("是否还款");
            XSSFCell headCell6 = headRow.createCell(5);
            headCell6.setCellValue("债权计划本金");
            XSSFCell headCell7 = headRow.createCell(6);
            headCell7.setCellValue("债权实收本金");
            XSSFCell headCell8 = headRow.createCell(7);
            headCell8.setCellValue("债权计划利息");
            XSSFCell headCell9 = headRow.createCell(8);
            headCell9.setCellValue("债权实收利息");
            XSSFCell headCell10 = headRow.createCell(9);
            headCell10.setCellValue("期数");
            XSSFCell headCell11 = headRow.createCell(10);
            headCell11.setCellValue("红包信息");
            XSSFCell headCell12 = headRow.createCell(11);
            headCell12.setCellValue("应收红包金额");
            XSSFCell headCell13 = headRow.createCell(12);
            headCell13.setCellValue("实收红包金额");
            XSSFCell headCell14 = headRow.createCell(13);
            headCell14.setCellValue("红包类型");
            XSSFCell headCell15 = headRow.createCell(14);
            headCell15.setCellValue("vip利率");
            XSSFCell headCell16 = headRow.createCell(15);
            headCell16.setCellValue("应收vip收益");
            XSSFCell headCell17 = headRow.createCell(16);
            headCell17.setCellValue("实收vip收益");
            XSSFCell headCell18 = headRow.createCell(17);
            headCell18.setCellValue("应收加息金额");
            XSSFCell headCell19 = headRow.createCell(18);
            headCell19.setCellValue("实收加息金额");
            XSSFCell headCell20 = headRow.createCell(19);
            headCell20.setCellValue("加息类型");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            for (int i = 0; i < repaymentModels.size(); i++) {
                XSSFRow dateRow = sheet.createRow(i + 1);
                XSSFCell dateCell1 = dateRow.createCell(0);
                dateCell1.setCellValue(repaymentModels.get(i).getCreditId());
                XSSFCell dateCell2 = dateRow.createCell(1);
                dateCell2.setCellValue(repaymentModels.get(i).getOrderId());
                XSSFCell dateCell3 = dateRow.createCell(2);
                dateCell3.setCellValue(repaymentModels.get(i).getUserId());
                XSSFCell dateCell4 = dateRow.createCell(3);
                Date date = new Date(repaymentModels.get(i).getPlanDate());
                dateCell4.setCellValue(dateFormat.format(date));
                XSSFCell dateCell5 = dateRow.createCell(4);
                if(repaymentModels.get(i).getBizStatus() != null){
                    if(repaymentModels.get(i).getBizStatus() == 100){
                        dateCell5.setCellValue("未还");
                    }else if(repaymentModels.get(i).getBizStatus() == 200){
                        dateCell5.setCellValue("已还");
                    }
                }
                XSSFCell dateCell6 = dateRow.createCell(5);
                dateCell6.setCellValue(repaymentModels.get(i).getCredPlanPrincipal().doubleValue());
                XSSFCell dateCell7 = dateRow.createCell(6);
                dateCell7.setCellValue(repaymentModels.get(i).getCredRealPrincipal().doubleValue());
                XSSFCell dateCell8 = dateRow.createCell(7);
                dateCell8.setCellValue(repaymentModels.get(i).getCredPlanInterest().doubleValue());
                XSSFCell dateCell9 = dateRow.createCell(8);
                dateCell9.setCellValue(repaymentModels.get(i).getCredRealInterest().doubleValue());
                XSSFCell dateCell10 = dateRow.createCell(9);
                dateCell10.setCellValue(repaymentModels.get(i).getCredTermNum()+"期");


                XSSFCell dateCell11 = dateRow.createCell(10);
                String redLocalInfo = repaymentModels.get(i).getRedLocalInfo();
                if (!StringUtils.isEmpty(redLocalInfo)) {
                    Map<String, Object> parse = (Map<String, Object>) JSONObject.parse(redLocalInfo);
                    dateCell11.setCellValue(parse.get("model_name").toString());
                }
                XSSFCell dateCell12 = dateRow.createCell(11);
                if(repaymentModels.get(i).getRedPlanAmount() != null){
                    dateCell12.setCellValue(repaymentModels.get(i).getRedPlanAmount().doubleValue());
                }else {
                    dateCell12.setCellValue(0);
                }
                XSSFCell dateCell13 = dateRow.createCell(12);
                if(repaymentModels.get(i).getRedRealAmount() != null){
                    dateCell13.setCellValue(repaymentModels.get(i).getRedRealAmount().doubleValue());
                }else {
                    dateCell13.setCellValue(0);
                }
                XSSFCell dateCell14 = dateRow.createCell(13);
                if(repaymentModels.get(i).getRedPackageType() != null){
                    if(repaymentModels.get(i).getRedPackageType() == 1000){
                        dateCell14.setCellValue("加息红包");
                    }else if(repaymentModels.get(i).getRedPackageType() == 1010){
                        dateCell14.setCellValue("返现红包");
                    }
                }


                XSSFCell dateCell15 = dateRow.createCell(14);
                if(repaymentModels.get(i).getVipRate() != null){
                    dateCell15.setCellValue(repaymentModels.get(i).getVipRate().doubleValue());
                }
                XSSFCell dateCell16 = dateRow.createCell(15);
                if(repaymentModels.get(i).getVipPlanAmount() != null){
                    dateCell16.setCellValue(repaymentModels.get(i).getVipPlanAmount().doubleValue());
                }else {
                    dateCell16.setCellValue(0);
                }
                XSSFCell dateCell17 = dateRow.createCell(16);
                if(repaymentModels.get(i).getVipRealAmount() != null){
                    dateCell17.setCellValue(repaymentModels.get(i).getVipRealAmount().doubleValue());
                }else {
                    dateCell17.setCellValue(0);
                }


                XSSFCell dateCell18 = dateRow.createCell(17);
                if(repaymentModels.get(i).getPfPlanAmount() != null){
                    dateCell18.setCellValue(repaymentModels.get(i).getPfPlanAmount().doubleValue());
                }else {
                    dateCell18.setCellValue(0);
                }
                XSSFCell dateCell19 = dateRow.createCell(18);
                if(repaymentModels.get(i).getPfRealAmount() != null){
                    dateCell19.setCellValue(repaymentModels.get(i).getPfRealAmount().doubleValue());
                }else {
                    dateCell19.setCellValue(0);
                }
                XSSFCell dateCell20 = dateRow.createCell(19);
                if(repaymentModels.get(i).getPfType() != null){
                    if(repaymentModels.get(i).getPfType() == 100){
                        dateCell20.setCellValue("首购加息");
                    }else if(repaymentModels.get(i).getPfType() == 200){
                        dateCell20.setCellValue("限时加息");
                    }else if(repaymentModels.get(i).getPfType() == 300){
                        dateCell20.setCellValue("项目加息");
                    }
                }
            }
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=repaymentDetail.xls");//默认Excel名称
            response.flushBuffer();
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
        }
    }

}
