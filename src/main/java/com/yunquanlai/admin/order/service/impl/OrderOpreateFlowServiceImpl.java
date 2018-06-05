package com.yunquanlai.admin.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.order.dao.OrderOpreateFlowDao;
import com.yunquanlai.admin.order.entity.OrderOpreateFlowEntity;
import com.yunquanlai.admin.order.service.OrderOpreateFlowService;
import org.springframework.transaction.annotation.Transactional;


@Service("orderOpreateFlowService")
@Transactional(rollbackFor = Exception.class)
public class OrderOpreateFlowServiceImpl implements OrderOpreateFlowService {
	@Autowired
	private OrderOpreateFlowDao orderOpreateFlowDao;
	
	@Override
	public OrderOpreateFlowEntity queryObject(Integer id){
		return orderOpreateFlowDao.queryObject(id,false);
	}
	
	@Override
	public List<OrderOpreateFlowEntity> queryList(Map<String, Object> map){
		return orderOpreateFlowDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderOpreateFlowDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderOpreateFlowEntity orderOpreateFlow){
		orderOpreateFlowDao.save(orderOpreateFlow);
	}
	
	@Override
	public void update(OrderOpreateFlowEntity orderOpreateFlow){
		orderOpreateFlowDao.update(orderOpreateFlow);
	}
	
	@Override
	public void delete(Integer id){
		orderOpreateFlowDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		orderOpreateFlowDao.deleteBatch(ids);
	}
	
}
