package com.yunquanlai.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class ConfigUtils {
    @Value("${yunquanlai.openTime.begin}")
    private Integer beginHour;

    @Value("${yunquanlai.openTime.end}")
    private Integer endHour;

    @Value("${yunquanlai.emptyValue}")
    private BigDecimal emptyValue;

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
}
