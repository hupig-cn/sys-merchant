package com.weisen.www.code.yjf.merchant.service.impl;
import com.google.common.collect.Lists;

import com.weisen.www.code.yjf.merchant.domain.Dishes;
import com.weisen.www.code.yjf.merchant.domain.DishesShop;
import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import com.weisen.www.code.yjf.merchant.domain.Merchant;
import com.weisen.www.code.yjf.merchant.repository.*;
import com.weisen.www.code.yjf.merchant.service.Rewrite_OrderingMealsService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesAndTypeDTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_orderShop2DTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_orderdishtDTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_ordertianjiaDTO;
import com.weisen.www.code.yjf.merchant.service.dto.submit.Rewrite_typeDTO;
import com.weisen.www.code.yjf.merchant.service.util.Result;
import com.weisen.www.code.yjf.merchant.service.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class Rewrite_OrderingMealsServiceImpl implements Rewrite_OrderingMealsService {

    private final Rewrite_MerchantRepository merchantRepository;

    private final Rewrite_DishestypeRepository rewrite_dishestypeRepository;

    private final Rewrite_DishesRepository dishesRepository;

    private final Rewrite_DishesorderRepository rewrite_dishesorderRepository;

    private final DishesShopRepository dishesShopRepository;

    public Rewrite_OrderingMealsServiceImpl(Rewrite_MerchantRepository merchantRepository, Rewrite_DishestypeRepository rewrite_dishestypeRepository, Rewrite_DishesRepository dishesRepository, Rewrite_DishesorderRepository rewrite_dishesorderRepository, DishesShopRepository dishesShopRepository) {
        this.merchantRepository = merchantRepository;
        this.rewrite_dishestypeRepository = rewrite_dishestypeRepository;
        this.dishesRepository = dishesRepository;
        this.rewrite_dishesorderRepository = rewrite_dishesorderRepository;
        this.dishesShopRepository = dishesShopRepository;
    }


    //根据用户id查找店铺图片和商家名称
    @Override
    public Result getMerchantNameAndData(String id) {
        Merchant merchant = new Merchant();
        Merchant merchantData = merchantRepository.findMerchantById(Long.parseLong(id));
        if (merchantData != null) {
            merchant.setId(merchantData.getId());
            merchant.setName(merchantData.getName());
            merchant.setMerchantphoto("http://app.yuanscore.com:8083/services/basic/api/public/getFiles/" + merchantData.getMerchantphoto());
        } else {
            return Result.suc("暂无此店");
        }

        return Result.suc("访问成功", merchant);
    }

    @Override
    public Result merchantDishestype(String id, String iocid) {
        List<Dishestype> allByMerchantid = rewrite_dishestypeRepository.findAllByMerchantid(id);
        List<DishesShop> cc = dishesShopRepository.findDishesShopByMerchatidAndIoc(Integer.valueOf(id), iocid);

        List<Rewrite_typeDTO> list = new ArrayList<>();
        for (int i = 0; i < allByMerchantid.size(); i++) {
            Dishestype dishestype = allByMerchantid.get(i);
            String name = dishestype.getName();
            String state = dishestype.getState();
            if (state.equals("0")) {
                continue;
            }
            List<Dishes> as = dishesRepository.findByMerchantidAndDishestypeid(id, dishestype.getId() + "");
            Rewrite_typeDTO rewrite_typeDTO = new Rewrite_typeDTO();
            rewrite_typeDTO.setName(name);
            List<Rewrite_ordertianjiaDTO> typelist = new ArrayList<>();

            for (int j = 0; j < as.size(); j++) {
                Dishes dishes = as.get(j);
                Rewrite_ordertianjiaDTO rewrite_ordertianjiaDTO = new Rewrite_ordertianjiaDTO();
                rewrite_ordertianjiaDTO.setCaiid(dishes.getId());
                rewrite_ordertianjiaDTO.setCainame(dishes.getName());
                rewrite_ordertianjiaDTO.setCaiprice(dishes.getPrice());
                rewrite_ordertianjiaDTO.setCainum("0");
                typelist.add(rewrite_ordertianjiaDTO);
            }

            if (cc == null || cc.size() == 0) {

            } else {
                for (int j = 0; j < cc.size(); j++) {
                    DishesShop dishesShop = cc.get(i);
                    String name1 = dishesShop.getName();
                    for (int k = 0; k < typelist.size(); k++) {
                        Rewrite_ordertianjiaDTO rewrite_ordertianjiaDTO = typelist.get(k);
                        String oname = rewrite_ordertianjiaDTO.getCainame();
                        if (oname.equals(name1)) {
                            rewrite_ordertianjiaDTO.setCainum(dishesShop.getNum()+ "");
                        }
                    }

                }
            }
            list.add(rewrite_typeDTO);

        }
        return Result.suc("查询成功", list);
    }

    //根据商家id查找店铺菜单类型
    @Override
    public Result getMerchantType(String id) {
        List<Dishestype> dishestypeList = rewrite_dishestypeRepository.findAllByMerchantid(id);
        if (!dishestypeList.isEmpty()) {
            ArrayList<DishesAndTypeDTO> dishesAndTypeDTOList = new ArrayList<>();
            for (Dishestype dishestype : dishestypeList) {
                if (dishestype.getState().equals("1") || dishestype.getState().equals("1")) {
                    Long typeId = dishestype.getId();
                    List<Dishes> dishesList = dishesRepository.findByMerchantidAndDishestypeid(id, "" + typeId);
                    if (!dishesList.isEmpty()) {
                        for (Dishes Dishes : dishesList) {
                            DishesAndTypeDTO dishesAndTypeDTO = new DishesAndTypeDTO();
                            String name = Dishes.getName();
                            String image = Dishes.getImage();
                            String price = Dishes.getPrice();
                            String typeName = dishestype.getName();

                            dishesAndTypeDTO.setDishestypeid("" + typeId);
                            dishesAndTypeDTO.setDishName(name);
                            dishesAndTypeDTO.setImage("http://app.yuanscore.com:8083/services/basic/api/public/getFiles/" + image);
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
        DishesShop d = dishesShopRepository.findDishesShopByMerchatidAndIocAndName(Integer.valueOf(merchatid), iocId, name);
        if (d == null) {
            //创建新的代购项目
            DishesShop dishesShop = new DishesShop();
            dishesShop.setMerchatid(Integer.valueOf(merchatid));
            dishesShop.setName(name);
            dishesShop.setIoc(iocId);
            //根据name 在店铺内寻找xxx
            Dishes ds = dishesRepository.findDishesByMerchantidAndNameAndState(merchatid, name, "1");
            if (ds == null){
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
        List<DishesShop> dishesShopByMerchatidAndIoc = dishesShopRepository.findDishesShopByMerchatidAndIoc(Integer.valueOf(merchatid), iocId);
        if (dishesShopByMerchatidAndIoc.size() == 0){
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
    public Result merchantOrders(String iocId, String merchatid,String other) {
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
            ros.setNum(num+"");
            ros.setPrice(price);
            ros.setSum(num*Double.valueOf(price)+"");
            c.add(ros);	
        }
        ro.setList(c);
        String name = merchantData.getName();
        ro.setmName(name);
        ro.setIocid(iocId);
        ro.setOrther(other);

        Result result = inAllOrders(iocId, merchatid);
        String data = result.getMessage();
        ro.setZongsum(data);
        return Result.suc("查询成功",ro);
    }

    /**
     * 总价
     */
    @Override
    public Result inAllOrders(String iocId, String merchatid) {
        List<DishesShop> ds = dishesShopRepository.findDishesShopByMerchatidAndIoc(Integer.valueOf(merchatid), iocId);
        if (!ds.isEmpty()) {
        BigDecimal sum = new BigDecimal(0).setScale(2, BigDecimal.ROUND_DOWN);
        for (int i = 0; i < ds.size(); i++) {
            DishesShop dishesShop = ds.get(i);
            Integer num = dishesShop.getNum();
            String price = dishesShop.getPrice();
            BigDecimal numB = new BigDecimal(num);
            BigDecimal priceB = new BigDecimal(price).setScale(2, BigDecimal.ROUND_DOWN);
            sum = sum.add(numB.multiply(priceB));
            
        }
        return Result.suc(sum+"");
        }
		return Result.suc("0");
    }


}
