package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.dto.CustomerServiceShowDto;
import com.facebank.usersupport.dto.ServiceJournalDto;
import com.facebank.usersupport.dto.ServiceShowDto;
import com.facebank.usersupport.model.CustomerServiceModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerServiceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerServiceModel record);

    int insertSelective(CustomerServiceModel record);

    CustomerServiceModel selectByPrimaryKey(Long id);

    CustomerServiceModel selectByServiceNo(Long serviceNo);

    int updateByPrimaryKeySelective(CustomerServiceModel record);

    int updateByPrimaryKey(CustomerServiceModel record);


    List<CustomerServiceShowDto> selectServiceByCondition(@Param("phoneNumber") String phoneNumber, @Param("workName") String workName,
                                                          @Param("status") Integer status,
                                                          @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
    Integer selectCountByCondition(@Param("phoneNumber") String phoneNumber, @Param("workerNumber") Integer workNumber,
                                   @Param("status") Integer status,
                                   @Param("beginTime") Long beginTime, @Param("endTime") Long endTime);


    List<CustomerServiceModel> selectService(CustomerServiceModel record);
    List<ServiceJournalDto> selectServicePhone(@Param("phoneNumber") String phoneNumber, @Param("workerNumber") Integer workNumber,
                                               @Param("status") Integer status,
                                               @Param("beginTime") Long beginTime, @Param("endTime") Long endTime);

    List<ServiceShowDto> selectServiceShow(@Param("id") Long id);
}