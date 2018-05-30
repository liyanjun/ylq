package com.yunlaiquan.admin.product.service.impl;

import com.yunlaiquan.admin.product.dao.ProductBrandDao;
import com.yunlaiquan.admin.product.dao.ProductInfoDao;
import com.yunlaiquan.admin.product.entity.ProductInfoEntity;
import com.yunlaiquan.admin.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {
	@Autowired
	private ProductInfoDao productInfoDao;

	@Autowired
	private ProductBrandDao productBrandDao;

	@Override
	public ProductInfoEntity queryObject(Integer id){
		return productInfoDao.queryObject(id);
	}

	@Override
	public List<ProductInfoEntity> queryList(Map<String, Object> map){
		return productInfoDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return productInfoDao.queryTotal(map);
	}

	@Override
	public void save(ProductInfoEntity productInfo){
		productInfo.setBrandName(productBrandDao.queryObject(productInfo.getBrandId()).getName());
		productInfoDao.save(productInfo);
	}

	@Override
	public void update(ProductInfoEntity productInfo){
		productInfoDao.update(productInfo);
	}

	@Override
	public void delete(Integer id){
		productInfoDao.delete(id);
	}

	@Override
	public void deleteBatch(Integer[] ids){
		productInfoDao.deleteBatch(ids);
	}
	
}
