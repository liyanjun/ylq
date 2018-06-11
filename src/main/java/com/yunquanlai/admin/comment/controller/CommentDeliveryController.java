package com.yunquanlai.admin.comment.controller;

import com.yunquanlai.admin.comment.entity.CommentDeliveryEntity;
import com.yunquanlai.admin.comment.service.CommentDeliveryService;
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
 * @date 2018-06-10 16:28:27
 */
@RestController
@RequestMapping("commentdelivery")
public class CommentDeliveryController extends AbstractController {
	@Autowired
	private CommentDeliveryService commentDeliveryService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("commentdelivery:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CommentDeliveryEntity> commentDeliveryList = commentDeliveryService.queryList(query);
		int total = commentDeliveryService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(commentDeliveryList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("commentdelivery:info")
	public R info(@PathVariable("id") Integer id){
		CommentDeliveryEntity commentDelivery = commentDeliveryService.queryObject(id);
		
		return R.ok().put("commentDelivery", commentDelivery);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("commentdelivery:save")
	public R save(@RequestBody CommentDeliveryEntity commentDelivery){
		commentDeliveryService.save(commentDelivery);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("commentdelivery:update")
	public R update(@RequestBody CommentDeliveryEntity commentDelivery){
		commentDeliveryService.update(commentDelivery);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("commentdelivery:delete")
	public R delete(@RequestBody Integer[] ids){
		commentDeliveryService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
