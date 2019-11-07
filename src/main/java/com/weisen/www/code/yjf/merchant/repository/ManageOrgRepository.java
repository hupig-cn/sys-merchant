package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.ManageOrg;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ManageOrg entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManageOrgRepository extends JpaRepository<ManageOrg, Long> {

}
