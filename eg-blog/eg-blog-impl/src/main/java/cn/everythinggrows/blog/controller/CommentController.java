package cn.everythinggrows.blog.controller;


import cn.everythinggrows.base.egResponse;
import cn.everythinggrows.blog.model.Comment;
import cn.everythinggrows.blog.service.CommentService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/blog/comment/{aid}")
    public egResponse getComment(@PathVariable("aid") long aid){
       List<Comment> comments = commentService.getCommentWithAid(aid);
        Map<String,Object> ret = Maps.newHashMap();
        ret.put("comments",comments);
        return new egResponse(ret);
    }

    @RequestMapping(value = "/blog/comment/insert")
    public egResponse insertComment(@RequestParam(value = "aid") long aid,
                                    @RequestParam(value = "contnet")String content,
                                    @RequestHeader(value = "x-eg-session") String session){
        long uid = getUid(session);
        int i = commentService.insertComment(aid,uid,content);
        if(i>0){
            return egResponse.OK;
        }else{
            return egResponse.error(10001,"system is errorr");
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
