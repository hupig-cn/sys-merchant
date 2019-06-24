package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.service.MerchantService;
import com.weisen.www.code.yjf.merchant.service.dto.MerchantDTO;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.Merchant}.
 */
@RestController
@RequestMapping("/api/public")
@Api(tags = "000-商户操作666")
public class Rewrite_MerchantResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_MerchantResource.class);

    private static final String ENTITY_NAME = "merchantMerchant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MerchantService merchantService;

    public Rewrite_MerchantResource(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping("/ObtainMerchant/{id}")
    @ApiOperation("根据商户ID查询商户信息")
    public ResponseEntity<MerchantDTO> getMerchant(@PathVariable Long id) {
        log.debug("REST request to get Merchant : {}", id);
        Optional<MerchantDTO> merchantDTO = merchantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(merchantDTO);
    }
}
