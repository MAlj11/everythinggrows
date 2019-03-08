package cn.everythinggrows.blog.dao.redisdao;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

public class RedisDao {
    public static final String ARTICLE_AID = "eg/article/aid/";

    @Autowired
    private static JedisCluster jedisCluster;

    public static long aidGeneration() {
        long aid = jedisCluster.incr(ARTICLE_AID);
        return aid;
    }
}
