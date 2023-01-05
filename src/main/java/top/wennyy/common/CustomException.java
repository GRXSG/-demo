package top.wennyy.common;

/**
 * @Author HanWen
 * @Date 2022/11/28 15:15
 * @Version 1.0
 * 自定义业务异常类
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }

}
