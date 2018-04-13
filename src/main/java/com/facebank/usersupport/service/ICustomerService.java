package com.facebank.usersupport.service;

import com.facebank.usersupport.dto.CustomerIdDto;
import com.facebank.usersupport.dto.ServiceJournalDto;
import com.facebank.usersupport.dto.ServiceShowDto;
import com.facebank.usersupport.model.CustomerProblemDescriptionModel;
import com.facebank.usersupport.model.CustomerProblemModel;
import com.facebank.usersupport.model.CustomerServiceModel;
import com.facebank.usersupport.model.RestModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Auther: quanli
 * @Date: 2018/3/27 10:39
 * @Description:
 */
public interface ICustomerService {

    List<CustomerServiceModel> getService(String phoneNumber, Integer workerNumber, Integer status, Long gmtCreate);

    List<ServiceJournalDto> getServicePhone(String phoneNumber, Integer workerNumber, Integer status, Long beginTime, Long endTime);
    RestModel selectServiceByCondition(String phoneNumber, Integer workerNumber, Integer status, Long beginTime, Long endTime, String draw);
    List<ServiceShowDto> getServiceShow(Long id);

    RestModel insertService(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile file[],
                            CustomerProblemDescriptionModel customerProblemDescription, Long beginTime, Long endTime, String solve);

    RestModel updateService(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile file[],
                            CustomerProblemDescriptionModel customerProblemDescription, String beginTime, String endTime, String solve, CustomerIdDto customerIdDto);

    RestModel updateServiceByNewSolve(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile file[],
                                      CustomerProblemDescriptionModel customerProblemDescription, String beginTime, String endTime, String solve, CustomerIdDto customerIdDto);

    int updateServiceProblem(Long id, Long problemId, String phoneNumber, String phoneType, String name, Long problemType, String title, String description, Integer Status);

    RestModel findProblemById(Long id);
}
