package com.yunlaiquan.admin.product.service.impl;

import com.yunlaiquan.admin.product.dao.ProductStockDao;
import com.yunlaiquan.admin.product.dao.ProductStockFlowDao;
import com.yunlaiquan.admin.product.entity.ProductStockEntity;
import com.yunlaiquan.admin.product.entity.ProductStockFlowEntity;
import com.yunlaiquan.admin.product.service.ProductStockService;
import com.yunlaiquan.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;




@Service("productStockService")
@Transactional(rollbackFor = Exception.class)
public class ProductStockServiceImpl implements ProductStockService {
	@Autowired
	private ProductStockDao productStockDao;

	@Autowired
	private ProductStockFlowDao productStockFlowDao;
	
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
	public void addStock(ProductStockEntity productStock, SysUserEntity user) {
		ProductStockFlowEntity productStockFlowEntity = new ProductStockFlowEntity();
		//记录库存流水
		ProductStockEntity temp = productStockDao.queryObject(productStock.getId(),true);
		productStockFlowEntity.setBeforeCount(temp.getCount());
		productStockFlowEntity.setCount(productStock.getCountAdd());
		productStockFlowEntity.setOpreator(user.getUsername());
		productStockFlowEntity.setOpreatorId(user.getUserId());
		productStockFlowEntity.setProductStockId(productStock.getId());
		productStockFlowEntity.setType(productStock.getCount() >= 0 ? 0 : 1);
		productStockFlowEntity.setCreationTime(new Date());

		temp.setCount(productStock.getCount() + productStock.getCountAdd());
		productStockFlowEntity.setAfterCount(temp.getCount());

		productStockDao.update(temp);
		productStockFlowDao.save(productStockFlowEntity);
	}

}
