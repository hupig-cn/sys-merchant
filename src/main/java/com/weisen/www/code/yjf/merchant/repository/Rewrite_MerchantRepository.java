package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface Rewrite_MerchantRepository extends JpaRepository<Merchant, Long> {

    Merchant findFirstByUserid(String userid);

    // 根据经度纬度查询 < distance 的附近商家
    @Query(value = "select id,userid,merchantphoto,name,businessid,state,address,province,city,county,longitude,latitude,concession,rebate," +
        " buslicenseimage,creditcode,weight,creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other," +
        "ROUND(6378.138*2*ASIN(SQRT(POW(SIN((?1*PI()/180-latitude*PI()/180)/2),2)+COS(?1*PI()/180)*COS(latitude*PI()/180)*POW(SIN((?2*PI()/180-lng*PI()/180)/2),2)))*1000) AS distance " +
        "from merchant HAVING juli < ?3 Order by distance limit ?4,?5",nativeQuery = true)
    List<Merchant>  findNearbyMerchant(BigDecimal longitude,BigDecimal latitude,int distance,int fromIndex,int pageSize);

    // 根据经度纬度查询附近热门商家
    @Query(value = "select id,userid,merchantphoto,name,businessid,state,address,province,city,county,longitude,latitude,concession,rebate," +
        " buslicenseimage,creditcode,weight,creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other," +
        "((?1 - longitude) * (?1 - longitude) + (?2 - latitude) * (?2 - latitude)) distance from merchant where state = ?3 Order by distance limit ?4,?5",nativeQuery = true)
    List<Merchant>  findNearbyMerchantAndHot(BigDecimal longitude,BigDecimal latitude,String state,int fromIndex,int pageSize);

    //名字模糊查询商户
    @Query(value = "select id,userid,merchantphoto,name,businessid,state,address,province,city,county,longitude,latitude,concession,rebate," +
        " buslicenseimage,creditcode,weight,creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other " +
        "from merchant where name like '%?1%' limit ?2,?3",nativeQuery = true)
    List<Merchant> findByNameLike(String name,int fromIndex,int pageSize);

    Merchant findByUserid(String userid);

    @Query(value = "select id,userid,merchantphoto,name,businessid,state,address,province,city,county,longitude,latitude,concession,rebate," +
        " buslicenseimage,creditcode,weight,creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other,jhi_show " +
        "from merchant order by createdate desc limit ?1,?2",nativeQuery = true)
    List<Merchant> findAllMerchant(int indexPage,int pageSize);
}
