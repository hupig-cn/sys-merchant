package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.ShoplocationDTO;

import java.util.List;

public interface Rewrite_ShoplocationService {


    // 根据商户id查询商户店内位置
    List<ShoplocationDTO> getAllMerchantLocation(Long merchantId);

    // 添加位置
    void createShoplocation(ShoplocationDTO shoplocationDTO);

    // 删除位置
    void deleteShoplocation(Long shoplocationId);
}
