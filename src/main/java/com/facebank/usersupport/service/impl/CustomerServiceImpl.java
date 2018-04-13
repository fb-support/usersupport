package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.dto.*;
import com.facebank.usersupport.mapper.usersupport.usersupport.*;
import com.facebank.usersupport.model.*;
import com.facebank.usersupport.service.ICustomerService;
import com.facebank.usersupport.util.ImgSaveUtil;
import com.facebank.usersupport.util.PageUtil;
import com.facebank.usersupport.util.StrUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
@Transactional
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

    @Override
    public List<CustomerServiceModel> getService(String phoneNumber, Integer workerNumber, Integer status, Long gmtCreate) {

        CustomerServiceModel customerServiceModel = new CustomerServiceModel();
        customerServiceModel.setPhoneNumber(phoneNumber);
        customerServiceModel.setWorkerNumber(workerNumber);
        customerServiceModel.setStatus(status);
        customerServiceModel.setGmtCreate(gmtCreate);
        return customerServiceMapper.selectService(customerServiceModel);
    }

    @Override
    public List<ServiceJournalDto> getServicePhone(String phoneNumber, Integer workerNumber, Integer status, Long beginTime, Long endTime) {
        return customerServiceMapper.selectServicePhone(phoneNumber, workerNumber, status, beginTime, endTime);
    }

    @Override
    public List<ServiceShowDto> getServiceShow(Long id) {
        return customerServiceMapper.selectServiceShow(id);
    }

    @Override
    public RestModel insertService(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile[] file, CustomerProblemDescriptionModel customerProblemDescription, Long beginTime, Long endTime, String solve) {
        //服务总表插入
        customerService.setGmtCreate(System.currentTimeMillis());
        long serviceNo = Long.parseLong(getNum19());
        customerService.setServiceNo(serviceNo);
        customerServiceMapper.insertSelective(customerService);
        //服务流水表
        CustomerServiceJournalModel customerServiceJournal = new CustomerServiceJournalModel();
        customerServiceJournal.setBeginTime(beginTime);
        customerServiceJournal.setEndTime(endTime);
        customerServiceJournal.setGmtCreate(System.currentTimeMillis());
        customerServiceJournal.setWorkerNumber(customerService.getWorkerNumber());
        customerServiceJournal.setServiceId(customerService.getId());
        customerServiceJournal.setName(customerService.getName());
        customerServiceJournalMapper.insertSelective(customerServiceJournal);
        //问题表插入
        customerProblem.setServiceId(customerService.getId());
        customerProblem.setGmtModified(System.currentTimeMillis());
        customerProblem.setGmtCreate(System.currentTimeMillis());
        customerProblemMapper.insertSelective(customerProblem);
        //问题详情表插入
        customerProblemDescription.setProblemId(customerProblem.getId());
        customerProblemDescription.setGmtCreate(System.currentTimeMillis());
        customerProblemDescriptionMapper.insertSelective(customerProblemDescription);
        // 插入图片
        String path = "static/images/upload/";
        for (MultipartFile f : file) {
            if (f != null) {
                String targetImgName = ImgSaveUtil.uploadImg(f, path);
                CustomerPictureModel customerPicture = new CustomerPictureModel();
                customerPicture.setPicUrl("/images/upload/" + targetImgName);
                customerPicture.setGmtCreate(System.currentTimeMillis());
                customerPicture.setProblemId(customerProblem.getId());
                customerPictureMapper.insertSelective(customerPicture);
            }
        }
        //插入问题解决
        CustomerProblemSolveModel customerProblemSolve = new CustomerProblemSolveModel();
        customerProblemSolve.setDescription(solve);
        customerProblemSolve.setGmtCreate(System.currentTimeMillis());
        customerProblemSolve.setProblemId(customerProblem.getId());
        customerProblemSolveMapper.insertSelective(customerProblemSolve);
        return new RestModel("提交成功");
    }

    @Override
    public RestModel updateService(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile[] file, CustomerProblemDescriptionModel customerProblemDescription, String beginTime, String endTime, String solve, CustomerIdDto customerIdDto) {

        //服务总表更新
        customerService.setGmtCreate(System.currentTimeMillis());
//        customerService.setStatus(0);
        customerService.setId(customerIdDto.getServiceId());
        customerService.setGmtModified(System.currentTimeMillis());
        customerServiceMapper.updateByPrimaryKeySelective(customerService);
        //服务流水表
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = null;
        long begintime = 0;
        long endtime = 0;
        if (beginTime != "" && beginTime != null) {
            try {
                time1 = sdf.parse(beginTime);
                begintime = time1.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Date time2 = null;
        if (endTime != "" && endTime != null) {
            try {
                time2 = sdf.parse(endTime);
                endtime = time2.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        CustomerServiceJournalModel customerServiceJournal = new CustomerServiceJournalModel();
        customerServiceJournal.setBeginTime(begintime);
        customerServiceJournal.setEndTime(endtime);
        customerServiceJournal.setGmtCreate(System.currentTimeMillis());
        customerServiceJournal.setWorkerNumber(customerService.getWorkerNumber());
        customerServiceJournal.setServiceId(customerService.getId());
        customerServiceJournal.setName(customerService.getName());
        customerProblem.setGmtModified(System.currentTimeMillis());
        customerServiceJournalMapper.insertSelective(customerServiceJournal);
        //问题表更新
        customerProblem.setId(customerIdDto.getProblemId());
        customerProblemDescription.setProblemId(customerProblem.getId());
        customerProblemMapper.updateByPrimaryKeySelective(customerProblem);
        //问题详情表更新
        customerProblemDescription.setId(customerIdDto.getQuestionId());
        customerProblemDescription.setGmtModified(System.currentTimeMillis());
        customerProblemDescriptionMapper.updateByPrimaryKeySelective(customerProblemDescription);
        // 插入图片
        String path = "static/images/upload/";
        for (MultipartFile f : file) {
            if (f != null) {
                String targetImgName = ImgSaveUtil.uploadImg(f, path);
                CustomerPictureModel customerPicture = new CustomerPictureModel();
                customerPicture.setPicUrl("/images/upload/" + targetImgName);
                customerPicture.setGmtCreate(System.currentTimeMillis());
                customerPicture.setProblemId(customerProblem.getId());
                customerPictureMapper.insertSelective(customerPicture);
            }
        }
        //更新问题解决
        CustomerProblemSolveModel customerProblemSolve = new CustomerProblemSolveModel();
        if (customerService.getStatus() != 2) {
            customerProblemSolve.setId(customerIdDto.getSolveId());
        }
        customerProblemSolve.setDescription(solve);
        customerProblemSolve.setGmtModified(System.currentTimeMillis());
        customerProblemSolve.setProblemId(customerProblem.getId());
        customerProblemSolveMapper.updateByPrimaryKeySelective(customerProblemSolve);
        return new RestModel("更新成功");
    }

    @Override
    public RestModel updateServiceByNewSolve(CustomerServiceModel customerService, CustomerProblemModel customerProblem, MultipartFile[] file, CustomerProblemDescriptionModel customerProblemDescription, String beginTime, String endTime, String solve, CustomerIdDto customerIdDto) {
        //服务总表待处理状态更新
        customerService.setGmtCreate(System.currentTimeMillis());
//        customerService.setStatus(0);
        customerService.setId(customerIdDto.getServiceId());
        customerServiceMapper.updateByPrimaryKeySelective(customerService);
        //服务流水表
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = null;
        long begintime = 0;
        long endtime = 0;
        if (beginTime != "" && beginTime != null) {
            try {
                time1 = sdf.parse(beginTime);
                begintime = time1.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Date time2 = null;
        if (endTime != "" && endTime != null) {
            try {
                time2 = sdf.parse(endTime);
                endtime = time2.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        CustomerServiceJournalModel customerServiceJournal = new CustomerServiceJournalModel();
        customerServiceJournal.setBeginTime(begintime);
        customerServiceJournal.setEndTime(endtime);
        customerServiceJournal.setGmtCreate(System.currentTimeMillis());
        customerServiceJournal.setWorkerNumber(customerService.getWorkerNumber());
        customerServiceJournal.setServiceId(customerService.getId());
        customerServiceJournal.setName(customerService.getName());
        customerServiceJournalMapper.insertSelective(customerServiceJournal);
        //问题表更新
        customerProblem.setId(customerIdDto.getProblemId());
        customerProblemDescription.setProblemId(customerProblem.getId());
        customerProblem.setGmtModified(System.currentTimeMillis());
        customerProblemMapper.updateByPrimaryKeySelective(customerProblem);
        //问题详情表更新
        customerProblemDescription.setId(customerIdDto.getQuestionId());
        customerProblemDescription.setGmtModified(System.currentTimeMillis());

        customerProblemDescriptionMapper.updateByPrimaryKeySelective(customerProblemDescription);
        // 插入图片
        String path = "static/images/upload/";
        for (MultipartFile f : file) {
            if (f != null) {
                String targetImgName = ImgSaveUtil.uploadImg(f, path);
                CustomerPictureModel customerPicture = new CustomerPictureModel();
                customerPicture.setPicUrl("/images/upload/" + targetImgName);
                customerPicture.setGmtCreate(System.currentTimeMillis());
                customerPicture.setProblemId(customerProblem.getId());
                customerPictureMapper.insertSelective(customerPicture);
            }
        }
        //更新问题解决
        CustomerProblemSolveModel customerProblemSolve = new CustomerProblemSolveModel();
        if (customerService.getStatus() != 2) {
            customerProblemSolve.setId(customerIdDto.getSolveId());
        }
        customerProblemSolve.setDescription(solve);
        customerProblemSolve.setGmtCreate(System.currentTimeMillis());
        customerProblemSolve.setProblemId(customerProblem.getId());
        customerProblemSolveMapper.insertSelective(customerProblemSolve);
        return new RestModel("更新成功");
    }

    @Override
    public RestModel selectServiceByCondition(String phoneNumber, String workName, Integer status, Long beginTime, Long endTime, String draw) {
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


    public String getNum19() {
        String numStr = "";
        String trandStr = String.valueOf((Math.random() * 9 + 1) * 1000000);
        String dataStr = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        numStr = trandStr.toString().substring(0, 4);
        numStr = numStr + dataStr;
        return numStr;
    }

    @Override
    public int updateServiceProblem(Long id, Long problemId, String phoneNumber, String phoneType, String name, Long problemType, String title, String description, Integer status) {
        //服务总表
        CustomerServiceModel customerServiceModel = new CustomerServiceModel();
        customerServiceModel.setId(id);
        customerServiceModel.setPhoneNumber(phoneNumber);
        customerServiceModel.setName(name);
        customerServiceModel.setPhoneType(phoneType);
        customerServiceModel.setStatus(status);
        customerServiceModel.setGmtModified(System.currentTimeMillis());
        customerServiceMapper.updateByPrimaryKeySelective(customerServiceModel);
        //服务问题总表
        CustomerProblemModel customerProblemModel = new CustomerProblemModel();
        customerProblemModel.setId(problemId);
        customerProblemModel.setTypeId(problemType);
        customerProblemModel.setTitle(title);
        customerServiceModel.setGmtModified(System.currentTimeMillis());
        customerProblemMapper.updateByPrimaryKeySelective(customerProblemModel);
        //问题描述表
        CustomerProblemDescriptionModel customerProblemDescriptionModel = new CustomerProblemDescriptionModel();
        customerProblemDescriptionModel.setProblemId(problemId);
        customerProblemDescriptionModel.setDescription(description);
        customerProblemDescriptionModel.setGmtModified(System.currentTimeMillis());
        customerProblemDescriptionMapper.updateByPrimaryKeyByProblemId(customerProblemDescriptionModel);
        //服务通话流水表
        CustomerServiceJournalModel customerServiceJournalModel = new CustomerServiceJournalModel();
        customerServiceJournalModel.setServiceId(id);
        customerServiceJournalModel.setPhoneNumber(phoneNumber);
        customerServiceJournalModel.setName(name);
        customerServiceJournalModel.setGmtModified(System.currentTimeMillis());
        customerServiceJournalMapper.updateByServiceId(customerServiceJournalModel);
        return 1;
    }

    @Override
    public RestModel findProblemById(Long id) {
        return new RestModel(customerProblemMapper.findProblemById(id));
    }
}
