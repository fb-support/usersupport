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
 *
 */
@Service
public class AcountServiceImpl implements IAcountService {

    @Autowired
    private AcountMapper acountMapper;

    /**
     * 分页获取考勤
     * @param
     * @param acountVo
     * @return
     */
    @Override
    public PageInfo<AcountDto> getAcountByPage(AcountVo acountVo) {
        PageHelper.startPage(acountVo.getPageNumber(),acountVo.getPageSize());
        List<AcountDto> acountDtoList = acountMapper.acountAll(acountVo);

        PageInfo<AcountDto> pageInfo =new PageInfo<>(acountDtoList);

        return pageInfo;
    }
}
