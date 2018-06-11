package com.yunquanlai.admin.comment.service.impl;

import com.yunquanlai.admin.comment.dao.CommentDeliveryDao;
import com.yunquanlai.admin.comment.entity.CommentDeliveryEntity;
import com.yunquanlai.admin.comment.service.CommentDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("commentDeliveryService")
@Transactional(rollbackFor = Exception.class)
public class CommentDeliveryServiceImpl implements CommentDeliveryService {
	@Autowired
	private CommentDeliveryDao commentDeliveryDao;
	
	@Override
	public CommentDeliveryEntity queryObject(Integer id){
		return commentDeliveryDao.queryObject(id,false);
	}
	
	@Override
	public List<CommentDeliveryEntity> queryList(Map<String, Object> map){
		return commentDeliveryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return commentDeliveryDao.queryTotal(map);
	}
	
	@Override
	public void save(CommentDeliveryEntity commentDelivery){
		commentDeliveryDao.save(commentDelivery);
	}
	
	@Override
	public void update(CommentDeliveryEntity commentDelivery){
		commentDeliveryDao.update(commentDelivery);
	}
	
	@Override
	public void delete(Integer id){
		commentDeliveryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		commentDeliveryDao.deleteBatch(ids);
	}

}
