package com.weisen.www.code.yjf.merchant.service.dto.submit;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/12/17 16:45
 */
public class Rewrite_orderShop2DTO4 implements Serializable {

    private String mid;

    private String ioc;

    private String userid;

    private String sum;

    private List<Rewrite_orderShop2DTO3> chishi;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getIoc() {
        return ioc;
    }

    public void setIoc(String ioc) {
        this.ioc = ioc;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public List<Rewrite_orderShop2DTO3> getChishi() {
        return chishi;
    }

    public void setChishi(List<Rewrite_orderShop2DTO3> chishi) {
        this.chishi = chishi;
    }
}
