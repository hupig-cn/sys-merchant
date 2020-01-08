package com.weisen.www.code.yjf.merchant.web.rewrite_001_订餐;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.merchant.service.Rewrite_OrderingMealsService;
import com.weisen.www.code.yjf.merchant.service.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * REST controller for managing
 * {@link com.weisen.www.code.yjf.basic.domain.Withdrawal}.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "001-订餐")
public class Rewrite_OrderingMealsResource {

	private final Logger log = LoggerFactory.getLogger(Rewrite_OrderingMealsResource.class);

	private final Rewrite_OrderingMealsService orderingMealsService;

	public Rewrite_OrderingMealsResource(Rewrite_OrderingMealsService orderingMealsService) {
		this.orderingMealsService = orderingMealsService;
	}



	/**
	 * 根据用户id查找店铺图片和商家名称
	 *
	 * @author sxx
	 * @date 2019-12-17 17:43:31
	 */
	@CrossOrigin
	@PostMapping("/public/get/merchantNameAndData")
	@ApiOperation("根据用户id查找店铺图片和商家名称")
	public ResponseEntity<Result> getMerchantNameAndData(@RequestParam String id) {
		Result result = orderingMealsService.getMerchantNameAndData(id);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/test/loc",id , result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
	
	/**
	 * 根据用户id查找店铺菜单类型('该接口没有被用到')
	 * @author sxx
	 * @date 2019-12-17 17:43:31
	 */
	@CrossOrigin
	@PostMapping("/public/get/merchantType")
	@ApiOperation("根据用户id查找店铺菜单类型('该接口没有被用到')")
	public ResponseEntity<Result> getMerchantType(@RequestParam String id) {
		Result result = orderingMealsService.getMerchantType(id);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/test/loc",id , result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
	
	/**
	 * 完成支付后更改订单状态
	 * @author sxx
	 * @date 2019-1-2 21:43:31
	 */
	@CrossOrigin
	@PostMapping("/public/post/changeOrderState")
	@ApiOperation("完成支付后更改订单状态")
	public ResponseEntity<Result> changeOrderState(@RequestParam String orderid) {
		Result result = orderingMealsService.changeOrderState(orderid);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/public/post/changeOrderState", orderid, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
	
	/**
	 *  app端支付时判断是否是菜品订单
	 * @author sxx
	 * @date 2019-1-8 11:43:31
	 */
	@CrossOrigin
	@PostMapping("/public/post/chackIsLittleOrder")
	@ApiOperation("app端支付时判断是否是菜品订单")
	public ResponseEntity<Result> chackIsLittleOrder(@RequestParam String orderid) {
		Result result = orderingMealsService.chackIsLittleOrder(orderid);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/public/post/chackIsLittleOrder", orderid, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
}
