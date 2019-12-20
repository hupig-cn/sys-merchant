package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface Rewrite_MerchantRepository extends JpaRepository<Merchant, Long> {

    Merchant findFirstByUserid(String userid);

    // 根据经度纬度查询 < distance 的附近商家
    @Query(value = "select id,userid,merchantphoto,name,businessid,state,address,province,city,county,longitude,latitude,concession,rebate," +
        " buslicenseimage,jhi_show,creditcode,weight,creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other," +
        "ROUND(6378.138*2*ASIN(SQRT(POW(SIN((?1*PI()/180-latitude*PI()/180)/2),2)+COS(?1*PI()/180)*COS(latitude*PI()/180)*POW(SIN((?2*PI()/180-lng*PI()/180)/2),2)))*1000) AS distance " +
        "from merchant HAVING juli < ?3 Order by distance limit ?4,?5",nativeQuery = true)
    List<Merchant>  findNearbyMerchant(BigDecimal longitude,BigDecimal latitude,int distance,int fromIndex,int pageSize);

    // 根据经度纬度查询附近热门商家
    @Query(value = "select id,userid,merchantphoto,name,businessid,state,address,province,city,county,longitude,latitude,concession,rebate," +
        " buslicenseimage,jhi_show,creditcode,weight,creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other," +
        "((?1 - longitude) * (?1 - longitude) + (?2 - latitude) * (?2 - latitude)) distance from merchant where state = ?3 Order by distance limit ?4,?5",nativeQuery = true)
    List<Merchant>  findNearbyMerchantAndHot(BigDecimal longitude,BigDecimal latitude,String state,int fromIndex,int pageSize);

    //名字模糊查询商户
    @Query(value = "select m.id,m.userid,merchantphoto,m.name,businessid,m.state,m.address,m.province,m.city,m.county,longitude,latitude,concession,rebate," + 
    		"    buslicenseimage,jhi_show,creditcode,weight,m.creator,m.createdate,m.modifier,m.modifierdate,m.modifiernum,m.logicdelete,m.other,u.phone" + 
    		"    from merchant  m LEFT JOIN basic.linkuser u on u.userid= m.userid " + 
    		"where m.name like %?1%  and m.state != '待审核'  and  m.province like %?2% or m.city like %?2% and  m.state != '待审核'  and m.name like %?1% ",nativeQuery = true)
    List<Merchant> findByNameLike(String name,String city);
    
    @Query(value = "select count(*) " + 
    		"from merchant  m LEFT JOIN basic.linkuser u on u.userid= m.userid where m.name like %?1% and m.province in (select gname FROM basic.area where name like %?2% ) ",nativeQuery = true)
    Integer findByNameLikeCount(String name,String city);

    Merchant findByUserid(String userid);

    @Query(value = "select id,userid,merchantphoto,name,businessid,state,address,province,city,county,longitude,latitude,concession,rebate," +
        " buslicenseimage,jhi_show,creditcode,weight,creator,createdate,modifier,modifierdate,modifiernum,logicdelete,other,jhi_show " +
        "from merchant where state = ?1 order by createdate desc limit ?2,?3",nativeQuery = true)
    List<Merchant> findAllMerchant(String state,int indexPage,int pageSize);

    // (后台)商户列表
    @Query(value = "select * from merchant where (?1 is null or userid = ?1) and (?2 is null or name = ?2) and (?3 is null or state = ?3) " +
        "order by createdate desc limit ?4,?5" ,nativeQuery = true)
    List<Merchant> adminFindAllMerchant(String userid,String name,String type,int indexPage,int pageSize);

    @Query(value = "select count(*) from merchant where (?1 is null or userid = ?1) and (?2 is null or name = ?2) and (?3 is null or state = ?3)" ,nativeQuery = true)
    Long countAdmin(String userid,String name,String type);
    
    @Query(value = " select m.*,u.phone from merchant m LEFT join basic.linkuser u on u.userid=m.userid where m.userid = ?1 ", nativeQuery = true)
    Map<String,Object> findFirstByUseridAndUserdeails(String userid);
    
    Merchant findMerchantById(Long id);
}
