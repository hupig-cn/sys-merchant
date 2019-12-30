package com.weisen.www.code.yjf.merchant.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.merchant.domain.Dishes;
import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.domain.Merchant;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishesRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishestypeRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesOnAndOffShelvesService;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_NewClassificationDTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_NewDishesDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;
import com.weisen.www.code.yjf.merchant.service.util.TimeUtil;

@Service
@Transactional
public class Rewrite_DishesOnAndOffShelvesServiceImpl implements Rewrite_DishesOnAndOffShelvesService {

	private final Rewrite_MerchantRepository rewrite_MerchantRepository;

	private final Rewrite_DishestypeRepository rewrite_DishestypeRepository;

	private final Rewrite_DishesRepository rewrite_DishesRepository;

	public Rewrite_DishesOnAndOffShelvesServiceImpl(Rewrite_MerchantRepository rewrite_MerchantRepository,
			Rewrite_DishestypeRepository rewrite_DishestypeRepository,
			Rewrite_DishesRepository rewrite_DishesRepository) {
		this.rewrite_MerchantRepository = rewrite_MerchantRepository;
		this.rewrite_DishestypeRepository = rewrite_DishestypeRepository;
		this.rewrite_DishesRepository = rewrite_DishesRepository;
	}

	/**
	 * 新增菜品分类
	 */
	@Override
	public Result newClassification(String merchantId, String name, String other) {
		Merchant merchant = rewrite_MerchantRepository.findByUseridAndBusinessid(merchantId, "餐饮");
		List<Dishestype> dishesTypeList = rewrite_DishestypeRepository.findByMerchantidOrderByTypeorderAsc(merchantId);
		if (merchant == null) {
			return Result.fail("您不是餐饮商家!不能对此功能进行操作!");
		}
		// 判断该商家是否已经有数据
		if (!dishesTypeList.isEmpty()) {
			Integer bigTypeNum = 1;
			for (Dishestype dishestype : dishesTypeList) {
				Integer typeNum = dishestype.getTypeorder();
				if (typeNum > bigTypeNum) {
					bigTypeNum = typeNum;
				}
			}
			Dishestype dishesType = rewrite_DishestypeRepository.findByMerchantidAndName(merchantId, name);
			if (dishesType != null) {
				return Result.fail("该分类名称已存在哦!请重新定义!");
			}
			Dishestype dishestype = new Dishestype();
			dishestype.setName(name);
			dishestype.setMerchantid(merchantId);
			dishestype.setState("1");
			dishestype.setCreator(merchantId);
			dishestype.setCreatedate(TimeUtil.getDate());
			dishestype.setLogicdelete(true);
			dishestype.setModifiernum(1L);
			dishestype.setTypeorder(bigTypeNum + 1);
			rewrite_DishestypeRepository.save(dishestype);
			return Result.suc("新增成功!");
		} else {
			// 第一次新增直接加入数据
			Dishestype dishestype = new Dishestype();
			dishestype.setName(name);
			dishestype.setMerchantid(merchantId);
			dishestype.setState("1");
			dishestype.setCreator(merchantId);
			dishestype.setCreatedate(TimeUtil.getDate());
			dishestype.setLogicdelete(true);
			dishestype.setModifiernum(1L);
			dishestype.setTypeorder(1);
			rewrite_DishestypeRepository.save(dishestype);
			return Result.suc("新增成功!");
		}
	}

