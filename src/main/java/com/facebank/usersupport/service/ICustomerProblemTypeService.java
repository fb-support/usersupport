package com.facebank.usersupport.service;

import com.facebank.usersupport.model.CustomerProblemTypeModel;
import com.facebank.usersupport.model.RestModel;

import java.util.List;

/**
 * @author JJ
 * @create
 **/
public interface ICustomerProblemTypeService {

    List<CustomerProblemTypeModel> getRootType();

    List<CustomerProblemTypeModel> findTypeByParentId(Long parentId);

    RestModel initPrombleType(Long typeId);

}
