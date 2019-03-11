package com.tensquare.friend.client;

import com.tensquare.friend.client.impl.UserClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author dongcheng
 * create date 2019/3/11
 **/
@FeignClient(value = "tensquare-user",fallback= UserClientImpl.class)
public interface UserClient {

    @RequestMapping(value = "/{userid}/{friendid}/{x}",method = RequestMethod.PUT)
    public void updatefanscountandfllowcount(@PathVariable String userid, @PathVariable String friendid, int x);

}
