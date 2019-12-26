package com.weisen.www.code.yjf.merchant.service.dto.submit;

import java.io.Serializable;

/**
 * @Author: 阮铭辉
 * @Date: 2019/12/17 16:45
 */
public class Rewrite_caidataDTO implements Serializable {

    private String caiid;

    private String cainame;

    private String cainum;

    private String caiprice;

    private String url;

    public String getCaiid() {
        return caiid;
    }

    public void setCaiid(String caiid) {
        this.caiid = caiid;
    }

    public String getCainame() {
        return cainame;
    }

    public void setCainame(String cainame) {
        this.cainame = cainame;
    }

    public String getCainum() {
        return cainum;
    }

    public void setCainum(String cainum) {
        this.cainum = cainum;
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

    @Override
    public String toString() {
        return "Rewrite_caidataDTO{" +
            "caiid='" + caiid + '\'' +
            ", cainame='" + cainame + '\'' +
            ", cainum='" + cainum + '\'' +
            ", caiprice='" + caiprice + '\'' +
            ", url='" + url + '\'' +
            '}';
    }
}
