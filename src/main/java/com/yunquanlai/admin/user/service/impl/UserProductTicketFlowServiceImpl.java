package com.yunquanlai.admin.user.service.impl;

import com.yunquanlai.admin.user.dao.UserProductTicketFlowDao;
import com.yunquanlai.admin.user.entity.UserProductTicketFlowEntity;
import com.yunquanlai.admin.user.service.UserProductTicketFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("userProductTicketFlowService")
@Transactional(rollbackFor = Exception.class)
public class UserProductTicketFlowServiceImpl implements UserProductTicketFlowService {
	@Autowired
	private UserProductTicketFlowDao userProductTicketFlowDao;
	
	@Override
	public UserProductTicketFlowEntity queryObject(Long id){
		return userProductTicketFlowDao.queryObject(id,false);
	}
	
	@Override
	public List<UserProductTicketFlowEntity> queryList(Map<String, Object> map){
		return userProductTicketFlowDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userProductTicketFlowDao.queryTotal(map);
	}
	
	@Override
	public void save(UserProductTicketFlowEntity userProductTicketFlow){
		userProductTicketFlowDao.save(userProductTicketFlow);
	}
	
	@Override
	public void update(UserProductTicketFlowEntity userProductTicketFlow){
		userProductTicketFlowDao.update(userProductTicketFlow);
	}
	
	@Override
	public void delete(Long id){
		userProductTicketFlowDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		userProductTicketFlowDao.deleteBatch(ids);
	}
	
}
