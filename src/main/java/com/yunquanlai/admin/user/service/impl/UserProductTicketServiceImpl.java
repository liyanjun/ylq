package com.yunquanlai.admin.user.service.impl;

import com.yunquanlai.admin.user.dao.UserProductTicketDao;
import com.yunquanlai.admin.user.entity.UserProductTicketEntity;
import com.yunquanlai.admin.user.service.UserProductTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("userProductTicketService")
@Transactional(rollbackFor = Exception.class)
public class UserProductTicketServiceImpl implements UserProductTicketService {
	@Autowired
	private UserProductTicketDao userProductTicketDao;
	
	@Override
	public UserProductTicketEntity queryObject(Long id){
		return userProductTicketDao.queryObject(id,false);
	}
	
	@Override
	public List<UserProductTicketEntity> queryList(Map<String, Object> map){
		return userProductTicketDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userProductTicketDao.queryTotal(map);
	}
	
	@Override
	public void save(UserProductTicketEntity userProductTicket){
		userProductTicketDao.save(userProductTicket);
	}
	
	@Override
	public void update(UserProductTicketEntity userProductTicket){
		userProductTicketDao.update(userProductTicket);
	}
	
	@Override
	public void delete(Long id){
		userProductTicketDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		userProductTicketDao.deleteBatch(ids);
	}
	
}
