package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.Dishes}.
 */
@RestController
@RequestMapping("/api/public")
@Api(tags = "000-菜品操作")
public class Rewrite_DishesResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_DishesResource.class);

    private static final String ENTITY_NAME = "merchantDishes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private Rewrite_DishesService dishesService;

    public Rewrite_DishesResource(Rewrite_DishesService dishesService) {
        this.dishesService = dishesService;
    }

    @GetMapping("/ObtainDishes/{merchantId}")
    @ApiOperation("根据商户ID查询菜品信息")
    public ResponseEntity<Map<String, List<DishesDTO>>> getDishes (@PathVariable Long merchantId) {
        return dishesService.getDishes(merchantId);
    }

    @GetMapping("/findAllUpDishes/{merchantId}")
    @ApiOperation("查询商家上架的商品列表")
    public ResponseEntity<Result> findAllUpDishes (@PathVariable Long merchantId) {
        List<DishesDTO> list = dishesService.findAllUpDishes(merchantId);
        return ResponseEntity.ok(Result.suc("成功",list));
    }

    @GetMapping("/findAllDownDishes/{merchantId}")
    @ApiOperation("查询下架的商品列表")
    public ResponseEntity<Result> findAllDownDishes (@PathVariable Long merchantId) {
        List<DishesDTO> list = dishesService.findAllDownDishes(merchantId);
        return ResponseEntity.ok(Result.suc("成功",list));
    }

    @PostMapping("/createDishes")
    @ApiOperation("添加商品")
    public ResponseEntity<Result> createDishes (@RequestBody DishesDTO dishesDTO) {
        dishesService.createDishes(dishesDTO);
        return ResponseEntity.ok(Result.suc("成功"));
    }


    @GetMapping("/findDishesInfo/{dishesId}")
    @ApiOperation("查询商品详情")
    public ResponseEntity<Result> findDishesInfo (@PathVariable Long dishesId) {
        DishesDTO dishesDTO = dishesService.findDishesInfo(dishesId);
        return ResponseEntity.ok(Result.suc("成功",dishesDTO));
    }

    @PostMapping("/updateDishes")
    @ApiOperation("修改商品信息")
    public ResponseEntity<Result> updateDishes (@RequestBody DishesDTO dishesDTO) {
        dishesService.updateDishes(dishesDTO);
        return ResponseEntity.ok(Result.suc("成功"));
    }


    @PostMapping("/upDishes")
    @ApiOperation("上架")
    public ResponseEntity<Result> upDishes (@RequestBody DishesDTO dishesDTO) {
        dishesService.upDishes(dishesDTO);
        return ResponseEntity.ok(Result.suc("成功"));
    }

    @PostMapping("/downDishes")
    @ApiOperation("下架")
    public ResponseEntity<Result> downDishes (@RequestBody DishesDTO dishesDTO) {
        dishesService.downDishes(dishesDTO);
        return ResponseEntity.ok(Result.suc("成功"));
    }


    @DeleteMapping("/deleteDishe/{dishesId}")
    @ApiOperation("删除商品")
    public ResponseEntity<Result> deleteDishes (@PathVariable Long dishesId) {
        dishesService.deleteDishes(dishesId);
        return ResponseEntity.ok(Result.suc("成功"));
    }

    @DeleteMapping("/deleteListDishes")
    @ApiOperation("批量删除")
    public ResponseEntity<Result> deleteListDishes (@PathVariable List<Long> dishesId) {
        dishesService.deleteListDishes(dishesId);
        return ResponseEntity.ok(Result.suc("成功"));
    }


}
