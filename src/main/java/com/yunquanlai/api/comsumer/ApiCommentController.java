package com.yunquanlai.api.comsumer;

import com.yunquanlai.admin.comment.entity.CommentProductEntity;
import com.yunquanlai.admin.comment.service.CommentProductService;
import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.comsumer.vo.OrderCommentVO;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import com.yunquanlai.utils.annotation.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户评论接口
 *
 * @author weicc
 * @date 2018/5/30 18:30
 * @desc
 **/
@RestController
@RequestMapping("/client/api")
@Api(value = "客户端-评论", description = "用户评论接口")
public class ApiCommentController {


    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private CommentProductService commentProductService;

    /**
     * 提交用户评论信息
     *
     * @return
     */
    @PostMapping("comment")
    @ApiOperation(value = "提交用户评论信息{\"orderId\":null,\"commentDeliveryEntity\":{\"deliveryDistributorId\":null,\"comment\":null,\"level\":null,\"creationTime\":null},\"commentProductEntities\":[{\"productId\":null,\"comment\":null,\"level\":null,\"creationTime\":null}]}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(name = "OrderCommentVO", value = "订单评论信息", required = true, dataType = "com.yunquanlai.api.comsumer.vo.OrderCommentVO", paramType = "body"),
    })
    public R comment(@LoginUser @ApiIgnore UserInfoEntity user, @ApiIgnore OrderCommentVO orderCommentVO) {

        return orderInfoService.saveComment(orderCommentVO, user.getId());
    }

    /**
     * 查询用户评论商品信息
     *
     * @return
     */
    @IgnoreAuth
    @PostMapping("query")
    @ApiOperation(value = "查询用户评论商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "long", name = "productId", value = "商品ID", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "offset", value = "位移数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "limit", value = "查询条数", required = true)
    })
    public R query(@RequestParam Long productId, @RequestParam Integer offset, @RequestParam Integer limit) {
        //TODO 处理计算商品总评分
        Map map = new HashMap(16);
        map.put("productId", productId);
        map.put("offset", offset);
        map.put("limit", limit);
        List<CommentProductEntity> commentProductList = commentProductService.queryList(map);

        return R.ok().put("commentProductList", commentProductList);
    }
}
