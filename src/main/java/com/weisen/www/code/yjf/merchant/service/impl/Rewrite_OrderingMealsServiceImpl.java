package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.domain.Dishes;
import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.domain.Merchant;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishesRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishestypeRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_OrderingMealsService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesAndTypeDTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_typeDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class Rewrite_OrderingMealsServiceImpl implements Rewrite_OrderingMealsService {

	private final Rewrite_MerchantRepository merchantRepository;

	private final Rewrite_DishestypeRepository rewrite_dishestypeRepository;

	private final Rewrite_DishesRepository dishesRepository;

    public Rewrite_OrderingMealsServiceImpl(Rewrite_MerchantRepository merchantRepository, Rewrite_DishestypeRepository rewrite_dishestypeRepository, Rewrite_DishesRepository dishesRepository) {
        this.merchantRepository = merchantRepository;
        this.rewrite_dishestypeRepository = rewrite_dishestypeRepository;
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
            List<Dishes> as = dishesRepository.findByMerchantidAndDishestypeid(id, dishestype.getId() + "");
            Rewrite_typeDTO rewrite_typeDTO = new Rewrite_typeDTO();
            rewrite_typeDTO.setName(name);
            rewrite_typeDTO.setId(a+"");
            rewrite_typeDTO.setDishesList(as);
            ++a;
            list.add(rewrite_typeDTO);
        }
        return Result.suc("查询成功",list);
    }

	//根据商家id查找店铺菜单类型
	@Override
	public Result getMerchantType(String id) {
		List<Dishestype> dishestypeList = rewrite_dishestypeRepository.findAllByMerchantid(id);
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
