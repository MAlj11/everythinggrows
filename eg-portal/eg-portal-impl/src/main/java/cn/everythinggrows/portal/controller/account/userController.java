package cn.everythinggrows.portal.controller.account;


import cn.everythinggrows.portal.Utils.egResponse;
import cn.everythinggrows.portal.Utils.HttpClientUtil;
import cn.everythinggrows.portal.service.emailVerifyService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.HashMap;

@Controller
public class userController {
    public static String EMAIL_VERIFY_KEY = "eg/email/verify/key";

    @Autowired
    private emailVerifyService emailVerifyService;

    @Autowired
    private JedisCluster jedisCluster;




    private Logger logger = LoggerFactory.getLogger(userController.class);
    @RequestMapping(value = "/register.html", method = RequestMethod.POST)
    public String Register(@Context HttpServletRequest request){

        String reEmail = request.getParameter("reEmail");
        String rePassword = request.getParameter("rePassword");
        String reVerify = request.getParameter( "reVerify" );


//        String url = "http://localhost:8081/user/register";
//        HashMap<String,String> registerParams = Maps.newHashMap();
//        registerParams.put("email",reEmail);
//        registerParams.put("password",rePassword);
//        registerParams.put("verify",reVerify);
//        String ret = HttpClientUtil.doPost(url,registerParams);
//        logger.info(">>>>>>>>>>>>>>>register result:{}>>>>>>>>>>>>>>>>>>",ret);


        return "lw-log";
    }


    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public  String Login(@Context HttpServletRequest request){
        String loEmail = request.getParameter("doc-vld-email-2-1");
        String loPassword = request.getParameter("passwordLog");

        String url = "http://localhost:8081/user/login";
        HashMap<String,String> logParams = Maps.newHashMap();
        logParams.put("email",loEmail);
        logParams.put("password",loPassword);
        String ret = HttpClientUtil.doPost(url,logParams);
        logger.info(">>>>>>>>>>>>>>>log result:{}>>>>>>>>>>>>>>>>>>",ret);
        return "lw-index";
    }

    @RequestMapping(value = "/emailVerify.html",method = RequestMethod.POST)
    @ResponseBody
    public egResponse getVerify(@Context HttpServletRequest request) throws MessagingException {
        String reEmail = request.getParameter("reEmail");
        String verify = emailVerifyService.getMailVerifyAndSend(reEmail);
        jedisCluster.setex(EMAIL_VERIFY_KEY+reEmail,5*60,verify);
        return egResponse.OK;
    }
}
