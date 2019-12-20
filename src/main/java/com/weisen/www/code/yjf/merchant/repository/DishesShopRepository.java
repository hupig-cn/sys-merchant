package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.DishesShop;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the DishesShop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DishesShopRepository extends JpaRepository<DishesShop, Long> {

    DishesShop findDishesShopByMerchatidAndIocAndName(Integer merchatid,String Ioc,String name);

    List<DishesShop> findDishesShopByMerchatidAndIoc(Integer merchatid, String Ioc);
}
