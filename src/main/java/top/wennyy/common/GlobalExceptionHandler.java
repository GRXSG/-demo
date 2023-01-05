package top.wennyy.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLIntegrityConstraintViolationException;


/**
 * @Author HanWen
 * @Date 2022/11/17 20:20
 * @Version 1.0
 */
//全局异常处理
    @ControllerAdvice(annotations = {RestController.class, Controller.class})//拦截处理加了RestController注释的类
    @ResponseBody
    @Slf4j
public class GlobalExceptionHandler {
        //进行异常处理方法
        @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
        public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
            log.error(ex.getMessage());
            if(ex.getMessage().contains("Duplicate entry")){
                //转变成数组存在
                String[] split = ex.getMessage().split(" ");
                String msg = split[2] + "已存在";
                return R.error(msg);

            }
            return R.error("未知错误");
        }

        @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex){
            log.error(ex.getMessage());
            return R.error(ex.getMessage());
        }
}
