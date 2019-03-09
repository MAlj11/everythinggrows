package cn.everythinggrows.blog.controller;


import cn.everythinggrows.base.egResponse;
import cn.everythinggrows.blog.model.Comment;
import cn.everythinggrows.blog.service.CommentService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
