package cn.everythinggrows.forum.service;


import cn.everythinggrows.base.egResponse;
import cn.everythinggrows.forum.Utils.idGeneration;
import cn.everythinggrows.forum.dao.ForumAllDao;
import cn.everythinggrows.forum.model.Topic;
import cn.everythinggrows.user.model.egUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class ForumAllService {
    @Autowired
    private ForumAllDao forumAllDao;
    public egResponse getForumAll(){

        //todo 分库分表进行分页查询
     return new egResponse();
    }


    public int insertTopic(egUser user,String content){
       long tid = idGeneration.getTid();
        Topic topic = new Topic();
        topic.setTid(tid);
        topic.setContent(content);
        topic.setUid(user.getUid());
        topic.setPortrait(user.getPortrait());
        topic.setUsername(user.getUsername());
        topic.setExtend("");
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH ) + 1;
        //获取到0-11，与我们正常的月份差1
        int day = cal.get(Calendar.HOUR_OF_DAY);
        String createAtStr = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
        int createAt = Integer.parseInt(createAtStr);
        topic.setCreateAt(createAt);
        int i = forumAllDao.insertTopic(topic);
        return i;
    }
}
