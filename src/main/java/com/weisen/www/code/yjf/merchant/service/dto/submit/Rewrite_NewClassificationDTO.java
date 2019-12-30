package com.weisen.www.code.yjf.merchant.service.dto.submit;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "修改商品分类DTO")
public class Rewrite_NewClassificationDTO implements Serializable {

	@ApiModelProperty(value = "菜品分类ID")
	private Long id;

	@ApiModelProperty(value = "类型名称")
	private String name;

	@ApiModelProperty(value = "类型状态")
	private String state;

	@ApiModelProperty(value = "商户ID")
	private String merchantId;

	@ApiModelProperty(value = "分类排序")
	private Integer typeOrder;

	@ApiModelProperty(value = "备注")
	private String other;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getTypeOrder() {
		return typeOrder;
	}

	public void setTypeOrder(Integer typeOrder) {
		this.typeOrder = typeOrder;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
}
