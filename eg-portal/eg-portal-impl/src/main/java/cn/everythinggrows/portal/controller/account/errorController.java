package cn.everythinggrows.portal.controller.account;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

@Controller
public class errorController {

    @RequestMapping(value = "/nosession")
    public String getSessionError(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("error","please login first");
        return "error";
    }
}
