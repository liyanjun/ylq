package com.yunquanlai.admin.order.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.order.entity.OrderProductDetailEntity;
import com.yunquanlai.admin.order.service.OrderProductDetailService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 订单商品信息表
 * 
 * @author weicc
 * @email 
 * @date 2018-06-10 16:17:39
 */
@RestController
@RequestMapping("orderproductdetail")
public class OrderProductDetailController extends AbstractController {
	@Autowired
	private OrderProductDetailService orderProductDetailService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("orderproductdetail:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderProductDetailEntity> orderProductDetailList = orderProductDetailService.queryList(query);
		int total = orderProductDetailService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(orderProductDetailList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("orderproductdetail:info")
	public R info(@PathVariable("id") Long id){
		OrderProductDetailEntity orderProductDetail = orderProductDetailService.queryObject(id);
		
		return R.ok().put("orderProductDetail", orderProductDetail);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("orderproductdetail:save")
	public R save(@RequestBody OrderProductDetailEntity orderProductDetail){
		orderProductDetailService.save(orderProductDetail);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("orderproductdetail:update")
	public R update(@RequestBody OrderProductDetailEntity orderProductDetail){
		orderProductDetailService.update(orderProductDetail);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("orderproductdetail:delete")
	public R delete(@RequestBody Long[] ids){
		orderProductDetailService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
