package com.wuhanyunzhong.itass;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
@MapperScan("com.wuhanyunzhong.itass.mapper")
public class ItassApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItassApplication.class, args);
    }

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://49.235.250.135:6379").setDatabase(0).setPassword("937122");
        return (Redisson)Redisson.create(config);
    }
}
