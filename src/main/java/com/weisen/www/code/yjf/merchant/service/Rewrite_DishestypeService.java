package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.service.dto.DishestypeDTO;

import java.util.List;

public interface Rewrite_DishestypeService {

    // 商户添加菜单类型

    // 查询商户的菜单类型
    List<DishestypeDTO> getMerchantmMenu(Long merchantId);

}
