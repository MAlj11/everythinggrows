package cn.everythinggrows.blog.dao;


import cn.everythinggrows.base.DBUtils;
import cn.everythinggrows.base.beanUtils;
import cn.everythinggrows.base.datasource.DBContextHolder;
import cn.everythinggrows.blog.model.EgTypeArticle;
import cn.everythinggrows.blog.model.egUidArticle;
import com.google.common.collect.Maps;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TypeArticleDao {

    @Autowired
    private SqlSessionTemplate blogSqlSession;

    public int insertUidArticle(EgTypeArticle egTypeArticle){
        Map<String,Object> dataMap = beanUtils.bean2map(egTypeArticle);
        long id = egTypeArticle.getType();
        dataMap.put("tableName", "eg_type_article" + String.valueOf(id));
        DBContextHolder.setDBKey(0L);
        int i = blogSqlSession.insert("TypeArticleDao.insertArticle", dataMap);
        DBContextHolder.clearDBKey();
        return i;
    }

    public List<EgTypeArticle> getTypeArticleList(int type){
        Map<String,Object> dataMap = Maps.newHashMap();
        dataMap.put("tableName", "eg_type_article_" + String.valueOf(type));
        DBContextHolder.setDBKey(0L);
        List<EgTypeArticle> egTypeArticleList = blogSqlSession.selectList("TypeArticleDao.selectArticle",dataMap);
        DBContextHolder.clearDBKey();
        return egTypeArticleList;
    }

}
