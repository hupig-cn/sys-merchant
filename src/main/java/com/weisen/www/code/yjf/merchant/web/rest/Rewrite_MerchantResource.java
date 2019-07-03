package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.service.MerchantService;
import com.weisen.www.code.yjf.merchant.service.Rewrite_MerchantService;
import com.weisen.www.code.yjf.merchant.service.dto.MerchantDTO;

import com.weisen.www.code.yjf.merchant.service.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.Merchant}.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "000-商户操作")
public class Rewrite_MerchantResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_MerchantResource.class);

    private static final String ENTITY_NAME = "merchantMerchant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Rewrite_MerchantService rewrite_MerchantService;
    private final MerchantService merchantService;

    public Rewrite_MerchantResource(MerchantService merchantService, Rewrite_MerchantService rewrite_MerchantService) {
        this.rewrite_MerchantService = rewrite_MerchantService;
        this.merchantService = merchantService;
    }

    @GetMapping("/public/ObtainMerchant/{id}")
    @ApiOperation("根据商户ID查询商户信息")
    public ResponseEntity<MerchantDTO> getMerchant(@PathVariable Long id) {
        log.debug("REST request to get Merchant : {}", id);
        Optional<MerchantDTO> merchantDTO = merchantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(merchantDTO);
    }

    @PostMapping("/createMerchant")
    @ApiOperation("添加商家店铺")
    public ResponseEntity<Result> createMerchant(@RequestBody MerchantDTO merchantDTO) {
        log.debug("REST createMerchant : {}", merchantDTO);
        rewrite_MerchantService.createMerchant(merchantDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功")));
    }


    @PostMapping("/updateMerchant")
    @ApiOperation("修改店铺信息")
    public ResponseEntity<Result> updateMerchant(@RequestBody MerchantDTO merchantDTO) {
        log.debug("REST updateMerchant : {}", merchantDTO);
        rewrite_MerchantService.updateMerchant(merchantDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功")));
    }

    @GetMapping("/findAllMyShop/{userId}")
    @ApiOperation("查询我的店铺列表")
    public ResponseEntity<Result> findAllMyShop(@PathVariable Long userId) {
        log.debug("REST findAllMyShop : {}", userId);
        List<MerchantDTO> list = rewrite_MerchantService.findAllMyShop(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",list)));
    }

    @GetMapping("/findShopInfo/{}")
    @ApiOperation("查询店铺信息")
    public ResponseEntity<Result> findShopInfo(@PathVariable Long userId) {
        log.debug("REST findShopInfo : {}", userId);
        MerchantDTO merchantDTO = rewrite_MerchantService.findShopInfo(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",merchantDTO)));
    }

    @GetMapping("/findPopularMerchant/{longitude}&{latitude}")
    @ApiOperation("查询附近热门店铺")
    public ResponseEntity<Result> findPopularMerchant(@PathVariable BigDecimal longitude, @PathVariable BigDecimal latitude) {
        log.debug("REST findPopularMerchant : {}", longitude);
        List<MerchantDTO> list = rewrite_MerchantService.findPopularMerchant(longitude,latitude);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",list)));
    }


    @GetMapping("/findNearMerchant/{longitude}&{latitude}")
    @ApiOperation("距离最近的店铺")
    public ResponseEntity<Result> findNearMerchant(@PathVariable String longitude, @PathVariable String latitude) {
        log.debug("REST findNearMerchant : {}", longitude);
        List<MerchantDTO> list = rewrite_MerchantService.findNearMerchant(longitude,latitude);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",list)));
    }




}
