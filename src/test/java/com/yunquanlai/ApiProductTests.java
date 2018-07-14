//package com.yunquanlai;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.yunquanlai.admin.comment.entity.CommentProductEntity;
//import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
//import com.yunquanlai.admin.delivery.service.DeliveryEndpointService;
//import com.yunquanlai.admin.user.entity.UserInfoEntity;
//import com.yunquanlai.admin.user.service.UserInfoService;
//import com.yunquanlai.api.comsumer.ApiCommentController;
//import com.yunquanlai.api.comsumer.ApiOrderController;
//import com.yunquanlai.api.comsumer.ApiProductController;
//import com.yunquanlai.api.comsumer.ApiUserController;
//import com.yunquanlai.api.comsumer.vo.OrderCommentVO;
//import com.yunquanlai.api.comsumer.vo.OrderVO;
//import com.yunquanlai.api.comsumer.vo.ProductOrderVO;
//import com.yunquanlai.utils.R;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Api产品模块单元测试
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ApiProductTests {
//
//	@Autowired
//    private ApiProductController apiProductController;
//
//	@Autowired
//    private ApiCommentController apiCommentController;
//
//	@Autowired
//    private ApiOrderController apiOrderController;
//
//	@Autowired
//    private  UserInfoService userInfoService;
//
//	@Autowired
//    private ApiUserController apiUserController;
//
//    /**
//     * 测试查询商品列表
//     */
//    //@Test
//    public void testApiProductQuery() {
//        Long[] types = new Long[]{Long.valueOf(10), Long.valueOf(20)};
//        //Long[] types = new Long[]{};
//        R r = apiProductController.queryProduct(null,null,null,null,types,0,5, 10);
//        List list = (List)r.get("productInfoList");
//        //Assert.assertEquals(list.size(),5);
//    }
//
//   // @Test
//    public void testApiCommentProduct(){
//        //  测试订单评论方法
//        UserInfoEntity user = userInfoService.queryObject((long) 19);
//        OrderCommentVO orderCommentVO = new OrderCommentVO();
//        orderCommentVO.setOrderId((long) 10019);
//        List<CommentProductEntity> list = new ArrayList<CommentProductEntity>();
//        CommentProductEntity commentProductEntity1 = new CommentProductEntity();
//        commentProductEntity1.setLevel(4);
//        commentProductEntity1.setProductId((long) 10002);
//        commentProductEntity1.setComment("啊呀呀~");
//        list.add(commentProductEntity1);
//        orderCommentVO.setCommentProductEntities(list);
//        R r = apiCommentController.comment(user, orderCommentVO);
//    }
//
//    //@Test
//    public void testOrder() throws ParseException, JsonProcessingException {
//        UserInfoEntity user = userInfoService.queryObject((long) 19);
//        OrderVO orderVO = new OrderVO();
//        ProductOrderVO productOrderVO = new ProductOrderVO();
//        productOrderVO.setCount(2);
//        productOrderVO.setProductInfoId((long) 10003);
//        List<ProductOrderVO> productOrderVOList = new ArrayList<ProductOrderVO>();
//        productOrderVOList.add(productOrderVO);
//        orderVO.setProductOrderVOList(productOrderVOList);
//        R r = apiOrderController.order(orderVO, user);
//    }
//
//    /**
//     * 测试：用户提交押金提现申请
//     */
//    @Test
//    public void testDepositoryWithdraw(){
//        UserInfoEntity userInfoEntity = userInfoService.queryObject((long) 4);
//
//        R r = apiUserController.depositoryWithdraw(userInfoEntity);
//    }
//
//    @Autowired
//    private DeliveryEndpointService deliveryEndpointService;
//    //@Test
//    public void testDeliveryEndpointSave(){
//        DeliveryEndpointEntity deliveryEndpointEntity = new DeliveryEndpointEntity();
//        deliveryEndpointEntity.setLocationX(new BigDecimal(13.0999));
//        deliveryEndpointEntity.setLocationY(new BigDecimal(12.5999));
//        deliveryEndpointEntity.setName("aiyaya配送点");
//        deliveryEndpointService.save(deliveryEndpointEntity);
//    }
//
//}
///