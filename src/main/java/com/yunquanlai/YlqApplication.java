package com.yunquanlai;

import com.yunquanlai.config.wechat.WxMaProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.servlet.MultipartConfigElement;

@EnableAsync
@EnableCaching
@SpringBootApplication()
@MapperScan({"com.yunquanlai.admin.system.dao","com.yunquanlai.admin.*.dao"})
public class YlqApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(YlqApplication.class, args);
	}

	/**
	 * 文件上传临时路径
	 */
	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation("/app/tmp");
		return factory.createMultipartConfig();
	}
}
