package com.yunquanlai.utils;

import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.user.entity.UserAddressEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class DistanceUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistanceUtils.class);

    /**
     * Google Maps计算两点间经纬度距离，暂时不用该方法
     * 取WGS84标准参考椭球中的地球长半径(单位:km),计算结果单位为km，即公里
     */
    public static final double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 所有配送点按距离排序
     *
     * @param x
     * @param y
     * @param deliveryEndpointEntities
     */
    public static void sortDeliveryEndpoint(BigDecimal x, BigDecimal y, List<DeliveryEndpointEntity> deliveryEndpointEntities) {
        for (DeliveryEndpointEntity deliveryEndpointEntity : deliveryEndpointEntities) {
            // 求出x,y的差值的绝对值，即为距离
            BigDecimal tempX = x.subtract(deliveryEndpointEntity.getLocationX()).abs();
            BigDecimal tempY = y.subtract(deliveryEndpointEntity.getLocationY()).abs();
            BigDecimal distance = tempX.pow(2).add(tempY.pow(2));
            // 不用开方，因为开方了对比大小还是一样的。
            deliveryEndpointEntity.setDistance(distance);
        }
        // 按照距离排序
        Collections.sort(deliveryEndpointEntities);
    }

    /**
     * 所有地址按距离排序
     *
     * @param x
     * @param y
     * @param userAddressEntities
     */
    public static void sortAddressEndpoint(BigDecimal x, BigDecimal y, List<UserAddressEntity> userAddressEntities) {
        for (UserAddressEntity userAddressEntity : userAddressEntities) {
            // 求出x,y的差值的绝对值，即为距离
            BigDecimal tempX = x.subtract(userAddressEntity.getLocationX()).abs();
            BigDecimal tempY = y.subtract(userAddressEntity.getLocationY()).abs();
            BigDecimal distance = tempX.pow(2).add(tempY.pow(2));
            // 不用开方，因为开方了对比大小还是一样的。
            userAddressEntity.setDistance(distance);
        }
        // 按照距离排序
        Collections.sort(userAddressEntities);
    }

    /**
     * 计算两点之间的距离，计算单位为公里
     *
     * @param fromLat
     * @param fromLng
     * @param toLat
     * @param toLng
     * @return
     */
    public static double getDistance(double fromLat, double fromLng, double toLat, double toLng) {
        String path = "https://apis.map.qq.com/ws/distance/v1/?mode=driving";
        String key = "&key=6L3BZ-37PWV-QYYPA-UU4YO-3735J-A2BCI";
        String from = "&from=" + fromLat + "," + fromLng;
        String to = "&to=" + toLat + "," + toLng;
        path += from + to + key;
        try {
            //计算距离
            String json = DistanceUtils.getResponse(path);
            JSONObject jsonObject;

            jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("elements");
            String distance = jsonArray.getJSONObject(0).getString("distance");
            //返回值将距离单位转换为 公里
            return Double.valueOf(distance) / 1000;
        } catch (Exception e) {
            LOGGER.error("计算订单到最近派送点距离失败。", e);
            return -1;
        }

    }

    /**
     * 连接及解析腾讯地图,距离计算单位是米
     *
     * @param serverUrl
     * @return
     * @throws IOException
     */
    public static String getResponse(String serverUrl) throws IOException {
        //发起请求，并返回json格式的结果
        StringBuilder response = new StringBuilder();
        URL url = new URL(serverUrl);
        //打开和url之间的连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //连接会话
        conn.connect();
        // 获取输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line;
        // 循环读取流
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        // 关闭流
        br.close();
        // 断开连接
        conn.disconnect();
        return response.toString();
    }

    /**
     * 获取距离
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
}