	/**
	 * 修改菜品分类
	 */
	@Override
	public Result updateClassification(List<Rewrite_NewClassificationDTO> rewrite_NewClassificationDTOList) {
		String merchantId = null;
		for (Rewrite_NewClassificationDTO rewrite_NewClassificationDTO : rewrite_NewClassificationDTOList) {
			Merchant merchant = rewrite_MerchantRepository
					.findByUseridAndBusinessid(rewrite_NewClassificationDTO.getMerchantId(), "餐饮");
			if (merchant == null) {
				return Result.fail("您不是餐饮商家!不能对此功能进行操作!");
			}
			// 判断该分类名称是否重复
			Dishestype dishesTypeName = rewrite_DishestypeRepository.findByMerchantidAndName(
					rewrite_NewClassificationDTO.getMerchantId(), rewrite_NewClassificationDTO.getName());
			if (dishesTypeName != null) {
				return Result.fail("该分类名称已存在哦!请重新定义!");
			}
			merchantId = rewrite_NewClassificationDTO.getMerchantId();
		}

		// 获取到该商家的所有菜品分类
		List<Dishestype> dishestypesList = rewrite_DishestypeRepository.findByMerchantidOrderByTypeorderAsc(merchantId);

		if (dishestypesList.isEmpty()) {
			return Result.fail("您还没有菜品分类哦!赶紧去添加吧!");
		} else {
			// 获取前端传过来的数组信息
			for (Rewrite_NewClassificationDTO rewrite_NewClassificationDTO : rewrite_NewClassificationDTOList) {

				Long newId = rewrite_NewClassificationDTO.getId();
				String newName = rewrite_NewClassificationDTO.getName();
				String newOther = rewrite_NewClassificationDTO.getOther();
				String newState = rewrite_NewClassificationDTO.getState();
				Integer newTypeOrder = rewrite_NewClassificationDTO.getTypeOrder();
				for (Dishestype dishestype : dishestypesList) {
					if (dishestype.getId() == newId) {
						dishestype.setId(newId);
						dishestype.setName(newName);
						dishestype.setMerchantid(merchantId);
						dishestype.setModifier(merchantId);
						dishestype.setModifierdate(TimeUtil.getDate());
						dishestype.setModifiernum(dishestype.getModifiernum() + 1L);
						dishestype.setState(newState);
						dishestype.setOther(newOther);
						dishestype.setTypeorder(newTypeOrder);
						rewrite_DishestypeRepository.saveAndFlush(dishestype);
					}
				}
			}
		}
		return Result.suc("修改成功!");
	}

	/**
	 * 删除菜品分类
	 */
	@Override
	public Result deleteClassification(Long id, String merchantId) {
		Merchant merchant = rewrite_MerchantRepository.findByUseridAndBusinessid(merchantId, "餐饮");
		if (merchant == null) {
			return Result.fail("您不是餐饮商家!不能对此功能进行操作!");
		} else {
			Dishestype dishesType = rewrite_DishestypeRepository.findByIdAndMerchantid(id, merchantId);
			if (dishesType == null) {
				return Result.fail("您没有该菜品分类哦!");
			}
			rewrite_DishestypeRepository.delete(dishesType);
			return Result.suc("删除成功!");
		}
	}

	/**
	 * 新增菜品
	 */
	@Override
	public Result newDishes(Rewrite_NewDishesDTO rewrite_NewDishesDTO) {
		Merchant merchant = rewrite_MerchantRepository.findByUseridAndBusinessid(rewrite_NewDishesDTO.getMerchantId(),
				"餐饮");
		List<Dishes> dishesList = rewrite_DishesRepository.findByMerchantidAndNameAndState(
				rewrite_NewDishesDTO.getMerchantId(), rewrite_NewDishesDTO.getName(), "1");
		if (merchant == null) {
			return Result.fail("您不是餐饮商家!不能对此功能进行操作!");
		}
		// 判断该商家是否已经有数据
		if (!dishesList.isEmpty()) {
			Dishes dishesName = rewrite_DishesRepository.findByMerchantidAndName(rewrite_NewDishesDTO.getMerchantId(),
					rewrite_NewDishesDTO.getName());
			if (dishesName != null) {
				return Result.fail("该菜品名称已存在哦!请重新定义!");
			}
			Dishes dishes = new Dishes();
			// 商户ID
			dishes.setMerchantid(rewrite_NewDishesDTO.getMerchantId());
			// 菜品名称
			dishes.setName(rewrite_NewDishesDTO.getName());
			Dishestype dishesType = rewrite_DishestypeRepository.findByIdAndMerchantid(rewrite_NewDishesDTO.getId(),
					rewrite_NewDishesDTO.getMerchantId());
			if (dishesType == null) {
				return Result.fail("您没有该菜品分类哦!");
			}
			// 菜品类型ID
			dishes.setDishestypeid(dishesType.getId().toString());
			// 菜品价格
			dishes.setPrice(rewrite_NewDishesDTO.getPrice());
			// 创建时间
			dishes.setCreatedate(TimeUtil.getDate());
			// 创建者
			dishes.setCreator(rewrite_NewDishesDTO.getMerchantId());
			// 逻辑删除
			dishes.setLogicdelete(true);
			// 菜品库存
			dishes.setNum(rewrite_NewDishesDTO.getName());
			// 菜品状态
			dishes.setState(rewrite_NewDishesDTO.getState());
			// 备注
			dishes.setOther(rewrite_NewDishesDTO.getOther());
			// 图片ID
			dishes.setImage(rewrite_NewDishesDTO.getImage());
			// 修改次数
			dishes.setModifiernum(1L);
			rewrite_DishesRepository.save(dishes);
			return Result.suc("新增成功!");
		} else {
			// 第一次新增直接加入数据
			Dishes dishes = new Dishes();
			dishes.setMerchantid(rewrite_NewDishesDTO.getMerchantId());
			dishes.setName(rewrite_NewDishesDTO.getName());
			Dishestype dishesType = rewrite_DishestypeRepository.findByIdAndMerchantid(rewrite_NewDishesDTO.getId(),
					rewrite_NewDishesDTO.getMerchantId());
			if (dishesType == null) {
				return Result.fail("您没有该菜品分类哦!");
			}
			dishes.setDishestypeid(dishesType.getId().toString());
			dishes.setPrice(rewrite_NewDishesDTO.getPrice());
			dishes.setCreatedate(TimeUtil.getDate());
			dishes.setCreator(rewrite_NewDishesDTO.getMerchantId());
			dishes.setLogicdelete(true);
			dishes.setNum(rewrite_NewDishesDTO.getNum());
			dishes.setState(rewrite_NewDishesDTO.getState());
			dishes.setOther(rewrite_NewDishesDTO.getOther());
			dishes.setImage(rewrite_NewDishesDTO.getImage());
			dishes.setModifiernum(1L);
			rewrite_DishesRepository.save(dishes);
			return Result.suc("新增成功!");
		}
	}

