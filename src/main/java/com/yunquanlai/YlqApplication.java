package com.yunquanlai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCaching
@SpringBootApplication()
@MapperScan({"com.yunquanlai.admin.system.dao","com.yunquanlai.admin.*.dao"})
public class YlqApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(YlqApplication.class, args);
	}
}
