package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesorderService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesorderDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_CreateMenuDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.Dishesorder}.
 */
@RestController
@RequestMapping("/api/public")
@Api(tags = "000-菜品订单操作")
public class Rewrite_DishesorderResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_DishesorderResource.class);

    private static final String ENTITY_NAME = "merchantDishesorder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private Rewrite_DishesorderService dishesorderService;

    public Rewrite_DishesorderResource(Rewrite_DishesorderService dishesorderService) {
        this.dishesorderService = dishesorderService;
    }

    @PostMapping("/ObtainDishesorder/createMenu")
    @ApiOperation("创建菜单")
    public ResponseEntity<DishesorderDTO> createMenu (@RequestBody Rewrite_CreateMenuDTO createMenuDTO) {
        return dishesorderService.createMenu(createMenuDTO);
    }
}
