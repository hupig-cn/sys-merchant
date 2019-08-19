package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_OrderDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;

public interface Rewrite_DishesorderService {

    // 创建菜单
    Result createMenu(Rewrite_OrderDTO rewrite_OrderDTO);
}
