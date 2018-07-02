package com.yunquanlai.admin.product.service.impl;

import com.yunquanlai.admin.delivery.dao.DeliveryEndpointDao;
import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.product.dao.*;
import com.yunquanlai.admin.product.entity.ProductDetailEntity;
import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.product.entity.ProductInfoVO;
import com.yunquanlai.admin.product.entity.ProductStockEntity;
import com.yunquanlai.admin.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    @Autowired
    private ProductStockFlowDao productStockFlowDao;

    @Override
    @Cacheable(value = "product", key = "#id")
    public ProductInfoVO queryProductInfoVO(Long id) {
        ProductInfoEntity productInfoEntity = productInfoDao.queryObject(id, false);
        ProductDetailEntity productDetailEntity = productDetailDao.queryObjectByProductInfoId(id);
        return new ProductInfoVO(productInfoEntity, productDetailEntity);
    }

    @Override
    public ProductInfoEntity queryObject(Long id) {
        return null;
    }

    @Override
    public ProductInfoEntity queryObjectByProductNum(String productNum) {
        return productInfoDao.queryObjectByProductNum(productNum);
    }

    @Override
    public List<ProductInfoEntity> queryList(Map<String, Object> map) {
        return productInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return productInfoDao.queryTotal(map);
    }

    @Override
    public void save(ProductInfoVO productInfoVO) {
        ProductInfoEntity productInfoEntity = productInfoVO.getProductInfoEntity();
        ProductDetailEntity productDetailEntity = productInfoVO.getProductDetailEntity();

        productInfoEntity.setBrandName(productBrandDao.queryObject(productInfoEntity.getBrandId(), false).getName());
        productInfoEntity.setStatus(10);
        productInfoDao.save(productInfoEntity);
        productDetailEntity.setProductInfoId(productInfoEntity.getId());
        productDetailEntity.setAverageLevel(BigDecimal.ZERO);
        productDetailDao.save(productDetailEntity);
        List<DeliveryEndpointEntity> deliveryEndpoints = deliveryEndpointDao.queryList(null);
        for (DeliveryEndpointEntity deliveryEndpointEntity : deliveryEndpoints) {
            ProductStockEntity productStockEntity = new ProductStockEntity(productInfoEntity, deliveryEndpointEntity);
            productStockDao.save(productStockEntity);
        }
    }

    @Override
    @CacheEvict(value = "product", key = "#productInfoVO.productInfoEntity.id")
    public void update(ProductInfoVO productInfoVO) {
        ProductInfoEntity temp = productInfoDao.queryObject(productInfoVO.getProductInfoEntity().getId(), true);
        ProductInfoEntity productInfoEntity = productInfoVO.getProductInfoEntity();
        ProductDetailEntity productDetailEntityEdit = productInfoVO.getProductDetailEntity();
        ProductDetailEntity productDetailEntity = productDetailDao.queryObjectByProductInfoId(productInfoEntity.getId());
        productInfoDao.update(productInfoEntity);
        productDetailEntityEdit.setId(productDetailEntity.getId());
        productDetailDao.update(productDetailEntityEdit);
        if (!temp.getName().equals(productInfoEntity.getName())) {
            ProductStockEntity p = new ProductStockEntity();
            p.setProductInfoId(productInfoEntity.getId());
            p.setProductName(productInfoEntity.getName());
            productStockDao.updateProductName(p);
        }
    }

    @Override
    public void updateShelves(Map<String, Object> map) {
        productInfoDao.updateShelves(map);
    }


    @Override
    public void delete(Long id) {
        productInfoDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        for (Long id : ids) {
            List<ProductStockEntity> productStockEntities = productStockDao.queryByProductId(id);
            for (ProductStockEntity productStockEntity : productStockEntities) {
                productStockDao.delete(productStockEntity.getId());
                productStockFlowDao.deleteByStockId(productStockEntity.getId());
            }
        }
        productInfoDao.deleteBatch(ids);
    }

    @Override
    public List<ProductInfoEntity> queryListForClient(Map<String, Object> map) {
        return productInfoDao.queryListForClient(map);
    }

}
