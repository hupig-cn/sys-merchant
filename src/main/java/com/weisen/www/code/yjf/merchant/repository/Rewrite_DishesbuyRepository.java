package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Dishesbuy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Rewrite_DishesbuyRepository extends JpaRepository<Dishesbuy, Long> {
	
	List<Dishesbuy> findByUseridAndMerchantidAndState(Long userid,Long merchantid,Integer state);
	
	List<Dishesbuy> findByOrderid(Long orderid);
}
