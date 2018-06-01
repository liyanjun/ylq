package com.yunlaiquan.admin.product.service;


import com.yunlaiquan.admin.product.entity.ProductStockEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-01 22:49:34
 */
public interface ProductStockService {
	
	ProductStockEntity queryObject(Long id);
	
	List<ProductStockEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ProductStockEntity productStock);
	
	void update(ProductStockEntity productStock);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

    void addStock(ProductStockEntity productStock);
}
