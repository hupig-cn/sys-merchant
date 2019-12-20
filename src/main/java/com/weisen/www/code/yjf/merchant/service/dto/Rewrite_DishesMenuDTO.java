package com.weisen.www.code.yjf.merchant.service.dto;

public class Rewrite_DishesMenuDTO {
	private Long dishesid;
	private String dishesname;
	private Double price;
	private Integer count;
	private String dishesImg;
	
	public Long getDishesid() {
		return dishesid;
	}
	public void setDishesid(Long dishesid) {
		this.dishesid = dishesid;
	}
	public String getDishesname() {
		return dishesname;
	}
	public void setDishesname(String dishesname) {
		this.dishesname = dishesname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getDishesImg() {
		return dishesImg;
	}
	public void setDishesImg(String dishesImg) {
		this.dishesImg = dishesImg;
	}
	 
}
