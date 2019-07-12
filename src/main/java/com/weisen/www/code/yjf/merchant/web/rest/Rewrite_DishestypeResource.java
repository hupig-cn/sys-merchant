package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesService;
import com.weisen.www.code.yjf.merchant.service.Rewrite_DishestypeService;
import com.weisen.www.code.yjf.merchant.service.dto.DishestypeDTO;
import com.weisen.www.code.yjf.merchant.service.dto.MerchantDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
@Api(tags = "000-菜单类型")
public class Rewrite_DishestypeResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_DishestypeResource.class);

    private Rewrite_DishestypeService rewrite_DishestypeService;

    public Rewrite_DishestypeResource(Rewrite_DishesService dishesService) {
        this.rewrite_DishestypeService = rewrite_DishestypeService;
    }

    @GetMapping("/getMerchantmMenu/{merchantId}")
    @ApiOperation("查询商户的菜单类型")
    public ResponseEntity<Result> getMerchantmMenu(@PathVariable Long merchantId) {
        log.debug("REST request to get Dishestype : {}", merchantId);
        List<DishestypeDTO> list = rewrite_DishestypeService.getMerchantmMenu(merchantId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",list)));
    }

}
