package com.yunquanlai.admin.user.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.user.entity.UserRecommendApprovalEntity;
import com.yunquanlai.admin.user.service.UserRecommendApprovalService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 推广审批申请表
 * 
 * @author weicc
 * @email 
 * @date 2018-07-21 18:00:31
 */
@RestController
@RequestMapping("userrecommendapproval")
public class UserRecommendApprovalController extends AbstractController {
	@Autowired
	private UserRecommendApprovalService userRecommenderApprovalService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("userrecommendapproval:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserRecommendApprovalEntity> userRecommenderApprovalList = userRecommenderApprovalService.queryList(query);
		int total = userRecommenderApprovalService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userRecommenderApprovalList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("userrecommendapproval:info")
	public R info(@PathVariable("id") Long id){
		UserRecommendApprovalEntity userRecommenderApproval = userRecommenderApprovalService.queryObject(id);
		
		return R.ok().put("userRecommenderApproval", userRecommenderApproval);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("userrecommendapproval:save")
	public R save(@RequestBody UserRecommendApprovalEntity userRecommenderApproval){
		userRecommenderApprovalService.save(userRecommenderApproval);
		
		return R.ok();
	}
	
	/**
	 * 审批
	 */
	@RequestMapping("/update")
	@RequiresPermissions("userrecommendapproval:update")
	public R update(@RequestBody UserRecommendApprovalEntity userRecommenderApproval){
		userRecommenderApprovalService.update(userRecommenderApproval);

		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("userrecommendapproval:delete")
	public R delete(@RequestBody Long[] ids){
		userRecommenderApprovalService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
