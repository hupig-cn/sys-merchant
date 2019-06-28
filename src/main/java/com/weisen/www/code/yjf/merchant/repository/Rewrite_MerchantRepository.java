package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface Rewrite_MerchantRepository extends JpaRepository<Merchant, Long> {


    List<Merchant> findAllByUserid(String userId);

    // 根据经度纬度查询附近商家
    @Query(value = "select id,userid,merchantphoto,name,businessid,state,address,province,city,county,longitude,latitude,concession,rebate," +
        "weight,creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other," +
        "((?1 - longitude) * (?1 - longitude) + (?2 - latitude) * (?2 - latitude)) distance from merchant Order by distance",nativeQuery = true)
    List<Merchant>  findNearbyMerchant(BigDecimal longitude,BigDecimal latitude);

    // 根据经度纬度查询附近热门商家
    @Query(value = "select id,userid,merchantphoto,name,businessid,state,address,province,city,county,longitude,latitude,concession,rebate," +
        "weight,creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other," +
        "((?1 - longitude) * (?1 - longitude) + (?2 - latitude) * (?2 - latitude)) distance from merchant where state = ?3 Order by distance",nativeQuery = true)
    List<Merchant>  findNearbyMerchantAndHot(BigDecimal longitude,BigDecimal latitude,String state);

}
