package com.yunquanlai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication()
@MapperScan({"com.yunquanlai.admin.system.dao","com.yunquanlai.admin.*.dao"})
public class YlqApplication {

	public static void main(String[] args) {
		SpringApplication.run(YlqApplication.class, args);
	}
}
