package com.switchvov.read.write.split.common.context;

/**
 * 数据库上下文holder
 *
 * @author switch
 * @since 2021/5/2
 */
public class MultiDataSourceContextHolder {
    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSourceName(String dataSourceName) {
        contextHolder.set(dataSourceName);
    }

    public static String getDataSourceName() {
        return contextHolder.get();
    }

    public static void clearDataSourceName() {
        contextHolder.remove();
    }

}
