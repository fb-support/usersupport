package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.dto.CustomerProblemDto;
import com.facebank.usersupport.model.CustomerProblemModel;
import com.facebank.usersupport.model.RestModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerProblemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerProblemModel record);

    int insertSelective(CustomerProblemModel record);

    CustomerProblemModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerProblemModel record);

    int updateByPrimaryKey(CustomerProblemModel record);

    List<CustomerProblemDto> findProblemById(Long id);
    List<RestModel> selectPersonById();
}