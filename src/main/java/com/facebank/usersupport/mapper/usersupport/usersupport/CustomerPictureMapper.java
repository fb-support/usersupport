package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.CustomerPictureModel;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPictureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerPictureModel record);

    int insertSelective(CustomerPictureModel record);

    CustomerPictureModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerPictureModel record);

    int updateByPrimaryKey(CustomerPictureModel record);
}