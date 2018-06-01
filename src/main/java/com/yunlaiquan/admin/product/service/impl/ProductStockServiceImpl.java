package com.yunlaiquan.admin.product.service.impl;

import com.yunlaiquan.admin.product.dao.ProductStockDao;
import com.yunlaiquan.admin.product.entity.ProductStockEntity;
import com.yunlaiquan.admin.product.service.ProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;




@Service("productStockService")
@Transactional(rollbackFor = Exception.class)
public class ProductStockServiceImpl implements ProductStockService {
	@Autowired
	private ProductStockDao productStockDao;
	
	@Override
	public ProductStockEntity queryObject(Long id){
		return productStockDao.queryObject(id, false);
	}
	
	@Override
	public List<ProductStockEntity> queryList(Map<String, Object> map){
		return productStockDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return productStockDao.queryTotal(map);
	}
	
	@Override
	public void save(ProductStockEntity productStock){
		productStockDao.save(productStock);
	}
	
	@Override
	public void update(ProductStockEntity productStock){
		productStockDao.update(productStock);
	}
	
	@Override
	public void delete(Long id){
		productStockDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		productStockDao.deleteBatch(ids);
	}

	@Override
	public void addStock(ProductStockEntity productStock) {
		ProductStockEntity temp = productStockDao.queryObject(productStock.getId(),true);
		temp.setCount(productStock.getCount() + productStock.getCountAdd());
		productStockDao.update(temp);
	}

}
