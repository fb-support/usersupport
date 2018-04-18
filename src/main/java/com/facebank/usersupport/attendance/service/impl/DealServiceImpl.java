package com.facebank.usersupport.attendance.service.impl;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.mapper.DealMapper;
import com.facebank.usersupport.attendance.model.DealModel;
import com.facebank.usersupport.attendance.service.IDealService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanguo.huang
 * @date 2018-04-02
 */
@Service
public class DealServiceImpl implements IDealService {
    @Autowired
    private DealMapper dealMapper;
    @Override
    public PageInfo selectByPage(int pageSize, int pageNumber, QueryVo queryVo) {
        PageHelper.startPage(pageNumber,pageSize);
        List<DealModel> dealModels = dealMapper.getDealModels(queryVo);
        PageInfo<DealModel> pageInfo =new PageInfo<>(dealModels);
        return pageInfo;
    }
}
