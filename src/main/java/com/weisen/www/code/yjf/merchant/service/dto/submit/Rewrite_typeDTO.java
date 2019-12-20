package com.weisen.www.code.yjf.merchant.service.dto.submit;

import com.weisen.www.code.yjf.merchant.domain.Dishes;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/12/17 16:45
 */
public class Rewrite_typeDTO implements Serializable {

    private String name;

    private String id;

    private List<Long> caiid;

    private List<String> cainame;

    private List<String> caiprice;

    private List<String> cainum;

    public List<Long> getCaiid() {
        return caiid;
    }

    public void setCaiid(List<Long> caiid) {
        this.caiid = caiid;
    }

    public List<String> getCainame() {
        return cainame;
    }

    public void setCainame(List<String> cainame) {
        this.cainame = cainame;
    }

    public List<String> getCaiprice() {
        return caiprice;
    }

    public void setCaiprice(List<String> caiprice) {
        this.caiprice = caiprice;
    }

    public List<String> getCainum() {
        return cainum;
    }

    public void setCainum(List<String> cainum) {
        this.cainum = cainum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
