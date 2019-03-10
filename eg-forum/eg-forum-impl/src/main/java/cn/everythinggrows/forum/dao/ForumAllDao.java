package cn.everythinggrows.forum.dao;


import cn.everythinggrows.base.DBUtils;
import cn.everythinggrows.base.beanUtils;
import cn.everythinggrows.base.datasource.DBContextHolder;
import cn.everythinggrows.forum.model.Topic;
import com.google.common.collect.Lists;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleBiFunction;

@Repository
public class ForumAllDao {
    @Autowired
    private SqlSessionTemplate forumSqlSession;

    public List<Topic> getTopicList(int limit,int offset){
        List<Topic> list = Lists.newArrayList();
        /*
        todo  分库分表进行分页查询
         */
        return list;
    }

    public int insertTopic(Topic topic){
        Map<String,Object> dataMap = beanUtils.bean2map(topic);
        dataMap.put("tableName", "eg_comment_" + DBUtils.getTableKey(topic.getTid()));
        dataMap.put("id",topic.getTid());
        long DBkey = DBUtils.getDBKey(topic.getTid());
        DBContextHolder.setDBKey(DBkey);
        int i = forumSqlSession.insert("ForumAllDao.insertTopic");
        DBContextHolder.clearDBKey();
        return i;
    }

}
