package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.domain.Dishesorder;
import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.repository.DishestypeRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishesorderRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_DishestypeRepository;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesorderService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesorderDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_CreateMenuDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.DishesorderMapper;
import com.weisen.www.code.yjf.merchant.service.mapper.MerchantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class Rewrite_DishesorderServiceImpl implements Rewrite_DishesorderService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_DishesorderServiceImpl.class);

    private final Rewrite_DishesorderRepository rewrite_DishesorderRepository;

    private final DishesorderMapper dishesorderMapper;

    public Rewrite_DishesorderServiceImpl(Rewrite_DishesorderRepository rewrite_DishesorderRepository, DishesorderMapper dishesorderMapper) {
        this.rewrite_DishesorderRepository = rewrite_DishesorderRepository;
        this.dishesorderMapper = dishesorderMapper;
    }

    // 创建菜单
    @Override
    public ResponseEntity<DishesorderDTO> createMenu(Rewrite_CreateMenuDTO createMenuDTO) {
        Dishesorder dishesorder = new Dishesorder();

        dishesorder.setBigorder(createMenuDTO.getBigorder());
        dishesorder.setLittleorder(null); /////
        dishesorder.setMerchantid(createMenuDTO.getMerchantId().toString());
        dishesorder.setLocation(createMenuDTO.getLocationId().toString());
        dishesorder.setName(createMenuDTO.getDishName());
        dishesorder.setState("0");
        dishesorder.setCreator(null);
        dishesorder.setCreatedate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        dishesorder.setLogicdelete(false);
        dishesorder.setOther(null);
        rewrite_DishesorderRepository.save(dishesorder);
        return null;
    }
}
