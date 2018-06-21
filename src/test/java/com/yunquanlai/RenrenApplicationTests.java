package com.yunquanlai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunquanlai.admin.comment.entity.CommentDeliveryEntity;
import com.yunquanlai.admin.comment.entity.CommentProductEntity;
import com.yunquanlai.api.comsumer.vo.OrderCommentVO;
import com.yunquanlai.api.event.OrderDeliveryNotifyEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Collections;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RenrenApplicationTests {

//	/**
//	 * 上下文对象
//	 */
//	@Resource
//	private ApplicationContext applicationContext;
//	ObjectMapper objectMapper = new ObjectMapper();
//	@Test
//	public void contextLoads() {
//		try {
//			OrderCommentVO o = new OrderCommentVO();
//			o.setCommentDeliveryEntity(new CommentDeliveryEntity());
//			o.setCommentProductEntities(Collections.singletonList(new CommentProductEntity()));
//			System.out.println(objectMapper.writeValueAsString(o));
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		applicationContext.publishEvent(new OrderDeliveryNotifyEvent("9ef699f5451d755c8fe3690c0b8050ed"));
//		applicationContext.publishEvent(new OrderDeliveryNotifyEvent("c758f22fe07a1d2fcb9c2bf100882647"));
//	}

}
