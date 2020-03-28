package com.tsing.grid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;
@EnableCaching
@SpringBootApplication(scanBasePackages = "com.tsing.grid")
@MapperScan("com.tsing.grid.mapper")
public class GridApplication {

    public static void main(String[] args) {
        SpringApplication.run(GridApplication.class, args);
    }

}
