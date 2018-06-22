package com.yunquanlai.admin.order.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.entity.OrderOperateFlowEntity;
import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.validator.Assert;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 订单信息表
 *
 * @author weicc
 * @email
 * @date 2018-06-10 16:17:39
 */
@RestController
@RequestMapping("orderinfo")
public class OrderInfoController extends AbstractController {
    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("orderinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<OrderInfoEntity> orderInfoList = orderInfoService.queryList(query);
        int total = orderInfoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(orderInfoList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }
    // TODO: 2018/6/10 订单查询、已支付异常订单重新分配

    /**
     * 手工标记订单为已处理
     *
     * @param orderId               订单号
     * @param deliveryDistributorId 配送点
     * @param deliveryEndpointId    配送员
     * @return
     */
    @RequestMapping("/handDistribute")
    @RequiresPermissions("orderinfo:update")
    public R handDistribute(@RequestParam Long orderId, Long deliveryDistributorId, Long deliveryEndpointId) {
        Assert.isNull(deliveryEndpointId, "请选择配送点。");
        Assert.isNull(deliveryDistributorId, "请选择配送员。");
        OrderOperateFlowEntity orderOperateFlowEntity = new OrderOperateFlowEntity(getUserId(),getUser().getUsername(),new Date());
        orderInfoService.handDistribute(orderId,deliveryDistributorId,deliveryEndpointId,orderOperateFlowEntity);
        return R.ok();
    }

    /**
     * 手工标记订单为已处理
     *
     * @param orderId 订单号
     * @param remark  处理结果
     * @return
     */
    @RequestMapping("/handle")
    @RequiresPermissions("orderinfo:update")
    public R handle(@RequestParam Long orderId, String remark) {
        Assert.isBlank(remark, "请填写手工处理备注。");
        OrderOperateFlowEntity orderOperateFlowEntity = new OrderOperateFlowEntity(getUserId(),getUser().getUsername(),new Date());
        orderOperateFlowEntity.setRemark(remark);
        orderInfoService.handle(orderId,orderOperateFlowEntity);
        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("orderinfo:info")
    public R info(@PathVariable("id") Long id) {
        OrderInfoEntity orderInfo = orderInfoService.queryObject(id);

        return R.ok().put("orderInfo", orderInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("orderinfo:save")
    public R save(@RequestBody OrderInfoEntity orderInfo) {
        orderInfoService.save(orderInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("orderinfo:update")
    public R update(@RequestBody OrderInfoEntity orderInfo) {
        orderInfoService.update(orderInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("orderinfo:delete")
    public R delete(@RequestBody Long[] ids) {
        orderInfoService.deleteBatch(ids);

        return R.ok();
    }

}
