package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Merchant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Merchant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

}
