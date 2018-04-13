package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.CustomerProblemDescriptionModel;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProblemDescriptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerProblemDescriptionModel record);

    int insertSelective(CustomerProblemDescriptionModel record);

    CustomerProblemDescriptionModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerProblemDescriptionModel record);

    int updateByPrimaryKey(CustomerProblemDescriptionModel record);

    int updateByPrimaryKeyByProblemId(CustomerProblemDescriptionModel record);
}