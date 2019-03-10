package cn.everythinggrows.portal.controller.account;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@Controller
public class ForumController {


    @RequestMapping(value = "/forum/index")
    public String getForumIndex(@Context HttpServletRequest request){

    }
}
