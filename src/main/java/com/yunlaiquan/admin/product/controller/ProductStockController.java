package com.yunlaiquan.admin.product.controller;

import java.util.List;
import java.util.Map;

import com.yunlaiquan.admin.AbstractController;
import com.yunlaiquan.admin.product.entity.ProductStockEntity;
import com.yunlaiquan.admin.product.service.ProductStockService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yunlaiquan.utils.PageUtils;
import com.yunlaiquan.utils.Query;
import com.yunlaiquan.utils.R;


/**
 * 商品库存信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-01 22:49:34
 */
@RestController
@RequestMapping("productstock")
public class ProductStockController extends AbstractController {
	@Autowired
	private ProductStockService productStockService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("productstock:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ProductStockEntity> productStockList = productStockService.queryList(query);
		int total = productStockService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(productStockList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("productstock:info")
	public R info(@PathVariable("id") Long id){
		ProductStockEntity productStock = productStockService.queryObject(id);
		
		return R.ok().put("productStock", productStock);
	}
	
	/**
	 * 库存添加
	 */
	@RequestMapping("/update")
	@RequiresPermissions("productstock:update")
	public R update(@RequestBody ProductStockEntity productStock){
		productStockService.addStock(productStock);
		
		return R.ok();
	}
	
}
