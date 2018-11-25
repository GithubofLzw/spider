package com.lzw.springbootmybatismodel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lzw.springbootmybatismodel.mapper")//指定项目中mapper接口所在包的路径
public class SpringbootmybatismodelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmybatismodelApplication.class, args);
    }
}
