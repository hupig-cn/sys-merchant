package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dishestype entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DishestypeRepository extends JpaRepository<Dishestype, Long> {

}
