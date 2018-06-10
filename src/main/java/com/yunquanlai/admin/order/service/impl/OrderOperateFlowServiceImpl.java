package com.yunquanlai.admin.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.order.dao.OrderOperateFlowDao;
import com.yunquanlai.admin.order.entity.OrderOperateFlowEntity;
import com.yunquanlai.admin.order.service.OrderOperateFlowService;
import org.springframework.transaction.annotation.Transactional;


@Service("orderOperateFlowService")
@Transactional(rollbackFor = Exception.class)
public class OrderOperateFlowServiceImpl implements OrderOperateFlowService {
	@Autowired
	private OrderOperateFlowDao orderOperateFlowDao;
	
	@Override
	public OrderOperateFlowEntity queryObject(Long id){
		return orderOperateFlowDao.queryObject(id,false);
	}
	
	@Override
	public List<OrderOperateFlowEntity> queryList(Map<String, Object> map){
		return orderOperateFlowDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderOperateFlowDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderOperateFlowEntity orderOperateFlow){
		orderOperateFlowDao.save(orderOperateFlow);
	}
	
	@Override
	public void update(OrderOperateFlowEntity orderOperateFlow){
		orderOperateFlowDao.update(orderOperateFlow);
	}
	
	@Override
	public void delete(Long id){
		orderOperateFlowDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		orderOperateFlowDao.deleteBatch(ids);
	}
	
}
