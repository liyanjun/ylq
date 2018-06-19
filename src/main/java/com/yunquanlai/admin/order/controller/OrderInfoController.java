package com.yunquanlai.admin.order.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderInfoEntity> orderInfoList = orderInfoService.queryList(query);
		int total = orderInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(orderInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
    // TODO: 2018/6/10 订单查询、异常订单处理、已支付异常订单重新分配 
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("orderinfo:info")
	public R info(@PathVariable("id") Long id){
		OrderInfoEntity orderInfo = orderInfoService.queryObject(id);
		
		return R.ok().put("orderInfo", orderInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("orderinfo:save")
	public R save(@RequestBody OrderInfoEntity orderInfo){
		orderInfoService.save(orderInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("orderinfo:update")
	public R update(@RequestBody OrderInfoEntity orderInfo){
		orderInfoService.update(orderInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("orderinfo:delete")
	public R delete(@RequestBody Long[] ids){
		orderInfoService.deleteBatch(ids);

		return R.ok();
	}

}
