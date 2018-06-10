package com.yunquanlai.admin.comment.service;

import com.yunquanlai.admin.comment.entity.CommentDeliveryEntity;

import java.util.List;
import java.util.Map;

/**
 * 配送员评价
 * 
 * @author weicc
 * @email 
 * @date 2018-06-10 16:28:27
 */
public interface CommentDeliveryService {
	
	CommentDeliveryEntity queryObject(Integer id);
	
	List<CommentDeliveryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CommentDeliveryEntity commentDelivery);
	
	void update(CommentDeliveryEntity commentDelivery);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
