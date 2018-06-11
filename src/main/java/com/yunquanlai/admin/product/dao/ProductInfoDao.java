package com.yunquanlai.admin.product.dao;

import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.system.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 商品信息表表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-30 17:16:47
 */
public interface ProductInfoDao extends BaseDao<ProductInfoEntity> {

    List<ProductInfoEntity> queryListForClient(Map<String, Object> map);

    void updateBrandName(ProductInfoEntity productInfoEntity);
}