	/**
	 * 修改菜品
	 */
	@Override
	public Result updateDishes(List<Rewrite_NewDishesDTO> rewrite_NewDishesDTOList) {
		String merchantId = null;
		for (Rewrite_NewDishesDTO rewrite_NewDishesDTO : rewrite_NewDishesDTOList) {
			Merchant merchant = rewrite_MerchantRepository
					.findByUseridAndBusinessid(rewrite_NewDishesDTO.getMerchantId(), "餐饮");
			if (merchant == null) {
				return Result.fail("您不是餐饮商家!不能对此功能进行操作!");
			}
			// 判断该分类名称是否重复
			Dishes dishesName = rewrite_DishesRepository.findByMerchantidAndName(rewrite_NewDishesDTO.getMerchantId(),
					rewrite_NewDishesDTO.getName());
			if (dishesName != null) {
				return Result.fail("该菜品名称已存在哦!请重新定义!");
			}
			merchantId = rewrite_NewDishesDTO.getMerchantId();

		}

		// 获取到该商家的所有菜品
		List<Dishes> dishesList = rewrite_DishesRepository.findByMerchantid(merchantId);

		if (dishesList.isEmpty()) {
			return Result.fail("您还没有该菜品哦!赶紧去添加吧!");
		} else {
			// 获取前端传过来的数组信息
			for (Rewrite_NewDishesDTO rewrite_NewDishesDTO : rewrite_NewDishesDTOList) {
				Long newId = rewrite_NewDishesDTO.getId();
				String newName = rewrite_NewDishesDTO.getName();
				String newOther = rewrite_NewDishesDTO.getOther();
				String newState = rewrite_NewDishesDTO.getState();
				String image = rewrite_NewDishesDTO.getImage();
				String num = rewrite_NewDishesDTO.getNum();
				String price = rewrite_NewDishesDTO.getPrice();
				String dishestypeId = rewrite_NewDishesDTO.getDishestypeId();
				Dishestype dishesType = rewrite_DishestypeRepository.findByIdAndMerchantid(
						Long.parseLong(rewrite_NewDishesDTO.getDishestypeId()), rewrite_NewDishesDTO.getMerchantId());
				if (dishesType == null) {
					return Result.fail("您没有该菜品分类哦!");
				}
				for (Dishes dishes : dishesList) {
					if (dishes.getId() == newId) {
						dishes.setName(newName);
						dishes.setMerchantid(merchantId);
						dishes.setModifier(merchantId);
						dishes.setModifierdate(TimeUtil.getDate());
						dishes.setModifiernum(dishes.getModifiernum() + 1L);
						dishes.setState(newState);
						dishes.setOther(newOther);
						dishes.setDishestypeid(dishestypeId);
						dishes.setImage(image);
						dishes.setNum(num);
						dishes.setPrice(price);
						rewrite_DishesRepository.saveAndFlush(dishes);
					}
				}
			}
		}
		return Result.suc("修改成功!");
	}

	/**
	 * 删除菜品
	 */
	@Override
	public Result deleteDishes(String merchantId, String name) {
		Merchant merchant = rewrite_MerchantRepository.findByUseridAndBusinessid(merchantId, "餐饮");
		if (merchant == null) {
			return Result.fail("您不是餐饮商家!不能对此功能进行操作!");
		} else {
			Dishes dishes = rewrite_DishesRepository.findByMerchantidAndName(merchantId, name);
			if (dishes == null) {
				return Result.fail("您没有该菜品哦!");
			}
			rewrite_DishesRepository.delete(dishes);
			return Result.suc("删除成功!");
		}
	}

}
