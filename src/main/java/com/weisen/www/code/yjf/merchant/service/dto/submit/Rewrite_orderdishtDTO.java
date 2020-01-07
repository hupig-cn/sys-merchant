package com.weisen.www.code.yjf.merchant.service.dto.submit;

import com.weisen.www.code.yjf.merchant.domain.Dishes;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/12/17 16:45
 */
public class Rewrite_orderdishtDTO implements Serializable {

    private String mName;//店铺名称

    private String iocid;//几号座

    private List<Rewrite_orderShop2DTO> list;

    private String zongsum;

    private String merchantphoto;


	public String getMerchantphoto() {
		return merchantphoto;
	}

	public void setMerchantphoto(String merchantphoto) {
		this.merchantphoto = merchantphoto;
	}

	public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getIocid() {
        return iocid;
    }

    public void setIocid(String iocid) {
        this.iocid = iocid;
    }

    public List<Rewrite_orderShop2DTO> getList() {
        return list;
    }

    public void setList(List<Rewrite_orderShop2DTO> list) {
        this.list = list;
    }

    public String getZongsum() {
        return zongsum;
    }

    public void setZongsum(String zongsum) {
        this.zongsum = zongsum;
    }
}
