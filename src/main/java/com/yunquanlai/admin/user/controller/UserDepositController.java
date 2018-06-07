package com.yunquanlai.admin.user.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.user.entity.UserDepositEntity;
import com.yunquanlai.admin.user.entity.UserDepositVO;
import com.yunquanlai.admin.user.service.UserDepositService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 客户押金信息表
 * 
 * @author weicc
 * @email 
 * @date 2018-06-07 12:41:08
 */
@RestController
@RequestMapping("userdeposit")
public class UserDepositController extends AbstractController {
	@Autowired
	private UserDepositService userDepositService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("userdeposit:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

        List<UserDepositVO> userDepositList = userDepositService.queryDepositList(query);
		//List<UserDepositEntity> userDepositList = userDepositService.queryList(query);
		int total = userDepositService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userDepositList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("userdeposit:info")
	public R info(@PathVariable("id") Long id){
		UserDepositEntity userDeposit = userDepositService.queryObject(id);
		
		return R.ok().put("userDeposit", userDeposit);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("userdeposit:save")
	public R save(@RequestBody UserDepositEntity userDeposit){
		userDepositService.save(userDeposit);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("userdeposit:update")
	public R update(@RequestBody UserDepositEntity userDeposit){
		userDepositService.update(userDeposit);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("userdeposit:delete")
	public R delete(@RequestBody Long[] ids){
		userDepositService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
