package com.yunquanlai.admin.delivery.controller;

import com.yunquanlai.admin.delivery.dao.DeliveryDistributorDao;
import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.delivery.service.DeliveryEndpointService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.validator.Assert;
import com.yunquanlai.utils.validator.ValidatorUtils;
import com.yunquanlai.utils.validator.group.AddGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 配送点信息
 * 
 * @author weicc
 * @email 
 * @date 2018-05-31 15:26:38
 */
@RestController
@RequestMapping("deliveryendpoint")
public class DeliveryEndpointController {
	@Autowired
	private DeliveryEndpointService deliveryEndpointService;

    @Autowired
	private DeliveryDistributorDao deliveryDistributorDao;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("deliveryendpoint:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DeliveryEndpointEntity> deliveryEndpointList = deliveryEndpointService.queryList(query);
		int total = deliveryEndpointService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(deliveryEndpointList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 所有配送点记录
	 * @return
	 */
    @RequestMapping("/select")
    @RequiresPermissions("deliveryendpoint:list")
	public R select(){
		Map<String, Object> map = new HashMap<>();
		List<DeliveryEndpointEntity> deliveryEndpointEntities = deliveryEndpointService.queryList(map);
		return R.ok().put("deliveryEndpointEntities", deliveryEndpointEntities);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("deliveryendpoint:info")
	public R info(@PathVariable("id") Long id){
		DeliveryEndpointEntity deliveryEndpoint = deliveryEndpointService.queryObject(id);
		
		return R.ok().put("deliveryEndpoint", deliveryEndpoint);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("deliveryendpoint:save")
	public R save(@RequestBody DeliveryEndpointEntity deliveryEndpoint){
		//校验配送点信息
		ValidatorUtils.validateEntity(deliveryEndpoint, AddGroup.class);
		Assert.isNull(deliveryEndpoint.getLocationX(),"请在地图中点选，配送点地址。");
        Assert.isNull(deliveryEndpoint.getLocationY(),"请在地图中点选，配送点地址。");
		deliveryEndpointService.save(deliveryEndpoint);

		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("deliveryendpoint:update")
	public R update(@RequestBody DeliveryEndpointEntity deliveryEndpoint){
		//校验配送点信息
		ValidatorUtils.validateEntity(deliveryEndpoint, AddGroup.class);

		deliveryEndpointService.update(deliveryEndpoint);

        //更新配送点信息同时更新配送员表中的配送点名
        Long delivery_endpoint_id = deliveryEndpoint.getId();
        String delivery_endpoint_name = deliveryEndpoint.getName();
        Map map = new HashMap(16);
        map.put("deliveryEndpointId", delivery_endpoint_id);
        map.put("deliveryEndpointName", delivery_endpoint_name);
        deliveryDistributorDao.updateDeliveryEndpointName(map);

		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("deliveryendpoint:delete")
	public R delete(@RequestBody Long[] ids){
		deliveryEndpointService.deleteBatch(ids);
		
		return R.ok();
	}

}
