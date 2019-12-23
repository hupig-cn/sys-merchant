package com.weisen.www.code.yjf.merchant.web.rewrite_001_订餐;

import com.weisen.www.code.yjf.merchant.service.Rewrite_OrderingMealsService;
import com.weisen.www.code.yjf.merchant.service.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
	public ResponseEntity<Result> merchantDishestype(@RequestParam String id,
                                                     @RequestParam String iocid) {
		Result result = orderingMealsService.merchantDishestype(id,iocid);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/public/post/merchantDishestype",id , result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}


	/**
     * iocid 餐位
     * num 数量
     * merchatid 商家id
     * name 商家食物
     * */
    @CrossOrigin
    @PostMapping("/public/post/takingOrders")
    @ApiOperation("点餐")
    public ResponseEntity<Result> takingOrders(@RequestParam String iocId,
                                               @RequestParam Integer num,
                                               @RequestParam String merchatid,
                                               @RequestParam String name) {
        Result result = orderingMealsService.takingOrders(iocId,num,merchatid,name);
        log.debug("访问地址: {},传入值: {},返回值: {}", "/public/post/takingOrders/takingOrders",iocId+":"+num+":"+merchatid , result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @CrossOrigin
    @PostMapping("/public/post/takingOrdersNum")
    @ApiOperation("重置菜的数量")
    public ResponseEntity<Result> takingOrdersNum(@RequestParam String iocId,
                                                  @RequestParam String merchatid) {
        Result result = orderingMealsService.takingOrdersNum(iocId,merchatid);
        log.debug("访问地址: {},传入值: {},返回值: {}", "/public/post/takingOrders/takingOrdersNum",iocId+":"+merchatid , result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @CrossOrigin
    @PostMapping("/public/post/merchantOrders2")
    @ApiOperation("点了菜的详情")
    public ResponseEntity<Result> merchantOrders2(@RequestParam String iocId,
                                                  @RequestParam String merchatid,
                                                  @Value("无备注") String other) {
        Result result = orderingMealsService.merchantOrders(iocId,merchatid,other);
        log.debug("访问地址: {},传入值: {},返回值: {}", "/public/post/takingOrders/merchantOrders2",iocId , result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @CrossOrigin
    @PostMapping("/public/post/inAllOrders")
    @ApiOperation("总价")
    public ResponseEntity<Result> inAllOrders(@RequestParam String iocId,
                                              @RequestParam String merchatid) {
        Result result = orderingMealsService.inAllOrders(iocId,merchatid);
        log.debug("访问地址: {},传入值: {},返回值: {}", "/public/post/takingOrders/inAllOrders",iocId , result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
}
