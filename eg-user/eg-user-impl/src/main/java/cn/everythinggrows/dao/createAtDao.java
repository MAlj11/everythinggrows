package cn.everythinggrows.dao;

import cn.everythinggrows.Utils.DBUtils;
import cn.everythinggrows.datasource.DBContextHolder;
import com.google.common.collect.Maps;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class createAtDao {

    @Autowired
    private SqlSessionTemplate userSqlSession;

    public int insertCreateAt(long uid,int time){
        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("uid",uid);
        dataMap.put("createAt",time);
        dataMap.put("tableName","eg_user_caeatetime_" + DBUtils.getTableKey(uid));
        int dbKey = DBUtils.getDBKey(uid);
        DBContextHolder.setDBKey((long)dbKey);
        int i = userSqlSession.insert("userCreateAt.insertUserCreateAt",dataMap);
        DBContextHolder.clearDBKey();
        return i;
    }
}
