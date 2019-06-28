package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.domain.Merchant;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_MerchantService;
import com.weisen.www.code.yjf.merchant.service.dto.MerchantDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.MerchantMapper;
import com.weisen.www.code.yjf.merchant.service.util.NormalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Rewrite_MerchantServiceImpl implements Rewrite_MerchantService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_MerchantServiceImpl.class);

    private final Rewrite_MerchantRepository rewrite_MerchantRepository;

    private final MerchantMapper merchantMapper;

    public Rewrite_MerchantServiceImpl(Rewrite_MerchantRepository rewrite_MerchantRepository, MerchantMapper merchantMapper) {
        this.rewrite_MerchantRepository = rewrite_MerchantRepository;
        this.merchantMapper = merchantMapper;
    }

    //添加商家店铺
    @Override
    public void createMerchant(MerchantDTO merchantDTO) {
        Merchant merchant = new Merchant();
        merchant.setUserid(merchantDTO.getUserid());
        merchant.setMerchantphoto(merchantDTO.getMerchantphoto());
        merchant.setName(merchantDTO.getName());
        merchant.setBusinessid(merchantDTO.getBusinessid());
        merchant.setState(merchantDTO.getState());
        merchant.setAddress(merchantDTO.getAddress());
        merchant.setProvince(merchantDTO.getProvince());
        merchant.setCity(merchantDTO.getCity());
        merchant.setCounty(merchantDTO.getCounty());
        merchant.setLongitude(merchantDTO.getLongitude());
        merchant.setLatitude(merchantDTO.getLatitude());
        merchant.setConcession(merchantDTO.getConcession());
        merchant.setRebate(merchantDTO.getRebate());
        merchant.setWeight(merchantDTO.getWeight());
        merchant.setCreatedate(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
        merchant.setCreator(merchantDTO.getCreator());
        merchant.setLogicdelete(merchantDTO.isLogicdelete());
        merchant.setOther(merchantDTO.getOther());
        rewrite_MerchantRepository.save(merchant);
    }

    //修改店铺信息
    @Override
    public void updateMerchant(MerchantDTO merchantDTO) {
        Merchant merchant = new Merchant();
        merchant.setId(merchantDTO.getId());
        merchant.setUserid(merchantDTO.getUserid());
        merchant.setMerchantphoto(merchantDTO.getMerchantphoto());
        merchant.setName(merchantDTO.getName());
        merchant.setBusinessid(merchantDTO.getBusinessid());
        merchant.setState(merchantDTO.getState());
        merchant.setAddress(merchantDTO.getAddress());
        merchant.setProvince(merchantDTO.getProvince());
        merchant.setCity(merchantDTO.getCity());
        merchant.setCounty(merchantDTO.getCounty());
        merchant.setLongitude(merchantDTO.getLongitude());
        merchant.setLatitude(merchantDTO.getLatitude());
        merchant.setConcession(merchantDTO.getConcession());
        merchant.setRebate(merchantDTO.getRebate());
        merchant.setWeight(merchantDTO.getWeight());
        merchant.setModifierdate(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
        merchant.setModifier(merchantDTO.getModifier());
        merchant.setModifiernum(merchantDTO.getModifiernum());
        merchant.setLogicdelete(merchantDTO.isLogicdelete());
        merchant.setOther(merchantDTO.getOther());
        rewrite_MerchantRepository.saveAndFlush(merchant);
    }

    //查询我的店铺列表
    @Override
    public List<MerchantDTO> findAllMyShop(Long userId) {
        List<Merchant> list = rewrite_MerchantRepository.findAllByUserid(userId.toString());
        return merchantMapper.toDto(list);
    }

    //查询店铺信息
    @Override
    public MerchantDTO findShopInfo(Long merchantId) {
        Optional<Merchant> optional = rewrite_MerchantRepository.findById(merchantId);
        if(!optional.isPresent()){
            return null;
        }

        Merchant merchant = optional.get();

        return merchantMapper.toDto(merchant);
    }

    /**
     * 查询附近热门店铺
     * @param longitude  // 经度
     * @param latitude   // 纬度
     * @return
     */
    @Override
    public List<MerchantDTO> findPopularMerchant(BigDecimal longitude, BigDecimal latitude) {
        List<Merchant> list = rewrite_MerchantRepository.findNearbyMerchantAndHot(BigDecimal longitude, BigDecimal latitude, NormalConstant.ONE);
        return merchantMapper.toDto(list);
        return null;
    }

    /**
     * 距离最近的店铺
     * @param longitude  // 经度
     * @param latitude   // 纬度
     * @return
     */
    @Override
    public List<MerchantDTO> findNearMerchant(String longitude, String latitude) {
        List<Merchant> list = rewrite_MerchantRepository.findNearbyMerchant(BigDecimal longitude, BigDecimal latitude);
        return merchantMapper.toDto(list);
    }
}
