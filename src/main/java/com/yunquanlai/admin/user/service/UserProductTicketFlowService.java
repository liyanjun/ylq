package com.yunquanlai.admin.user.service;

import com.yunquanlai.admin.user.entity.UserProductTicketFlowEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户水票消费流水
 * 
 * @author weicc
 * @email 
 * @date 2018-07-20 18:14:10
 */
public interface UserProductTicketFlowService {
	
	UserProductTicketFlowEntity queryObject(Long id);
	
	List<UserProductTicketFlowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserProductTicketFlowEntity userProductTicketFlow);
	
	void update(UserProductTicketFlowEntity userProductTicketFlow);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
