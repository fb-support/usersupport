package com.facebank.usersupport.service;

import com.facebank.usersupport.dto.CustomerIdDto;
import com.facebank.usersupport.dto.reqDto.CustomerServiceForm;
import com.facebank.usersupport.model.CustomerProblemDescriptionModel;
import com.facebank.usersupport.model.CustomerProblemModel;
import com.facebank.usersupport.model.CustomerServiceModel;
import com.facebank.usersupport.model.RestModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @Auther: quanli
 * @Date: 2018/3/27 10:39
 * @Description:
 */
public interface ICustomerService {


    RestModel selectServiceByCondition(String phoneNumber, String workName, Integer status, Long beginTime, Long endTime, String draw);

    RestModel insertService(CustomerServiceForm customerServiceForm, HttpSession session);

    RestModel updateService(CustomerServiceForm customerServiceForm,HttpSession session);

    RestModel updateServiceByNewSolve(CustomerServiceForm customerServiceForm,HttpSession session);

    RestModel findProblemById(Long id);
}
