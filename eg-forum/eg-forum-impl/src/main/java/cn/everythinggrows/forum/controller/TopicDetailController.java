package cn.everythinggrows.forum.controller;


import cn.everythinggrows.base.egResponse;
import cn.everythinggrows.forum.dao.Topicdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@Controller
public class TopicDetailController {

    @Autowired
    private Topicdao topicdao;


    @RequestMapping(value = "/forum/topic/detail/{tid}")
    public egResponse getTopicDetailWithTid(@Context HttpServletRequest request,
                                            @PathVariable("tid") long tid){

        

    }

    @RequestMapping(value = "forum/topic/insert")
    public egResponse inseretTopicDetail(@Context HttpServletRequest request,
                                         @RequestParam(value = "tid",defaultValue = "0") long tid,
                                         @RequestParam(value = "content",defaultValue = "") String content)
}
