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

    @Query(value = "from Dishes where merchantid = ?1 and state = ?2")
    List<Dishes> getDishesByMerchantidAndState(String merchantId,String state);
}
