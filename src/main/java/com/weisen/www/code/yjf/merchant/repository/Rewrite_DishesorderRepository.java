package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Dishesorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_DishesorderRepository extends JpaRepository<Dishesorder, Long> {

	// 查询订单详情
	List<Dishesorder> findDishesorderByBigorder(String bigorderid);

	// 查询商家所有订单
	@Query(value = "select * from dishesorder where merchantid = ?1 and state in ( 2 , 3 ) order by createdate desc",nativeQuery = true)
	List<Dishesorder> findByMerchantidAndStateOrderByCreatedateDesc(String merchantId);

	Dishesorder findByBigorder(String bigorder);
	
	// 查询订单详情并按时间排序
	List<Dishesorder> findDishesorderByBigorderOrderByCreatedateDesc(String bigorderid);
}
