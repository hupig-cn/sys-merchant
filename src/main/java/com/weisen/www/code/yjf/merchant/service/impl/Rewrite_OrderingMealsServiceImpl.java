package com.weisen.www.code.yjf.merchant.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.merchant.domain.Merchant;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_OrderingMealsService;
import com.weisen.www.code.yjf.merchant.service.util.Result;


@Service
@Transactional
public class Rewrite_OrderingMealsServiceImpl implements Rewrite_OrderingMealsService {

	private final Rewrite_MerchantRepository merchantRepository;


	public Rewrite_OrderingMealsServiceImpl(Rewrite_MerchantRepository merchantRepository) {
		this.merchantRepository = merchantRepository;
	}

	//根据用户id查找店铺图片和商家名称
	@Override
	public Result getMerchantNameAndData(String id) {
		Merchant merchant = new Merchant();
		Merchant merchantData = merchantRepository.findMerchantById(Long.parseLong(id));
		if (merchantData!=null) {
			merchant.setId(merchantData.getId());
			merchant.setName(merchantData.getName());
			merchant.setMerchantphoto("http://app.yuanscore.com:8083/services/basic/api/public/getFiles/"+merchantData.getMerchantphoto());
		}else {
			return Result.suc("暂无此店");
		}
		
		return Result.suc("访问成功",merchant);
	}


}
