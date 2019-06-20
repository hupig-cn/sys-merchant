package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Dishes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dishes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DishesRepository extends JpaRepository<Dishes, Long> {

}
