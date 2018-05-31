package com.yunlaiquan.admin.product.service.impl;

import com.yunlaiquan.admin.product.dao.ProductBrandDao;
import com.yunlaiquan.admin.product.dao.ProductDetailDao;
import com.yunlaiquan.admin.product.dao.ProductInfoDao;
import com.yunlaiquan.admin.product.entity.ProductDetailEntity;
import com.yunlaiquan.admin.product.entity.ProductInfoEntity;
import com.yunlaiquan.admin.product.entity.ProductInfoVO;
import com.yunlaiquan.admin.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;




@Service("productInfoService")
@Transactional(rollbackFor = Exception.class)
public class ProductInfoServiceImpl implements ProductInfoService {
	@Autowired
	private ProductInfoDao productInfoDao;

	@Autowired
	private ProductBrandDao productBrandDao;

	@Autowired
	private ProductDetailDao productDetailDao;

	@Override
	public ProductInfoVO queryObject(Integer id){
		ProductInfoEntity productInfoEntity = productInfoDao.queryObject(id);
		ProductDetailEntity productDetailEntity = productDetailDao.queryObjectByProductInfoId(id);
		return new ProductInfoVO(productInfoEntity,productDetailEntity);
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
	public void save(ProductInfoVO productInfoVO){
		ProductInfoEntity productInfoEntity = productInfoVO.getProductInfoEntity();
		ProductDetailEntity productDetailEntity = productInfoVO.getProductDetailEntity();

		productInfoEntity.setBrandName(productBrandDao.queryObject(productInfoEntity.getBrandId()).getName());
		productInfoDao.save(productInfoEntity);
		productDetailEntity.setProductInfoId(productInfoEntity.getId());
		productDetailDao.save(productDetailEntity);
	}

	@Override
	public void update(ProductInfoVO productInfoVO){
		ProductInfoEntity productInfoEntity = productInfoVO.getProductInfoEntity();
		ProductDetailEntity productDetailEntityEdit = productInfoVO.getProductDetailEntity();
		ProductDetailEntity productDetailEntity = productDetailDao.queryObjectByProductInfoId(productInfoEntity.getId());
		productInfoDao.update(productInfoEntity);
		productDetailEntityEdit.setId(productDetailEntity.getId());
		productDetailDao.update(productDetailEntityEdit);
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
