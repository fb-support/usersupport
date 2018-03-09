package com.facebank.usersupport.mapper.usersupport.base;

import java.io.Serializable;
import java.util.List;

/**
 * BaseWriteMapper
 *
 * @author hailong.Yang
 * @create 2018-03-05 下午3:46
 **/
public interface BaseWriteMapper<T, ID extends Serializable> extends  BaseReadMapper<T, ID>{

    int deleteByPrimaryKey(ID id);

    int insert(T model);

    T selectByPrimaryKey(ID id);

    List<T> selectAll();

    int updateByPrimaryKey(T record);


}
