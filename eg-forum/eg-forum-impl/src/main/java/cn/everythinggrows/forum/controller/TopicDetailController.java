package cn.everythinggrows.forum.controller;


import cn.everythinggrows.base.egResponse;
import cn.everythinggrows.forum.aop.NeedSession;
import cn.everythinggrows.forum.dao.Topicdao;
import cn.everythinggrows.forum.model.TopicDetail;
import cn.everythinggrows.forum.service.TopicService;
import cn.everythinggrows.user.model.egUser;
import cn.everythinggrows.user.service.IUserAccount;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.List;
import java.util.Map;

@Controller
public class TopicDetailController {

    @Autowired
    private Topicdao topicdao;
    @Autowired
    private IUserAccount iUserAccount;
    @Autowired
    private TopicService topicService;


    /**
     * 根据tid查询该标题的所有内容
     * @param request
     * @param tid
     * @return
     */
    @RequestMapping(value = "/forum/topic/detail/{tid}")
    public egResponse getTopicDetailWithTid(@Context HttpServletRequest request,
                                            @PathVariable("tid") long tid){
        List<TopicDetail> topicDetails = topicService.getTopicDetailLsit(tid);
        Map<String,Object> ret = Maps.newHashMap();
        ret.put("topicDetails",topicDetails);
        return new egResponse(ret);

    }


    /**
     * 向该标题插入一条内容
     * @param request
     * @param tid
     * @param content
     * @param session
     * @return
     */
    @NeedSession
    @RequestMapping(value = "/forum/topic/detail/insert")
    public egResponse inseretTopicDetail(@Context HttpServletRequest request,
                                         @RequestParam(value = "tid",defaultValue = "0") long tid,
                                         @RequestParam(value = "content",defaultValue = "") String content,
                                         @RequestHeader(value = "x-eg-session") String session){

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


    /**
     * 删除论坛话题的某一条内容
      * @param request
     * @param id
     * @param tid
     * @return
     */
    @NeedSession
    @RequestMapping(value = "/forum/topic/detail/delete")
    public egResponse deleteTopicDetail(@Context HttpServletRequest request,
                                        @RequestParam(value = "id") long id,
                                        @RequestParam(value = "tid") long tid){
        int i = topicService.deleteTopicDetail(id,tid);
        return egResponse.OK;
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
