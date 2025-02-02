package com.yunquanlai.admin.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.entity.OrderOperateFlowEntity;
import com.yunquanlai.admin.order.entity.OrderProductDetailEntity;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.comsumer.vo.OrderCommentVO;
import com.yunquanlai.api.comsumer.vo.OrderVO;
import com.yunquanlai.utils.R;

import java.text.ParseException;
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

    void delete(Long id);

    void deleteBatch(Long[] ids);

    R newOrder(OrderVO orderVO, UserInfoEntity user) throws ParseException, JsonProcessingException;

    /**
     * 标记订单已支付
     *
     * @param out_trade_no 微信返回的商户号 ID
     * @param total_fee    微信返回的支付金额
     */
    void orderPay(Object out_trade_no, Integer total_fee);


    /**
     * 判断该配送点是否有充足库存和人手，如果充足完成配送单分配
     *
     * @param orderProductDetailEntities 购买商品信息
     * @param orderDeliveryInfoEntity    配送单信息
     * @param deliveryEndpointEntity     配送点信息
     */
    boolean findDeliveryDistributor(List<OrderProductDetailEntity> orderProductDetailEntities, OrderDeliveryInfoEntity orderDeliveryInfoEntity, DeliveryEndpointEntity deliveryEndpointEntity);

    /**
     * 关闭订单
     *
     * @param orderId
     */
    void closeOrder(Long orderId, Long userId);

    /**
     * 订单内容确定，计算押金
     *
     * @param orderVO
     * @param user
     *
     * @return
     */
    R confirm(OrderVO orderVO, UserInfoEntity user);

    /**
     * 标记异常
     *
     * @param orderInfoEntity
     * @param orderDeliveryInfoEntity
     */
    void markException(OrderInfoEntity orderInfoEntity, OrderDeliveryInfoEntity orderDeliveryInfoEntity);

    /**
     * 支付超时
     *
     * @param orderInfoEntity
     */
    void payTimeOut(OrderInfoEntity orderInfoEntity);

    R saveComment(OrderCommentVO orderCommentVO, UserInfoEntity userInfoEntity);

    List<OrderInfoEntity> queryListClient(Map filter);

    void handle(Long orderId, OrderOperateFlowEntity orderOperateFlowEntity);

    void handDistribute(Long orderId, Long deliveryDistributorId, Long deliveryEndpointId, OrderOperateFlowEntity orderOperateFlowEntity);

    List<OrderInfoEntity> queryUnpaidByUserId(Long userId);

    R newTicketOrder(OrderVO orderVO, UserInfoEntity user) throws ParseException, JsonProcessingException;
}
