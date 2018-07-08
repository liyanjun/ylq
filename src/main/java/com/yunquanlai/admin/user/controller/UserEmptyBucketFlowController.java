package com.yunquanlai.admin.user.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.user.entity.UserEmptyBucketFlowEntity;
import com.yunquanlai.admin.user.service.UserEmptyBucketFlowService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 客户空桶流水信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-07-08 16:09:15
 */
@RestController
@RequestMapping("useremptybucketflow")
public class UserEmptyBucketFlowController extends AbstractController {
	@Autowired
	private UserEmptyBucketFlowService userEmptyBucketFlowService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("useremptybucketflow:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserEmptyBucketFlowEntity> userEmptyBucketFlowList = userEmptyBucketFlowService.queryList(query);
		int total = userEmptyBucketFlowService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userEmptyBucketFlowList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("useremptybucketflow:info")
	public R info(@PathVariable("id") Long id){
		UserEmptyBucketFlowEntity userEmptyBucketFlow = userEmptyBucketFlowService.queryObject(id);
		
		return R.ok().put("userEmptyBucketFlow", userEmptyBucketFlow);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("useremptybucketflow:save")
	public R save(@RequestBody UserEmptyBucketFlowEntity userEmptyBucketFlow){
		userEmptyBucketFlowService.save(userEmptyBucketFlow);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("useremptybucketflow:update")
	public R update(@RequestBody UserEmptyBucketFlowEntity userEmptyBucketFlow){
		userEmptyBucketFlowService.update(userEmptyBucketFlow);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("useremptybucketflow:delete")
	public R delete(@RequestBody Long[] ids){
		userEmptyBucketFlowService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
