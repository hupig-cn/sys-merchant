package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_orderShop2DTO4;
import com.weisen.www.code.yjf.merchant.service.util.Result;

public interface Rewrite_OrderingMealsService {

	Result getMerchantNameAndData(String id);

    Result merchantDishestype(String id ,String iocid);

    // ('该接口没有被用到')
	Result getMerchantType(String id);

    Result takingOrders(String iocId, Integer num, String merchatid,String name);

    Result takingOrdersNum(String iocId, String merchatid);

    Result merchantOrders(String iocId, String merchatid,String other);

    Result inAllOrders(String iocId, String merchatid);

    Result takingOrders2(Rewrite_orderShop2DTO4 list);

    Result createCaiOrder(String userid, String orderid);

    Result chackIsLittleOrder(String orderid);

    Result caiorder(String orderid);

    Result changeOrderState(String orderid);

}
