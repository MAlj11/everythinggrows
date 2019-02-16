package cn.everythinggrows.service;


import cn.everythinggrows.Utils.egResponse;
import cn.everythinggrows.Utils.idGeneration;
import cn.everythinggrows.model.egUser;
import org.springframework.stereotype.Service;


@Service
public class userService {

    public egResponse createUser(egUser user, String verify){
      long uid = idGeneration.uidGeneration();
      user.setUid(uid);

    }
}
