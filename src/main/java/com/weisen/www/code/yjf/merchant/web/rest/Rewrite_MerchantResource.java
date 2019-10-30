package com.weisen.www.code.yjf.merchant.web.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.merchant.service.MerchantService;
import com.weisen.www.code.yjf.merchant.service.Rewrite_MerchantService;
import com.weisen.www.code.yjf.merchant.service.dto.MerchantDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_ForNearShop;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_MerchantDTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_JudgeMerchantDTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_MerchentForAll;
import com.weisen.www.code.yjf.merchant.service.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing
 * {@link com.weisen.www.code.yjf.merchant.domain.Merchant}.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "000-商户操作")
public class Rewrite_MerchantResource {

	private final Logger log = LoggerFactory.getLogger(Rewrite_MerchantResource.class);

//    private static final String ENTITY_NAME = "merchantMerchant";

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

	@GetMapping("/ObtainMerchantUserId/{userid}")
	@ApiOperation("根据用户ID查询用户的商户信息")
	public ResponseEntity<MerchantDTO> getMerchantUserId(@PathVariable Long userid) {
		log.debug("REST request to get Merchant : {}", userid);
		MerchantDTO merchantDTO = rewrite_MerchantService.findMyShop(userid);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(merchantDTO));
	}

	@PostMapping("/createMerchant")
	@ApiOperation("添加商家店铺")
	public String createMerchant(@RequestBody MerchantDTO merchantDTO) {
		log.debug("REST createMerchant : {}", merchantDTO);
		return rewrite_MerchantService.createMerchant(merchantDTO);
	}

	@PostMapping("/updateMerchant")
	@ApiOperation("修改店铺信息")
	public ResponseEntity<Result> updateMerchant(@RequestBody Rewrite_MerchantDTO merchantDTO) {
		Result result = rewrite_MerchantService.updateMerchant(merchantDTO);
		log.debug("访问成功:{},传入值:{},返回值:{}", "/user/getList/FriendsApply", merchantDTO, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@GetMapping("/findShopInfo/{userId}")
	@ApiOperation("查询店铺信息")
	public ResponseEntity<Result> findShopInfo(@PathVariable Long userId) {
		log.debug("REST findShopInfo : {}", userId);
		MerchantDTO merchantDTO = rewrite_MerchantService.findShopInfo(userId);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功", merchantDTO)));
	}

	@PostMapping("/findPopularMerchant")
	@ApiOperation("查询附近热门店铺")
	public ResponseEntity<Result> findPopularMerchant(@RequestBody Rewrite_ForNearShop rewrite_ForNearShop) {
		log.debug("REST findPopularMerchant : {}", rewrite_ForNearShop);
		List<MerchantDTO> list = rewrite_MerchantService.findPopularMerchant(rewrite_ForNearShop);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功", list)));
	}

	@PostMapping("/findNearMerchant")
	@ApiOperation("距离最近的店铺")
	public ResponseEntity<Result> findNearMerchant(@RequestBody Rewrite_ForNearShop rewrite_ForNearShop) {
		log.debug("REST findNearMerchant : {}", rewrite_ForNearShop);
		List<MerchantDTO> list = rewrite_MerchantService.findNearMerchant(rewrite_ForNearShop);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功", list)));
	}

	@PostMapping("/findByNameLike")
	@ApiOperation("根据搜索内容查询商户")
	public ResponseEntity<Result> findByNameLike(@RequestBody Rewrite_ForNearShop rewrite_ForNearShop) {
		log.debug("REST findNearMerchant : {}", rewrite_ForNearShop);
		Result list = rewrite_MerchantService.findByNameLike(rewrite_ForNearShop);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(list));
	}

	@GetMapping("/public/findAllMerchant")
	@ApiOperation("分页倒叙查询商家")
	public ResponseEntity<Result> findAllMerchant(@RequestParam int satrtPage, int pageSize, BigDecimal longitude,
			BigDecimal latitude) {
		log.debug("REST findAllMerchant : {}");
		List<MerchantDTO> list = rewrite_MerchantService.findAllMerchant(satrtPage, pageSize, longitude, latitude);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功", list)));
	}

	@PostMapping("/admin/adminFindAllMerchant")
	@ApiOperation("(后台)商户列表")
	public ResponseEntity<Result> adminFindAllMerchant(@RequestBody Rewrite_MerchentForAll rewrite_MerchentForAll) {
		log.debug("REST adminFindAllMerchant : {}");
		Result result = rewrite_MerchantService.adminFindAllMerchant(rewrite_MerchentForAll.getUserid(),
				rewrite_MerchentForAll.getName(), rewrite_MerchentForAll.getType(),
				rewrite_MerchentForAll.getStartPage(), rewrite_MerchentForAll.getPageSize());
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

	@PostMapping("/admin/judgeMerchant")
	@ApiOperation("(后台)审批商户")
	public ResponseEntity<Result> judgeMerchant(@RequestBody Rewrite_JudgeMerchantDTO rewrite_JudgeMerchantDTO) {
		log.debug("REST adminFindAllMerchant : {}");
		Result result = rewrite_MerchantService.judgeMerchant(rewrite_JudgeMerchantDTO);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}

}
