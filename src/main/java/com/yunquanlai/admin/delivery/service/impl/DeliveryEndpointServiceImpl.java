package com.yunquanlai.admin.delivery.service.impl;

import com.yunquanlai.admin.delivery.dao.DeliveryEndpointDao;
import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.delivery.service.DeliveryEndpointService;
import com.yunquanlai.admin.product.dao.ProductInfoDao;
import com.yunquanlai.admin.product.dao.ProductStockDao;
import com.yunquanlai.admin.product.dao.ProductStockFlowDao;
import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.product.entity.ProductStockEntity;
import com.yunquanlai.admin.product.service.ProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("deliveryEndpointService")
@Transactional(rollbackFor = Exception.class)
public class DeliveryEndpointServiceImpl implements DeliveryEndpointService {
    @Autowired
    private DeliveryEndpointDao deliveryEndpointDao;

    @Autowired
    private ProductStockDao productStockDao;

    @Autowired
    private ProductStockFlowDao productStockFlowDao;

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public DeliveryEndpointEntity queryObject(Long id) {
        return deliveryEndpointDao.queryObject(id, false);
    }

    @Override
    public List<DeliveryEndpointEntity> queryList(Map<String, Object> map) {
        return deliveryEndpointDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return deliveryEndpointDao.queryTotal(map);
    }

    @Override
    public void save(DeliveryEndpointEntity deliveryEndpoint) {
        List<ProductInfoEntity> productInfoEntities = productInfoDao.queryList(null);
        deliveryEndpointDao.save(deliveryEndpoint);
        for (ProductInfoEntity productInfoEntity : productInfoEntities) {
            ProductStockEntity productStockEntity = new ProductStockEntity(productInfoEntity, deliveryEndpoint);
            productStockDao.save(productStockEntity);
        }

    }

    @Override
    public void update(DeliveryEndpointEntity deliveryEndpoint) {
        DeliveryEndpointEntity deliveryEndpointEntity = deliveryEndpointDao.queryObject(deliveryEndpoint, true);
        if (deliveryEndpoint == null) {
            return;
        }
        if (!deliveryEndpoint.getName().equals(deliveryEndpointEntity.getName())) {
            ProductStockEntity p = new ProductStockEntity();
            p.setDeliveryEndpointId(deliveryEndpoint.getId());
            p.setDeliveryName(deliveryEndpoint.getName());
            // 修改配送点名称时要级联修改，库存里面的名称,商品同理
            productStockDao.updateDeliveryEndpointName(p);
        }

        deliveryEndpointDao.update(deliveryEndpoint);
    }

    @Override
    public void delete(Long id) {
        deliveryEndpointDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        for (Long id : ids) {
            List<ProductStockEntity> productStockEntities = productStockDao.queryByDeliveryEndpointId(id);
            for (ProductStockEntity productStockEntity : productStockEntities) {
                productStockDao.delete(productStockEntity.getId());
                productStockFlowDao.deleteByStockId(productStockEntity.getId());
            }
        }
        deliveryEndpointDao.deleteBatch(ids);
    }

}
