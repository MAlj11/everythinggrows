package cn.everythinggrows.blog.controller;


import cn.everythinggrows.base.egResponse;
import cn.everythinggrows.blog.dao.IndexDao;
import cn.everythinggrows.blog.dao.TypeArticleDao;
import cn.everythinggrows.blog.dao.UidArticleDao;
import cn.everythinggrows.blog.dao.redisdao.RedisDao;
import cn.everythinggrows.blog.event.IndexArticleEvent;
import cn.everythinggrows.blog.model.EgTypeArticle;
import cn.everythinggrows.blog.model.egArticle;
import cn.everythinggrows.blog.model.egUidArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

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

    @RequestMapping(value = "blog/article/insert",method = RequestMethod.POST)
    public egResponse InsertArticle(@RequestParam(defaultValue = "") String articleName,
                                    @RequestParam() long uid,
                                    @RequestParam(defaultValue = "") String content,
                                    @RequestParam(defaultValue = "") String coverPic,
                                    @RequestParam(defaultValue = "1") int type,
                                    @RequestParam(defaultValue = "") String title){
        egArticle egArticle = new egArticle();
        long aid = RedisDao.aidGeneration();
        int createAt = (int)System.currentTimeMillis()/1000;
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
}
