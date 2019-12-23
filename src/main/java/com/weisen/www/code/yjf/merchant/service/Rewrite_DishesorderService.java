package com.weisen.www.code.yjf.merchant.service;

import java.util.List;

import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_DishesbuyDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_OrderDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;

public interface Rewrite_DishesorderService {

    // 创建菜单
    Result createMenu(Rewrite_OrderDTO rewrite_OrderDTO);
    
    // 查看历史点菜单
    Result getMenu(Long userid,Long merchantid);
    
    // 新增点餐 修改以点的餐
    Result updateMenu(Rewrite_DishesbuyDTO dishesbugDTO);
}
