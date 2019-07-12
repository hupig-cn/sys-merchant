package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.DishesorderDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_CreateMenuDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_OrderDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;
import org.springframework.http.ResponseEntity;

public interface Rewrite_DishesorderService {

    // 创建菜单
    Result createMenu(Rewrite_OrderDTO rewrite_OrderDTO);
}
