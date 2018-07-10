package com.yunquanlai.admin.order.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunquanlai.admin.comment.dao.CommentDeliveryDao;
import com.yunquanlai.admin.comment.dao.CommentProductDao;
import com.yunquanlai.admin.comment.entity.CommentDeliveryEntity;
import com.yunquanlai.admin.comment.entity.CommentProductEntity;
import com.yunquanlai.admin.delivery.dao.DeliveryDistributorDao;
import com.yunquanlai.admin.delivery.dao.DeliveryEndpointDao;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.order.dao.OrderDeliveryInfoDao;
import com.yunquanlai.admin.order.dao.OrderInfoDao;
import com.yunquanlai.admin.order.dao.OrderOperateFlowDao;
import com.yunquanlai.admin.order.dao.OrderProductDetailDao;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.entity.OrderOperateFlowEntity;
import com.yunquanlai.admin.order.entity.OrderProductDetailEntity;
import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.admin.product.dao.ProductDetailDao;
import com.yunquanlai.admin.product.dao.ProductInfoDao;
import com.yunquanlai.admin.product.dao.ProductStockDao;
import com.yunquanlai.admin.product.entity.ProductDetailEntity;
import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.product.entity.ProductStockEntity;
import com.yunquanlai.admin.system.dao.SysConfigDao;
import com.yunquanlai.admin.user.dao.UserInfoDao;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.comsumer.vo.OrderCommentVO;
import com.yunquanlai.api.comsumer.vo.OrderVO;
import com.yunquanlai.api.comsumer.vo.ProductOrderVO;
import com.yunquanlai.api.event.OrderDeliveryNotifyEvent;
import com.yunquanlai.api.event.OrderDistributeEvent;
import com.yunquanlai.utils.ConfigUtils;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.RRException;
import com.yunquanlai.utils.TokenUtils;
import com.yunquanlai.utils.validator.Assert;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service("orderInfoService")
@Transactional(rollbackFor = Exception.class)
public class OrderInfoServiceImpl implements OrderInfoService {

    Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private OrderProductDetailDao orderProductDetailDao;

    @Autowired
    private OrderDeliveryInfoDao orderDeliveryInfoDao;

    @Autowired
    private OrderOperateFlowDao orderOperateFlowDao;

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductStockDao productStockDao;

    @Autowired
    private DeliveryEndpointDao deliveryEndpointDao;

    @Autowired
    private DeliveryDistributorDao deliveryDistributorDao;

    @Autowired
    private SysConfigDao sysConfigDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private CommentDeliveryDao commentDeliveryDao;

    @Autowired
    private CommentProductDao commentProductDao;

    @Autowired
    private ProductDetailDao productDetailDao;

    @Autowired
    private ConfigUtils configUtils;
    /**
     * 上下文对象
     */
    @Resource
    private ApplicationContext applicationContext;

    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public OrderInfoEntity queryObject(Long id) {
        return orderInfoDao.queryObject(id, false);
    }

