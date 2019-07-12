package com.weisen.www.code.yjf.merchant.service.dto;

import java.math.BigDecimal;

public class Rewrite_ForNearShop {

    private BigDecimal longitude;  // 经度

    private BigDecimal latitude;   // 纬度

    private int startNum;  // 起始条目

    private int pageSize;  // 一页数量

    private int distance;  // 距离

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
