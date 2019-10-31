package com.weisen.www.code.yjf.merchant.service.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class Rewrite_ForNearShop {
	
	@ApiModelProperty(value = "经度", example = "")
    private BigDecimal longitude;  // 经度
	
	@ApiModelProperty(value = "纬度", example = "")
    private BigDecimal latitude;   // 纬度
	
	@ApiModelProperty(value = "起始条目", example = "0")
    private int startNum;  // 起始条目

	@ApiModelProperty(value = "一页数量", example = "15")
    private int pageSize;  // 一页数量
	
	@ApiModelProperty(value = "距离", example = "")
    private int distance;  // 距离
	
	@ApiModelProperty(value = " 内容", example = "")
    private String name;  // 内容
	
	@ApiModelProperty(value = "城市", example = "")
    private String city; // 城市
	
	@ApiModelProperty(value = "类型", example = "1")
	private Integer type; // 类型
	
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

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
    
    
}
