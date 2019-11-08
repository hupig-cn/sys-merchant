package com.weisen.www.code.yjf.merchant.service;

import java.math.BigDecimal;
import java.util.List;

import com.weisen.www.code.yjf.merchant.service.dto.MerchantDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_ForNearShop;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_MerchantDTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_JudgeMerchantDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;

public interface Rewrite_MerchantService {

	// 添加商家店铺
	String createMerchant(MerchantDTO merchantDTO);

	// 修改店铺信息
	Result updateMerchant(Rewrite_MerchantDTO merchantDTO);

	// 查询我的店铺
	MerchantDTO findMyShop(Long userid);

	// 查询店铺信息
	MerchantDTO findShopInfo(Long userid);

	// 查询附近热门店铺
	List<MerchantDTO> findPopularMerchant(Rewrite_ForNearShop rewrite_ForNearShop);

	// 距离最近的店铺
	List<MerchantDTO> findNearMerchant(Rewrite_ForNearShop rewrite_ForNearShop);

	// 根据搜索内容查询商户
	Result findByNameLike(Rewrite_ForNearShop rewrite_ForNearShop);

	// 分页倒叙查询商家
	List<MerchantDTO> findAllMerchant(int satrtPage, int pageSize, BigDecimal longitude, BigDecimal latitude);

	// (后台)商户列表
	Result adminFindAllMerchant(String userid, String merchantName, String type, int satrtPage, int pageSize);

	// 审批商户
	Result judgeMerchant(Rewrite_JudgeMerchantDTO rewrite_JudgeMerchantDTO);
	
	// 查询我的店铺 添加用户信息
	Result findMyShopAndUserdeail(Long userid);
}
