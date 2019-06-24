package com.weisen.www.code.yjf.merchant.service;

import com.weisen.www.code.yjf.merchant.service.dto.DishesDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface Rewrite_DishesService {

    public ResponseEntity<Map<String, List<DishesDTO>>> getDishes (Long merchantId);
}
