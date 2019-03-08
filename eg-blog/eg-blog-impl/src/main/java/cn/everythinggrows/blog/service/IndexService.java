package cn.everythinggrows.blog.service;


import cn.everythinggrows.base.egResponse;
import cn.everythinggrows.blog.Utils.ArticleUtils;
import cn.everythinggrows.blog.dao.BannerDao;
import cn.everythinggrows.blog.dao.IndexDao;
import cn.everythinggrows.blog.model.Banner;
import cn.everythinggrows.blog.model.egArticle;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class IndexService {
    public static final String INDEX_ARTICLE_AID = "eg/index/article/aid/";
    @Autowired
    private JedisCluster jedisCluster;
    @Autowired
    private IndexDao indexDao;
    @Autowired
    private BannerDao bannerDao;


    public egResponse getArticleList(){
        Set<Tuple> tuples = jedisCluster.zrangeWithScores(INDEX_ARTICLE_AID,0,9);
        List<egArticle> articles = Lists.newArrayList();
        for(Tuple tuple : tuples){
            egArticle egArticle = indexDao.getArtcleOne(Long.parseLong(tuple.getElement()));
            egArticle.setTypeString(ArticleUtils.getTypeWithInt(egArticle.getType()));
            articles.add(egArticle);
        }
        HashMap<String,Object> ret = Maps.newHashMap();
        ret.put("articleList",articles);
        return new egResponse(ret);
    }

    public egResponse getRecommend(){
        Set<Tuple> tuples = jedisCluster.zrangeWithScores(INDEX_ARTICLE_AID,10,14);
        List<egArticle> articles = Lists.newArrayList();
        for(Tuple tuple : tuples){
            egArticle egArticle = indexDao.getArtcleOne(Long.parseLong(tuple.getElement()));
            egArticle.setTypeString(ArticleUtils.getTypeWithInt(egArticle.getType()));
            articles.add(egArticle);
        }
        HashMap<String,Object> ret = Maps.newHashMap();
        ret.put("recommendList",articles);
        return new egResponse(ret);
    }


    public egResponse getIndexBanner(){
        List<Banner> bannerList = bannerDao.getBanner();
        Map<String,Object> ret = Maps.newHashMap();
        ret.put("bannerList",bannerList);
        return new egResponse(ret);
    }





}
