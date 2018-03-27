package com.facebank.usersupport.online.process.mapper.base;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;

/**
 * BaseReadMapper
 *
 * @author hailong.Yang
 * @create 2018-03-05 下午3:45
 **/
public interface BaseReadMapper<T, ID extends Serializable> {

    List<T> selectAll(RowBounds rowBounds);

    List<T> selectAll(RowBounds rowBounds, Long offsetId);

    List<T> selectAll();

}
