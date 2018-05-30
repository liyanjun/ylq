package com.yunlaiquan.admin.product.controller;

import java.util.List;
import java.util.Map;

import com.yunlaiquan.admin.product.entity.ProductInfoEntity;
import com.yunlaiquan.admin.product.service.ProductInfoService;
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
 * 商品信息表表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-30 17:16:47
 */
@RestController
@RequestMapping("productinfo")
public class ProductInfoController {
	@Autowired
	private ProductInfoService productInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("productinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ProductInfoEntity> productInfoList = productInfoService.queryList(query);
		int total = productInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(productInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("productinfo:info")
	public R info(@PathVariable("id") Integer id){
		ProductInfoEntity productInfo = productInfoService.queryObject(id);
		
		return R.ok().put("productInfo", productInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("productinfo:save")
	public R save(@RequestBody ProductInfoEntity productInfo){
		productInfoService.save(productInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("productinfo:update")
	public R update(@RequestBody ProductInfoEntity productInfo){
		productInfoService.update(productInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("productinfo:delete")
	public R delete(@RequestBody Integer[] ids){
		productInfoService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
