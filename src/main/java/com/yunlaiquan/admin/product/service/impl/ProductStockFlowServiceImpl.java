package com.yunlaiquan.admin.product.service.impl;

import com.yunlaiquan.admin.product.dao.ProductStockFlowDao;
import com.yunlaiquan.admin.product.entity.ProductStockFlowEntity;
import com.yunlaiquan.admin.product.service.ProductStockFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;




@Service("productStockFlowService")
@Transactional(rollbackFor = Exception.class)
public class ProductStockFlowServiceImpl implements ProductStockFlowService {
	@Autowired
	private ProductStockFlowDao productStockFlowDao;
	
	@Override
	public ProductStockFlowEntity queryObject(Long id){
		return productStockFlowDao.queryObject(id,false);
	}
	
	@Override
	public List<ProductStockFlowEntity> queryList(Map<String, Object> map){
		return productStockFlowDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return productStockFlowDao.queryTotal(map);
	}
	
	@Override
	public void save(ProductStockFlowEntity productStockFlow){
		productStockFlowDao.save(productStockFlow);
	}
	
	@Override
	public void update(ProductStockFlowEntity productStockFlow){
		productStockFlowDao.update(productStockFlow);
	}
	
	@Override
	public void delete(Long id){
		productStockFlowDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		productStockFlowDao.deleteBatch(ids);
	}
	
}
