package com.yunquanlai.admin.order.service;

import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.comsumer.vo.OrderVO;

import java.util.List;
import java.util.Map;

/**
 * 订单信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 22:42:21
 */
public interface OrderInfoService {
	
	OrderInfoEntity queryObject(Integer id);
	
	List<OrderInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderInfoEntity orderInfo);
	
	void update(OrderInfoEntity orderInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	boolean newOrder(OrderVO orderVO, UserInfoEntity user);
}
