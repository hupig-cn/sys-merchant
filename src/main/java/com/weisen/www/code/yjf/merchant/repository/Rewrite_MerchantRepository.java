package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_MerchantRepository extends JpaRepository<Merchant, Long> {


    List<Merchant> findAllByUserid(String userId);


}
