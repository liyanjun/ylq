package com.yunquanlai.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ConfigUtils implements InitializingBean {
    @Value("${yunquanlai.openTime.begin}")
    private Integer beginHour;

    @Value("${yunquanlai.openTime.end}")
    private Integer endHour;

    @Value("${yunquanlai.emptyValue}")
    private BigDecimal emptyValue;

    private Map<Integer,BigDecimal> map;

    /**
     * 判断是否是送水时间
     *
     * @return
     */
    public boolean isOpenTime() {
        boolean isOpenTime = false;
        int hour = LocalDateTime.now().getHour();
        if (hour >= beginHour && hour < endHour) {
            isOpenTime = true;
        }
        return isOpenTime;
    }

    /**
     * 获取空桶价值
     *
     * @return
     */
    public BigDecimal getEmptyValue() {
        return emptyValue;
    }

    public Map<Integer, BigDecimal> getMap() {
        return map;
    }

    public void setMap(Map<Integer, BigDecimal> map) {
        this.map = map;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        map = new LinkedHashMap();
        map.put(1,emptyValue.multiply(BigDecimal.ONE));
        map.put(2,emptyValue.multiply(BigDecimal.valueOf(2)));
        map.put(4,emptyValue.multiply(BigDecimal.valueOf(4)));
        map.put(8,emptyValue.multiply(BigDecimal.valueOf(8)));
    }
}
