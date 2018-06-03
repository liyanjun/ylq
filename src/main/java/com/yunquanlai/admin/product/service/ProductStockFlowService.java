package com.yunquanlai.admin.product.service;


import com.yunquanlai.admin.product.entity.ProductStockFlowEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存流水信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-02 22:31:01
 */
public interface ProductStockFlowService {
	
	ProductStockFlowEntity queryObject(Long id);
	
	List<ProductStockFlowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ProductStockFlowEntity productStockFlow);
	
	void update(ProductStockFlowEntity productStockFlow);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
