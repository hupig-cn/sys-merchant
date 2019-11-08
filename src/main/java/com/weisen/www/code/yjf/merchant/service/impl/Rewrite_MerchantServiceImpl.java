package com.weisen.www.code.yjf.merchant.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.weisen.www.code.yjf.merchant.web.rest.SensitiveWord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.merchant.domain.Merchant;
import com.weisen.www.code.yjf.merchant.repository.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.merchant.service.Rewrite_MerchantService;
import com.weisen.www.code.yjf.merchant.service.dto.MerchantDTO;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_ForNearShop;
import com.weisen.www.code.yjf.merchant.service.dto.Rewrite_MerchantDTO;
import com.weisen.www.code.yjf.merchant.service.dto.show.Rewrite_AdminMerchantDTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_JudgeMerchantDTO;
import com.weisen.www.code.yjf.merchant.service.mapper.MerchantMapper;
import com.weisen.www.code.yjf.merchant.service.util.LocationUtils;
import com.weisen.www.code.yjf.merchant.service.util.NormalConstant;
import com.weisen.www.code.yjf.merchant.service.util.Result;
import com.weisen.www.code.yjf.merchant.service.util.TimeUtil;

@Service
@Transactional
public class Rewrite_MerchantServiceImpl implements Rewrite_MerchantService {

	private final Rewrite_MerchantRepository rewrite_MerchantRepository;

	private final MerchantMapper merchantMapper;

	public Rewrite_MerchantServiceImpl(Rewrite_MerchantRepository rewrite_MerchantRepository,
			MerchantMapper merchantMapper) {
		this.rewrite_MerchantRepository = rewrite_MerchantRepository;
		this.merchantMapper = merchantMapper;
	}

	// 添加商家店铺
	@Override
	public String createMerchant(MerchantDTO merchantDTO) {
		if (!SensitiveWord.check(merchantDTO.getName())) {
			Result.fail("商户名称含有敏感词，重新输入！！");
		}
		Merchant merchants = rewrite_MerchantRepository.findFirstByUserid(merchantDTO.getUserid());
		if (merchants != null)
			return "请勿多次提交申请。";
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
		merchant.setRebate(merchantDTO.getConcession() == 5 ? 15 : merchantDTO.getConcession() == 10 ? 30 : 50);
		merchant.setBuslicenseimage(merchantDTO.getBuslicenseimage());
		merchant.setCreditcode(merchantDTO.getCreditcode());
		merchant.setWeight("0");
		rewrite_MerchantRepository.save(merchant);
		merchant.setCreator(merchant.getId().toString());
		return merchant.getId().toString();
	}

	// 修改店铺信息
	@Override
	public Result updateMerchant(Rewrite_MerchantDTO merchantDTO) {
		if (!SensitiveWord.check(merchantDTO.getName())) {
			Result.fail("商户名称含有敏感词，重新输入！！");
		}
		Merchant merchant = rewrite_MerchantRepository.findByUserid(merchantDTO.getUserid());
		if (merchant == null) {
			return Result.fail("没有该用户!");
		} else {
			if (!SensitiveWord.check(merchantDTO.getName())) {
				Result.fail("商户名称含有敏感词，重新输入！！");
			}
			merchant.setUserid(merchantDTO.getUserid());
			merchant.setMerchantphoto(merchantDTO.getMerchantphoto());
			merchant.setName(merchantDTO.getName());
			merchant.setBusinessid(merchantDTO.getBusinessid());
			merchant.setAddress(merchantDTO.getAddress());
			merchant.setProvince(merchantDTO.getProvince());
			merchant.setCity(merchantDTO.getCity());
			merchant.setCounty(merchantDTO.getCounty());
			merchant.setLongitude(merchantDTO.getLongitude());
			merchant.setLatitude(merchantDTO.getLatitude());
			merchant.setModifierdate(TimeUtil.getDate());
			merchant.setCreditcode(merchantDTO.getCreditcode());
			merchant.setConcession(merchantDTO.getConcession());
			// 让利比例5%，10%，15%
			// 返积分比例15%，30%，50%
			if (merchantDTO.getConcession().equals(5)) {
				merchant.setRebate(15);
			}
			if (merchantDTO.getConcession().equals(10)) {
				merchant.setRebate(30);
			}
			if (merchantDTO.getConcession().equals(15)) {
				merchant.setRebate(50);
			}
			rewrite_MerchantRepository.saveAndFlush(merchant);
			return Result.suc("修改成功!");
		}
	}

