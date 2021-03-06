package cn.everythinggrows.user.Utils;

import org.springframework.beans.factory.annotation.Value;

public class UserUtils {

    @Value("${officialUids}")
    static String officialUids;


    public static boolean isOffcialUid(long uid){
        String[] officislUidList = officialUids.split(",");
        for(int i=0;i<officislUidList.length;i++){
            if(uid == Long.parseLong(officislUidList[i])){
                return true;
            }
        }
        return false;
        }
}
