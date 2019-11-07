package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.ManageMenus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ManageMenus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManageMenusRepository extends JpaRepository<ManageMenus, Long> {

}
