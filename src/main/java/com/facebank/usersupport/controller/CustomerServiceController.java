package com.facebank.usersupport.controller;

import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.CustomerIdDto;
import com.facebank.usersupport.dto.ServiceJournalDto;
import com.facebank.usersupport.dto.ServiceShowDto;
import com.facebank.usersupport.model.CustomerProblemDescriptionModel;
import com.facebank.usersupport.model.CustomerProblemModel;
import com.facebank.usersupport.model.CustomerServiceModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.ICustomerProblemTypeService;
import com.facebank.usersupport.service.ICustomerService;
import com.facebank.usersupport.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: quanli
 * @Date: 2018/3/27 10:51
 * @Description:
 */
@RestController
public class CustomerServiceController extends BaseController {
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    ICustomerProblemTypeService customerProblemTypeService;

    @RequestMapping("/customer/add")
    public RestModel customAdd(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile file[], HttpSession session,
                               CustomerProblemDescriptionModel customerProblemDescription, Long beginTime, Long endTime, Integer staats, String solve){
        customerService.setStatus(staats);
        customerService.setWorkerNumber(SessionUtil.getUser(session).getWorkNumber());
        return iCustomerService.insertService(customerService,customerProblem,file,customerProblemDescription,beginTime,endTime,solve);
    }
    @RequestMapping("/customer/update")
    public RestModel customUpdate(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile file[], HttpSession session,
                                  CustomerProblemDescriptionModel customerProblemDescription, String beginTime, String endTime, Integer staats, String solve, CustomerIdDto customerIdDto){
        customerService.setStatus(staats);
        customerService.setWorkerNumber(SessionUtil.getUser(session).getWorkNumber());
        return iCustomerService.updateService(customerService,customerProblem,file,customerProblemDescription,beginTime,endTime,solve,customerIdDto);
    }

    @RequestMapping("/customer/updateSolve")
    public RestModel customUpdateNewSolve(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile file[], HttpSession session,
                                          CustomerProblemDescriptionModel customerProblemDescription, String beginTime, String endTime, Integer staats, String solve, CustomerIdDto customerIdDto){
        customerService.setStatus(staats);
        customerService.setWorkerNumber(SessionUtil.getUser(session).getWorkNumber());
        return iCustomerService.updateServiceByNewSolve(customerService,customerProblem,file,customerProblemDescription,beginTime,endTime,solve,customerIdDto);
    }

    @RequestMapping("/customer/getService")
    public RestModel getService(String phoneNumber, Integer workerNumber, Integer status, Long gmtCreate){
            List<CustomerServiceModel> customerServiceModels = iCustomerService.getService(phoneNumber,workerNumber,status,gmtCreate);
            return this.success(customerServiceModels);
    }

    @RequestMapping("/customer/getServicePhone")
    public RestModel getServicePhone(String phoneNumber, Integer workerNumber, Integer status, Long beginTime, Long endTime){
        List<ServiceJournalDto> customerServiceModels = iCustomerService.getServicePhone(phoneNumber,workerNumber,status,beginTime,endTime);
        return this.success(customerServiceModels);
    }

    @RequestMapping("/customer/getServiceByCondition")
    public RestModel getServiceByPhone(String phoneNumber, Integer workerNumber, Integer status, String beginTime, String endTime, String draw){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time1 = null;
        Date time2 = null;
        long begintime = 0;
        long endtime = 0;
        if(beginTime != "" && beginTime != null){
            try{
                time1 = sdf.parse(beginTime);
                begintime = time1.getTime();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(endTime != "" && endTime != null){
            try {
                time2 = sdf.parse(endTime);
                endtime = time2.getTime();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
      /*  if(endtime-begintime>432000000){return new RestModel("日期有误，无法查询");}*/
        try{
            RestModel restModel = iCustomerService.selectServiceByCondition(phoneNumber,workerNumber,status,begintime,endtime,draw);
            return restModel;
        }catch (Exception e){
            return this.excpRestModel();
        }
    }

    @RequestMapping("/customer/getServiceShow")
    public RestModel getServiceShow(Long id){
        List<ServiceShowDto> customerServiceModels = iCustomerService.getServiceShow(id);
        return this.success(customerServiceModels);
    }

    @RequestMapping("/customer/updateService")
    public RestModel updateService(Long id, Long problemId, String phoneNumber, String phoneType, String name, Long problemType, String title, String description, Integer status){
        iCustomerService.updateServiceProblem(id,problemId,phoneNumber,phoneType,name,problemType,title,description,status);
        return new RestModel();
    }

    @RequestMapping("/customer/detail")
    public RestModel detail(Long id){
        return iCustomerService.findProblemById(id);
    }

    /**
     *
     * 功能描述: 通过问题父类id获取下级问题列表
     *
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/customer/type")
    public RestModel findTypeByParentId(@RequestParam(value ="parentId",defaultValue="0") Long parentId){
        return new RestModel(customerProblemTypeService.findTypeByParentId(parentId));
    }
    /**
     *
     * 功能描述: 初始化问题列表
     *
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/customer/type_init")
    public RestModel typeInit(Long typeId){
        return customerProblemTypeService.initPrombleType(typeId);
    }
}
