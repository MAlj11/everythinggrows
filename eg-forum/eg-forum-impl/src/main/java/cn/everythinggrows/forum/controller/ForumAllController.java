package cn.everythinggrows.forum.controller;

import cn.everythinggrows.base.egResponse;
import cn.everythinggrows.forum.aop.NeedSession;
import cn.everythinggrows.forum.service.ForumAllService;
import cn.everythinggrows.user.model.egUser;
import cn.everythinggrows.user.service.IUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class ForumAllController {
    @Autowired
    private ForumAllService forumAllService;
    @Autowired
    private IUserAccount iUserAccount;

    /**
     * 查询论坛所有话题
     * @return
     */
    @RequestMapping("/forum/index/all")
    public egResponse getForumAll(@RequestParam(value = "perPage") int perPage,
                                  @RequestParam(value = "pageSize") int pageSize){
     //todo 分库分表进行分页查询
       egResponse ret = forumAllService.getForumAll(perPage,pageSize);
       return ret;
    }


    /**
     * 删除某条话题，同时删除该话题对应的详情表
     * @param tid
     * @return
     */
    @NeedSession
    @RequestMapping(value = "/forum/index/delete/{tid}")
    public egResponse deleteForumTopic(@PathVariable("tid") long tid){
        int i = forumAllService.deleteTopic(tid);
        if(i<=0){
            return egResponse.error(100001,"system is error");
        }
        return egResponse.OK;
    }


    /**
     * 插入一条话题
     * @param content
     * @param session
     * @return
     */
    @NeedSession
    @RequestMapping(value = "/forum/index/insert")
    public egResponse insertTopic(@RequestParam(value = "content") String content,
                           @RequestHeader(value = "EG-SESSION") String session){
        egUser user = iUserAccount.getUser(getUid(session));
        int i = forumAllService.insertTopic(user,content);
        if(i<=0){
            return egResponse.error(100001,"system is error");
        }
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
