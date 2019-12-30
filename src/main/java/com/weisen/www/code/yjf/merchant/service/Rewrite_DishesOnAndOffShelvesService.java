package com.weisen.www.code.yjf.merchant.service;

import java.util.List;

import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_NewClassificationDTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_NewDishesDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;

public interface Rewrite_DishesOnAndOffShelvesService {

	// 新增菜品分类
	Result newClassification(String merchantId, String name, String other);

	// 修改菜品分类
	Result updateClassification(List<Rewrite_NewClassificationDTO> rewrite_NewClassificationDTOList);

	// 删除菜品分类
	Result deleteClassification(Long id, String merchantId);

	// 新增菜品
	Result newDishes(Rewrite_NewDishesDTO rewrite_NewDishesDTO);

	// 修改菜品
	Result updateDishes(List<Rewrite_NewDishesDTO> rewrite_NewDishesDTOList);

	// 删除菜品
	Result deleteDishes(String merchantId, String name);
}
