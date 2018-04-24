package com.facebank.usersupport.attendance.service.impl;

import com.facebank.usersupport.attendance.dto.AcountDto;
import com.facebank.usersupport.attendance.dto.reqDto.AcountVo;
import com.facebank.usersupport.attendance.mapper.AcountMapper;
import com.facebank.usersupport.attendance.service.IAcountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考勤统计
 * @author zhanguo.huang
 * @date 2018-04-11
 */
@Service
public class AcountServiceImpl implements IAcountService {

    @Autowired
    private AcountMapper acountMapper;

    /**
     * 分页获取考勤
     * @param pageSize
     * @param pageNumber
     * @param acountVo
     * @return
     */
    @Override
    public PageInfo getAcountByPage(int pageSize, int pageNumber, AcountVo acountVo) {
        PageHelper.startPage(pageNumber,pageSize);
        List<AcountDto> acountDtoList = acountMapper.acountAll(acountVo);
        PageInfo<AcountDto> pageInfo =new PageInfo<>(acountDtoList);
        return pageInfo;
    }
}
