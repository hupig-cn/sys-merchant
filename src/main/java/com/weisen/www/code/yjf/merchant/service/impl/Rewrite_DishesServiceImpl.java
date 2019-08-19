package com.weisen.www.code.yjf.merchant.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.merchant.domain.Dishes;
import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.domain.Merchant;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishesRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishestypeRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_GosCountDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.DishesMapper;
import com.weisen.www.code.yjf.merchant.service.util.Result;

@Service
@Transactional
public class Rewrite_DishesServiceImpl implements Rewrite_DishesService {

    private Rewrite_MerchantRepository merchantRepository;

    private Rewrite_DishestypeRepository dishestypeRepository;

    private Rewrite_DishesRepository dishesRepository;

    private final DishesMapper dishesMapper;

    public Rewrite_DishesServiceImpl(Rewrite_MerchantRepository merchantRepository, Rewrite_DishestypeRepository dishestypeRepository, Rewrite_DishesRepository dishesRepository, DishesMapper dishesMapper) {
        this.merchantRepository = merchantRepository;
        this.dishestypeRepository = dishestypeRepository;
        this.dishesRepository = dishesRepository;
        this.dishesMapper = dishesMapper;
    }

    //获取商家全部的商品列表
    @Override
    public ResponseEntity<Map<String, List<DishesDTO>>> getDishes(Long merchantId) {
        Optional<Merchant> optional = merchantRepository.findById(merchantId);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Map<String, List<DishesDTO>> dishesMap = new TreeMap<>();
        Merchant merchant = optional.get();
        List<Dishestype> dishestypeListByCreator = dishestypeRepository.getDishestypeByCreator(merchant.getCreator());
        for (Dishestype dishestype : dishestypeListByCreator) {
            List<DishesDTO> dishesByType = dishesRepository.getDishesByType(dishestype.getId()).stream().map(dishesMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
            dishesMap.put(dishestype.getName(), dishesByType);
        }
        return ResponseEntity.ok(dishesMap);
    }

    //查询商家上架的商品列表
    @Override
    public List<DishesDTO> findAllUpDishes(Long merchantId) {
        List<Dishes> list = dishesRepository.getDishesByMerchantidAndState(merchantId.toString(),"1");
        return dishesMapper.toDto(list);
    }

    //查询下架的商品列表
    @Override
    public List<DishesDTO> findAllDownDishes(Long merchantId) {
        List<Dishes> list = dishesRepository.getDishesByMerchantidAndState(merchantId.toString(),"2");
        return dishesMapper.toDto(list);
    }

    //添加商品
    @Override
    public void createDishes(DishesDTO dishesDTO) {
        Dishes dishes = new Dishes();
        dishes.setName(dishesDTO.getName());
        dishes.setImage(dishesDTO.getImage());
        dishes.setDishestypeid(dishesDTO.getDishestypeid());
        dishes.setPrice(dishesDTO.getPrice());
        dishes.setNum(dishesDTO.getNum());
        dishes.setCreator(""); // 拿取创建者
        dishes.setCreatedate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        dishes.setModifiernum(dishesDTO.getModifiernum());
        dishes.setModifier(dishesDTO.getModifier());
        dishes.setLogicdelete(dishesDTO.isLogicdelete());
        dishes.setState(dishesDTO.getState());
        dishes.setOther(dishesDTO.getOther());
        dishesRepository.save(dishes);
    }

    //查询商品详情
    @Override
    public DishesDTO findDishesInfo(Long dishesId) {
        Dishes dishes = dishesRepository.getOne(dishesId);
        return dishesMapper.toDto(dishes);
    }

    //修改商品信息
    @Override
    public void updateDishes(DishesDTO dishesDTO) {
        Dishes dishes = new Dishes();
        dishes.setMerchantid(dishesDTO.getMerchantid());
        dishes.setName(dishesDTO.getName());
        dishes.setImage(dishesDTO.getImage());
        dishes.setDishestypeid(dishesDTO.getDishestypeid());
        dishes.setPrice(dishesDTO.getPrice());
        dishes.setNum(dishesDTO.getNum());
        dishes.setCreator(dishesDTO.getCreator());
        dishes.setModifiernum(dishesDTO.getModifiernum());
        dishes.setModifier(dishesDTO.getModifier());
        dishes.setModifierdate(dishesDTO.getModifierdate());
        dishes.setLogicdelete(dishesDTO.isLogicdelete());
        dishes.setState(dishesDTO.getState());
        dishes.setOther(dishesDTO.getOther());
        dishesRepository.saveAndFlush(dishes);
    }

    //上架
    @Override
    public void upDishes(DishesDTO dishesDTO) {
        Dishes dishes = dishesMapper.toEntity(dishesDTO);
        dishes.setState("1");
        dishesRepository.saveAndFlush(dishes);
    }

    //下架
    @Override
    public void downDishes(DishesDTO dishesDTO) {
        Dishes dishes = dishesMapper.toEntity(dishesDTO);
        dishes.setState("2");
        dishesRepository.saveAndFlush(dishes);
    }

    //删除商品
    @Override
    public void deleteDishes(Long dishesId) {
        dishesRepository.deleteById(dishesId);
    }

    //批量删除
    @Override
    public void deleteListDishes(List<Long> dishesId) {
        dishesId.forEach(x -> dishesRepository.deleteById(x));
    }

    //获取商户 出售中，已下架，草稿的商品
    @Override
    public Result getInfoForGoods(Long userId) {
        Merchant merchant = merchantRepository.findByUserid(userId.toString());
        if(merchant == null ){
            return Result.fail("商户不存在");
        }
        // 草稿
        long zero = dishesRepository.getDishesByMerchantidAndState(merchant.getId().toString(),"0").stream().count();
        // 上架
        long one = dishesRepository.getDishesByMerchantidAndState(merchant.getId().toString(),"1").stream().count();
        // 下架
        long two = dishesRepository.getDishesByMerchantidAndState(merchant.getId().toString(),"2").stream().count();

        Rewrite_GosCountDTO rewrite_GosCountDTO = new Rewrite_GosCountDTO(one,two,zero);
        return Result.suc("成功",rewrite_GosCountDTO);
    }
}
