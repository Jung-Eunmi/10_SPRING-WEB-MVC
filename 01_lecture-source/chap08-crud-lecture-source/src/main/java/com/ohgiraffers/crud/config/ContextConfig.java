package com.ohgiraffers.crud.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
@ComponentScan(basePackages= "com.ohgiraffers.crud")
// Spring 이 아닌 MyBatis 의 것을 쓰는 것이기때문에 Bean 으로 담을 때 Scan 을 따로 해줘야함
@MapperScan(basePackages = "com.ohgiraffers.crud", annotationClass = Mapper.class)
public class ContextConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        /* comment.
            message.properties 파일을 자바 객체 형식으로 읽어들일 수 있게 만든다. */
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        // classpath : src/main/resource, src/main/java 를 의미한다.
        source.setBasename("classpath:/messages/message"); // == src/main/resource/messages/message
        source.setDefaultEncoding("UTF-8");
        /* 제공하지 않는 언어로 요청 시에 사용 할 메세지 설정 */
        Locale.setDefault(Locale.KOREA);

        return source;
    }


}
