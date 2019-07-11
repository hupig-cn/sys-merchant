package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.repository.DishestypeRepository;
import com.weisen.www.code.yjf.merchant.repository.MerchantRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishesRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishestypeRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_DishestypeService;
import com.weisen.www.code.yjf.merchant.service.dto.DishestypeDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.DishesMapper;
import com.weisen.www.code.yjf.merchant.service.mapper.DishestypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Rewrite_DishestypeServiceImpl implements Rewrite_DishestypeService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_DishestypeServiceImpl.class);

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
