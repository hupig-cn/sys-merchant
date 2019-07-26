package com.weisen.www.code.yjf.merchant.service.dto;

public class Rewrite_GosCountDTO {

    private Long onsale ;

    private Long takedown;

    private Long draft ;

    public Rewrite_GosCountDTO(Long onsale ,Long takedown, Long draft){
        this.onsale = onsale;
        this.takedown = takedown;
        this.draft = draft;
    }

    public Long getOnsale() {
        return onsale;
    }

    public void setOnsale(Long onsale) {
        this.onsale = onsale;
    }

    public Long getTakedown() {
        return takedown;
    }

    public void setTakedown(Long takedown) {
        this.takedown = takedown;
    }

    public Long getDraft() {
        return draft;
    }

    public void setDraft(Long draft) {
        this.draft = draft;
    }
}
