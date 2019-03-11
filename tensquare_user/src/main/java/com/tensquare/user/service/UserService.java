package com.tensquare.user.service;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author dongcheng
 * create date 2019/3/5
 **/
@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findAll(){
        return userDao.findAll();
    }

    /**
     * 生成验证码发送信息
     * */
    public String sendCheckCode(String mobile){
        if(redisTemplate.opsForValue().get("checkcode_"+mobile)==null){
            //生成六位的随机数
            String code = RandomStringUtils.randomNumeric(6);
            redisTemplate.opsForValue().set("checkcode_"+mobile,code,5, TimeUnit.MINUTES);
            Map<String,String> map = new HashMap<String,String>();
            map.put("mobile",mobile);
            map.put("checkcode",code);
            rabbitTemplate.convertAndSend("sms",map);
            return code;
        }
        return (String) redisTemplate.opsForValue().get("checkcode_"+mobile);
    }

    public void register(String mobile, String code) throws Exception {
        if(redisTemplate.opsForValue().get("checkcode_"+mobile)==null){
            throw new Exception("验证码已过期，请重新获取");
        }
        String checkcode = (String) redisTemplate.opsForValue().get("checkcode_"+mobile);
        if( code == null || "".equals(code)){
            throw new Exception("请输入验证码");
        }

        if (checkcode.equals(code)){
            throw new Exception("验证码错误");
        }
        User user = new User();
        user.setMobile(mobile);
        user.setId(idWorker.nextId()+"");
        //初始化密码
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        userDao.save(user);
    }

    public User login(String mobile,String password){

        if (mobile !=null && !"".equals(mobile) && password !=null && !"".equals(password)){
            User user = userDao.findByMobile(mobile);
            if (user != null){
                password = bCryptPasswordEncoder.encode(password);
                if (user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }

    public void delete(String id){
        userDao.deleteById(id);
    }

    public void updatefanscountandfollowcount(String userid, String friendid, int x){
        //更新粉丝数
        userDao.incFanscount(friendid,x);
        //更新关注数
        userDao.updatefollowcount(userid,x);
    }

}
