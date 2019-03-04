package cn.everythinggrows.user.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * @author MA
 */
public class idGeneration{
    public static String EG_UID_PREFIX = "eg/uid/generation";
    @Autowired
    private static JedisCluster jedisCluster;

    public static long uidGeneration(){
        long uid = jedisCluster.incr(EG_UID_PREFIX);
        return uid;
    }


}
