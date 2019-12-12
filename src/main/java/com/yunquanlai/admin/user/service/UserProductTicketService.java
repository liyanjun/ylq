package com.yunquanlai.admin.user.service;

import com.yunquanlai.admin.product.entity.ProductTicketEntity;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.entity.UserProductTicketEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户水票信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-07-17 12:11:07
 */
public interface UserProductTicketService {
	
	UserProductTicketEntity queryObject(Long id);
	
	List<UserProductTicketEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserProductTicketEntity userProductTicket);
	
	void update(UserProductTicketEntity userProductTicket);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

    void buyTicket(UserInfoEntity user, ProductTicketEntity productTicket);

	void pay(String outTradeNo, Integer totalFee);
}
