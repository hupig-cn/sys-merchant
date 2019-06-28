package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.domain.Shoplocation;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_ShoplocationRepository;
import com.weisen.www.code.yjf.merchant.repository.ShoplocationRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_ShoplocationService;
import com.weisen.www.code.yjf.merchant.service.dto.ShoplocationDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.ShoplocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class Rewrite_ShoplocationServiceImpl implements Rewrite_ShoplocationService {

    private final Logger log = LoggerFactory.getLogger(ShoplocationServiceImpl.class);

    private final Rewrite_ShoplocationRepository rewrite_ShoplocationRepository;

    private final ShoplocationMapper shoplocationMapper;

    public Rewrite_ShoplocationServiceImpl(Rewrite_ShoplocationRepository rewrite_ShoplocationRepository, ShoplocationMapper shoplocationMapper) {
        this.rewrite_ShoplocationRepository = rewrite_ShoplocationRepository;
        this.shoplocationMapper = shoplocationMapper;
    }

    // 根据商户id查询商户店内位置
    @Override
    public List<ShoplocationDTO> getAllMerchantLocation(Long merchantId) {
        List<Shoplocation> shoplocation = rewrite_ShoplocationRepository.findAllByMerchantid(merchantId.toString());
        return shoplocationMapper.toDto(shoplocation);
    }

    // 添加位置
    @Override
    public void createShoplocation(ShoplocationDTO shoplocationDTO) {
        Shoplocation shoplocation = new Shoplocation();
        shoplocation.setMerchantid(shoplocationDTO.getMerchantid());
        shoplocation.setLocation(shoplocationDTO.getLocation());
        shoplocation.setNum(shoplocationDTO.getNum());
        shoplocation.setCreator(shoplocationDTO.getCreator());
        shoplocation.setCreatedate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        shoplocation.setLogicdelete(false);
        shoplocation.setOther(null);
        rewrite_ShoplocationRepository.save(shoplocation);
    }

    // 删除位置
    @Override
    public void deleteShoplocation(Long shoplocationId) {
        rewrite_ShoplocationRepository.deleteById(shoplocationId);
    }
}
