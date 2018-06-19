package com.yunquanlai.admin.user.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.user.entity.UserWithdrawDepositEntity;
import com.yunquanlai.admin.user.service.UserWithdrawDepositService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;import java.util.Map;


/**
 * 客户押金提现申请表
 * 
 * @author weicc
 * @email 
 * @date 2018-06-07 12:44:30
 */
@RestController
@RequestMapping("userwithdrawdeposit")
public class UserWithdrawDepositController extends AbstractController {
	@Autowired
	private UserWithdrawDepositService userWithdrawDepositService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("userwithdrawdeposit:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserWithdrawDepositEntity> userWithdrawDepositList = userWithdrawDepositService.queryList(query);
		int total = userWithdrawDepositService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userWithdrawDepositList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("userwithdrawdeposit:info")
	public R info(@PathVariable("id") Long id){
		UserWithdrawDepositEntity userWithdrawDeposit = userWithdrawDepositService.queryObject(id);
		
		return R.ok().put("userWithdrawDeposit", userWithdrawDeposit);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("userwithdrawdeposit:save")
	public R save(@RequestBody UserWithdrawDepositEntity userWithdrawDeposit){
		userWithdrawDepositService.save(userWithdrawDeposit);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("userwithdrawdeposit:update")
	public R update(@RequestBody UserWithdrawDepositEntity userWithdrawDeposit){
		userWithdrawDepositService.update(userWithdrawDeposit);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("userwithdrawdeposit:delete")
	public R delete(@RequestBody Long[] ids){
		userWithdrawDepositService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 处理提现申请
	 */
	@RequestMapping("/handle")
	@RequiresPermissions("userwithdrawdeposit:handle")
	public R handleDepositoryWithdraw(@RequestBody UserWithdrawDepositEntity userWithdrawDepositEntity){
		userWithdrawDepositEntity.setIsHandle(20);
        // TODO: 2018/6/10 需要获取当前操作人id 和 name
		userWithdrawDepositEntity.setHandlerId(getUserId());
		userWithdrawDepositEntity.setHandlerName(getUser().getUsername());
		userWithdrawDepositEntity.setIsHandle(20);
		userWithdrawDepositService.save(userWithdrawDepositEntity);
		return R.ok();
	}
	
}
