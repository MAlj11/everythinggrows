package cn.everythinggrows.forum.dao;

import cn.everythinggrows.base.DBUtils;
import cn.everythinggrows.base.beanUtils;
import cn.everythinggrows.base.datasource.DBContextHolder;
import cn.everythinggrows.forum.model.TopicDetail;
import com.google.common.collect.Maps;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Topicdao {
      @Autowired
      private SqlSessionTemplate topicSqlSession;

    public int createTopicDetailTable(long tid){
        Map<String,Object> dataMap = Maps.newHashMap();
        dataMap.put("tid",tid);
        dataMap.put("tableName", "eg_topic_detail_" + DBUtils.getTableKey(tid));
        long DBkey = DBUtils.getDBKey(tid);
        DBContextHolder.setDBKey(DBkey);
        int i = topicSqlSession.update("TopicDetailDao.createTable",dataMap);
        DBContextHolder.clearDBKey();
        return i;
    }

    public int deleteTopicDetailTable(long tid){
        Map<String,Object> dataMap = Maps.newHashMap();
        dataMap.put("tid",tid);
        dataMap.put("tableName", "eg_topic_detail_" + DBUtils.getTableKey(tid));
        long DBkey = DBUtils.getDBKey(tid);
        DBContextHolder.setDBKey(DBkey);
        int i = topicSqlSession.update("TopicDetailDao.dropTable",dataMap);
        DBContextHolder.clearDBKey();
        return i;
    }

    public List<TopicDetail> getTopicDetailList(long tid){
        Map<String,Object> dataMap = Maps.newHashMap();
        dataMap.put("tid",tid);
        dataMap.put("tableName","eg_topic_detail_" + String.valueOf(tid));
        long DBKey = DBUtils.getDBKey(tid);
        DBContextHolder.setDBKey(DBKey);
        List<TopicDetail> topicDetails = topicSqlSession.selectList("TopicDetailDao.selectTopicDetail",dataMap);
        DBContextHolder.clearDBKey();
        return topicDetails;
    }

    public int insertTopicDetail(TopicDetail topicDetail){
        Map<String,Object> dataMap = beanUtils.bean2map(topicDetail);
        dataMap.put("tableName", "eg_topic_detail_" + String.valueOf(topicDetail.getTid()));
        dataMap.put("tid",topicDetail.getTid());
        long DBkey = DBUtils.getDBKey(topicDetail.getTid());
        DBContextHolder.setDBKey(DBkey);
        int i = topicSqlSession.insert("TopicDetailDao.insertTopicDetail");
        DBContextHolder.clearDBKey();
        return i;
    }

    public int deleteTopicDetail(long id,long tid){
        Map<String,Object> dataMap = Maps.newHashMap();
        dataMap.put("tableName", "eg_topic_detail_" + String.valueOf(String.valueOf(tid)));
        dataMap.put("id",id);
        long DBkey = DBUtils.getDBKey(tid);
        DBContextHolder.setDBKey(DBkey);
        int i = topicSqlSession.delete("TopicDetailDao.deleteTopicDetail");
        DBContextHolder.clearDBKey();
        return i;
    }
}
