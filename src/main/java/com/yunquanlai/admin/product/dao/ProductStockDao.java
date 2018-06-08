package com.yunquanlai.admin.product.dao;

import com.yunquanlai.admin.product.entity.ProductStockEntity;
import com.yunquanlai.admin.system.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存信息表
 *
 * @author liyanjun
 * @email
 * @date 2018-06-01 22:49:34
 */
public interface ProductStockDao extends BaseDao<ProductStockEntity> {


    ProductStockEntity queryByDeliveryEndpointIdAndProductId(@Param("productId") Long id, @Param("endpointId") Long endpointId, @Param("isLock") boolean isLock);

    void updateDeliveryEndpointName(ProductStockEntity productStockEntity);

    void updateProductName(ProductStockEntity productStockEntity);
}
