package com.yunquanlai.admin.product.controller;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.AbstractController;
import com.yunquanlai.admin.product.entity.ProductStockFlowEntity;
import com.yunquanlai.admin.product.service.ProductStockFlowService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;


/**
 * 商品库存流水信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-02 22:31:01
 */
@RestController
@RequestMapping("productstockflow")
public class ProductStockFlowController extends AbstractController {
	@Autowired
	private ProductStockFlowService productStockFlowService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("productstock:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ProductStockFlowEntity> productStockFlowList = productStockFlowService.queryList(query);
		int total = productStockFlowService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(productStockFlowList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
}
