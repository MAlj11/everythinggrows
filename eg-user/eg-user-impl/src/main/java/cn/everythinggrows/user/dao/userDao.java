package cn.everythinggrows.user.dao;


import cn.everythinggrows.user.Utils.DBUtils;
import cn.everythinggrows.user.Utils.beanUtils;
import cn.everythinggrows.user.datasource.DBContextHolder;
import cn.everythinggrows.user.model.egUser;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class userDao {
    @Autowired
    private SqlSessionTemplate userSqlSession;


    public int insertUser(egUser user){
        Map<String,Object> dataMap = beanUtils.bean2map(user);
        dataMap.put("tableName","eg_user_" + DBUtils.getTableKey(user.getUid()));
        int dbKey = DBUtils.getDBKey(user.getUid());
        DBContextHolder.setDBKey((long)dbKey);
        int i = userSqlSession.insert("userDao.insertUser",dataMap);
        DBContextHolder.clearDBKey();
        return i;
        }
}
