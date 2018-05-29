package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.dto.CustomerTypeDto;
import com.facebank.usersupport.model.CustomerProblemTypeModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerProblemTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerProblemTypeModel record);

    int insertSelective(CustomerProblemTypeModel record);

    CustomerProblemTypeModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerProblemTypeModel record);

    int updateByPrimaryKey(CustomerProblemTypeModel record);

    List<CustomerProblemTypeModel> getRootType();

    List<CustomerProblemTypeModel> findTypeByParentId(Long parentId);

    CustomerTypeDto initPrombleType(Long typeId);
}