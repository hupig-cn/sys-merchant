package com.weisen.www.code.yjf.merchant.web.rewrite_001_订餐;

import com.weisen.www.code.yjf.merchant.service.Rewrite_OrderingMealsService;
import com.weisen.www.code.yjf.merchant.service.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@RestController
@RequestMapping("/api")
@Api(tags = "001-订餐-2")
public class Rewrite_OrderingMealsResource2 {

	private final Logger log = LoggerFactory.getLogger(Rewrite_OrderingMealsResource2.class);

	private final Rewrite_OrderingMealsService orderingMealsService;

	public Rewrite_OrderingMealsResource2(Rewrite_OrderingMealsService orderingMealsService) {
		this.orderingMealsService = orderingMealsService;
	}




	@CrossOrigin
	@PostMapping("/public/post/merchantDishestype")
	@ApiOperation("根据用户id查找店铺分类表")
	public ResponseEntity<Result> merchantDishestype(@RequestParam String id) {
		Result result = orderingMealsService.merchantDishestype(id);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/test/loc",id , result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
}
