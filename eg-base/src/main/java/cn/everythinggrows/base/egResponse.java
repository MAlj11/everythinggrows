package cn.everythinggrows.base;

import org.apache.commons.collections.map.HashedMap;


public class egResponse {
    public static final egResponse FORBIDDEN = error(403,"Insufficient privilage for action");
    public static final egResponse BAD_REQUSET = error(400,"bad request");
    public static final egResponse BAD_CODE = error(400,"vercity code not match");
    public static final egResponse SYSTEM_ERROR = error(401,"system error");
    public static final egResponse OK = new egResponse();

    public int errorCode;
    public String message;
    public Object data;

    public egResponse(int errorCode, String message, Object data){
        this.errorCode = errorCode;
        this.message = message;
        this.data=data;
    }

    public egResponse(){this(0,"ok",(Object)null);}

    public egResponse(Object data){
        this(0,"ok",data);
    }

    public static egResponse error(int code,String message){
        return new egResponse(code,message,null);
    }

    public static egResponse data(Object...fieldAndVals){
        HashedMap ret = new HashedMap();

        for(int i=0;i<fieldAndVals.length;i+=2){
            String key = (String) fieldAndVals[i];
            Object val = i + 1 > fieldAndVals.length ? null : fieldAndVals[i+1];
            ret.put(key,val);
        }
        return new egResponse(0,"ok",ret);
    }

    public static egResponse msg(String s){
        return new egResponse(0,s,null);
    }
}
