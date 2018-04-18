package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.CustomerServiceJournalModel;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServiceJournalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerServiceJournalModel record);

    int insertSelective(CustomerServiceJournalModel record);

    CustomerServiceJournalModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerServiceJournalModel record);

    int updateByPrimaryKey(CustomerServiceJournalModel record);

    int updateByServiceId(CustomerServiceJournalModel record);
}