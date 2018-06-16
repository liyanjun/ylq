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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    /**
     * 是否符合配送范围
     * @param locationX：X坐标
     * @param locationY：Y坐标
     * @return
     */
	public boolean availableDelivery(BigDecimal locationX,BigDecimal locationY){
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

            // 求出x,y的差值的绝对值
            BigDecimal x = deliveryEndpointEntity.getLocationX().subtract(deliveryEndpointEntity.getLocationX()).abs();
            BigDecimal y = deliveryEndpointEntity.getLocationY().subtract(deliveryEndpointEntity.getLocationY()).abs();
            BigDecimal distance = x.pow(2).add(y.pow(2));
            // 不用开方，因为开方了对比大小还是一样的。
            deliveryEndpointEntity.setDistance(distance);
		}
        // 按照距离排序
        Collections.sort(deliveryEndpointEntities);
		//获取距离最短的配送点
		DeliveryEndpointEntity deliveryEndpointEntity = deliveryEndpointEntities.get(0);
        desX = deliveryEndpointEntity.getLocationX().doubleValue();
        desY = deliveryEndpointEntity.getLocationY().doubleValue();
        double distance = getDistance(curX,curY,desX,desY);
        //配送范围：6公里
        if (distance > 0 && distance <= 6){
            return true;//可配送
        } else {
            return false;//距离太远，不可配送
        }
	}

    @RequestMapping("/test")
    @RequiresPermissions("deliveryendpoint:info")
	public R test() throws JSONException {
	    //String dis = getDistance(39.983171,116.308479,39.949226,116.394309);
        BigDecimal x = new BigDecimal(22.7812900000);
        BigDecimal y = new BigDecimal(108.2733100000);
        boolean b = availableDelivery(x,y);
	    return R.ok().put("b",b);
    }

    /**
     * 计算两点之间的距离，计算单位为公里
     * @param fromLat
     * @param fromLng
     * @param toLat
     * @param toLng
     * @return
     */
	public double getDistance(double fromLat, double fromLng, double toLat, double toLng){
	    String path = "http://apis.map.qq.com/ws/distance/v1/?mode=driving";
	    String key = "&key=6L3BZ-37PWV-QYYPA-UU4YO-3735J-A2BCI";
	    String from = "&from="+fromLat+","+fromLng;
	    String to = "&to="+toLat+","+toLng;
	    path += from + to + key;
	    //计算距离
        String json = getResponse(path);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("elements");
            String distance = jsonArray.getJSONObject(0).getString("distance");
            //返回值将距离单位转换为 公里
            return Double.valueOf(distance)/1000;
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }

    }

    //连接及解析腾讯地图,距离计算单位是米
    private String getResponse(String serverUrl) {
	    //发起请求，并返回json格式的结果
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(serverUrl);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //连接会话
            conn.connect();
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {// 循环读取流
                response.append(line);
            }
            br.close();// 关闭流
            conn.disconnect();// 断开连接
            return response.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "error";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    //Google Maps计算两点间经纬度距离，暂时不用该方法
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
