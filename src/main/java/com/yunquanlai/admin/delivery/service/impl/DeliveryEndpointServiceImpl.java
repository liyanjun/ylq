package com.yunquanlai.admin.delivery.service.impl;

import com.yunquanlai.admin.delivery.dao.DeliveryEndpointDao;
import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.delivery.service.DeliveryEndpointService;
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
        // TODO 初始化库存信息，详细见商品的save
        deliveryEndpointDao.save(deliveryEndpoint);
    }

    @Override
    public void update(DeliveryEndpointEntity deliveryEndpoint) {
        // TODO 修改配送点名称时要级联修改，库存里面的名称
        deliveryEndpointDao.update(deliveryEndpoint);
    }

    @Override
    public void delete(Long id) {
        deliveryEndpointDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        deliveryEndpointDao.deleteBatch(ids);
    }

}
