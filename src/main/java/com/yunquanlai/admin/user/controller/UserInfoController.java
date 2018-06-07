package com.yunquanlai.admin.user.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.service.UserInfoService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 客户信息表
 * 
 * @author weicc
 * @email 
 * @date 2018-06-07 09:47:10
 */
@RestController
@RequestMapping("userinfo")
public class UserInfoController extends AbstractController {
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("userinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserInfoEntity> userInfoList = userInfoService.queryList(query);
		int total = userInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("userinfo:info")
	public R info(@PathVariable("id") Long id){
		UserInfoEntity userInfo = userInfoService.queryObject(id);
		
		return R.ok().put("userInfo", userInfo);
	}

	/**
	 * 保存
	 */
/*
	@RequestMapping("/save")
	@RequiresPermissions("userinfo:save")
	public R save(@RequestBody UserInfoEntity userInfo){
		userInfoService.save(userInfo);

		return R.ok();
	}
*/

	/**
	 * 修改
	 */
/*
	@RequestMapping("/update")
	@RequiresPermissions("userinfo:update")
	public R update(@RequestBody UserInfoEntity userInfo){
		userInfoService.update(userInfo);

		return R.ok();
	}
*/

	/**
	 * 删除
	 */
/*
	@RequestMapping("/delete")
	@RequiresPermissions("userinfo:delete")
	public R delete(@RequestBody Long[] ids){
		userInfoService.deleteBatch(ids);

		return R.ok();
	}
*/

}
