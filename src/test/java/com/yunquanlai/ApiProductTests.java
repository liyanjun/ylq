package com.yunquanlai;

import com.yunquanlai.admin.comment.entity.CommentProductEntity;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.service.UserInfoService;
import com.yunquanlai.api.comsumer.ApiCommentController;
import com.yunquanlai.api.comsumer.ApiProductController;
import com.yunquanlai.api.comsumer.vo.OrderCommentVO;
import com.yunquanlai.utils.R;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Api产品模块单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiProductTests {

	@Autowired
    private ApiProductController apiProductController;

	@Autowired
    private ApiCommentController apiCommentController;

	@Autowired
    private  UserInfoService userInfoService;

    /**
     * 测试查询商品列表
     */
    @Test
    public void testApiProductQuery() {
        R r = apiProductController.queryProduct(null,null,null,null,10,0,5);
        List list = (List)r.get("productInfoList");
        Assert.assertEquals(list.size(),5);
    }

//    @Test
//    public void testApiCommentProduct(){
//        // TODO: 2018/6/27 测试订单评论方法
//        UserInfoEntity user = userInfoService.queryObject((long) 19);
//        OrderCommentVO orderCommentVO = new OrderCommentVO();
//        orderCommentVO.setOrderId((long) 10018);
//        List<CommentProductEntity> list = new ArrayList<CommentProductEntity>();
//        CommentProductEntity commentProductEntity1 = new CommentProductEntity();
//        commentProductEntity1.setLevel(4);
//        commentProductEntity1.setProductId((long) 10003);
//        commentProductEntity1.setComment("非常不错~");
//        list.add(commentProductEntity1);
//        orderCommentVO.setCommentProductEntities(list);
//        R r = apiCommentController.comment(user, orderCommentVO);
//    }

}
