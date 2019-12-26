package com.weisen.www.code.yjf.merchant.service.dto.submit;

import java.io.Serializable;

/**
 * @Author: 阮铭辉
 * @Date: 2019/12/17 16:45
 */
public class Rewrite_orderShop2DTO3 implements Serializable {

   private Integer caiid;

   private String cainame;

   private String caiprice;

   private Integer cainum;

   private String url;


    public String getCainame() {
        return cainame;
    }

    public void setCainame(String cainame) {
        this.cainame = cainame;
    }

    public String getCaiprice() {
        return caiprice;
    }

    public void setCaiprice(String caiprice) {
        this.caiprice = caiprice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCaiid() {
        return caiid;
    }

    public void setCaiid(Integer caiid) {
        this.caiid = caiid;
    }

    public Integer getCainum() {
        return cainum;
    }

    public void setCainum(Integer cainum) {
        this.cainum = cainum;
    }
}