	// 查询我的店铺
	@Override
	public MerchantDTO findMyShop(Long userid) {
		Merchant merchant = rewrite_MerchantRepository.findFirstByUserid(userid.toString());
		return merchantMapper.toDto(merchant);
	}

	// 查询店铺信息
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
		int fromIndex = rewrite_ForNearShop.getStartNum() * rewrite_ForNearShop.getPageSize(); // 起始索引
		List<Merchant> list = rewrite_MerchantRepository.findNearbyMerchantAndHot(rewrite_ForNearShop.getLongitude(),
				rewrite_ForNearShop.getLatitude(), NormalConstant.ONE, fromIndex, rewrite_ForNearShop.getPageSize());
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
		int fromIndex = rewrite_ForNearShop.getStartNum() * rewrite_ForNearShop.getPageSize(); // 起始索引
		List<Merchant> list = rewrite_MerchantRepository.findNearbyMerchant(rewrite_ForNearShop.getLongitude(),
				rewrite_ForNearShop.getLatitude(), rewrite_ForNearShop.getDistance(), fromIndex,
				rewrite_ForNearShop.getPageSize());
		return merchantMapper.toDto(list);
	}

	// 根据搜索内容查询商户
	@Override
	public Result findByNameLike(Rewrite_ForNearShop rewrite_ForNearShop) {
		int fromIndex = rewrite_ForNearShop.getStartNum() * rewrite_ForNearShop.getPageSize(); // 起始索引
		List<Merchant> list = rewrite_MerchantRepository.findByNameLike(rewrite_ForNearShop.getName(),
				rewrite_ForNearShop.getCity());
		Integer count = rewrite_MerchantRepository.findByNameLikeCount(rewrite_ForNearShop.getName(),
				rewrite_ForNearShop.getCity());
		List<MerchantDTO> merchantdto = new ArrayList<MerchantDTO>();
		list.forEach(x -> {
			MerchantDTO rewrite_AdminMerchantDTO = new MerchantDTO();
			double disance = LocationUtils.getDistance((rewrite_ForNearShop.getLongitude()).doubleValue(),
					(rewrite_ForNearShop.getLatitude()).doubleValue(), (x.getLongitude()).doubleValue(),
					(x.getLatitude().doubleValue()));
//			if(disance>500000) {
//				return;
//			}
			rewrite_AdminMerchantDTO.setId(x.getId());
			rewrite_AdminMerchantDTO.setUserid(x.getUserid());
			rewrite_AdminMerchantDTO.setMerchantphoto(x.getMerchantphoto());
			rewrite_AdminMerchantDTO.setName(x.getName());
			rewrite_AdminMerchantDTO.setBusinessid(x.getBusinessid());
			rewrite_AdminMerchantDTO.setState(x.getState());
			rewrite_AdminMerchantDTO.setAddress(x.getAddress());
			rewrite_AdminMerchantDTO.setProvince(x.getProvince());
			rewrite_AdminMerchantDTO.setCity(x.getCity());
			rewrite_AdminMerchantDTO.setCounty(x.getCounty());
			rewrite_AdminMerchantDTO.setLongitude(x.getLongitude());
			rewrite_AdminMerchantDTO.setLatitude(x.getLatitude());
			rewrite_AdminMerchantDTO.setConcession(x.getConcession());
			rewrite_AdminMerchantDTO.setRebate(x.getRebate());
			rewrite_AdminMerchantDTO.setBuslicenseimage(x.getBuslicenseimage());
			rewrite_AdminMerchantDTO.setShow(x.getShow());
			rewrite_AdminMerchantDTO.setCreditcode(x.getCreditcode());
			rewrite_AdminMerchantDTO.setWeight(x.getWeight());
			rewrite_AdminMerchantDTO.setCreator(x.getCreator());
			rewrite_AdminMerchantDTO.setCreatedate(x.getCreatedate());
			rewrite_AdminMerchantDTO.setModifier(x.getModifier());
			rewrite_AdminMerchantDTO.setModifierdate(x.getModifierdate());
			rewrite_AdminMerchantDTO.setModifiernum(x.getModifiernum());
			rewrite_AdminMerchantDTO.setModifiernum(x.getModifiernum());
			rewrite_AdminMerchantDTO.setLogicdelete(x.isLogicdelete());
			rewrite_AdminMerchantDTO.setOther(x.getOther());
			rewrite_AdminMerchantDTO.setDistance(disance);
			merchantdto.add(rewrite_AdminMerchantDTO);
		});
		if (rewrite_ForNearShop.getType() == 1) {
			ListSort(merchantdto);
		} else {
			merchantdto.sort(new Comparator<MerchantDTO>() {
				@Override
				public int compare(MerchantDTO o1, MerchantDTO o2) {
					return (int) (o1.getDistance() - o2.getDistance());
				}
			});// 按照距离排序
		}
		List dto = Page(rewrite_ForNearShop.getStartNum(), rewrite_ForNearShop.getPageSize(), merchantdto.size(), merchantdto);
		return Result.suc("成功", dto, merchantdto.size());
	}

	// 分页倒叙查询商家
	@Override
	public List<MerchantDTO> findAllMerchant(int satrtPage, int pageSize, BigDecimal longitude, BigDecimal latitude) {
		List<Merchant> list = rewrite_MerchantRepository.findAllMerchant(NormalConstant.PASS, satrtPage * pageSize,
				pageSize);
		if (list != null) {
			List<MerchantDTO> returnList = merchantMapper.toDto(list);
			if (longitude != null && latitude != null) {
				for (MerchantDTO merchantDTO : returnList) {
					merchantDTO.setDistance(LocationUtils.getDistance(longitude.doubleValue(), latitude.doubleValue(),
							Double.parseDouble(merchantDTO.getLongitude().toString()),
							Double.parseDouble(merchantDTO.getLatitude().toString())));
				}
			}
			return returnList;
		}
		return null;
	}

	// (后台)商户列表
	@Override
	public Result adminFindAllMerchant(String userid, String merchantName, String type, int satrtPage, int pageSize) {
		List<Merchant> list = rewrite_MerchantRepository.adminFindAllMerchant(userid, merchantName, type,
				satrtPage * pageSize, pageSize);

		List<Rewrite_AdminMerchantDTO> adminList = new ArrayList<>();
		list.forEach(x -> {
			Rewrite_AdminMerchantDTO rewrite_AdminMerchantDTO = new Rewrite_AdminMerchantDTO();
			rewrite_AdminMerchantDTO.setId(x.getId().toString());
			rewrite_AdminMerchantDTO.setUserid(x.getUserid());
			rewrite_AdminMerchantDTO.setName(x.getName());
			rewrite_AdminMerchantDTO.setShopLocation(x.getAddress());
			rewrite_AdminMerchantDTO.setShopType(x.getBusinessid());
			rewrite_AdminMerchantDTO.setCreateTime(x.getCreatedate());
			rewrite_AdminMerchantDTO.setState(x.getState());
			rewrite_AdminMerchantDTO.setConcession(x.getConcession().toString() + "%");
			adminList.add(rewrite_AdminMerchantDTO);
		});

		Long count = rewrite_MerchantRepository.countAdmin(userid, merchantName, type);

		return Result.suc("成功", adminList, count.intValue());
	}

	// 审批商户
	@Override
	public Result judgeMerchant(Rewrite_JudgeMerchantDTO rewrite_JudgeMerchantDTO) {
		Optional<Merchant> merchant = rewrite_MerchantRepository.findById(rewrite_JudgeMerchantDTO.getMerchantid());
		if (!merchant.isPresent()) {
			Result.fail("该商户不存在");
		} else {
			Merchant save = merchant.get();
			save.setState("已通过");
			save.setModifierdate(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
			rewrite_MerchantRepository.saveAndFlush(save);
		}

		return Result.suc("成功");
	}

	//查询商铺消息 和用户信息
	@Override
	public Result findMyShopAndUserdeail(Long userid) {
		Map<String,Object> merchant = rewrite_MerchantRepository.findFirstByUseridAndUserdeails(userid.toString());
		return Result.suc("成功",merchant);
	}
	
	//根据2个字段排序
	public static void ListSort(List<MerchantDTO> list) {
		Collections.sort(list, new Comparator<MerchantDTO>() {

			@Override
			public int compare(MerchantDTO o1, MerchantDTO o2) {
				try {
					if (o1.getRebate() > o2.getRebate()) {
						return -1;
					} else if (o1.getRebate() < o2.getRebate()) {
						return 1;
					} else {
						if (o1.getDistance() > o2.getDistance()) {
							return 1;
						} else if (o1.getDistance() < o2.getDistance()) {
							return -1;
						} else {
							return 0;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
		});
	}

	public static List Page(Integer pageNum, Integer pageSize, Integer sum, List T) {
		int pageNo = pageNum * pageSize;
		if (pageNo + pageSize > sum) {
			T = T.subList(pageNo, sum);
		} else {
			T = T.subList(pageNo, pageNo + pageSize);
		}
		return T;
	}



}
