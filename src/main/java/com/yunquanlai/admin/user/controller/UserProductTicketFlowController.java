package com.yunquanlai.admin.user.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.user.entity.UserProductTicketFlowEntity;
import com.yunquanlai.admin.user.service.UserProductTicketFlowService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 用户水票消费流水
 * 
 * @author weicc
 * @email 
 * @date 2018-07-20 18:14:10
 */
@RestController
@RequestMapping("userproductticketflow")
public class UserProductTicketFlowController extends AbstractController {
	@Autowired
	private UserProductTicketFlowService userProductTicketFlowService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("userproductticketflow:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserProductTicketFlowEntity> userProductTicketFlowList = userProductTicketFlowService.queryList(query);
		int total = userProductTicketFlowService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userProductTicketFlowList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("userproductticketflow:info")
	public R info(@PathVariable("id") Long id){
		UserProductTicketFlowEntity userProductTicketFlow = userProductTicketFlowService.queryObject(id);
		
		return R.ok().put("userProductTicketFlow", userProductTicketFlow);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("userproductticketflow:save")
	public R save(@RequestBody UserProductTicketFlowEntity userProductTicketFlow){
		userProductTicketFlowService.save(userProductTicketFlow);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("userproductticketflow:update")
	public R update(@RequestBody UserProductTicketFlowEntity userProductTicketFlow){
		userProductTicketFlowService.update(userProductTicketFlow);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("userproductticketflow:delete")
	public R delete(@RequestBody Long[] ids){
		userProductTicketFlowService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
