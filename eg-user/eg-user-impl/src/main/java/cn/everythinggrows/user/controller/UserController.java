package cn.everythinggrows.user.controller;


import cn.everythinggrows.base.egResponse;
import cn.everythinggrows.user.model.egUser;
import cn.everythinggrows.user.service.UserAccountImpl;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.JedisCluster;

import java.util.Map;

@Controller
public class UserController {


    @Autowired
    private UserAccountImpl userAccount;
    @Autowired
    private JedisCluster jedisCluster;

    @RequestMapping(value = "/user/detail/{uid}")
    public egResponse getUserDetail(@PathVariable(value = "0") long uid){
         egUser user = userAccount.getUser(uid);
         Map<String,Object> ret = Maps.newHashMap();
         ret.put("userDetail",user);
         return new egResponse(ret);
    }
}
