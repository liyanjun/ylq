package com.yunquanlai.admin.order.service;

import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.comsumer.vo.OrderVO;
import com.yunquanlai.utils.R;

import java.util.List;
import java.util.Map;

/**
 * 订单信息表
 *
 * @author liyanjun
 * @email
 * @date 2018-06-04 22:42:21
 */
public interface OrderInfoService {

    OrderInfoEntity queryObject(Long id);

    List<OrderInfoEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(OrderInfoEntity orderInfo);

    void update(OrderInfoEntity orderInfo);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    R newOrder(OrderVO orderVO, UserInfoEntity user);

    /**
     * 标记订单已支付
     *
     * @param out_trade_no 微信返回的商户号 ID
     * @param total_fee    微信返回的支付金额
     */
    void orderPay(Object out_trade_no, Object total_fee);

    /**
     * 处理订单配送逻辑
     * @param orderId
     */
    void orderDelivery(Object orderId);
}
