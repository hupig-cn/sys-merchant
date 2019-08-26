package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Dishesorder;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dishesorder entity.
 */
@Repository
public interface DishesorderRepository extends JpaRepository<Dishesorder, Long> {

}
