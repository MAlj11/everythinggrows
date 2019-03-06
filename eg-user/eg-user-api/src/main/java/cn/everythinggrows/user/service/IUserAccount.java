package cn.everythinggrows.user.service;


import cn.everythinggrows.user.Utils.egResponse;
import cn.everythinggrows.user.model.egUser;

public interface IUserAccount {
    public String getMailVerifyAndSend(String toMail);

    public egResponse ICreateUser(egUser user,String vertify);

    public egResponse login(egUser user);
}

