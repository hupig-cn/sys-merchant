package com.weisen.www.code.yjf.merchant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.merchant.domain.Userorder;



/**
 * Spring Data  repository for the Userorder entity.
 */
@Repository
public interface UserorderRepository extends JpaRepository<Userorder, Long> {

	@Query(value="select * from basic.userorder where ordercode = ?1",nativeQuery = true)
	Userorder findUserorderByOrdercode(String orderId);
}
