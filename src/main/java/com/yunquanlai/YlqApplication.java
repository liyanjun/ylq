package com.yunquanlai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan({"com.yunquanlai.dao","com.yunquanlai.admin.*.dao"})
public class YlqApplication {

	public static void main(String[] args) {
		SpringApplication.run(YlqApplication.class, args);
	}
}
