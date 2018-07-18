package com.yunquanlai.admin.product.service;

import com.yunquanlai.admin.product.entity.ProductTicketEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品水票信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-07-17 12:11:07
 */
public interface ProductTicketService {
	
	ProductTicketEntity queryObject(Long id);

	ProductTicketEntity queryObjectByProductTicketNum(String productTicketNum);

	List<ProductTicketEntity> queryList(Map<String, Object> map);

	List<ProductTicketEntity> queryListByProductId(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);
	
	void save(ProductTicketEntity productTicket);
	
	void update(ProductTicketEntity productTicket);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
