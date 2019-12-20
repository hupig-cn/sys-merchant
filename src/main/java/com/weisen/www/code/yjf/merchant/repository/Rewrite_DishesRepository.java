package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_DishesRepository extends JpaRepository<Dishes, Long> {

    @Query(value = "from Dishes where dishestypeid = ?1")
    List<Dishes> getDishesByType (Long dishestypeId);

    // state = 0 草稿  1 上架   2下架
    @Query(value = "from Dishes where merchantid = ?1 and state = ?2")
    List<Dishes> getDishesByMerchantidAndState(String merchantId,String state);

    @Query(value = "select * from Dishes where merchantid = ?1 and state = 1 and dishestypeid = ?2 and logicdelete = 1",nativeQuery = true)
    List<Dishes> findByMerchantidAndDishestypeid(String merchantid,String dishestypeid);

    Dishes findDishesByMerchantidAndNameAndState(String Merchantid ,String Name ,String State);
}
