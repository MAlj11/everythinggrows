package cn.everythinggrows.service;


import cn.everythinggrows.Utils.UserUtils;
import cn.everythinggrows.Utils.egResponse;
import cn.everythinggrows.Utils.idGeneration;
import cn.everythinggrows.dao.createAtDao;
import cn.everythinggrows.dao.userDao;
import cn.everythinggrows.model.egUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;
import java.util.ArrayList;
import java.util.Random;


@Service
public class userService {
    public static String EMAIL_VERIFY_KEY = "eg/email/verify/key";
    @Value("${portrait_1}")
    String portrait_1;

    @Value("${portrait_2}")
    String portrait_2;

    @Value("${portrait_3}")
    String portrait_3;

    @Value("${portrait_4}")
    String portrait_4;

    @Value("${portrait_5}")
    String portrait_5;

    @Value("${portrait_6}")
    String portrait_6;

    @Value("${portrait_7}")
    String portrait_7;

    @Value("${portrait_8}")
    String portrait_8;

    @Value("${portrait_9}")
    String portrait_9;

    @Value("${portrait_10}")
    String portrait_10;

    @Value("${portrait_11}")
    String portrait_11;

    @Value("${portrait_12}")
    String portrait_12;

    @Value("${portrait_13}")
    String portrait_13;

    @Value("${portrait_14}")
    String portrait_14;

    @Value("${portrait_15}")
    String portrait_15;

    @Value("${portrait_16}")
    String portrait_16;

    @Value("${portrait_17}")
    String portrait_17;

    @Value("${portrait_18}")
    String portrait_18;

    @Value("${portrait_19}")
    String portrait_19;

    @Value("${portrait_20}")
    String portrait_20;

    @Autowired
    private userDao userDao;

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private createAtDao createAtDao;



    public egResponse createUser(egUser user, String verify){
      long uid = idGeneration.uidGeneration();
      if(UserUtils.isOffcialUid(uid)){
          uid = idGeneration.uidGeneration();
      }
      user.setUid(uid);
      String portrait = getRandomPortrait();
      user.setPortrait(portrait);
      String redisVerify = jedisCluster.get(EMAIL_VERIFY_KEY+user.getEmail());
      if(redisVerify == null){
          redisVerify = "";
      }
      if(!redisVerify.equals(verify)){
          return egResponse.error(40001,"verify is error");
      }
      int time = (int)System.currentTimeMillis()/1000;
      int i = userDao.insertUser(user);
      int j = createAtDao.insertCreateAt(user.getUid(),time);
      if(i > 0 && j > 0){
          return egResponse.OK;
      }else {
          return egResponse.SYSTEM_ERROR;
      }
      }


    public String getRandomPortrait(){
        ArrayList<String> portraits = new ArrayList<>();
        portraits.add(portrait_1);
        portraits.add(portrait_2);
        portraits.add(portrait_3);
        portraits.add(portrait_4);
        portraits.add(portrait_5);
        portraits.add(portrait_6);
        portraits.add(portrait_7);
        portraits.add(portrait_8);
        portraits.add(portrait_9);
        portraits.add(portrait_10);
        portraits.add(portrait_11);
        portraits.add(portrait_12);
        portraits.add(portrait_13);
        portraits.add(portrait_14);
        portraits.add(portrait_15);
        portraits.add(portrait_16);
        portraits.add(portrait_17);
        portraits.add(portrait_18);
        portraits.add(portrait_19);
        portraits.add(portrait_20);

        int random = new Random().nextInt(19);
        String portrait = portraits.get(random);
        return portrait;
    }


}
