package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
