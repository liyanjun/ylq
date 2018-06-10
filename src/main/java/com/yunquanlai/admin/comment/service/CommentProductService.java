package com.yunquanlai.admin.comment.service;

import com.yunquanlai.admin.comment.entity.CommentProductEntity;

import java.util.List;
import java.util.Map;

/**
 * 配送员评价
 * 
 * @author weicc
 * @email 
 * @date 2018-06-10 16:28:26
 */
public interface CommentProductService {
	
	CommentProductEntity queryObject(Integer id);
	
	List<CommentProductEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CommentProductEntity commentProduct);
	
	void update(CommentProductEntity commentProduct);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
