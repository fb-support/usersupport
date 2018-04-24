package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.dto.CustomerTypeDto;
import com.facebank.usersupport.mapper.usersupport.usersupport.CustomerProblemTypeMapper;
import com.facebank.usersupport.model.CustomerProblemTypeModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.ICustomerProblemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JJ
 * @create
 **/
@Service
public class CustomerProblemTypeServiceImpl implements ICustomerProblemTypeService {
    @Autowired
    CustomerProblemTypeMapper customerProblemTypeMapper;
    @Override
    public List<CustomerProblemTypeModel> getRootType() {
        return customerProblemTypeMapper.getRootType();
    }

    @Override
    @Cacheable(key="#parentId",value="userCache")
    public List<CustomerProblemTypeModel> findTypeByParentId(Long parentId) {
        return customerProblemTypeMapper.findTypeByParentId(parentId);
    }

    @Override
    public RestModel initPrombleType(Long typeId) {
        CustomerTypeDto customerTypeDto  = customerProblemTypeMapper.initPrombleType(typeId);
        return new RestModel(customerTypeDto);
    }
}
