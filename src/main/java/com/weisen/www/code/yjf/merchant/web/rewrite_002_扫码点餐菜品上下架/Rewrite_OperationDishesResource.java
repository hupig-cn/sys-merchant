package com.weisen.www.code.yjf.merchant.web.rewrite_002_扫码点餐菜品上下架;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesOnAndOffShelvesService;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_NewClassificationDTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_NewDishesDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author LuoJinShui
 * @date 2019-12-30 11:42:42
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = "002-扫码点餐菜品上下架")
public class Rewrite_OperationDishesResource {

	private final Logger log = LoggerFactory.getLogger(Rewrite_OperationDishesResource.class);

	private final Rewrite_DishesOnAndOffShelvesService rewrite_DishesOnAndOffShelvesService;

	public Rewrite_OperationDishesResource(Rewrite_DishesOnAndOffShelvesService rewrite_DishesOnAndOffShelvesService) {
		this.rewrite_DishesOnAndOffShelvesService = rewrite_DishesOnAndOffShelvesService;
	}

	@PostMapping("/post/NewClassification")
	@ApiOperation("新增菜品分类")
	public ResponseEntity<Result> NewClassification(@RequestParam(value = "merchantId") String merchantId,
			@RequestParam(value = "name") String name, String other) {
		Result result = rewrite_DishesOnAndOffShelvesService.newClassification(merchantId, name, other);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/post/NewClassification", merchantId + "," + name + "," + other, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@PostMapping("/post/UpdateClassification")
	@ApiOperation("修改菜品分类")
	public ResponseEntity<Result> UpdateClassification(
			@RequestBody List<Rewrite_NewClassificationDTO> rewrite_NewClassificationDTOList) {
		Result result = rewrite_DishesOnAndOffShelvesService.updateClassification(rewrite_NewClassificationDTOList);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/post/UpdateClassification", rewrite_NewClassificationDTOList, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@PostMapping("/post/DeleteClassification")
	@ApiOperation("删除菜品分类")
	public ResponseEntity<Result> DeleteClassification(@RequestParam(value = "id") Long id,
			@RequestParam(value = "merchantId") String merchantId) {
		Result result = rewrite_DishesOnAndOffShelvesService.deleteClassification(id, merchantId);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/post/DeleteClassification", id + "," + merchantId, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@PostMapping("/post/NewDishes")
	@ApiOperation("新增菜品")
	public ResponseEntity<Result> NewDishes(@RequestBody Rewrite_NewDishesDTO rewrite_NewDishesDTO) {
		Result result = rewrite_DishesOnAndOffShelvesService.newDishes(rewrite_NewDishesDTO);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/post/NewDishes", rewrite_NewDishesDTO, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@PostMapping("/post/UpdateDishes")
	@ApiOperation("修改菜品")
	public ResponseEntity<Result> UpdateDishes(@RequestBody List<Rewrite_NewDishesDTO> rewrite_NewDishesDTOList) {
		Result result = rewrite_DishesOnAndOffShelvesService.updateDishes(rewrite_NewDishesDTOList);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/post/UpdateDishes", rewrite_NewDishesDTOList, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@PostMapping("/post/DeleteDishes")
	@ApiOperation("删除菜品")
	public ResponseEntity<Result> DeleteDishes(@RequestParam(value = "merchantId") String merchantId,
			@RequestParam(value = "name") String name) {
		Result result = rewrite_DishesOnAndOffShelvesService.deleteDishes(merchantId, name);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/post/DeleteDishes", merchantId + "," + name, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

}
