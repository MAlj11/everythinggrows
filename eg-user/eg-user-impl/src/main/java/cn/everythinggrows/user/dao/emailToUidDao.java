package cn.everythinggrows.user.dao;

import cn.everythinggrows.user.Utils.DBUtils;
import cn.everythinggrows.user.Utils.beanUtils;
import cn.everythinggrows.user.datasource.DBContextHolder;
import cn.everythinggrows.user.model.egUser;
import cn.everythinggrows.user.model.emailUid;
import com.google.common.collect.Maps;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class emailToUidDao {

    @Autowired
    private SqlSessionTemplate userSqlSession;


    public int insertEmailUid(emailUid emailUid){
        Map<String,Object> dataMap = beanUtils.bean2map(emailUid);
        dataMap.put("tableName","eg_user_" + DBUtils.getTableKey(emailUid.getHashid()));
        int dbKey = DBUtils.getDBKey(emailUid.getHashid());
        DBContextHolder.setDBKey((long)dbKey);
        int i = userSqlSession.insert("emailToUserDao.insertUser",dataMap);
        DBContextHolder.clearDBKey();
        return i;
    }

    public emailUid selectEmailUid(long hashid){
        Map<String,Object> dataMap = Maps.newHashMap();
        dataMap.put("tableName","email_uid_" + DBUtils.getTableKey(hashid));
        int dbKey = DBUtils.getDBKey(hashid);
        DBContextHolder.setDBKey((long)dbKey);
        emailUid emailuid = userSqlSession.selectOne("emailToUserDao.selectUser",dataMap);
        return emailuid;
    }

}
