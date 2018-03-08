package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.MenuModel;
import com.facebank.usersupport.model.MenuExampleModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuMapper {
    long countByExample(MenuExampleModel example);

    int deleteByExample(MenuExampleModel example);

    int deleteByPrimaryKey(Long menuId);

    int insert(MenuModel record);

    int insertSelective(MenuModel record);

    List<MenuModel> selectByExample(MenuExampleModel example);

    MenuModel selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") MenuModel record, @Param("example") MenuExampleModel example);

    int updateByExample(@Param("record") MenuModel record, @Param("example") MenuExampleModel example);

    int updateByPrimaryKeySelective(MenuModel record);

    int updateByPrimaryKey(MenuModel record);
}