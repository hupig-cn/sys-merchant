package com.weisen.www.code.yjf.merchant.service.dto;

public class Rewrite_CreateMenuDTO {

    private Long merchantId;

    private Long locationId;

    private Long[] dishesIds;

    private String bigorder;

    private String dishName;

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getBigorder() {
        return bigorder;
    }

    public void setBigorder(String bigorder) {
        this.bigorder = bigorder;
    }

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
