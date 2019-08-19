package com.weisen.www.code.yjf.merchant.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.merchant.domain.Dishesorder;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishesorderRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesorderService;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_OrderDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;

@Service
@Transactional
public class Rewrite_DishesorderServiceImpl implements Rewrite_DishesorderService {

    private final Rewrite_DishesorderRepository rewrite_DishesorderRepository;

    public Rewrite_DishesorderServiceImpl(Rewrite_DishesorderRepository rewrite_DishesorderRepository ) {
        this.rewrite_DishesorderRepository = rewrite_DishesorderRepository;
    }

    // 创建菜单订单
    @Override
    public Result createMenu(Rewrite_OrderDTO rewrite_OrderDTO) {
        Dishesorder dishesorder = new Dishesorder();

        dishesorder.setBigorder(rewrite_OrderDTO.getBigOrder());
        dishesorder.setLittleorder(null); /////
        dishesorder.setMerchantid(rewrite_OrderDTO.getMerchantId());
        dishesorder.setLocation(rewrite_OrderDTO.getLocation());
        dishesorder.setName(rewrite_OrderDTO.getName());
        dishesorder.setState("0");
        dishesorder.setCreator(null);
        dishesorder.setCreatedate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        dishesorder.setLogicdelete(false);
        dishesorder.setOther(null);
        rewrite_DishesorderRepository.save(dishesorder);
        return Result.suc("成功");
    }
}
