package com.weisen.www.code.yjf.merchant.service.dto.submit;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "查询商家所有订单DTO")
public class Rewrite_OrderDTO implements Serializable {

	@ApiModelProperty(value = "订单号")
	private String bigorder;

	@ApiModelProperty(value = "座位号")
	private String location;

	@ApiModelProperty(value = "支付时间")
	private String modifierdate;

	@ApiModelProperty(value = "订单总价格")
	private String numprice;

	public String getBigorder() {
		return bigorder;
	}

	public void setBigorder(String bigorder) {
		this.bigorder = bigorder;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getModifierdate() {
		return modifierdate;
	}

	public void setModifierdate(String modifierdate) {
		this.modifierdate = modifierdate;
	}

	public String getNumprice() {
		return numprice;
	}

	public void setNumprice(String numprice) {
		this.numprice = numprice;
	}
}
