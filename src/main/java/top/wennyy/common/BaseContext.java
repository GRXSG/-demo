package top.wennyy.common;

/**
 * @Author HanWen
 * @Date 2022/11/23 20:40
 * @Version 1.0
 */

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }
}
