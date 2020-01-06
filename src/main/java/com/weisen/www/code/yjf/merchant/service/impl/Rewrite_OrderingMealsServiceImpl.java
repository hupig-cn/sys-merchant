package com.weisen.www.code.yjf.merchant.service.impl;

import com.weisen.www.code.yjf.merchant.domain.*;
import com.weisen.www.code.yjf.merchant.repository.*;
import com.weisen.www.code.yjf.merchant.service.Rewrite_OrderingMealsService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesAndTypeDTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.*;
import com.weisen.www.code.yjf.merchant.service.util.Result;
import com.weisen.www.code.yjf.merchant.service.util.TimeUtil;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Rewrite_OrderingMealsServiceImpl implements Rewrite_OrderingMealsService {

	private final Rewrite_MerchantRepository merchantRepository;

	private final Rewrite_DishestypeRepository rewrite_dishestypeRepository;

	private final UserorderRepository userorderRepository;

	private final Rewrite_DishesRepository dishesRepository;

	private final Rewrite_DishesorderRepository rewrite_dishesorderRepository;

	private final DishesShopRepository dishesShopRepository;

	public Rewrite_OrderingMealsServiceImpl(Rewrite_MerchantRepository merchantRepository,
			UserorderRepository userorderRepository,
			Rewrite_DishestypeRepository rewrite_dishestypeRepository, Rewrite_DishesRepository dishesRepository,
			Rewrite_DishesorderRepository rewrite_dishesorderRepository, DishesShopRepository dishesShopRepository) {
		this.merchantRepository = merchantRepository;
		this.rewrite_dishestypeRepository = rewrite_dishestypeRepository;
		this.userorderRepository = userorderRepository;
		this.dishesRepository = dishesRepository;
		this.rewrite_dishesorderRepository = rewrite_dishesorderRepository;
		this.dishesShopRepository = dishesShopRepository;
	}

	// 根据用户id查找店铺图片和商家名称
	@Override
	public Result getMerchantNameAndData(String id) {
		Merchant merchant = new Merchant();
		Merchant merchantData = merchantRepository.findMerchantById(Long.parseLong(id));
		if (merchantData != null) {
			merchant.setId(merchantData.getId());
			merchant.setName(merchantData.getName());
			merchant.setMerchantphoto("http://app.yuanscore.com:8083/services/basic/api/public/getFiles/"
					+ merchantData.getMerchantphoto());
		} else {
			return Result.suc("暂无此店");
		}

		return Result.suc("访问成功", merchant);
	}

	@Override
	public Result merchantDishestype(String id, String iocid) {
		List<Dishestype> allByMerchantid = rewrite_dishestypeRepository.findAllByMerchantid(id);
		List<DishesShop> cc = dishesShopRepository.findDishesShopByMerchatidAndIoc(Integer.valueOf(id), iocid);
		Integer num = 0;
		Integer caishu = 0;
		List<Rewrite_typeDTO> list = new ArrayList<>();
		for (int i = 0; i < allByMerchantid.size(); i++) {
			Dishestype dishestype = allByMerchantid.get(i);
			Long distyid = dishestype.getId();
			String name = dishestype.getName();
			String state = dishestype.getState();
			if (state.equals("0")) {
				continue;
			}
			List<Dishes> as = dishesRepository.findByMerchantidAndDishestypeid2(id, dishestype.getId() + "");
			Rewrite_typeDTO rewrite_typeDTO = new Rewrite_typeDTO();
			rewrite_typeDTO.setName(name);
			List<Rewrite_ordertianjiaDTO> typelist = new ArrayList<>();
			caishu += as.size();

			for (int j = 0; j < as.size(); j++) {
				Dishes dishes = as.get(j);
				Rewrite_ordertianjiaDTO rewrite_ordertianjiaDTO = new Rewrite_ordertianjiaDTO();
				rewrite_ordertianjiaDTO.setCaiid(dishes.getId());
				rewrite_ordertianjiaDTO.setCainame(dishes.getName());
				rewrite_ordertianjiaDTO.setCaiprice(dishes.getPrice());
				rewrite_ordertianjiaDTO.setCainum("0");
				rewrite_ordertianjiaDTO.setUrl(
						"http://app.yuanscore.com:8083/services/basic/api/public/getFiles/" + dishes.getImage());
				typelist.add(rewrite_ordertianjiaDTO);
			}

			if (cc == null || cc.size() == 0) {

			} else {
				for (int j = 0; j < cc.size(); j++) {
					DishesShop dishesShop = cc.get(j);
					String name1 = dishesShop.getName();
					for (int k = 0; k < typelist.size(); k++) {
						Rewrite_ordertianjiaDTO rewrite_ordertianjiaDTO = typelist.get(k);
						String oname = rewrite_ordertianjiaDTO.getCainame();
						if (oname.equals(name1)) {
							Integer num1 = dishesShop.getNum();
							rewrite_ordertianjiaDTO.setCainum(num1 + "");
							num += num1;
						}
					}
				}
			}
			rewrite_typeDTO.setId(distyid + "");
			rewrite_typeDTO.setList(typelist);
			list.add(rewrite_typeDTO);

		}
		return Result.suc("查询成功", list, caishu);
	}

	// 根据商家id查找店铺菜单类型('该接口没有被用到')
	@Override
	public Result getMerchantType(String id) {
		List<Dishestype> dishestypeList = rewrite_dishestypeRepository.findAllByMerchantid(id);
		if (!dishestypeList.isEmpty()) {
			ArrayList<DishesAndTypeDTO> dishesAndTypeDTOList = new ArrayList<>();
			for (Dishestype dishestype : dishestypeList) {
				if (dishestype.getState().equals("1") || dishestype.getState() == "1") {
					Long typeId = dishestype.getId();
					List<Dishes> dishesList = dishesRepository.findByMerchantidAndDishestypeid2(id, "" + typeId);
					if (!dishesList.isEmpty()) {
						for (Dishes Dishes : dishesList) {
							DishesAndTypeDTO dishesAndTypeDTO = new DishesAndTypeDTO();
							String name = Dishes.getName();
							String image = Dishes.getImage();
							String price = Dishes.getPrice();
							String typeName = dishestype.getName();

							dishesAndTypeDTO.setDishestypeid("" + typeId);
							dishesAndTypeDTO.setDishName(name);
							dishesAndTypeDTO.setImage(
									"http://app.yuanscore.com:8083/services/basic/api/public/getFiles/" + image);
							dishesAndTypeDTO.setPrice(price);
							dishesAndTypeDTO.setTypeName(typeName);
							dishesAndTypeDTOList.add(dishesAndTypeDTO);

						}

					}
				}
			}
			return Result.suc("访问成功!", dishesAndTypeDTOList);
		} else {
			return Result.suc("暂无其他菜品!");
		}
	}

	/**
	 * 点菜
	 */
	@Override
	public Result takingOrders(String iocId, Integer num, String merchatid, String name) {
		DishesShop d = dishesShopRepository.findDishesShopByMerchatidAndIocAndName(Integer.valueOf(merchatid), iocId,
				name);
		if (d == null) {
			// 创建新的代购项目
			DishesShop dishesShop = new DishesShop();
			dishesShop.setMerchatid(Integer.valueOf(merchatid));
			dishesShop.setName(name);
			dishesShop.setIoc(iocId);
			// 根据name 在店铺内寻找xxx
			Dishes ds = dishesRepository.findDishesByMerchantidAndNameAndState(merchatid, name, "1");
			if (ds == null) {
				return Result.fail("暂无这个菜品，请重新点菜");
			}
			String image = ds.getImage();
			dishesShop.setUrl("http://app.yuanscore.com:8083/services/basic/api/public/getFiles/" + image);
			dishesShop.setFilesid(Long.valueOf(image));
			dishesShop.setNum(num);
			dishesShop.setPrice(ds.getPrice());
			dishesShop.setCreateDate(TimeUtil.getDate());
			dishesShopRepository.save(dishesShop);
			return Result.suc("", dishesShop.getNum());
		} else if (num == 0) {
			dishesShopRepository.delete(d);
			return Result.suc("", 0);
		} else {
			Long id = d.getId();
			d.setId(id);
			d.setNum(num);
			dishesShopRepository.save(d);
			return Result.suc("", d.getNum());
		}

	}

	/**
	 * 重置订单
	 */
	@Override
	public Result takingOrdersNum(String iocId, String merchatid) {
		List<DishesShop> dishesShopByMerchatidAndIoc = dishesShopRepository
				.findDishesShopByMerchatidAndIoc(Integer.valueOf(merchatid), iocId);
		if (dishesShopByMerchatidAndIoc.size() == 0) {
			return Result.suc("重置成功");
		}
		List<Long> numList = new ArrayList<>();
		for (int i = 0; i < dishesShopByMerchatidAndIoc.size(); i++) {
			DishesShop dishesShop = dishesShopByMerchatidAndIoc.get(i);
			Long id = dishesShop.getId();
			numList.add(id);
		}
		for (int i = 0; i < numList.size(); i++) {
			Long a = numList.get(i);
			dishesShopRepository.deleteById(a);
		}
		return Result.suc("重置成功");
	}

	/**
	 * 详情
	 */
	@Override
	public Result merchantOrders(String iocId, String merchatid, String other) {
		List<DishesShop> ds = dishesShopRepository.findDishesShopByMerchatidAndIoc(Integer.valueOf(merchatid), iocId);
		Merchant merchantData = merchantRepository.findMerchantById(Long.parseLong(merchatid));
		Rewrite_orderdishtDTO ro = new Rewrite_orderdishtDTO();
		List<Rewrite_orderShop2DTO> c = new ArrayList<>();
		for (int i = 0; i < ds.size(); i++) {
			DishesShop dishesShop = ds.get(i);
			Rewrite_orderShop2DTO ros = new Rewrite_orderShop2DTO();
			String name = dishesShop.getName();
			Integer num = dishesShop.getNum();
			String price = dishesShop.getPrice();
			ros.setName(name);
			ros.setNum(num + "");
			ros.setPrice(price);
			ros.setSum(num * Double.valueOf(price) + "");
			c.add(ros);
		}
		ro.setList(c);
		String name = merchantData.getName();
		ro.setmName(name);
		ro.setIocid(iocId);

		Result result = inAllOrders(iocId, merchatid);
		String data = result.getMessage();
		ro.setZongsum(data);
		return Result.suc("查询成功", ro);
	}

	/**
	 * 总价
	 */
	@Override
	public Result inAllOrders(String iocId, String merchatid) {
		List<DishesShop> ds = dishesShopRepository.findDishesShopByMerchatidAndIoc(Integer.valueOf(merchatid), iocId);
		Integer cainum = 0;
		if (!ds.isEmpty()) {
			BigDecimal sum = new BigDecimal(0).setScale(2, BigDecimal.ROUND_DOWN);
			for (int i = 0; i < ds.size(); i++) {
				DishesShop dishesShop = ds.get(i);
				Integer num = dishesShop.getNum();
				cainum += num;
				String price = dishesShop.getPrice();
				BigDecimal numB = new BigDecimal(num);
				BigDecimal priceB = new BigDecimal(price).setScale(2, BigDecimal.ROUND_DOWN);
				sum = sum.add(numB.multiply(priceB));

			}
			return Result.suc(sum + "", "", cainum);
		}
		return Result.suc("0", "", cainum);
	}

	// 点菜
	@Override
	public Result takingOrders2(Rewrite_orderShop2DTO4 list) {
		// 点菜 商家id 座位id 用户 点的菜品
		String ioc = list.getIoc();
		List<Rewrite_orderShop2DTO3> chishi = list.getChishi();
		String mid = list.getMid();
//		String date = TimeUtil.getDate();
		String ac = RandomStringUtils.randomAlphanumeric(32);
//		String ac = date + "::" + mid + "::" + ioc;
		for (int i = 0; i < chishi.size(); i++) {
			Rewrite_orderShop2DTO3 rewrite_orderShop2DTO3 = chishi.get(i);
			rewrite_orderShop2DTO3.getCaiid();
			String cainame = rewrite_orderShop2DTO3.getCainame();
			String caiprice = rewrite_orderShop2DTO3.getCaiprice();
			Integer cainum = rewrite_orderShop2DTO3.getCainum();
			String url = rewrite_orderShop2DTO3.getUrl();
			Dishesorder dishesorder = new Dishesorder();

			dishesorder.setBigorder(ac);
			dishesorder.setMerchantid(mid);
			dishesorder.setLocation(ioc);
			dishesorder.setName(cainame);
			dishesorder.setState("1");// 1-未付款 2-已付款  3-已上菜
			dishesorder.setCreatedate(TimeUtil.getDate());// 现在创建的
			dishesorder.setNum(Integer.valueOf(cainum));
			dishesorder.setPrice(caiprice);
			dishesorder.setNumprice(cainum * Double.parseDouble(caiprice) + "");
			dishesorder.setOther(url);
			rewrite_dishesorderRepository.save(dishesorder);
		}
		return Result.suc("下单成功", ac);
	}

	// 付钱成功
	@Override
	public Result createCaiOrder(String userid, String orderid) {
		List<Dishesorder> ds2 = rewrite_dishesorderRepository.findDishesorderByBigorder(orderid);
		for (int i = 0; i < ds2.size(); i++) {
			Dishesorder ds = ds2.get(i);
			ds.setId(ds.getId());
			ds.setState("2");  // 1-未付款 2-已付款  3-已上菜
			ds.setCreator(userid);
			rewrite_dishesorderRepository.save(ds);
		}

		return Result.suc("");
	}

	// 详情
	@Override
	public Result caiorder(String orderid) {

		List<Dishesorder> db = rewrite_dishesorderRepository.findDishesorderByBigorder(orderid);
		Rewrite_orderdishtDTO ro = new Rewrite_orderdishtDTO();
		List<Rewrite_orderShop2DTO> list = new ArrayList<>();
		String merxxid = "";
		String iocid = "";
		Double sum = 0.00;
		for (int i = 0; i < db.size(); i++) {
			Dishesorder dishesorder = db.get(i);
			if (i == 0) {
				String merchantid = dishesorder.getMerchantid();
				Merchant merchantById = merchantRepository.findMerchantById(Long.valueOf(merchantid));
				merxxid = merchantById.getName();
				iocid = dishesorder.getLocation();
			}
			Rewrite_orderShop2DTO r2 = new Rewrite_orderShop2DTO();
			String name = dishesorder.getName();
			Integer num = dishesorder.getNum();
			String numprice = dishesorder.getNumprice();
			String price = dishesorder.getPrice();
			String other = dishesorder.getOther();
			sum += Double.valueOf(numprice);
			r2.setName(name);
			r2.setNum(num + "");
			r2.setPrice(price);
			r2.setSum(numprice);
			r2.setUrl(other);
			list.add(r2);
		}
		ro.setmName(merxxid);
		ro.setIocid(iocid);
		ro.setList(list);
		ro.setZongsum(sum + "");
		return Result.suc("查询成功", ro);
	}
	
	// 完成支付后更改订单状态
	@Override
	public Result changeOrderState(String orderid) {
		// 查找大订单
		Userorder ordercodeStatus = userorderRepository.findUserorderByOrdercode(orderid);
		// 如果有订单
		if (ordercodeStatus!=null) {
			// 状态为已付款
			if (ordercodeStatus.getOrderstatus() == "2" || ordercodeStatus.getOrderstatus().equals("2")) {
				// 如果小订单不为空,将小订单状态改为已支付
				List<Dishesorder> dishesOrderList = rewrite_dishesorderRepository.findDishesorderByBigorder(orderid);
				if (!dishesOrderList.isEmpty()) {
					for (Dishesorder dishesorder : dishesOrderList) {
						dishesorder.setId(dishesorder.getId());
						dishesorder.setState("2");    // 1-未付款 2-已付款  3-已上菜
						dishesorder.setModifierdate(TimeUtil.getDate());// 修改时间
						rewrite_dishesorderRepository.save(dishesorder);
					}
					
				}else {
					return Result.fail("没有点餐！");
				}
			} else {
				return Result.fail("订单未支付！");
			}
			
		}else {
			return Result.fail("没有生成订单！");
		}
		return Result.suc("订单已支付！");
	}

}
