package com.weisen.www.code.yjf.merchant.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "修改店铺DTO")
public class Rewrite_MerchantDTO implements Serializable {

	private String userid;

	@ApiModelProperty(value = "店铺门头照")
	private String merchantphoto;

	private String name;

	private String businessid;

	private String address;

	private String province;

	private String city;

	private String county;

	private BigDecimal longitude;

	private BigDecimal latitude;

	private Integer concession;

	private String creditcode;

	private Boolean logicdelete;

	@ApiModelProperty(value = "商家店铺营业时间")
	private String other;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMerchantphoto() {
		return merchantphoto;
	}

	public void setMerchantphoto(String merchantphoto) {
		this.merchantphoto = merchantphoto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
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

	public Integer getConcession() {
		return concession;
	}

	public void setConcession(Integer concession) {
		this.concession = concession;
	}

	public String getCreditcode() {
		return creditcode;
	}

	public void setCreditcode(String creditcode) {
		this.creditcode = creditcode;
	}

	public Boolean getLogicdelete() {
		return logicdelete;
	}

	public void setLogicdelete(Boolean logicdelete) {
		this.logicdelete = logicdelete;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
