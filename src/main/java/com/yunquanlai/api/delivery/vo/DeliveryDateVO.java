package com.yunquanlai.api.delivery.vo;

import java.math.BigDecimal;

/**
 * @author liyanjun
 */

public class DeliveryDateVO {

    private Integer dayDelivery;
    private BigDecimal dayDeliveryAmount;
    private Integer allDelivery;
    private BigDecimal allDeliveryAmount;

    public Integer getDayDelivery() {
        return dayDelivery;
    }

    public void setDayDelivery(Integer dayDelivery) {
        this.dayDelivery = dayDelivery;
    }

    public BigDecimal getDayDeliveryAmount() {
        return dayDeliveryAmount;
    }

    public void setDayDeliveryAmount(BigDecimal dayDeliveryAmount) {
        this.dayDeliveryAmount = dayDeliveryAmount;
    }

    public Integer getAllDelivery() {
        return allDelivery;
    }

    public void setAllDelivery(Integer allDelivery) {
        this.allDelivery = allDelivery;
    }

    public BigDecimal getAllDeliveryAmount() {
        return allDeliveryAmount;
    }

    public void setAllDeliveryAmount(BigDecimal allDeliveryAmount) {
        this.allDeliveryAmount = allDeliveryAmount;
    }
}
