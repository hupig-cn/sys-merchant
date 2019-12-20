package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Dishesbuy;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dishesbuy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DishesbuyRepository extends JpaRepository<Dishesbuy, Long> {

}
