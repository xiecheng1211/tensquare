package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import com.tensquare.friend.service.NoFriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dongcheng
 * create date 2019/3/11
 **/
@RestController
@RequestMapping("/friend")
@CrossOrigin
public class FriendController {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private FriendService friendService;
    @Autowired
    private NoFriendService noFriendService;
    @Autowired
    private UserClient userClient;


    @RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid, @PathVariable String type){
        Claims claims = (Claims)httpServletRequest.getAttribute("claims_user");
        if (claims == null){
            return new Result(false, StatusCode.ACCESSERROR,"权限不足,无法访问");
        }
        if ("1".equals(type)){
            //还不是好友
            if(friendService.addFriend(claims.getId(),friendid) == 0){
                return new Result(false,StatusCode.REPERROR,"您已经添加过了好友");
            }else if (friendService.addFriend(claims.getId(),friendid) == 1){
                userClient.updatefanscountandfllowcount(claims.getId(),friendid,1);
                return new Result(true,StatusCode.Ok,"添加好友成功");
            }
        }else if ("0".equals(type)){
            //还不是好友
            if(noFriendService.addFriend(claims.getId(),friendid) == 0){
                return new Result(false,StatusCode.REPERROR,"您已经添加过了好友");
            }else if (noFriendService.addFriend(claims.getId(),friendid) == 1){
                userClient.updatefanscountandfllowcount(claims.getId(),friendid,1);
                return new Result(true,StatusCode.Ok,"添加好友成功");
            }
        }
        return new Result(false,StatusCode.ERROR,"非法请求参数");
    }

}
