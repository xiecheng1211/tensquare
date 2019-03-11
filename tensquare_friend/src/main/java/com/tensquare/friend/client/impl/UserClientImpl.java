package com.tensquare.friend.client.impl;

import com.tensquare.friend.client.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



/**
 * @author dongcheng
 * create date 2019/3/11
 **/
@Component
public class UserClientImpl implements UserClient {

    Logger logger = LoggerFactory.getLogger(Logger.class);

    @Override
    public void updatefanscountandfllowcount(String userid, String friendid, int x) {
        logger.info("熔断器触发了");
    }
}
