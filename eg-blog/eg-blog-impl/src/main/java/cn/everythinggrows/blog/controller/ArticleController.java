package cn.everythinggrows.blog.controller;


import cn.everythinggrows.base.egResponse;
import cn.everythinggrows.blog.aop.NeedSession;
import cn.everythinggrows.blog.dao.IndexDao;
import cn.everythinggrows.blog.dao.TypeArticleDao;
import cn.everythinggrows.blog.dao.UidArticleDao;
import cn.everythinggrows.blog.dao.redisdao.RedisDao;
import cn.everythinggrows.blog.event.IndexArticleEvent;
import cn.everythinggrows.blog.model.EgTypeArticle;
import cn.everythinggrows.blog.model.egArticle;
import cn.everythinggrows.blog.model.egUidArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDate;
import java.util.Calendar;

@RestController
public class ArticleController {

    public static final String INDEX_ARTICLE_AID = "eg/index/article/aid/";

    @Autowired
    private IndexDao indexDao;
    @Autowired
    private UidArticleDao uidArticleDao;
    @Autowired
    private TypeArticleDao typeArticleDao;
    @Autowired
    private JedisCluster jedisCluster;

    @RequestMapping(value = "/blog/article/insert",method = RequestMethod.POST)
    @NeedSession
    public egResponse InsertArticle(@RequestParam(value = "articleName",defaultValue = "") String articleName,
                                    @RequestParam(value = "content",defaultValue = "") String content,
                                    @RequestParam(value = "coverPic",defaultValue = "") String coverPic,
                                    @RequestParam(value = "type",defaultValue = "1") int type,
                                    @RequestParam(value = "title",defaultValue = "") String title,
                                    @RequestHeader(value = "x-eg-session") String session){
        long uid = getUid(session);
        egArticle egArticle = new egArticle();
        long aid = RedisDao.aidGeneration();
        Calendar calendar=Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String today = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
        int createAt = Integer.parseInt(today);
        egArticle.setId(aid);
        egArticle.setArticleName(articleName);
        egArticle.setUid(uid);
        egArticle.setContent(content);
        egArticle.setCoverPic(coverPic);
        egArticle.setType(type);
        egArticle.setTitle(title);
        egArticle.setCreateAt(createAt);
        int i = indexDao.insertArticle(egArticle);

        egUidArticle egUidArticle = new egUidArticle();
        egUidArticle.setAid(aid);
        egUidArticle.setArticleName(articleName);
        egUidArticle.setCoverPic(coverPic);
        egUidArticle.setUid(uid);
        egUidArticle.setType(type);
        egUidArticle.setTitle(title);
        egUidArticle.setCreateAt(createAt);
        uidArticleDao.insertUidArticle(egUidArticle);

        EgTypeArticle egTypeArticle = new EgTypeArticle();
        egTypeArticle.setType(type);
        egTypeArticle.setArticleName(articleName);
        egTypeArticle.setCoverPic(coverPic);
        egTypeArticle.setAid(aid);
        egTypeArticle.setUid(uid);
        egTypeArticle.setTitle(title);
        typeArticleDao.insertUidArticle(egTypeArticle);

        //增加文章事件
        IndexArticleEvent.IndexArticleScore(aid,1);
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
