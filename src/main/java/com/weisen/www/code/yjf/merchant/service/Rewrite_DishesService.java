package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.DishesDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface Rewrite_DishesService {

    //获取商家全部的商品列表
    public ResponseEntity<Map<String, List<DishesDTO>>> getDishes (Long merchantId);

    //查询商家上架的商品列表
    List<DishesDTO> findAllUpDishes(Long merchantId);

    //查询下架的商品列表
    List<DishesDTO> findAllDownDishes(Long merchantId);

    //添加商品
    void createDishes(DishesDTO dishesDTO);

    //查询商品详情
    DishesDTO findDishesInfo(Long dishesId);

    //修改商品信息
    void updateDishes(DishesDTO dishesDTO);

    //上架
    void upDishes(DishesDTO dishesDTO);

    //下架
    void downDishes(DishesDTO dishesDTO);

    //删除商品
    void deleteDishes(Long dishesId);

    //批量删除
    void deleteListDishes(List<Long> dishesId);

    //获取商户 出售中，已下架，草稿的商品
    Result getInfoForGoods(Long userId);


}
