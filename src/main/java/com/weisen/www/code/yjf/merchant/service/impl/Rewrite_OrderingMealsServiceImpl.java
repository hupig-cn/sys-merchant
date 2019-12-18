package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishestypeRepository;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_typeDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.merchant.domain.Merchant;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_OrderingMealsService;
import com.weisen.www.code.yjf.merchant.service.util.Result;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class Rewrite_OrderingMealsServiceImpl implements Rewrite_OrderingMealsService {

	private final Rewrite_MerchantRepository merchantRepository;

	private final Rewrite_DishestypeRepository rewrite_dishestypeRepository;


	public Rewrite_OrderingMealsServiceImpl(Rewrite_MerchantRepository merchantRepository, Rewrite_DishestypeRepository rewrite_dishestypeRepository) {
		this.merchantRepository = merchantRepository;
        this.rewrite_dishestypeRepository = rewrite_dishestypeRepository;
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

    @Override
    public Result merchantDishestype(String id) {
        List<Dishestype> allByMerchantid = rewrite_dishestypeRepository.findAllByMerchantid(id);

        List<Rewrite_typeDTO> list = new ArrayList<>();
        int a = 1;
        for (int i = 0; i < allByMerchantid.size(); i++) {
            Dishestype dishestype = allByMerchantid.get(i);
            String name = dishestype.getName();
            String state = dishestype.getState();
            if (state.equals("0")){
                continue;
            }

            Rewrite_typeDTO rewrite_typeDTO = new Rewrite_typeDTO();
            rewrite_typeDTO.setName(name);
            rewrite_typeDTO.setId(a+"");
            ++a;
            list.add(rewrite_typeDTO);
        }
        return Result.suc("查询成功",list);
    }


}
