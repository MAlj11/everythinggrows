package cn.everythinggrows.forum.controller;


import cn.everythinggrows.base.egResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForumAllController {

    @RequestMapping("/forum/index/all")
    public egResponse getForumAll(){
     //todo 分库分表进行分页查询
        return new egResponse();
    }
}
