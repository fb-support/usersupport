package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.CustomerProblemSolveModel;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProblemSolveMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerProblemSolveModel record);

    int insertSelective(CustomerProblemSolveModel record);

    CustomerProblemSolveModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerProblemSolveModel record);

    int updateByPrimaryKey(CustomerProblemSolveModel record);
}