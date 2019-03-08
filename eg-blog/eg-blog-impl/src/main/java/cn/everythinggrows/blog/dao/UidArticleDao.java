package cn.everythinggrows.blog.dao;


import cn.everythinggrows.base.DBUtils;
import cn.everythinggrows.base.beanUtils;
import cn.everythinggrows.base.datasource.DBContextHolder;
import cn.everythinggrows.blog.model.egArticle;
import cn.everythinggrows.blog.model.egUidArticle;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UidArticleDao {
    @Autowired
    private SqlSessionTemplate blogSqlSession;


    public int insertUidArticle(egUidArticle egUidArticle){
            Map<String,Object> dataMap = beanUtils.bean2map(egUidArticle);
            long id = egUidArticle.getUid();
            dataMap.put("tableName", "eg_uid_article_" + DBUtils.getTableKey(id));
            long DBkey = DBUtils.getDBKey(id);
            DBContextHolder.setDBKey(DBkey);
            int i = blogSqlSession.insert("UidArticleDao.selectArticle", dataMap);
            DBContextHolder.clearDBKey();
            return i;
    }


}
