package com.weisen.www.code.yjf.merchant.service.dto;
import java.io.Serializable;
import java.util.Objects;

import io.swagger.annotations.ApiModel;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.merchant.domain.Dishes} entity.
 */
@SuppressWarnings("serial")
@ApiModel(value = "订餐页面类型和菜品DTO")
public class DishesAndTypeDTO implements Serializable {
    
    private String typeName;		//类型名称    

	private String dishName;		//菜名

    private String image;			//图片url

    private String dishestypeid;	//菜品类型id

    private String price;			//价格

    public String getTypeName() {
    	return typeName;
    }
    
    public void setTypeName(String typeName) {
    	this.typeName = typeName;
    }


	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDishestypeid() {
		return dishestypeid;
	}

	public void setDishestypeid(String dishestypeid) {
		this.dishestypeid = dishestypeid;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


}
