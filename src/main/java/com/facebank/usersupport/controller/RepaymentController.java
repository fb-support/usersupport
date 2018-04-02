package com.facebank.usersupport.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.reqDto.RepaymentForm;
import com.facebank.usersupport.mapper.usersupport.p2p.UserMainP2PReadMapper;
import com.facebank.usersupport.model.PageBeanModel;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RepaymentModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.IRepaymentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private UserMainP2PReadMapper userMainP2PReadMapper;

    /**
     * 根据手机号、用户名、还款日期组合条件查询还款信息
     *
     * @param repaymentForm
     * @return
     */
    @PostMapping("/service/repayment")
    public RestModel repaymentSearch(RepaymentForm repaymentForm,String draw) {
        try {
            // 手机号和orderId都为空
            boolean isAllEmpty = StringUtils.isEmpty(repaymentForm.getOrderId()) && StringUtils.isEmpty(repaymentForm.getMobile());
            // 开始时间大于结束时间
            boolean timeInterval = !StringUtils.isEmpty(repaymentForm.getStartTime()) && !StringUtils.isEmpty(repaymentForm.getEndTime()) && (repaymentForm.getEndTime() < repaymentForm.getStartTime());
            // 用户ID与订单ID都为空或者开始时间大于结束时间则直接返回参数错误
            if (isAllEmpty || timeInterval) {
                    return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
            }
            if(!StringUtils.isEmpty(repaymentForm.getMobile())){
                Long userId = userMainP2PReadMapper.selectUserIdByMobile(repaymentForm.getMobile());
                repaymentForm.setUserId(userId);
            }
            // 查询还款信息
            List<RepaymentModel> repaymentModels = repaymentService.getRepaymentModelByRepaymentForm(repaymentForm);

            PageRestModel pageRestModel = new PageRestModel(
                    draw,
                    new Long(repaymentModels.size()+""),
                    new Long(repaymentModels.size()+""),
                    repaymentModels
            );

            return this.success(pageRestModel);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

}
