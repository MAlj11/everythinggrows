package cn.everythinggrows.portal.service;


import cn.everythinggrows.blog.model.Comment;
import cn.everythinggrows.blog.model.egArticle;
import cn.everythinggrows.portal.Utils.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSONString;

@Service
public class ArticleDetailService {
    @Value("${BASE_URL_BLOG}")
    String BASE_URL_BLOG;

    public List<Comment> getCommentDetail(long aid){
        String url = BASE_URL_BLOG + "/comment/" + String.valueOf(aid);
        String httpRet = HttpClientUtil.doGet(url);
        JSONObject json = JSON.parseObject(httpRet);
        Map<String,Object> articleMap = JSONObject.toJavaObject(json, Map.class);
        List<Comment> comments = null;
        if(articleMap.get("errorCode").equals("0")){
            comments = (List<Comment>) articleMap.get("articleList");
        }
//        String ret =  JSONObjectSON.toJSONString(comments);
        return comments;
    }
}
