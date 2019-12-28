package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Dishesorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_DishesorderRepository extends JpaRepository<Dishesorder, Long> {

    List<Dishesorder> findDishesorderByBigorder(String bigorderid);
}
