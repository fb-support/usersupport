package com.facebank.usersupport.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.reqDto.RepaymentForm;
import com.facebank.usersupport.model.PageBeanModel;
import com.facebank.usersupport.model.RepaymentModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.IRepaymentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 还款查询业务Controller
 *
 * @author NingKui
 * @date 2018/3/9 10:42
 **/
@RestController
public class RepaymentController extends BaseController {

    @Autowired
    private IRepaymentService repaymentService;

    /**
     * 根据手机号、用户名、还款日期组合条件查询还款信息
     *
     * @param repaymentForm
     * @return
     */
    @PostMapping("/service/repayment")
    public RestModel repaymentSearch(RepaymentForm repaymentForm) {
        try {
            // userId和orderId都为空
            boolean isAllEmpty = StringUtils.isEmpty(repaymentForm.getOrderId()) && StringUtils.isEmpty(repaymentForm.getUserId());
            // 时间间隔不满足5天内
            boolean timeInterval = StringUtils.isEmpty(repaymentForm.getStartTime()) || StringUtils.isEmpty(repaymentForm.getEndTime()) || (repaymentForm.getEndTime() - repaymentForm.getStartTime() > 432000000L);
            // 用户ID不为空、订单ID不为空、间隔5天内这三者都不满足则直接返回参数错误
            if (isAllEmpty && timeInterval) {
                    return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
            }
            // 查询分页信息
            PageInfo<RepaymentModel> pageInfo = repaymentService.getRepaymentModelByRepaymenyForm(repaymentForm);
            // 封装PageBeanModel
            PageBeanModel pageBeanModel = new PageBeanModel();
            // 设置当前页
            pageBeanModel.setPage(repaymentForm.getPage());
            // 设置每页显示条数
            pageBeanModel.setPageSize(repaymentForm.getPageSize());
            // 设置总条数
            pageBeanModel.setTotalCount(pageInfo.getTotal());
            // 设置总页数
            pageBeanModel.setTotalPage(pageInfo.getPages());
            // 设置分页数据
            pageBeanModel.setData(pageInfo.getList());
            return this.success(pageBeanModel);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

}
