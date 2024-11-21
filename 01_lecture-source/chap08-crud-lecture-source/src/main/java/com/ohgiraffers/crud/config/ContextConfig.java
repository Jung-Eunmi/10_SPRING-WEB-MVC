package com.ohgiraffers.crud.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= "com.ohgiraffers.crud")
// Spring 이 아닌 MyBatis 의 것을 쓰는 것이기때문에 Bean 으로 담을 때 Scan 을 따로 해줘야함
@MapperScan(basePackages = "com.ohgiraffers.crud", annotationClass = Mapper.class)
public class ContextConfig {}
