package com.yunquanlai;

import com.yunquanlai.api.event.OrderDeliveryNotifyEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RenrenApplicationTests {

	/**
	 * 上下文对象
	 */
	@Resource
	private ApplicationContext applicationContext;
	@Test
	public void contextLoads() {
		applicationContext.publishEvent(new OrderDeliveryNotifyEvent("c7a78eaace99133d894df15d087a2bba4fb2aa3b7d96ffde057c86a746c9cf9c"));
	}

}
