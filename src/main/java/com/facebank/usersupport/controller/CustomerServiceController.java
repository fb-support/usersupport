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
import com.facebank.usersupport.util.TimeUtil;
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


    /**
     * 功能描述: 新增服务
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    @RequestMapping("/customer/add")
    public RestModel customAdd(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile file[], HttpSession session,
                               CustomerProblemDescriptionModel customerProblemDescription, Long beginTime, Long endTime,@RequestParam(name="status") Integer staats, String solve){
        customerService.setStatus(staats);
        System.out.println(customerService.toString());
        customerService.setWorkerNumber(SessionUtil.getUser(session).getWorkNumber());
        return iCustomerService.insertService(customerService,customerProblem,file,customerProblemDescription,beginTime,endTime,solve);
    }
    /**
     * 功能描述: 服务修改，草稿状态提交至待处理状态
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    @RequestMapping("/customer/update")
    public RestModel customUpdate(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile file[], HttpSession session,
                                  CustomerProblemDescriptionModel customerProblemDescription, String beginTime, String endTime, Integer staats, String solve, CustomerIdDto customerIdDto){
        customerService.setStatus(staats);
        customerService.setWorkerNumber(SessionUtil.getUser(session).getWorkNumber());
        return iCustomerService.updateService(customerService,customerProblem,file,customerProblemDescription,beginTime,endTime,solve,customerIdDto);
    }
    /**
     * 功能描述: 服务修改，待处理状态提交至已处理状态
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    @RequestMapping("/customer/updateSolve")
    public RestModel customUpdateNewSolve(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile file[], HttpSession session,
                                          CustomerProblemDescriptionModel customerProblemDescription, String beginTime, String endTime, Integer staats, String solve, CustomerIdDto customerIdDto){
        customerService.setStatus(staats);
        customerService.setWorkerNumber(SessionUtil.getUser(session).getWorkNumber());
        return iCustomerService.updateServiceByNewSolve(customerService,customerProblem,file,customerProblemDescription,beginTime,endTime,solve,customerIdDto);
    }
    /**
     *
     * 功能描述: 根据电话号码，客服名称，处理状态以及时间段获取服务列表
     *
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    @RequestMapping("/customer/getServiceByCondition")
    public RestModel getServiceByPhone(String phoneNumber, String workName, Integer status, Long beginTime, Long endTime, String draw){
        System.out.println(beginTime+"   "+endTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time1 = null; //TimeUtil.LongToDate(beginTime,sdf);
        Date time2 = null; //TimeUtil.LongToDate(endTime,sdf);
        if(beginTime != null){
            try{
                time1 = TimeUtil.LongToDate(beginTime,sdf);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(endTime != null){
            try {
                time2 = TimeUtil.LongToDate(endTime,sdf);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        /*long begintime = 0;
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
        System.out.println(workName+"   "+begintime+"  "+endtime);*/
      /*  if(endtime-begintime>432000000){return new RestModel("日期有误，无法查询");}*/
        try{
            RestModel restModel = iCustomerService.selectServiceByCondition(phoneNumber,workName,status,time1,time2,draw);
            System.out.println(restModel.toString());
            return restModel;
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel();
        }
    }
    /**
     * 功能描述: 根据服务id获取单个服务信息
     * @param:
     * @return:
     * @auther:
     * @date:
     */
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
