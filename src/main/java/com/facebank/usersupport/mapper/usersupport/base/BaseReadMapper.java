package com.facebank.usersupport.mapper.usersupport.base;

import org.apache.ibatis.session.RowBounds;
import org.jboss.logging.Param;

import java.io.Serializable;
import java.util.List;

/**
 * BaseReadMapper
 *
 * @author hailong.Yang
 * @create 2018-03-05 下午3:45
 **/
public interface BaseReadMapper<T, ID extends Serializable> {

    T selectByPrimaryKey(ID id);

    List<T> selectAll(RowBounds rowBounds);

    List<T> selectAll(RowBounds rowBounds, Long offsetId);

    List<T> selectAll();

}
