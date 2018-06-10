package com.yunquanlai.admin.order.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.order.entity.OrderOperateFlowEntity;
import com.yunquanlai.admin.order.service.OrderOperateFlowService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 订单手工处理流水记录表
 * 
 * @author weicc
 * @email 
 * @date 2018-06-10 16:17:39
 */
@RestController
@RequestMapping("orderoperateflow")
public class OrderOperateFlowController extends AbstractController {
	@Autowired
	private OrderOperateFlowService orderOperateFlowService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("orderoperateflow:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrderOperateFlowEntity> orderOperateFlowList = orderOperateFlowService.queryList(query);
		int total = orderOperateFlowService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(orderOperateFlowList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("orderoperateflow:info")
	public R info(@PathVariable("id") Long id){
		OrderOperateFlowEntity orderOperateFlow = orderOperateFlowService.queryObject(id);
		
		return R.ok().put("orderOperateFlow", orderOperateFlow);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("orderoperateflow:save")
	public R save(@RequestBody OrderOperateFlowEntity orderOperateFlow){
		orderOperateFlowService.save(orderOperateFlow);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("orderoperateflow:update")
	public R update(@RequestBody OrderOperateFlowEntity orderOperateFlow){
		orderOperateFlowService.update(orderOperateFlow);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("orderoperateflow:delete")
	public R delete(@RequestBody Long[] ids){
		orderOperateFlowService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
