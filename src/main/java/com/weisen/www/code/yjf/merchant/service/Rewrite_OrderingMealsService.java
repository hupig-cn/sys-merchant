package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.util.Result;

public interface Rewrite_OrderingMealsService {

	Result getMerchantNameAndData(String id);

    Result merchantDishestype(String id ,String iocid);

	Result getMerchantType(String id);

    Result takingOrders(String iocId, Integer num, String merchatid,String name);

    Result takingOrdersNum(String iocId, String merchatid);

    Result merchantOrders(String iocId, String merchatid,String other);

    Result inAllOrders(String iocId, String merchatid);

}
