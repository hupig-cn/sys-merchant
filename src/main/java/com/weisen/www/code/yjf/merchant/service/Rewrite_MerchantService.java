package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.MerchantDTO;

import java.util.List;

public interface Rewrite_MerchantService {

    //添加商家店铺
    void createMerchant(MerchantDTO merchantDTO);

    //修改店铺信息
    void updateMerchant(MerchantDTO merchantDTO);

    //查询我的店铺列表
    List<MerchantDTO> findAllMyShop(Long userId);

    //查询店铺信息
    MerchantDTO findShopInfo(Long merchantId);


}
