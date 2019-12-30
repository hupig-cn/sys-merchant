package com.weisen.www.code.yjf.merchant.service.dto.submit;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "新增菜品DTO")
public class Rewrite_NewDishesDTO implements Serializable {

	@ApiModelProperty(value = "菜品ID")
	private Long id;

	@ApiModelProperty(value = "菜品父级分类ID")
	private String dishestypeId;

	@ApiModelProperty(value = "菜品名称")
	private String name;

	@ApiModelProperty(value = "菜品状态")
	private String state;

	@ApiModelProperty(value = "商户ID")
	private String merchantId;

	@ApiModelProperty(value = "库存")
	private String num;

	@ApiModelProperty(value = "价格")
	private String price;

	@ApiModelProperty(value = "备注")
	private String other;

	@ApiModelProperty(value = "菜品图片ID")
	private String image;

	public String getDishestypeId() {
		return dishestypeId;
	}

	public void setDishestypeId(String dishestypeId) {
		this.dishestypeId = dishestypeId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

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

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
