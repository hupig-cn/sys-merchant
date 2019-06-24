package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.domain.Merchant;
import com.weisen.www.code.yjf.merchant.repository.MerchantRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishesRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishestypeRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.DishesMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class Rewrite_DishesServiceImpl implements Rewrite_DishesService {

    private MerchantRepository merchantRepository;

    private Rewrite_DishestypeRepository dishestypeRepository;

    private Rewrite_DishesRepository dishesRepository;

    private final DishesMapper dishesMapper;

    public Rewrite_DishesServiceImpl(MerchantRepository merchantRepository, Rewrite_DishestypeRepository dishestypeRepository, Rewrite_DishesRepository dishesRepository, DishesMapper dishesMapper) {
        this.merchantRepository = merchantRepository;
        this.dishestypeRepository = dishestypeRepository;
        this.dishesRepository = dishesRepository;
        this.dishesMapper = dishesMapper;
    }

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
}
