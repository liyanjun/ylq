package com.yunquanlai.admin.user.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.user.entity.UserProductTicketEntity;
import com.yunquanlai.admin.user.service.UserProductTicketService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 用户水票信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-07-17 12:11:07
 */
@RestController
@RequestMapping("userproductticket")
public class UserProductTicketController extends AbstractController {
	@Autowired
	private UserProductTicketService userProductTicketService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("userproductticket:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserProductTicketEntity> userProductTicketList = userProductTicketService.queryList(query);
		int total = userProductTicketService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userProductTicketList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("userproductticket:info")
	public R info(@PathVariable("id") Long id){
		UserProductTicketEntity userProductTicket = userProductTicketService.queryObject(id);
		
		return R.ok().put("userProductTicket", userProductTicket);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("userproductticket:save")
	public R save(@RequestBody UserProductTicketEntity userProductTicket){
		userProductTicketService.save(userProductTicket);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("userproductticket:update")
	public R update(@RequestBody UserProductTicketEntity userProductTicket){
		userProductTicketService.update(userProductTicket);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("userproductticket:delete")
	public R delete(@RequestBody Long[] ids){
		userProductTicketService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
