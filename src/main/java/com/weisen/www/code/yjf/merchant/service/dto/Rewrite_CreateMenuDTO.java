package com.weisen.www.code.yjf.merchant.service.dto;

public class Rewrite_CreateMenuDTO {

    private Long merchantId;

    private Long locationId;

    private Long[] dishesIds;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long[] getDishesIds() {
        return dishesIds;
    }

    public void setDishesIds(Long[] dishesIds) {
        this.dishesIds = dishesIds;
    }
}
