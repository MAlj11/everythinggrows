package cn.everythinggrows.base.datasource;

public class DBContextHolder {
    private static final ThreadLocal<Long> contextHolder = new ThreadLocal<Long>();


    public static void setDBKey(Long num) {
        contextHolder.set(num);
    }

    public static Long getDBKey() {
        return (Long)contextHolder.get();
    }

    public static void clearDBKey() {
        contextHolder.remove();
    }
}
