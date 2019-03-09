package cn.everythinggrows.blog.dao;


import cn.everythinggrows.base.DBUtils;
import cn.everythinggrows.base.beanUtils;
import cn.everythinggrows.base.datasource.DBContextHolder;
import cn.everythinggrows.blog.model.egArticle;

import cn.everythinggrows.user.model.egUser;
import com.google.common.collect.Maps;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class IndexDao {
    @Autowired
    private SqlSessionTemplate blogSqlSession;

    public egArticle getArtcleOne(long id){
        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("tableName", "eg_article_" + DBUtils.getTableKey(id));
        dataMap.put("id",id);
        long DBkey = DBUtils.getDBKey(id);
        DBContextHolder.setDBKey(DBkey);
        egArticle egArticle = blogSqlSession.selectOne("blogIndexDao.selectArticle", dataMap);
        DBContextHolder.clearDBKey();
        return egArticle;
    }

    public int insertArticle(egArticle egArticle){
        Map<String,Object> dataMap = beanUtils.bean2map(egArticle);
        long id = egArticle.getId();
        dataMap.put("tableName", "eg_article_" + DBUtils.getTableKey(id));
        long DBkey = DBUtils.getDBKey(id);
        DBContextHolder.setDBKey(DBkey);
        int i = blogSqlSession.insert("insertArticle.selectArticle", dataMap);
        DBContextHolder.clearDBKey();
        return i;
    }



}
