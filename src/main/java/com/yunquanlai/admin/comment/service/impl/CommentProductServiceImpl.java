package com.yunquanlai.admin.comment.service.impl;

import com.yunquanlai.admin.comment.dao.CommentProductDao;
import com.yunquanlai.admin.comment.entity.CommentProductEntity;
import com.yunquanlai.admin.comment.service.CommentProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("commentProductService")
@Transactional(rollbackFor = Exception.class)
public class CommentProductServiceImpl implements CommentProductService {
	@Autowired
	private CommentProductDao commentProductDao;
	
	@Override
	public CommentProductEntity queryObject(Integer id){
		return commentProductDao.queryObject(id,false);
	}
	
	@Override
	public List<CommentProductEntity> queryList(Map<String, Object> map){
		return commentProductDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return commentProductDao.queryTotal(map);
	}
	
	@Override
	public void save(CommentProductEntity commentProduct){
		commentProductDao.save(commentProduct);
	}
	
	@Override
	public void update(CommentProductEntity commentProduct){
		commentProductDao.update(commentProduct);
	}
	
	@Override
	public void delete(Integer id){
		commentProductDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		commentProductDao.deleteBatch(ids);
	}
	
}
