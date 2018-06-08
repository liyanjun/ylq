package com.yunquanlai.admin.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.order.dao.OrderOperateFlowDao;
import com.yunquanlai.admin.order.entity.OrderOperateFlowEntity;
import com.yunquanlai.admin.order.service.OrderOperateFlowService;
import org.springframework.transaction.annotation.Transactional;


@Service("orderOpreateFlowService")
@Transactional(rollbackFor = Exception.class)
public class OrderOperateFlowServiceImpl implements OrderOperateFlowService {
	@Autowired
	private OrderOperateFlowDao orderOpreateFlowDao;
	
	@Override
	public OrderOperateFlowEntity queryObject(Integer id){
		return orderOpreateFlowDao.queryObject(id,false);
	}
	
	@Override
	public List<OrderOperateFlowEntity> queryList(Map<String, Object> map){
		return orderOpreateFlowDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderOpreateFlowDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderOperateFlowEntity orderOpreateFlow){
		orderOpreateFlowDao.save(orderOpreateFlow);
	}
	
	@Override
	public void update(OrderOperateFlowEntity orderOpreateFlow){
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
