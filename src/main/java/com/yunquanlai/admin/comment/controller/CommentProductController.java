package com.yunquanlai.admin.comment.controller;

import com.yunquanlai.admin.comment.entity.CommentProductEntity;
import com.yunquanlai.admin.comment.service.CommentProductService;
import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 配送员评价
 * 
 * @author weicc
 * @email 
 * @date 2018-06-10 16:28:26
 */
@RestController
@RequestMapping("commentproduct")
public class CommentProductController extends AbstractController {
	@Autowired
	private CommentProductService commentProductService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("commentproduct:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CommentProductEntity> commentProductList = commentProductService.queryList(query);
		int total = commentProductService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(commentProductList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("commentproduct:info")
	public R info(@PathVariable("id") Long id){
		CommentProductEntity commentProduct = commentProductService.queryObject(id);
		
		return R.ok().put("commentProduct", commentProduct);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("commentproduct:save")
	public R save(@RequestBody CommentProductEntity commentProduct){
		commentProductService.save(commentProduct);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("commentproduct:update")
	public R update(@RequestBody CommentProductEntity commentProduct){
		commentProductService.update(commentProduct);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("commentproduct:delete")
	public R delete(@RequestBody Long[] ids){
		commentProductService.deleteBatch(ids);
		
		return R.ok();
	}

}
