package com.weisen.www.code.yjf.merchant.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Dishes.
 */
@Entity
@Table(name = "dishes")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dishes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "merchantid")
    private String merchantid;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "dishestypeid")
    private String dishestypeid;

    @Column(name = "price")
    private String price;

    @Column(name = "num")
    private String num;

    @Column(name = "creator")
    private String creator;

    @Column(name = "createdate")
    private String createdate;

    @Column(name = "modifier")
    private String modifier;

    @Column(name = "modifierdate")
    private String modifierdate;

    @Column(name = "modifiernum")
    private Long modifiernum;

    @Column(name = "logicdelete")
    private Boolean logicdelete;

    @Column(name = "state")
    private String state;

    @Column(name = "other")
    private String other;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public Dishes merchantid(String merchantid) {
        this.merchantid = merchantid;
        return this;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public String getName() {
        return name;
    }

    public Dishes name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public Dishes image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDishestypeid() {
        return dishestypeid;
    }

    public Dishes dishestypeid(String dishestypeid) {
        this.dishestypeid = dishestypeid;
        return this;
    }

    public void setDishestypeid(String dishestypeid) {
        this.dishestypeid = dishestypeid;
    }

    public String getPrice() {
        return price;
    }

    public Dishes price(String price) {
        this.price = price;
        return this;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public Dishes num(String num) {
        this.num = num;
        return this;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCreator() {
        return creator;
    }

    public Dishes creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Dishes createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Dishes modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Dishes modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Dishes modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Dishes logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getState() {
        return state;
    }

    public Dishes state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOther() {
        return other;
    }

    public Dishes other(String other) {
        this.other = other;
        return this;
    }

    public void setOther(String other) {
        this.other = other;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dishes)) {
            return false;
        }
        return id != null && id.equals(((Dishes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dishes{" +
            "id=" + getId() +
            ", merchantid='" + getMerchantid() + "'" +
            ", name='" + getName() + "'" +
            ", image='" + getImage() + "'" +
            ", dishestypeid='" + getDishestypeid() + "'" +
            ", price='" + getPrice() + "'" +
            ", num='" + getNum() + "'" +
            ", creator='" + getCreator() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", logicdelete='" + isLogicdelete() + "'" +
            ", state='" + getState() + "'" +
            ", other='" + getOther() + "'" +
            "}";
    }
}
