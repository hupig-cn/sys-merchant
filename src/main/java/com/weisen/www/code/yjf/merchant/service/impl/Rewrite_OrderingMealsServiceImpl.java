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

import com.weisen.www.code.yjf.merchant.domain.Dishes;
import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.domain.Merchant;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishesRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishestypeRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_OrderingMealsService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesAndTypeDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;


@Service
@Transactional
public class Rewrite_OrderingMealsServiceImpl implements Rewrite_OrderingMealsService {

	private final Rewrite_MerchantRepository merchantRepository;

	private final Rewrite_DishestypeRepository dishestypeRepository;

	private final Rewrite_DishesRepository dishesRepository;
	
	public Rewrite_OrderingMealsServiceImpl(Rewrite_MerchantRepository merchantRepository,
			Rewrite_DishestypeRepository dishestypeRepository,Rewrite_DishesRepository dishesRepository) {
		this.merchantRepository = merchantRepository;
		this.dishestypeRepository = dishestypeRepository;
		this.dishesRepository = dishesRepository;
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

	//根据商家id查找店铺菜单类型
	@Override
	public Result getMerchantType(String id) {
		List<Dishestype> dishestypeList = dishestypeRepository.findAllByMerchantid(id);
		if (!dishestypeList.isEmpty()) {
			ArrayList<DishesAndTypeDTO> dishesAndTypeDTOList = new ArrayList<>();
			for (Dishestype dishestype : dishestypeList) {
				if (dishestype.getState().equals("1")|| dishestype.getState() == "1") {
					Long typeId = dishestype.getId();
					List<Dishes> dishesList = dishesRepository.findByMerchantidAndDishestypeid(id,""+typeId);
					if (!dishesList.isEmpty()) {
						for (Dishes Dishes : dishesList) {
							DishesAndTypeDTO dishesAndTypeDTO = new DishesAndTypeDTO();
							String name = Dishes.getName();
							String image = Dishes.getImage();
							String price = Dishes.getPrice();
							String typeName = dishestype.getName();
							
							dishesAndTypeDTO.setDishestypeid(""+typeId);
							dishesAndTypeDTO.setDishName(name);
							dishesAndTypeDTO.setImage("http://app.yuanscore.com:8083/services/basic/api/public/getFiles/"+image);
							dishesAndTypeDTO.setPrice(price);
							dishesAndTypeDTO.setTypeName(typeName);
							dishesAndTypeDTOList.add(dishesAndTypeDTO);
							
						}
						
					}
				}
			}
			return Result.suc("访问成功!",dishesAndTypeDTOList);
		}else {
			return Result.suc("暂无其他菜品!");
		}
	}


}
