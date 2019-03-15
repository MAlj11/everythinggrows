package cn.everythinggrows.portal.controller.account;


import cn.everythinggrows.blog.model.EgTypeArticle;
import cn.everythinggrows.forum.model.TopicIndex;
import cn.everythinggrows.portal.Utils.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.util.List;
import java.util.Map;

@Controller
public class ForumController {

    @Value("BASE_URL_FORUM")
    String BASE_URL_FORUM;

    @RequestMapping(value = "/forum/index")
    public String getForumIndex(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        String Url = BASE_URL_FORUM + "/forum/index/all";
        String typeRet = HttpClientUtil.doGet(Url);
        JSONObject json = JSON.parseObject(typeRet);
        Map<String,Object> typeArticleMap = JSONObject.toJavaObject(json, Map.class);
        List<TopicIndex> topicIndices = null;
        if(typeArticleMap.get("errorCode").equals("0")){
            topicIndices = (List<TopicIndex>) typeArticleMap.get("articleWithTypeList");
        }
        session.setAttribute("topicIndices",topicIndices);

        //todo 前端页面，写好后将所有话题塞到里面
        return "lw-Photography";
    }
}
