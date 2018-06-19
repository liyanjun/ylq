package com.yunquanlai.admin.order.service;

import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.api.delivery.vo.DeliveryDateVO;

import java.util.List;
import java.util.Map;

/**
 * 订单配送信息表
 *
 * @author liyanjun
 * @email
 * @date 2018-06-04 22:42:21
 */
public interface OrderDeliveryInfoService {

    OrderDeliveryInfoEntity queryObject(Long id);

    List<OrderDeliveryInfoEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(OrderDeliveryInfoEntity orderDeliveryInfo);

    void update(OrderDeliveryInfoEntity orderDeliveryInfo);

    void delete(Long id);

    void deleteBatch(Long[] ids);

    List<OrderDeliveryInfoEntity> queryByDistributorId(Map<String, Object> filter);

    void orderDelivery(DeliveryDistributorEntity deliveryDistributorEntity, OrderDeliveryInfoEntity orderDeliveryInfoEntity);

    OrderDeliveryInfoEntity queryObjectByOrderId(Long orderId);

    /**
     * 标记订单异常
     *
     * @param orderDeliveryInfoEntity
     * @param exception
     */
    void markerException(OrderDeliveryInfoEntity orderDeliveryInfoEntity, String exception);

    /**
     * 配送单分配超时
     *
     * @param orderDeliveryInfoEntity
     */
    void distributorTimeOut(OrderDeliveryInfoEntity orderDeliveryInfoEntity);

    /**
     * 配送单配送
     *
     * @param id
     */
    void distributeOrder(Long id);

    DeliveryDateVO queryDeliveryDate(Map<String, Object> filter);
}
