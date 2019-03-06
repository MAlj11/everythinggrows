package cn.everythinggrows.portal.controller.account;


import cn.everythinggrows.portal.Utils.egResponse;
import cn.everythinggrows.portal.Utils.HttpClientUtil;
import cn.everythinggrows.portal.service.emailVerifyService;
import cn.everythinggrows.user.model.egUser;
import cn.everythinggrows.user.service.IUserAccount;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.util.HashMap;

@Controller
public class userController {
    public static final String EMAIL_VERIFY = "eg/email/verify/";

    @Autowired
    private emailVerifyService emailVerifyService;

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private IUserAccount userAccount;


    private Logger logger = LoggerFactory.getLogger(userController.class);

    @RequestMapping(value = "/emailVerify.html", method = RequestMethod.GET)
    public egResponse getEmailVerify(@RequestParam String reEmail){
        if(reEmail == null){
            return egResponse.error(10002,"email is null");
        }
        String verify = userAccount.getMailVerifyAndSend(reEmail);
        return egResponse.OK;
    }


    @RequestMapping(value = "/register.html", method = RequestMethod.POST)
    public String Register(@Context HttpServletRequest request){

        String reEmail = request.getParameter("reEmail");
        String rePassword = request.getParameter("rePassword");
        String reVerify = request.getParameter( "reVerify" );

        egUser user = new egUser();
        user.setEmail(reEmail);
        user.setPassword(rePassword);
        user.setUsername(reEmail);

        HttpSession session = request.getSession();
        cn.everythinggrows.user.Utils.egResponse ret = userAccount.ICreateUser(user,reVerify);
        if(ret.errorCode == 10002){
            session.setAttribute("error","您的验证码有误");
            return "error.vm";
        }
        session.setAttribute("token",egResponse.data("token"));
        return "lw-index.vm";
    }


    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public  String Login(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        String loEmail = request.getParameter("doc-vld-email-2-1");
        String loPassword = request.getParameter("passwordLog");

        egUser user = new egUser();
        user.setEmail(loEmail);
        user.setPassword(loPassword);
        cn.everythinggrows.user.Utils.egResponse ret = userAccount.login(user);
        if(ret.errorCode == 100001){
            session.setAttribute("error","您的密码有误");
            return "error.vm";
        }
        return "lw-index";
    }

}
