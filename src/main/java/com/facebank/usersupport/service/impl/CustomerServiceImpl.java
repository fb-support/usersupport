package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.dto.CustomerIdDto;
import com.facebank.usersupport.dto.CustomerServiceShowDto;
import com.facebank.usersupport.dto.reqDto.CustomerServiceForm;
import com.facebank.usersupport.mapper.usersupport.usersupport.*;
import com.facebank.usersupport.model.*;
import com.facebank.usersupport.service.ICustomerService;
import com.facebank.usersupport.util.ImgSaveUtil;
import com.facebank.usersupport.util.SessionUtil;
import com.facebank.usersupport.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: quanli
 * @Date: 2018/3/27 10:43
 * @Description:
 */
@Service
@Transactional//事物管理
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerServiceMapper customerServiceMapper;
    @Autowired
    private CustomerPictureMapper customerPictureMapper;
    @Autowired
    private CustomerProblemMapper customerProblemMapper;
    @Autowired
    private CustomerProblemDescriptionMapper customerProblemDescriptionMapper;
    @Autowired
    private CustomerServiceJournalMapper customerServiceJournalMapper;
    @Autowired
    private CustomerProblemSolveMapper customerProblemSolveMapper;

    /**
     * 功能描述: 多表新增客服服务
     *
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @Override
    public RestModel insertService(CustomerServiceForm customerServiceForm, HttpSession session) {
        UserModel user = SessionUtil.getUser(session);
        //服务总表插入
        CustomerServiceModel customerService = new CustomerServiceModel();
        long serviceNo = Long.parseLong(getNum19());
        customerService.setServiceNo(serviceNo);//插入单号
        customerService.setPhoneType(customerServiceForm.getPhoneType());//插入手机类型
        customerService.setPhoneNumber(customerServiceForm.getPhoneNumber());//客户插入手机号
        customerService.setName(customerServiceForm.getName());//插入客户名
        customerService.setWorkerNumber(user.getWorkNumber());//插入客服工号
        customerService.setStatus(customerServiceForm.getStatus());//插入服务状态
        customerService.setGmtCreate(System.currentTimeMillis());//插入创建时间
        customerService.setGmtModified(System.currentTimeMillis());//创建更新时间
        int is_success = customerServiceMapper.insertSelective(customerService);//插入数据库
        if (is_success != 1) {
            return new RestModel("0", "插入服务总表失败");
        }
        //服务问题表插入
        CustomerProblemModel customerProblem = new CustomerProblemModel();
        customerProblem.setServiceId(customerService.getId());//插入服务表总表id
        customerProblem.setTypeId(customerServiceForm.getTypeId());//插入问题类型id
        customerProblem.setTitle(customerServiceForm.getTitle());//插入问题标题
        customerProblem.setGmtCreate(System.currentTimeMillis());//创建时间
        customerProblem.setGmtModified(System.currentTimeMillis());//更新时间
        is_success = customerProblemMapper.insertSelective(customerProblem);//插入问题表
        if (is_success != 1) {
            return new RestModel("0", "插入服务问题表失败");
        }
        Long customerPromblemId = customerProblem.getId();
        //问题详情表插入
        CustomerProblemDescriptionModel customerProblemDescription = new CustomerProblemDescriptionModel();
        customerProblemDescription.setProblemId(customerPromblemId);//插入问题表id
        customerProblemDescription.setDescription(customerServiceForm.getDescription());//插入问题描述
        customerProblemDescription.setGmtCreate(System.currentTimeMillis());//创建时间
        customerProblemDescription.setGmtModified(System.currentTimeMillis());//更新时间
        is_success = customerProblemDescriptionMapper.insertSelective(customerProblemDescription);//插入问题详情表
        if (is_success != 1) {
            return new RestModel("0", "插入服务问题详情表失败");
        }
        //插入问题解决
        CustomerProblemSolveModel customerProblemSolve = new CustomerProblemSolveModel();
        customerProblemSolve.setProblemId(customerPromblemId);//插入问题id
        customerProblemSolve.setDescription(customerServiceForm.getSolve());//插入问题处理内容
        customerProblemSolve.setGmtCreate(System.currentTimeMillis());//创建时间
        customerProblemSolve.setGmtModified(System.currentTimeMillis());//更新时间
        is_success = customerProblemSolveMapper.insertSelective(customerProblemSolve);
        if (is_success != 1) {
            return new RestModel("0", "插入服务处理表失败");
        }
        // 插入图片
        if (customerServiceForm.getFile() != null) {
            String path = "static/images/upload/";
            for (MultipartFile f : customerServiceForm.getFile()) {
                if (f != null) {
                    String targetImgName = ImgSaveUtil.uploadImg(f, path);//获取图片新名字和存储图片
                    CustomerPictureModel customerPicture = new CustomerPictureModel();
                    customerPicture.setProblemId(customerPromblemId);//插入问题表id
                    customerPicture.setPicUrl("/images/upload/" + targetImgName);//插入图片路径
                    customerPicture.setGmtCreate(System.currentTimeMillis());//创建时间
                    is_success = customerPictureMapper.insertSelective(customerPicture);
                    if (is_success != 1) {
                        return new RestModel("0", "插入服务图片表失败");
                    }
                }
            }
        }
        return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
    }

    @Override
    public RestModel updateService(CustomerServiceForm customerServiceForm, HttpSession session) {
        //服务总表更新，草稿状态提交至待处理状态
        UserModel user = SessionUtil.getUser(session);
        //服务总表插入
        CustomerServiceModel customerService = new CustomerServiceModel();
        customerService.setId(customerServiceForm.getServiceId());//设置服务id
        customerService.setPhoneType(customerServiceForm.getPhoneType());//插入手机类型
        customerService.setPhoneNumber(customerServiceForm.getPhoneNumber());//客户插入手机号
        customerService.setName(customerServiceForm.getName());//插入客户名
        customerService.setWorkerNumber(user.getWorkNumber());//插入客服工号
        customerService.setStatus(1);//插入服务状态
        customerService.setGmtModified(System.currentTimeMillis());//更新时间
        int is_success = customerServiceMapper.updateByPrimaryKeySelective(customerService);//更新总表
        if (is_success != 1) {
            return new RestModel("0", "更新服务总表失败");
        }
        //问题表更新
        CustomerProblemModel customerProblem = new CustomerProblemModel();
        customerProblem.setId(customerServiceForm.getQuestionId());
        customerProblem.setTypeId(customerServiceForm.getTypeId());//插入问题类型id
        customerProblem.setTitle(customerServiceForm.getTitle());//插入问题标题
        customerProblem.setGmtModified(System.currentTimeMillis());//更新时间
        is_success = customerProblemMapper.updateByPrimaryKeySelective(customerProblem);//更新问题表
        if (is_success != 1) {
            return new RestModel("0", "更新服务问题表失败");
        }
        //问题详情表更新
        CustomerProblemDescriptionModel customerProblemDescription = new CustomerProblemDescriptionModel();
        customerProblemDescription.setId(customerServiceForm.getQuestionId());//设置问题详情id
        customerProblemDescription.setDescription(customerServiceForm.getDescription());//插入问题描述
        customerProblemDescription.setGmtModified(System.currentTimeMillis());//更新时间
        is_success = customerProblemDescriptionMapper.updateByPrimaryKeySelective(customerProblemDescription);//更新问题详情表
        if (is_success != 1) {
            return new RestModel("0", "更新服务问题详情表失败");
        }
        //更新问题解决
        CustomerProblemSolveModel customerProblemSolve = new CustomerProblemSolveModel();
        customerProblemSolve.setId(customerServiceForm.getSolveId());//设置问题解决id
        customerProblemSolve.setDescription(customerServiceForm.getSolve());//插入问题处理内容
        customerProblemSolve.setGmtCreate(System.currentTimeMillis());//创建时间
        customerProblemSolve.setGmtModified(System.currentTimeMillis());//更新时间
        is_success = customerProblemSolveMapper.updateByPrimaryKeySelective(customerProblemSolve);//更新问题处理表
        if (is_success != 1) {
            return new RestModel("0", "更新服务处理表失败");
        }
        // 插入图片
        if (customerServiceForm.getFile() != null) {
            String path = "static/images/upload/";
            for (MultipartFile f : customerServiceForm.getFile()) {
                if (f != null) {
                    String targetImgName = ImgSaveUtil.uploadImg(f, path);//获取图片新名字和存储图片
                    CustomerPictureModel customerPicture = new CustomerPictureModel();
                    customerPicture.setProblemId(customerServiceForm.getProblemId());//插入问题表id
                    customerPicture.setPicUrl("/images/upload/" + targetImgName);//插入图片路径
                    customerPicture.setGmtCreate(System.currentTimeMillis());//创建时间
                    is_success = customerPictureMapper.insertSelective(customerPicture);
                    if (is_success != 1) {
                        return new RestModel("0", "插入服务图片表失败");
                    }
                }
            }
        }
        return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
    }

    @Override
    public RestModel updateServiceByNewSolve(CustomerServiceForm customerServiceForm,HttpSession session) {
        //服务总表插入
        CustomerServiceModel customerService = new CustomerServiceModel();
        customerService.setId(customerServiceForm.getServiceId());//设置服务id
        customerService.setStatus(2);//插入服务状态
        customerService.setGmtModified(System.currentTimeMillis());//更新时间
        int is_success = customerServiceMapper.updateByPrimaryKeySelective(customerService);//更新总表
        if (is_success != 1) {
            return new RestModel("0", "更新服务总表失败");
        }
        //插入问题解决
        String solve = customerServiceForm.getSolve();
        if (solve != null && solve.equals("")==false) {
            CustomerProblemSolveModel customerProblemSolve = new CustomerProblemSolveModel();
            customerProblemSolve.setProblemId(customerServiceForm.getProblemId());//插入问题id
            customerProblemSolve.setDescription(customerServiceForm.getSolve());//插入问题处理内容
            customerProblemSolve.setGmtCreate(System.currentTimeMillis());//创建时间
            customerProblemSolve.setGmtModified(System.currentTimeMillis());//更新时间
            is_success = customerProblemSolveMapper.insertSelective(customerProblemSolve);
            if (is_success != 1) {
                return new RestModel("0", "插入服务处理表失败");
            }
        }
        return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
    }

    @Override
    public RestModel selectServiceByCondition(String phoneNumber, String workName, Integer status, Long beginTime, Long endTime, String draw) {
        //获取服务列表
        status = StrUtil.parseStringToInt(status, -1);
        List<CustomerServiceShowDto> serviceJournalDtos = customerServiceMapper.selectServiceByCondition(phoneNumber, workName, status, beginTime, endTime);
        PageRestModel pageRestModel = new PageRestModel(
                draw,
                new Long(serviceJournalDtos.size() + ""),
                new Long(serviceJournalDtos.size() + ""),
                serviceJournalDtos
        );
        return new RestModel(pageRestModel);
    }

    @Override
    public RestModel findProblemById(Long id) {
        //根据id查看单个服务
        return new RestModel(customerProblemMapper.findProblemById(id));
    }

    public String getNum19() {
        //生成服务单号
        String numStr = "";
        String trandStr = String.valueOf((Math.random() * 9 + 1) * 1000000);
        String dataStr = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        numStr = trandStr.toString().substring(0, 4);
        numStr = numStr + dataStr;
        return numStr;
    }
}
