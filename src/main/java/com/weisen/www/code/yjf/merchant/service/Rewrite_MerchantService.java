package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.MerchantDTO;

import java.math.BigDecimal;
import java.util.List;

public interface Rewrite_MerchantService {

    //添加商家店铺
    String createMerchant(MerchantDTO merchantDTO);

    //修改店铺信息
    void updateMerchant(MerchantDTO merchantDTO);

    //查询我的店铺
    MerchantDTO findMyShop(Long userid);

    //查询店铺信息
    MerchantDTO findShopInfo(Long merchantId);

    //查询附近热门店铺
    List<MerchantDTO> findPopularMerchant(BigDecimal longitude, BigDecimal latitude);

    //距离最近的店铺
    List<MerchantDTO> findNearMerchant(String longitude,String latitude);

}
