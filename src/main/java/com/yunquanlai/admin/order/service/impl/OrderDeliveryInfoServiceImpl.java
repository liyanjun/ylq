package com.yunquanlai.admin.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.order.dao.OrderDeliveryInfoDao;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("orderDeliveryInfoService")
@Transactional(rollbackFor = Exception.class)
public class OrderDeliveryInfoServiceImpl implements OrderDeliveryInfoService {
	@Autowired
	private OrderDeliveryInfoDao orderDeliveryInfoDao;
	
	@Override
	public OrderDeliveryInfoEntity queryObject(Integer id){
		return orderDeliveryInfoDao.queryObject(id,false);
	}
	
	@Override
	public List<OrderDeliveryInfoEntity> queryList(Map<String, Object> map){
		return orderDeliveryInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderDeliveryInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderDeliveryInfoEntity orderDeliveryInfo){
		orderDeliveryInfoDao.save(orderDeliveryInfo);
	}
	
	@Override
	public void update(OrderDeliveryInfoEntity orderDeliveryInfo){
		orderDeliveryInfoDao.update(orderDeliveryInfo);
	}
	
	@Override
	public void delete(Integer id){
		orderDeliveryInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		orderDeliveryInfoDao.deleteBatch(ids);
	}
	
}
