package com.yunlaiquan.admin.product.dao;

import com.yunlaiquan.admin.product.entity.ProductDetailEntity;
import com.yunlaiquan.dao.BaseDao;

/**
 * @author liyanjun
 * @email
 * @date 2018-05-31 21:53:08
 */
public interface ProductDetailDao extends BaseDao<ProductDetailEntity> {

    /**
     * 根据 ProductInfoId 查找 ProductDetail 信息
     *
     * @param id
     */
    ProductDetailEntity queryObjectByProductInfoId(Object id);
}
