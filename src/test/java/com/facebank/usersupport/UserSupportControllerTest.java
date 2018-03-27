package com.facebank.usersupport;

import com.alibaba.fastjson.JSON;
import com.facebank.usersupport.model.RestModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * test
 *
 * @author hailong.Yang
 * @create 2018-03-05 下午5:01
 **/

  public class UserSupportControllerTest {


    private static Logger logger = LoggerFactory.getLogger(UserSupportControllerTest.class);

    private static final String url = "http://localhost:8080/v1.0/usertest/getP2PUserMainByUserId";
    private RestTemplate restTemplate = new RestTemplate();


    @Test
    public void testGetUserMain(){
        RestModel restModel = restTemplate.getForObject(url, RestModel.class);
        logger.info(JSON.toJSONString(restModel));
    }



}
