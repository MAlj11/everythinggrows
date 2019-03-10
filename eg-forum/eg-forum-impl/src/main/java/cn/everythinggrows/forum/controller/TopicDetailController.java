package cn.everythinggrows.forum.controller;


import cn.everythinggrows.base.egResponse;
import cn.everythinggrows.forum.dao.Topicdao;
import cn.everythinggrows.forum.service.TopicService;
import cn.everythinggrows.user.model.egUser;
import cn.everythinggrows.user.service.IUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@Controller
public class TopicDetailController {

    @Autowired
    private Topicdao topicdao;
    @Autowired
    private IUserAccount iUserAccount;
    @Autowired
    private TopicService topicService;


    @RequestMapping(value = "/forum/topic/detail/{tid}")
    public egResponse getTopicDetailWithTid(@Context HttpServletRequest request,
                                            @PathVariable("tid") long tid){



    }

    @RequestMapping(value = "forum/topic/insert")
    public egResponse inseretTopicDetail(@Context HttpServletRequest request,
                                         @RequestParam(value = "tid",defaultValue = "0") long tid,
                                         @RequestParam(value = "content",defaultValue = "") String content,
                                         @RequestHeader(value = "EG-SESSION") String session){

//        String token = String.valueOf(uid) + ";" + uuid;
        long uid = getUid(session);
        egUser user = iUserAccount.getUser(uid);
        int i = topicService.insertTopicDetail(user,content,tid);
        if (i > 0){
            return egResponse.OK;
        }
        else {
            return egResponse.error(100001, "system is error");
        }
    }


    public long getUid(String session){
        if(session == null || session.length() == 0){
            return 0;
        }
        String[] line = session.split(";");
        long uid = Long.parseLong(line[0]);
        return uid;
    }

}
