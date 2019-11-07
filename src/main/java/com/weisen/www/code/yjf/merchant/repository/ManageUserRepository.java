package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.ManageUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ManageUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManageUserRepository extends JpaRepository<ManageUser, Long> {

}
