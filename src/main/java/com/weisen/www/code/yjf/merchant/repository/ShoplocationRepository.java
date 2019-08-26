package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Shoplocation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Shoplocation entity.
 */
@Repository
public interface ShoplocationRepository extends JpaRepository<Shoplocation, Long> {

}
