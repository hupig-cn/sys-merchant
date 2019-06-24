package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.service.Rewrite_DishesorderService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesorderDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_CreateMenuDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Rewrite_DishesorderServiceImpl implements Rewrite_DishesorderService {

    @Override
    public ResponseEntity<DishesorderDTO> createMenu(Rewrite_CreateMenuDTO createMenuDTO) {
        return null;
    }
}
