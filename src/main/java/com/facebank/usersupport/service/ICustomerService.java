package com.facebank.usersupport.service;

import com.facebank.usersupport.dto.CustomerIdDto;
import com.facebank.usersupport.model.CustomerProblemDescriptionModel;
import com.facebank.usersupport.model.CustomerProblemModel;
import com.facebank.usersupport.model.CustomerServiceModel;
import com.facebank.usersupport.model.RestModel;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: quanli
 * @Date: 2018/3/27 10:39
 * @Description:
 */
public interface ICustomerService {


    RestModel selectServiceByCondition(String phoneNumber, String workName, Integer status, Long beginTime, Long endTime, String draw);

    RestModel insertService(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile file[],
                            CustomerProblemDescriptionModel customerProblemDescription, Long beginTime, Long endTime, String solve);

    RestModel updateService(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile file[],
                            CustomerProblemDescriptionModel customerProblemDescription, String beginTime, String endTime, String solve, CustomerIdDto customerIdDto);

    RestModel updateServiceByNewSolve(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile file[],
                                      CustomerProblemDescriptionModel customerProblemDescription, String beginTime, String endTime, String solve, CustomerIdDto customerIdDto);


    RestModel findProblemById(Long id);
}
