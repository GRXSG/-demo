package top.wennyy.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author HanWen
 * @Date 2022/11/17 23:31
 * @Version 1.0
 */
//配置MP的分页插件
    @Configuration
public class MybatisPlusConfig {
        @Bean
        public MybatisPlusInterceptor mybatisPlusInterceptor(){
            MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
            mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
            return mybatisPlusInterceptor;
        }

}
