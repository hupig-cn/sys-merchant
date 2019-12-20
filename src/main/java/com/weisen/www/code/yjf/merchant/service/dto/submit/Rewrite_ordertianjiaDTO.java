package com.weisen.www.code.yjf.merchant.service.dto.submit;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/12/17 16:45
 */
public class Rewrite_ordertianjiaDTO implements Serializable {

    private Long caiid;

    private String cainame;

    private String caiprice;

    private String cainum;

    public Long getCaiid() {
        return caiid;
    }

    public void setCaiid(Long caiid) {
        this.caiid = caiid;
    }

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

    public String getCainum() {
        return cainum;
    }

    public void setCainum(String cainum) {
        this.cainum = cainum;
    }
}
