package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Shoplocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_ShoplocationRepository extends JpaRepository<Shoplocation, Long> {

    // 查询商户的所有位置
    List<Shoplocation> findAllByMerchantid(String merchantId);
}
