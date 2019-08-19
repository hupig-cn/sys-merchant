package com.weisen.www.code.yjf.merchant.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.merchant.domain.Dishesorder} entity.
 */
@SuppressWarnings("serial")
public class DishesorderDTO implements Serializable {

    private Long id;

    private String bigorder;

    private String littleorder;

    private String merchantid;

    private String location;

    private String name;

    private String state;

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

    public String getBigorder() {
        return bigorder;
    }

    public void setBigorder(String bigorder) {
        this.bigorder = bigorder;
    }

    public String getLittleorder() {
        return littleorder;
    }

    public void setLittleorder(String littleorder) {
        this.littleorder = littleorder;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

        DishesorderDTO dishesorderDTO = (DishesorderDTO) o;
        if (dishesorderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dishesorderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DishesorderDTO{" +
            "id=" + getId() +
            ", bigorder='" + getBigorder() + "'" +
            ", littleorder='" + getLittleorder() + "'" +
            ", merchantid='" + getMerchantid() + "'" +
            ", location='" + getLocation() + "'" +
            ", name='" + getName() + "'" +
            ", state='" + getState() + "'" +
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
