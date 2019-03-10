package cn.everythinggrows.forum.service;


import cn.everythinggrows.forum.dao.Topicdao;
import cn.everythinggrows.forum.model.TopicDetail;
import cn.everythinggrows.user.model.egUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class TopicService {

    @Autowired
    private Topicdao topicdao;

    public int insertTopicDetail(egUser user,String content,long tid){
        TopicDetail topicDetail = new TopicDetail();
        topicDetail.setTid(tid);
        topicDetail.setContent(content);
        topicDetail.setUid(user.getUid());
        topicDetail.setUsername(user.getUsername());
        topicDetail.setPortrait(user.getPortrait());
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH ) + 1;
        //获取到0-11，与我们正常的月份差1
        int day = cal.get(Calendar.HOUR_OF_DAY);
        String createAtStr = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
        int createAt = Integer.parseInt(createAtStr);
        topicDetail.setCreateAt(createAt);
        int i = topicdao.insertTopicDetail(topicDetail);
        return i;
    }
}
