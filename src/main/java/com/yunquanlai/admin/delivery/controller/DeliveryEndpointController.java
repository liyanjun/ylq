package com.yunquanlai.admin.delivery.controller;

import com.yunquanlai.admin.delivery.dao.DeliveryDistributorDao;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.delivery.service.DeliveryEndpointService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
		deliveryEndpointService.save(deliveryEndpoint);

		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("deliveryendpoint:update")
	public R update(@RequestBody DeliveryEndpointEntity deliveryEndpoint){
		deliveryEndpointService.update(deliveryEndpoint);

        //更新配送点信息同时更新配送员表中的配送点名
        Long delivery_endpoint_id = deliveryEndpoint.getId();
        Map map = new HashMap(16);
        map.put("delivery_endpoint_id", delivery_endpoint_id);
        List<DeliveryDistributorEntity> deliveryDistributorEntities = deliveryDistributorDao.queryListForDeliveryEndpoint(map);
        for(DeliveryDistributorEntity deliveryDistributorEntity : deliveryDistributorEntities){
            deliveryDistributorEntity.setDeliveryEndpointName(deliveryEndpoint.getName());
            deliveryDistributorDao.update(deliveryDistributorEntity);
        }

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
