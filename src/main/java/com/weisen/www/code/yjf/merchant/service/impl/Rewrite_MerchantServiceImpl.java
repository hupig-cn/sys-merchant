package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.domain.Merchant;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_MerchantService;
import com.weisen.www.code.yjf.merchant.service.dto.MerchantDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_ForNearShop;
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
    public String createMerchant(MerchantDTO merchantDTO) {
    	Merchant merchants = rewrite_MerchantRepository.findFirstByUserid(merchantDTO.getUserid());
    	if (merchants != null) return "请勿多次提交申请。";
        Merchant merchant = new Merchant();
        merchant.setUserid(merchantDTO.getUserid());
        merchant.setMerchantphoto(merchantDTO.getMerchantphoto());
        merchant.setName(merchantDTO.getName());
        merchant.setBusinessid(merchantDTO.getBusinessid());
        merchant.setState("待审核");
        merchant.setShow(merchantDTO.getShow());
        merchant.setAddress(merchantDTO.getAddress());
        merchant.setProvince(merchantDTO.getProvince());
        merchant.setCity(merchantDTO.getCity());
        merchant.setCounty(merchantDTO.getCounty());
        merchant.setLongitude(merchantDTO.getLongitude());
        merchant.setLatitude(merchantDTO.getLatitude());
        merchant.setCreatedate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        merchant.setConcession(merchantDTO.getConcession());
        merchant.setRebate(merchantDTO.getConcession()==5?15:merchantDTO.getConcession()==10?30:50);
        merchant.setBuslicenseimage(merchantDTO.getBuslicenseimage());
        merchant.setCreditcode(merchantDTO.getCreditcode());
        merchant.setWeight("0");
        rewrite_MerchantRepository.save(merchant);
        merchant.setCreator(merchant.getId().toString());
        return merchant.getId().toString();
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
        merchant.setShow(merchantDTO.getShow());
        merchant.setCounty(merchantDTO.getCounty());
        merchant.setLongitude(merchantDTO.getLongitude());
        merchant.setLatitude(merchantDTO.getLatitude());
        merchant.setConcession(merchantDTO.getConcession());
        merchant.setRebate(merchantDTO.getRebate());
        merchant.setWeight(merchantDTO.getWeight());
        merchant.setModifierdate(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
        merchant.setModifier(merchantDTO.getId().toString());
        merchant.setBuslicenseimage(merchantDTO.getBuslicenseimage());
        merchant.setCreditcode(merchantDTO.getCreditcode());
        merchant.setModifiernum(merchantDTO.getModifiernum());
        merchant.setLogicdelete(merchantDTO.isLogicdelete());
        merchant.setOther(merchantDTO.getOther());
        rewrite_MerchantRepository.saveAndFlush(merchant);
    }

    //查询我的店铺
    @Override
    public MerchantDTO findMyShop(Long userid) {
        Merchant merchant = rewrite_MerchantRepository.findFirstByUserid(userid.toString());
        return merchantMapper.toDto(merchant);
    }
    
    //查询店铺信息
    @Override
    public MerchantDTO findShopInfo(Long userid) {
        Merchant merchant = rewrite_MerchantRepository.findByUserid(userid.toString());
        if (null == merchant) {
            return null;
        }
        return merchantMapper.toDto(merchant);
    }

    /**
     * 查询附近热门店铺
     *
     * @param //longitude // 经度
     * @param //latitude  // 纬度
     * @return
     */
    @Override
    public List<MerchantDTO> findPopularMerchant(Rewrite_ForNearShop rewrite_ForNearShop) {
//        if(rewrite_ForNearShop.getStartNum()){
//
//        }
        int fromIndex = rewrite_ForNearShop.getStartNum() * rewrite_ForNearShop.getPageSize();  //起始索引
        List<Merchant> list = rewrite_MerchantRepository.findNearbyMerchantAndHot(rewrite_ForNearShop.getLongitude(),
            rewrite_ForNearShop.getLatitude(), NormalConstant.ONE,fromIndex,rewrite_ForNearShop.getPageSize());
        return merchantMapper.toDto(list);
    }

    /**
     * 距离最近的店铺
     *
     * @param //longitude // 经度
     * @param //latitude  // 纬度
     * @return
     */
    @Override
    public List<MerchantDTO> findNearMerchant(Rewrite_ForNearShop rewrite_ForNearShop) {
        int fromIndex = rewrite_ForNearShop.getStartNum() * rewrite_ForNearShop.getPageSize(); //起始索引
        List<Merchant> list = rewrite_MerchantRepository.findNearbyMerchant(rewrite_ForNearShop.getLongitude(),
            rewrite_ForNearShop.getLatitude(),rewrite_ForNearShop.getDistance(),fromIndex,rewrite_ForNearShop.getPageSize());
        return merchantMapper.toDto(list);
    }

    //根据搜索内容查询商户
    @Override
    public List<MerchantDTO> findByNameLike(Rewrite_ForNearShop rewrite_ForNearShop) {
        int fromIndex = rewrite_ForNearShop.getStartNum() * rewrite_ForNearShop.getPageSize();  //起始索引

        List<Merchant> list = rewrite_MerchantRepository.findByNameLike(rewrite_ForNearShop.getName(),
            fromIndex,rewrite_ForNearShop.getPageSize());

        return merchantMapper.toDto(list);
    }

    // 分页倒叙查询商家
    @Override
    public List<MerchantDTO> findAllMerchant(int satrtPage, int pageSize) {
        List<Merchant> list = rewrite_MerchantRepository.findAllMerchant(NormalConstant.PASS,satrtPage * pageSize, pageSize);
        if(list != null){
            return merchantMapper.toDto(list);
        }
        return null;
    }
}
