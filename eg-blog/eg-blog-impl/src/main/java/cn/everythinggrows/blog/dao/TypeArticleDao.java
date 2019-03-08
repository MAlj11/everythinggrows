package cn.everythinggrows.blog.dao;


import cn.everythinggrows.base.DBUtils;
import cn.everythinggrows.base.beanUtils;
import cn.everythinggrows.base.datasource.DBContextHolder;
import cn.everythinggrows.blog.model.EgTypeArticle;
import cn.everythinggrows.blog.model.egUidArticle;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
