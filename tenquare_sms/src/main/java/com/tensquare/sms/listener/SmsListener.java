package com.tensquare.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author dongcheng
 * create date 2019/3/7
 **/
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @RabbitHandler
    public void executeSms(Map<String, String> map){

        String mobile = map.get("mobile");
        String code = map.get("code");
        System.out.println("手机号码:"+mobile);
        System.out.println("验证码:"+code);
    }

}
