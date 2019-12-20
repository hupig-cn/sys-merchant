package com.weisen.www.code.yjf.merchant.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.merchant.domain.Dishes;
import com.weisen.www.code.yjf.merchant.domain.Dishesbuy;
import com.weisen.www.code.yjf.merchant.domain.Dishesorder;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishesRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishesbuyRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishesorderRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishestypeRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesorderService;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_DishesMenuDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_DishesbuyDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_OrderDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;

@Service
@Transactional
public class Rewrite_DishesorderServiceImpl implements Rewrite_DishesorderService {

    private final Rewrite_DishesorderRepository rewrite_DishesorderRepository;

    private final Rewrite_DishesbuyRepository rewrite_DishesbuyRepository;
    
    private final Rewrite_DishesRepository rewrite_DishesRepository;
    public Rewrite_DishesorderServiceImpl(Rewrite_DishesorderRepository rewrite_DishesorderRepository,
    		Rewrite_DishesbuyRepository rewrite_DishesbuyRepository,
    		Rewrite_DishesRepository rewrite_DishesRepository) {
        this.rewrite_DishesorderRepository = rewrite_DishesorderRepository;
        this.rewrite_DishesbuyRepository =  rewrite_DishesbuyRepository;
        this.rewrite_DishesRepository = rewrite_DishesRepository;
    }

    // 创建菜单订单
    @Override
    public Result createMenu(Rewrite_OrderDTO rewrite_OrderDTO) {
        Dishesorder dishesorder = new Dishesorder();

        dishesorder.setBigorder(rewrite_OrderDTO.getBigOrder());
        dishesorder.setLittleorder(null); /////
        dishesorder.setMerchantid(rewrite_OrderDTO.getMerchantId());
        dishesorder.setLocation(rewrite_OrderDTO.getLocation());
        dishesorder.setName(rewrite_OrderDTO.getName());
        dishesorder.setState("0");
        dishesorder.setCreator(null);
        dishesorder.setCreatedate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        dishesorder.setLogicdelete(false);
        dishesorder.setOther(null);
        rewrite_DishesorderRepository.save(dishesorder);
        return Result.suc("成功");
    }

	@Override
	public Result getMenu(Long userid, Long merchantid) {
		List<Dishesbuy> menu = rewrite_DishesbuyRepository.findByUseridAndMerchantidAndState(userid,merchantid,0);
		List<Rewrite_DishesMenuDTO> returndto=new ArrayList<Rewrite_DishesMenuDTO>();
		for (Dishesbuy menui : menu) {
			Rewrite_DishesMenuDTO dto=new Rewrite_DishesMenuDTO();
			Dishes dishes = rewrite_DishesRepository.findById(menui.getDishesid()).get();
			dto.setDishesid(menui.getDishesid());
			dto.setDishesname(dishes.getName());
			dto.setCount(menui.getCount());
			dto.setPrice(menui.getPrice());
			dto.setDishesImg(dishes.getImage());
			returndto.add(dto);
		}
		return Result.suc("成功",returndto);
	}

	@Override
	public Result updateMenu(Rewrite_DishesbuyDTO dishersbuyDTO) {
		List<Dishesbuy> ifnull = rewrite_DishesbuyRepository.findByUseridAndMerchantidAndState(dishersbuyDTO.getUserid(), dishersbuyDTO.getMerchantid(), 0);
		if(ifnull==null) {
			for (Rewrite_DishesMenuDTO dto : dishersbuyDTO.getMenu()) {
				Rewrite_DishesMenuDTO addDto=new Rewrite_DishesMenuDTO();
				addDto.setDishesid(dto.getDishesid());
				addDto.setCount(dto.getCount());
				addDto.setPrice(dto.getPrice());
			}
		}else{
			
		}
		return null;
	}
	
	
}
