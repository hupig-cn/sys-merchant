package com.weisen.www.code.yjf.merchant.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishestypeRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_DishestypeService;
import com.weisen.www.code.yjf.merchant.service.dto.DishestypeDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.DishestypeMapper;

@Service
@Transactional
public class Rewrite_DishestypeServiceImpl implements Rewrite_DishestypeService {

    private final Rewrite_DishestypeRepository rewrite_DishestypeRepository;

    private final DishestypeMapper dishestypeMapper;

    public Rewrite_DishestypeServiceImpl(Rewrite_DishestypeRepository rewrite_DishestypeRepository, DishestypeMapper dishestypeMapper) {
        this.rewrite_DishestypeRepository = rewrite_DishestypeRepository;
        this.dishestypeMapper = dishestypeMapper;
    }

    // 查询商户的菜单类型
    @Override
    public List<DishestypeDTO> getMerchantmMenu(Long merchantId) {
        List<Dishestype> list = rewrite_DishestypeRepository.findAllByMerchantid(merchantId.toString());
        return dishestypeMapper.toDto(list);
    }
}
