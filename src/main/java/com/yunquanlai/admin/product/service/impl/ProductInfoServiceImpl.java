package com.yunquanlai.admin.product.service.impl;

import com.yunquanlai.admin.delivery.dao.DeliveryEndpointDao;
import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.product.dao.ProductBrandDao;
import com.yunquanlai.admin.product.dao.ProductDetailDao;
import com.yunquanlai.admin.product.dao.ProductInfoDao;
import com.yunquanlai.admin.product.dao.ProductStockDao;
import com.yunquanlai.admin.product.entity.ProductDetailEntity;
import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.product.entity.ProductInfoVO;
import com.yunquanlai.admin.product.entity.ProductStockEntity;
import com.yunquanlai.admin.product.service.ProductInfoService;
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

	@Autowired
	private DeliveryEndpointDao deliveryEndpointDao;

	@Autowired
	private ProductStockDao productStockDao;

	@Override
	public ProductInfoVO queryObject(Integer id){
		ProductInfoEntity productInfoEntity = productInfoDao.queryObject(id, false);
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

		productInfoEntity.setBrandName(productBrandDao.queryObject(productInfoEntity.getBrandId(), false).getName());
		productInfoDao.save(productInfoEntity);
		productDetailEntity.setProductInfoId(productInfoEntity.getId());
		productDetailDao.save(productDetailEntity);
		List<DeliveryEndpointEntity> deliveryEndpoints = deliveryEndpointDao.queryList(null);
		for (DeliveryEndpointEntity deliveryEndpointEntity: deliveryEndpoints) {
			ProductStockEntity productStockEntity = new ProductStockEntity();
			productStockEntity.setProductInfoId(productInfoEntity.getId());
			productStockEntity.setProductName(productInfoEntity.getName());
			productStockEntity.setDeliveryName(deliveryEndpointEntity.getName());
			productStockEntity.setDeliveryEndpointId(deliveryEndpointEntity.getId());
			productStockEntity.setCount(0);
			productStockDao.save(productStockEntity);
		}
	}

	@Override
	public void update(ProductInfoVO productInfoVO){
		// TODO 修改商品名称时要级联修改，库存里面的名称
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
		// TODO 删除对应库存信息
	}

	@Override
	public void deleteBatch(Integer[] ids){
		productInfoDao.deleteBatch(ids);
	}
	
}
