package cn.everythinggrows.forum.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@Controller
public class SessionController {

    @RequestMapping(value = "/nosession")
    public String getSessionError(@Context HttpServletRequest request){
        request.setAttribute("error","please login first");
        return "error";
    }
}
