package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.DishesorderDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_CreateMenuDTO;
import org.springframework.http.ResponseEntity;

public interface Rewrite_DishesorderService {

    ResponseEntity<DishesorderDTO> createMenu(Rewrite_CreateMenuDTO createMenuDTO);
}
