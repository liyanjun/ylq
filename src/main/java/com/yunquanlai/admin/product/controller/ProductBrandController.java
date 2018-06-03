package com.yunquanlai.admin.product.controller;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.product.entity.ProductBrandEntity;
import com.yunquanlai.admin.product.service.ProductBrandService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;


/**
 * 商品品牌信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-05-30 23:09:11
 */
@RestController
@RequestMapping("productbrand")
public class ProductBrandController {
	@Autowired
	private ProductBrandService productBrandService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("productbrand:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ProductBrandEntity> productBrandList = productBrandService.queryList(query);
		int total = productBrandService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(productBrandList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 列表
	 */
	@RequestMapping("/queryAll")
	@RequiresPermissions("productbrand:list")
	public R queryAll(){
		List<ProductBrandEntity> list = productBrandService.queryAll();

		return R.ok().put("list", list);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("productbrand:info")
	public R info(@PathVariable("id") Long id){
		ProductBrandEntity productBrand = productBrandService.queryObject(id);
		
		return R.ok().put("productBrand", productBrand);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("productbrand:save")
	public R save(@RequestBody ProductBrandEntity productBrand){
		productBrandService.save(productBrand);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("productbrand:update")
	public R update(@RequestBody ProductBrandEntity productBrand){
		productBrandService.update(productBrand);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("productbrand:delete")
	public R delete(@RequestBody Long[] ids){
		productBrandService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
