package cn.everythinggrows.blog.dao;


import cn.everythinggrows.base.DBUtils;
import cn.everythinggrows.base.beanUtils;
import cn.everythinggrows.base.datasource.DBContextHolder;
import cn.everythinggrows.blog.Utils.ArticleUtils;
import cn.everythinggrows.blog.model.Comment;
import cn.everythinggrows.blog.model.egArticle;
import com.google.common.collect.Maps;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommentDao {
    @Autowired
    private SqlSessionTemplate blogSqlSession;

    public List<Comment> getCommentList(long aid){
        Map<String,Object> dataMap = Maps.newHashMap();
        dataMap.put("tableName", "eg_comment_" + DBUtils.getTableKey(aid));
        dataMap.put("id",aid);
        long DBkey = DBUtils.getDBKey(aid);
        DBContextHolder.setDBKey(DBkey);
        List<Comment> comments = blogSqlSession.selectList("CommentDao.selectComment");
        DBContextHolder.clearDBKey();
        return comments;
    }

    public int insertComment(Comment comment){
        Map<String,Object> dataMap = beanUtils.bean2map(comment);
        dataMap.put("tableName", "eg_comment_" + DBUtils.getTableKey(comment.getAid()));
        dataMap.put("id",comment.getAid());
        long DBkey = DBUtils.getDBKey(comment.getAid());
        DBContextHolder.setDBKey(DBkey);
        int i = blogSqlSession.insert("CommentDao.insertComment");
        DBContextHolder.clearDBKey();
        return i;
    }
}
