package com.yunquanlai.admin.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.order.dao.OrderProductDetailDao;
import com.yunquanlai.admin.order.entity.OrderProductDetailEntity;
import com.yunquanlai.admin.order.service.OrderProductDetailService;
import org.springframework.transaction.annotation.Transactional;


@Service("orderProductDetailService")
@Transactional(rollbackFor = Exception.class)
public class OrderProductDetailServiceImpl implements OrderProductDetailService {
	@Autowired
	private OrderProductDetailDao orderProductDetailDao;
	
	@Override
	public OrderProductDetailEntity queryObject(Long id){
		return orderProductDetailDao.queryObject(id,false);
	}
	
	@Override
	public List<OrderProductDetailEntity> queryList(Map<String, Object> map){
		return orderProductDetailDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderProductDetailDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderProductDetailEntity orderProductDetail){
		orderProductDetailDao.save(orderProductDetail);
	}
	
	@Override
	public void update(OrderProductDetailEntity orderProductDetail){
		orderProductDetailDao.update(orderProductDetail);
	}
	
	@Override
	public void delete(Long id){
		orderProductDetailDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		orderProductDetailDao.deleteBatch(ids);
	}

	@Override
	public List<OrderProductDetailEntity> queryListByOrderId(Long orderInfoId) {
		return orderProductDetailDao.queryListByOrderId(orderInfoId);
	}

}
