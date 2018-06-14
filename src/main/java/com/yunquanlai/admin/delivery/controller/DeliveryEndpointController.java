package com.yunquanlai.admin.delivery.controller;

import com.yunquanlai.admin.delivery.dao.DeliveryDistributorDao;
import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.delivery.service.DeliveryEndpointService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.validator.ValidatorUtils;
import com.yunquanlai.utils.validator.group.AddGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    /**
     * 查找距离给定坐标最近的配送点
     * @param locationX：X坐标
     * @param locationY：Y坐标
     * @return
     */
	public DeliveryEndpointEntity nearestDeliveryEndpoint(BigDecimal locationX,BigDecimal locationY){
		DeliveryEndpointEntity nearestEndpoint = new DeliveryEndpointEntity();
		//当前坐标
		double curX = locationX.doubleValue();
		double curY = locationY.doubleValue();
		//目标坐标
		double desX, desY;
		// 找出所有配送点
		List<DeliveryEndpointEntity> deliveryEndpointEntities = deliveryEndpointService.queryList(null);
		//符合配送条件的配送点
		List<DeliveryEndpointEntity> deliveryEndpointEntityList = new ArrayList<DeliveryEndpointEntity>();
		for (DeliveryEndpointEntity deliveryEndpointEntity : deliveryEndpointEntities) {
		    desX = deliveryEndpointEntity.getLocationX().doubleValue();
		    desY = deliveryEndpointEntity.getLocationY().doubleValue();
		    double distance = GetDistance(curX, curY, desX, desY);//距离
            //如果距离大于6公里，不考虑配送
            if (distance > 6){
                continue;
            }
		    deliveryEndpointEntity.setDistance(BigDecimal.valueOf(distance));
		    deliveryEndpointEntityList.add(deliveryEndpointEntity);
		}
		if (deliveryEndpointEntityList.size()>0){
            // 按照距离排序
            Collections.sort(deliveryEndpointEntityList);
            //返回最近的配送点
            return deliveryEndpointEntities.get(0);
        } else {
		    return null;
        }
	}

	//Google Maps计算两点间经纬度距离
    // 取WGS84标准参考椭球中的地球长半径(单位:km),计算结果单位为km，即公里
    public static final double EARTH_RADIUS = 6378.137;
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    public static double GetDistance(double lat1, double lng1, double lat2, double lng2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b/2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
}
