package com.yunquanlai.admin.product.service;


import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.product.entity.ProductInfoVO;

import java.util.List;
import java.util.Map;

/**
 * 商品信息表表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-30 17:16:47
 */
public interface ProductInfoService {
	
	ProductInfoVO queryObject(Integer id);
	
	List<ProductInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ProductInfoVO productInfoVO);
	
	void update(ProductInfoVO productInfoVO);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