    @Override
    public List<OrderInfoEntity> queryList(Map<String, Object> map) {
        return orderInfoDao.queryList(map);
    }


    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderInfoDao.queryTotal(map);
    }

    @Override
    public void save(OrderInfoEntity orderInfo) {
        orderInfoDao.save(orderInfo);
    }

    @Override
    public void update(OrderInfoEntity orderInfo) {
        orderInfoDao.update(orderInfo);
    }

    @Override
    public void delete(Long id) {
        orderInfoDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        orderInfoDao.deleteBatch(ids);
    }

    @Override
    public R newOrder(OrderVO orderVO, UserInfoEntity user) throws ParseException, JsonProcessingException {
        //检查该用户之前是否有未支付订单
        List<OrderInfoEntity> orderInfoEntities = orderInfoDao.queryUnpaidByUserId(user.getId());
        for (OrderInfoEntity orderInfoEntity : orderInfoEntities) {
            if (orderInfoEntity != null && orderInfoEntity.getStatus() == 10) {
                //关闭未支付订单
                orderInfoEntity.setStatus(OrderInfoEntity.STATUS_CLOSE);
                orderInfoEntity.setCloseTime(new Date());
                orderInfoDao.update(orderInfoEntity);
            }
        }
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = new OrderDeliveryInfoEntity();
        OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
        List<OrderProductDetailEntity> orderProductDetailEntities = new ArrayList<>(16);
        BigDecimal bEmptyValue = configUtils.getEmptyValue();
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal amountDeliveryFee = BigDecimal.ZERO;
        BigDecimal deposit = BigDecimal.ZERO;

        for (ProductOrderVO productOrderVO : orderVO.getProductOrderVOList()) {
            // 计算订单总额,押金总额
            ProductInfoEntity productInfoEntity = productInfoDao.queryObject(productOrderVO.getProductInfoId(), false);
            if (productInfoEntity == null || productInfoEntity.getStatus() != 20) {
                return R.error("找不到购买的商品，可能商品已下架，请重新购买。");
            }
            amount = amount.add(productInfoEntity.getAmount().multiply(new BigDecimal(productOrderVO.getCount())));
            if (productInfoEntity.getAmountShow() != null) {
                amountTotal = amountTotal.add(productInfoEntity.getAmountShow().multiply(new BigDecimal(productOrderVO.getCount())));
            }

            if (productInfoEntity.getBucketType() == ProductInfoEntity.BUCKET_TYPE_RECYCLE) {
                deposit = deposit.add(bEmptyValue.multiply(new BigDecimal(productOrderVO.getCount())));
            }

            amountDeliveryFee = amountDeliveryFee.add(productInfoEntity.getDeliveryFee());
        }
        //押金校验 用户可用押金+本次提交押金>本次下单需要的押金阈值
        if(deposit.compareTo(user.getEnableDepositAmount().add(orderVO.getDeposit())) == 1){
            return R.error("缴纳押金不足，请重新选择押金金额。").put("orderToken",tokenUtils.getToken()).put("code",507);
        }
        for (ProductOrderVO productOrderVO : orderVO.getProductOrderVOList()) {
            ProductInfoEntity productInfoEntity = productInfoDao.queryObject(productOrderVO.getProductInfoId(), true);
            OrderProductDetailEntity orderProductDetailEntity = new OrderProductDetailEntity();
            orderProductDetailEntity.setCount(productOrderVO.getCount());
            orderProductDetailEntity.setProductInfoId(productInfoEntity.getId());
            orderProductDetailEntity.setProductName(productInfoEntity.getName());
            orderProductDetailEntity.setBucketType(productInfoEntity.getBucketType());
            orderProductDetailEntity.setAmount(productInfoEntity.getAmount());
            orderProductDetailEntities.add(orderProductDetailEntity);
            productInfoEntity.setCount(productInfoEntity.getCount() + productOrderVO.getCount());
            productInfoDao.update(productInfoEntity);
        }
        orderInfoEntity.setDeposit(orderVO.getDeposit());
        orderInfoEntity.setBucketNum(orderVO.getBucketNum());
        orderInfoEntity.setAmount(amount);
        orderInfoEntity.setAmountTotal(amountTotal);
        orderInfoEntity.setAmountDeliveryFee(amountDeliveryFee);
        orderInfoEntity.setUsername(user.getUsername());
        orderInfoEntity.setUserPhone(orderVO.getPhone());
        orderInfoEntity.setUserInfoId(user.getId());
        orderInfoEntity.setCreationTime(new Date());
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_NEW);
        orderInfoEntity.setType(OrderInfoEntity.TYPE_NORMAL);
        orderInfoEntity.setPayType(OrderInfoEntity.PAY_TYPE_CASH);
        orderInfoEntity.setRemark(orderVO.getRemark());
        orderInfoEntity.setDetail(mapper.writeValueAsString(orderProductDetailEntities));
        // 订单
        orderInfoDao.save(orderInfoEntity);
        orderDeliveryInfoEntity.setAddress(orderVO.getAddress());
        if (StringUtils.isNotBlank(orderVO.getDeliveryTime())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            orderDeliveryInfoEntity.setDeliveryTime(sdf.parse(orderVO.getDeliveryTime()));
        }

        orderDeliveryInfoEntity.setAmountDeliveryFee(amountDeliveryFee);
        orderDeliveryInfoEntity.setDetail(mapper.writeValueAsString(orderProductDetailEntities));
        orderDeliveryInfoEntity.setLocationX(orderVO.getLocationX());
        orderDeliveryInfoEntity.setLocationY(orderVO.getLocationY());
        orderDeliveryInfoEntity.setOrderInfoId(orderInfoEntity.getId());
        orderDeliveryInfoEntity.setPhone(orderVO.getPhone());
        orderDeliveryInfoEntity.setName(orderVO.getName());
        orderDeliveryInfoEntity.setRemark(orderVO.getRemark());
        orderDeliveryInfoEntity.setSex(orderVO.getSex());
        orderDeliveryInfoEntity.setUserInfoId(user.getId());
        orderDeliveryInfoEntity.setOrderInfoId(orderInfoEntity.getId());
        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_NEW);
        orderDeliveryInfoEntity.setCreationTime(new Date());
        orderDeliveryInfoEntity.setEmptyBarrels(0);
        // 配送单
        orderDeliveryInfoDao.save(orderDeliveryInfoEntity);
        // TODO 发票，发票抬头(先不做)

        for (OrderProductDetailEntity orderProductDetailEntity : orderProductDetailEntities) {
            orderProductDetailEntity.setOrderInfoId(orderInfoEntity.getId());
            // 订单商品明细
            orderProductDetailDao.save(orderProductDetailEntity);
        }
        return R.ok().put("orderInfo", orderInfoEntity).put("orderDetail", orderProductDetailEntities);
    }

    @Override
    public void orderPay(Object outTradeNo, Object totalFee) {
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(outTradeNo, true);
        if (orderInfoEntity == null) {
            throw new RuntimeException("找不到有效的订单");
        }
        // 订单不是可支付状态,幂等性返回(已关闭的订单，如果收到支付完成通知，把它拉起来到已支付，然后配送，已关闭指的是不能发起支付)
        if (orderInfoEntity.getStatus() != OrderInfoEntity.STATUS_NEW && orderInfoEntity.getStatus() != OrderInfoEntity.STATUS_CLOSE) {
            return;
        }
        // TODO 上线打开，校验金额，微信返回的金额单位是分，我们先除以100
//        BigDecimal wechatBackFee = new BigDecimal(totalFee.toString()).divide(BigDecimal.TEN).divide(BigDecimal.TEN);
        if (orderInfoEntity.getDeposit() != null && !orderInfoEntity.getDeposit().equals(BigDecimal.ZERO)) {
//            // 订单包含押金
//            if (!(orderInfoEntity.getAmount().add(orderInfoEntity.getDeposit())).equals(wechatBackFee)) {
//                throw new RuntimeException("支付金额不等于订单金额");
//            }
            // 更新用户押金
            UserInfoEntity userInfoEntity = userInfoDao.queryObject(orderInfoEntity.getUserInfoId(), true);
            userInfoEntity.setDepositAmount(userInfoEntity.getDepositAmount().add(orderInfoEntity.getDeposit()));
            userInfoEntity.setEnableDepositAmount(userInfoEntity.getEnableDepositAmount().add(orderInfoEntity.getDeposit()));
            userInfoDao.update(userInfoEntity);
//        } else {
//            // 订单不包含押金
//            if (!orderInfoEntity.getAmount().equals(wechatBackFee)) {
//                throw new RuntimeException("支付金额不等于订单金额");
//            }
        }
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_PAID);
        orderInfoEntity.setPaidTime(new Date());
        orderInfoDao.update(orderInfoEntity);

        OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObjectByOrderId(orderInfoEntity.getId(), true);
        if (orderDeliveryInfoEntity.getDeliveryTime() != null && orderDeliveryInfoEntity.getDeliveryTime().after(new Date())) {
            // 还未到期望配送时间，先不处理配送单，等定时任务处理分配
            return;
        }
        if (OrderDeliveryInfoEntity.STATUS_NEW != orderDeliveryInfoEntity.getStatus()) {
            logger.error("配送单" + orderDeliveryInfoEntity.getId() + "已支付并处理派送【" + orderDeliveryInfoEntity.getStatus() + "】");
            return;
        }
        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_UN_DISTRIBUTE);
        orderDeliveryInfoEntity.setDistributeTime(new Date());
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);

        applicationContext.publishEvent(new OrderDistributeEvent(orderInfoEntity.getId()));
    }

    @Override
    public boolean findDeliveryDistributor(List<OrderProductDetailEntity> orderProductDetailEntities, OrderDeliveryInfoEntity orderDeliveryInfoEntity, DeliveryEndpointEntity deliveryEndpointEntity) {
        //先锁配送点，再开始锁配送点库存，否则有可能造成死锁
        deliveryEndpointDao.queryObject(deliveryEndpointEntity.getId(), true);
        DeliveryDistributorEntity deliveryDistributorEntity;
        List<ProductStockEntity> productStockEntities;

        deliveryDistributorEntity = deliveryDistributorDao.pickOne(deliveryEndpointEntity.getId());
        if (deliveryDistributorEntity == null) {
            throw new RuntimeException();
            // 该配送点找不到能送的人，找下一个配送点
        }

        productStockEntities = checkStock(orderProductDetailEntities, deliveryEndpointEntity);
        if (productStockEntities.isEmpty()) {
            // 库存不足，回滚之前扣的库存，找下一个点
            throw new RuntimeException();
        }
        //有配送员，有库存,扣库存
        for (ProductStockEntity productStockEntity : productStockEntities) {
            productStockDao.update(productStockEntity);
        }
        // 分配订单，配送中订单数加一
        deliveryDistributorEntity.setOrderCount(deliveryDistributorEntity.getOrderCount() + 1);
        deliveryDistributorDao.update(deliveryDistributorEntity);

        // 更新订单分配时间
        OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
        orderInfoEntity.setId(orderDeliveryInfoEntity.getOrderInfoId());
        orderInfoEntity.setDistributeTime(new Date());
        orderInfoEntity.setDeliveryDistributorId(deliveryDistributorEntity.getId());
        orderInfoEntity.setDeliveryDistributorName(deliveryDistributorEntity.getName());
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_ON_DELIVERY);
        // 只要分配出去了，异常也是可以被宽恕的，毕竟要向前看
        orderInfoEntity.setException("");
        orderInfoEntity.setType(OrderInfoEntity.TYPE_NORMAL);
        orderInfoDao.update(orderInfoEntity);

        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_ON_DELIVERY);
        orderDeliveryInfoEntity.setDeliveryDistributorId(deliveryDistributorEntity.getId());
        orderDeliveryInfoEntity.setDeliveryDistributorName(deliveryDistributorEntity.getName());
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
        applicationContext.publishEvent(new OrderDeliveryNotifyEvent(orderDeliveryInfoEntity.getId()));
        return true;
    }

    @Override
    public void closeOrder(Long orderId, Long userId) {
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(orderId, true);
        Assert.isNotEqual(orderInfoEntity.getUserInfoId(), userId, "不能关闭别人的订单");
        Assert.isNotEqual(orderInfoEntity.getStatus(), OrderInfoEntity.STATUS_NEW, "订单不是可关闭状态");
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_CLOSE);
        orderInfoEntity.setCloseTime(new Date());
        orderInfoDao.update(orderInfoEntity);
    }

    @Override
    public R confirm(OrderVO orderVO, UserInfoEntity user) {
        List<OrderProductDetailEntity> orderProductDetailEntities = new ArrayList<>(16);
        BigDecimal bEmptyValue = configUtils.getEmptyValue();
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal deposit = BigDecimal.ZERO;

        for (ProductOrderVO productOrderVO : orderVO.getProductOrderVOList()) {
            // 计算订单总额，押金总额
            ProductInfoEntity productInfoEntity = productInfoDao.queryObject(productOrderVO.getProductInfoId(), false);
            if (productInfoEntity == null) {
                return R.error("找不到购买的商品【" + productOrderVO.getProductInfoId() + "】");
            }
            amount = amount.add(productInfoEntity.getAmount().multiply(new BigDecimal(productOrderVO.getCount())));
            if (productInfoEntity.getBucketType() == ProductInfoEntity.BUCKET_TYPE_RECYCLE) {
                deposit = deposit.add(bEmptyValue.multiply(new BigDecimal(productOrderVO.getCount())));
            }

            OrderProductDetailEntity orderProductDetailEntity = new OrderProductDetailEntity();
            orderProductDetailEntity.setCount(productOrderVO.getCount());
            orderProductDetailEntity.setProductInfoId(productInfoEntity.getId());
            orderProductDetailEntity.setProductName(productInfoEntity.getName());
            orderProductDetailEntity.setAmount(productInfoEntity.getAmount());
            orderProductDetailEntities.add(orderProductDetailEntity);
        }
        if(deposit.compareTo(user.getEnableDepositAmount()) == -1 || deposit.compareTo(user.getEnableDepositAmount()) == 0){
            deposit = BigDecimal.ZERO;
        }
        if(deposit.compareTo(user.getEnableDepositAmount()) == 1){
            deposit = deposit.subtract(user.getEnableDepositAmount());
        }
        return R.ok().put("orderProductDetails", orderProductDetailEntities).
                put("deposit", deposit).put("amount", amount).put("orderToken", tokenUtils.getToken());
    }

    @Override
    public void markException(OrderInfoEntity orderInfoEntity, OrderDeliveryInfoEntity orderDeliveryInfoEntity) {
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
        orderInfoDao.update(orderInfoEntity);
    }

    @Override
    public void payTimeOut(OrderInfoEntity temp) {
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(temp.getId(), true);
        if (orderInfoEntity.getStatus() == OrderInfoEntity.STATUS_NEW) {
            orderInfoEntity.setStatus(OrderInfoEntity.STATUS_CLOSE);
            OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObjectByOrderId(temp.getId(), true);
            orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_CLOSE);
            orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
            orderInfoDao.update(orderInfoEntity);
        }
    }

    @Override
    public R saveComment(OrderCommentVO orderCommentVO, UserInfoEntity userInfoEntity) throws RuntimeException{
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(orderCommentVO.getOrderId(), true);
        if (userInfoEntity.getId().longValue() != orderInfoEntity.getUserInfoId().longValue()) {
            return R.error("不能评价别人的订单。");
        }
        if (orderInfoEntity.getStatus() != OrderInfoEntity.STATUS_DELIVERY_END) {
            return R.error("订单已评论或未送达，不能评论。");
        }
        //计算商品平均评分
        Map<String, Object> map = new HashMap<String, Object>();
        List<CommentProductEntity> commentProductList;
        BigDecimal averageCommentProductLevel;
        Long productId;
        //提交评论信息中的商品评分列表
        List<CommentProductEntity> commentProductEntityList = orderCommentVO.getCommentProductEntities();
        if (commentProductEntityList != null) {
            for (CommentProductEntity commentProductEntity : commentProductEntityList) {
                productId = commentProductEntity.getProductId();
                map.put("productId", productId);
                //数据库中同一种商品的所有评分list
                commentProductList = commentProductDao.queryList(map);
                commentProductList.add(commentProductEntity);
                averageCommentProductLevel = getAverageCommProLevel(commentProductList);
                //根据商品id获取商品详细信息
                ProductDetailEntity productDetailEntity = productDetailDao.queryObjectByProductInfoId(productId);
                productDetailEntity.setAverageLevel(averageCommentProductLevel);
                productDetailDao.update(productDetailEntity);
            }
        }
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_COMMENT);
        CommentDeliveryEntity commentDeliveryEntity = orderCommentVO.getCommentDeliveryEntity();
        if (commentDeliveryEntity != null) {
            commentDeliveryEntity.setUserId(userInfoEntity.getId());
            commentDeliveryEntity.setUserName(userInfoEntity.getUsername());
            commentDeliveryDao.save(commentDeliveryEntity);
        }
        List<CommentProductEntity> commentProductEntities = orderCommentVO.getCommentProductEntities();
        if (commentProductEntities != null) {
            for (CommentProductEntity commentProductEntity : commentProductEntities) {
                commentProductEntity.setUserId(userInfoEntity.getId());
                commentProductEntity.setUserName(userInfoEntity.getUsername());
                commentProductDao.save(commentProductEntity);
            }
        }
        orderInfoDao.update(orderInfoEntity);
        return R.ok().put("msg","评论提交成功");
    }

    @Override
    public List<OrderInfoEntity> queryListClient(Map filter) {
        return orderInfoDao.queryListClient(filter);
    }

    @Override
    public void handle(Long orderId, OrderOperateFlowEntity orderOperateFlowEntity) {
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(orderId, true);
        orderOperateFlowEntity.setType(OrderOperateFlowEntity.TYPE_HANDLE);
        orderOperateFlowEntity.setBeforeStatus(orderInfoEntity.getStatus());
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_CLOSE);
        orderInfoEntity.setType(OrderInfoEntity.TYPE_NORMAL);
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObjectByOrderId(orderId, true);
        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_CLOSE);
        orderOperateFlowEntity.setAfterStatus(OrderInfoEntity.STATUS_CLOSE);
        orderInfoEntity.setRemark("订单已人工关闭，处理办法：" + orderOperateFlowEntity.getRemark());
        orderOperateFlowEntity.setOrderId(orderInfoEntity.getId());
        orderInfoDao.update(orderInfoEntity);
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
        orderOperateFlowDao.save(orderOperateFlowEntity);
    }

    @Override
    public void handDistribute(Long orderId, Long deliveryDistributorId, Long deliveryEndpointId, OrderOperateFlowEntity orderOperateFlowEntity) {
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(orderId, true);
        //先锁配送点，再开始锁配送点库存，否则有可能造成死锁
        DeliveryEndpointEntity deliveryEndpointEntity = deliveryEndpointDao.queryObject(deliveryEndpointId, true);
        if (deliveryEndpointEntity == null) {
            throw new RRException("找不到ID为【" + deliveryEndpointId + "】的配送点。");
        }
        DeliveryDistributorEntity deliveryDistributorEntity = deliveryDistributorDao.queryObject(deliveryDistributorId, true);
        if (deliveryDistributorEntity == null) {
            throw new RRException("找不到ID为【" + deliveryDistributorId + "】的配送员。");
        }
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObjectByOrderId(orderId, true);
        if (deliveryDistributorEntity == null) {
            throw new RRException("找不到订单ID为【" + orderId + "】的配送单员。");
        }
        if (orderInfoEntity.getStatus() != OrderInfoEntity.TYPE_EXCEPTION) {
            throw new RRException("订单不是异常状态，不能手工派单。");
        }

        List<OrderProductDetailEntity> orderProductDetailEntities = orderProductDetailDao.queryListByOrderId(orderId);
        for (OrderProductDetailEntity orderProductDetailEntity : orderProductDetailEntities) {
            ProductStockEntity productStockEntity = productStockDao.queryByDeliveryEndpointIdAndProductId(orderProductDetailEntity.getProductInfoId(), deliveryEndpointEntity.getId(), true);
            // 强行扣库存，有可能扣到负数
            productStockEntity.setCount(productStockEntity.getCount() - orderProductDetailEntity.getCount());
            productStockDao.update(productStockEntity);
        }
        // 分配订单，配送中订单数加一
        deliveryDistributorEntity.setOrderCount(deliveryDistributorEntity.getOrderCount() + 1);
        deliveryDistributorDao.update(deliveryDistributorEntity);
        // 更新订单分配时间
        orderOperateFlowEntity.setBeforeStatus(orderInfoEntity.getStatus());
        orderInfoEntity.setId(orderId);
        orderInfoEntity.setDistributeTime(new Date());
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_ON_DELIVERY);
        orderOperateFlowEntity.setAfterStatus(OrderInfoEntity.STATUS_ON_DELIVERY);
        // 只要分配出去了，异常也是可以被宽恕的，毕竟要向前看
        orderInfoEntity.setException("");
        orderInfoEntity.setType(OrderInfoEntity.TYPE_NORMAL);
        orderInfoDao.update(orderInfoEntity);

        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_ON_DELIVERY);
        orderDeliveryInfoEntity.setDeliveryDistributorId(deliveryDistributorEntity.getId());
        orderDeliveryInfoEntity.setDeliveryDistributorName(deliveryDistributorEntity.getName());
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
        orderOperateFlowEntity.setOrderId(orderInfoEntity.getId());
        orderOperateFlowEntity.setType(OrderOperateFlowEntity.TYPE_HAND_DISTRIBUTOR);
        orderOperateFlowEntity.setRemark("手工分配订单【" + orderId + "】到【" + deliveryEndpointEntity.getName() + "：编号" + deliveryEndpointEntity.getId() + "】，【" + deliveryDistributorEntity.getName() + "：编号" + deliveryDistributorEntity.getId() + "】");
        orderOperateFlowDao.save(orderOperateFlowEntity);
        applicationContext.publishEvent(new OrderDeliveryNotifyEvent(orderDeliveryInfoEntity.getId()));
    }

    @Override
    public List<OrderInfoEntity> queryUnpaidByUserId(Long userId) {
        return orderInfoDao.queryUnpaidByUserId(userId);
    }


    /**
     * 检查库存，并预生成要扣除的库存数
     *
     * @param orderProductDetailEntities 订单所购买的商品
     * @param deliveryEndpointEntity     配送点实体
     * @return
     */
    private List<ProductStockEntity> checkStock(List<OrderProductDetailEntity> orderProductDetailEntities, DeliveryEndpointEntity deliveryEndpointEntity) {
        List<ProductStockEntity> productStockEntities = new ArrayList<>(16);
        for (OrderProductDetailEntity orderProductDetailEntity : orderProductDetailEntities) {
            ProductStockEntity productStockEntity = productStockDao.queryByDeliveryEndpointIdAndProductId(orderProductDetailEntity.getProductInfoId(), deliveryEndpointEntity.getId(), true);
            if (!(productStockEntity.getCount().intValue() >= orderProductDetailEntity.getCount().intValue())) {
                // 库存不足
                return Collections.emptyList();
            }
            productStockEntity.setCount(productStockEntity.getCount() - orderProductDetailEntity.getCount());
            productStockEntities.add(productStockEntity);
        }
        return productStockEntities;
    }

    /**
     * 获取商品平均评分
     *
     * @param commentProductList
     */
    private BigDecimal getAverageCommProLevel(List<CommentProductEntity> commentProductList) {
        if (commentProductList.size() > 0) {
            BigDecimal sumCommentProductLevel = BigDecimal.ZERO;
            BigDecimal averageCommentProductLevel = BigDecimal.ZERO;
            int size = commentProductList.size();
            for (int i = 0; i < size; i++) {
                sumCommentProductLevel = sumCommentProductLevel.add(new BigDecimal(commentProductList.get(i).getLevel()));
            }
            averageCommentProductLevel = sumCommentProductLevel.divide(new BigDecimal(size), 2, BigDecimal.ROUND_HALF_UP);
            return averageCommentProductLevel;
        } else {
            return BigDecimal.ZERO;
        }
    }

}
