package com.yunquanlai.admin.order.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 订单配送信息表
 * 
 * @author weicc
 * @email 
 * @date 2018-06-10 16:17:38
 */
@RestController
@RequestMapping("orderdeliveryinfo")
public class OrderDeliveryInfoController extends AbstractController {
	@Autowired
	private OrderDeliveryInfoService orderDeliveryInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("orderdeliveryinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderDeliveryInfoEntity> orderDeliveryInfoList = orderDeliveryInfoService.queryList(query);
		int total = orderDeliveryInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(orderDeliveryInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("orderdeliveryinfo:info")
	public R info(@PathVariable("id") Long id){
		OrderDeliveryInfoEntity orderDeliveryInfo = orderDeliveryInfoService.queryObject(id);
		
		return R.ok().put("orderDeliveryInfo", orderDeliveryInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("orderdeliveryinfo:save")
	public R save(@RequestBody OrderDeliveryInfoEntity orderDeliveryInfo){
		orderDeliveryInfoService.save(orderDeliveryInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("orderdeliveryinfo:update")
	public R update(@RequestBody OrderDeliveryInfoEntity orderDeliveryInfo){
		orderDeliveryInfoService.update(orderDeliveryInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("orderdeliveryinfo:delete")
	public R delete(@RequestBody Long[] ids){
		orderDeliveryInfoService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
