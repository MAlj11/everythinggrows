package cn.everythinggrows.blog.Utils;

public class ArticleUtils {
    public static String getTypeWithInt(int type){
        switch(type){
            case 1:
                return "摄影";
            case 2:
                return "互联网";
            case 3:
                return "影音";
            case 4:
                return "感悟";
            default:
                return "分类";
        }
    }

}
