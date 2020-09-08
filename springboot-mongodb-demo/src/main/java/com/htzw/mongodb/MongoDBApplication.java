package com.htzw.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author J.L.G
 * @version 1.0
 * @date 2020/9/7 15:49
 */
@EnableSwagger2
@SpringBootApplication
public class MongoDBApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongoDBApplication.class, args);
    }
}
