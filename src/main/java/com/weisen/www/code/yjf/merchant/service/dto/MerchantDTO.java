package com.weisen.www.code.yjf.merchant.service.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.merchant.domain.Merchant} entity.
 */
public class MerchantDTO implements Serializable {

    private Long id;

    private String userid;

    private String merchantphoto;

    private String name;

    private String businessid;

    private String state;

    private String address;

    private String province;

    private String city;

    private String county;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Integer concession;

    private Integer rebate;

    private String buslicenseimage;

    private String show;

    private String creditcode;

    private String weight;

    private String creator;

    private String createdate;

    private String modifier;

    private String modifierdate;

    private Long modifiernum;

    private Boolean logicdelete;

    private String other;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMerchantphoto() {
        return merchantphoto;
    }

    public void setMerchantphoto(String merchantphoto) {
        this.merchantphoto = merchantphoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getConcession() {
        return concession;
    }

    public void setConcession(Integer concession) {
        this.concession = concession;
    }

    public Integer getRebate() {
        return rebate;
    }

    public void setRebate(Integer rebate) {
        this.rebate = rebate;
    }

    public String getBuslicenseimage() {
        return buslicenseimage;
    }

    public void setBuslicenseimage(String buslicenseimage) {
        this.buslicenseimage = buslicenseimage;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getCreditcode() {
        return creditcode;
    }

    public void setCreditcode(String creditcode) {
        this.creditcode = creditcode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MerchantDTO merchantDTO = (MerchantDTO) o;
        if (merchantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), merchantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MerchantDTO{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", merchantphoto='" + getMerchantphoto() + "'" +
            ", name='" + getName() + "'" +
            ", businessid='" + getBusinessid() + "'" +
            ", state='" + getState() + "'" +
            ", address='" + getAddress() + "'" +
            ", province='" + getProvince() + "'" +
            ", city='" + getCity() + "'" +
            ", county='" + getCounty() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", concession=" + getConcession() +
            ", rebate=" + getRebate() +
            ", buslicenseimage='" + getBuslicenseimage() + "'" +
            ", show='" + getShow() + "'" +
            ", creditcode='" + getCreditcode() + "'" +
            ", weight='" + getWeight() + "'" +
            ", creator='" + getCreator() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", logicdelete='" + isLogicdelete() + "'" +
            ", other='" + getOther() + "'" +
            "}";
    }
}
