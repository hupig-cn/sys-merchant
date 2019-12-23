package com.weisen.www.code.yjf.merchant.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Dishesbuy.
 */
@Entity
@Table(name = "dishesbuy")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dishesbuy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "merchantid")
    private Long merchantid;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "dishesid")
    private Long dishesid;

    @Column(name = "count")
    private Integer count;

    @Column(name = "price")
    private Double price;

    @Column(name = "state")
    private Integer state;

    @Column(name = "creator")
    private Long creator;

    @Column(name = "createtime")
    private String createtime;

    @Column(name = "modifier")
    private Long modifier;

    @Column(name = "modifinum")
    private Integer modifinum;

    @Column(name = "modifitime")
    private String modifitime;

    @Column(name = "logic_delete")
    private Integer logicDelete;

    @Column(name = "other")
    private String other;

    @Column(name = "orderid")
    private Long orderid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantid() {
        return merchantid;
    }

    public Dishesbuy merchantid(Long merchantid) {
        this.merchantid = merchantid;
        return this;
    }

    public void setMerchantid(Long merchantid) {
        this.merchantid = merchantid;
    }

    public Long getUserid() {
        return userid;
    }

    public Dishesbuy userid(Long userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getDishesid() {
        return dishesid;
    }

    public Dishesbuy dishesid(Long dishesid) {
        this.dishesid = dishesid;
        return this;
    }

    public void setDishesid(Long dishesid) {
        this.dishesid = dishesid;
    }

    public Integer getCount() {
        return count;
    }

    public Dishesbuy count(Integer count) {
        this.count = count;
        return this;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public Dishesbuy price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getState() {
        return state;
    }

    public Dishesbuy state(Integer state) {
        this.state = state;
        return this;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCreator() {
        return creator;
    }

    public Dishesbuy creator(Long creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getCreatetime() {
        return createtime;
    }

    public Dishesbuy createtime(String createtime) {
        this.createtime = createtime;
        return this;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Long getModifier() {
        return modifier;
    }

    public Dishesbuy modifier(Long modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    public Integer getModifinum() {
        return modifinum;
    }

    public Dishesbuy modifinum(Integer modifinum) {
        this.modifinum = modifinum;
        return this;
    }

    public void setModifinum(Integer modifinum) {
        this.modifinum = modifinum;
    }

    public String getModifitime() {
        return modifitime;
    }

    public Dishesbuy modifitime(String modifitime) {
        this.modifitime = modifitime;
        return this;
    }

    public void setModifitime(String modifitime) {
        this.modifitime = modifitime;
    }

    public Integer getLogicDelete() {
        return logicDelete;
    }

    public Dishesbuy logicDelete(Integer logicDelete) {
        this.logicDelete = logicDelete;
        return this;
    }

    public void setLogicDelete(Integer logicDelete) {
        this.logicDelete = logicDelete;
    }

    public String getOther() {
        return other;
    }

    public Dishesbuy other(String other) {
        this.other = other;
        return this;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Long getOrderid() {
        return orderid;
    }

    public Dishesbuy orderid(Long orderid) {
        this.orderid = orderid;
        return this;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dishesbuy)) {
            return false;
        }
        return id != null && id.equals(((Dishesbuy) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dishesbuy{" +
            "id=" + getId() +
            ", merchantid=" + getMerchantid() +
            ", userid=" + getUserid() +
            ", dishesid=" + getDishesid() +
            ", count=" + getCount() +
            ", price=" + getPrice() +
            ", state=" + getState() +
            ", creator=" + getCreator() +
            ", createtime='" + getCreatetime() + "'" +
            ", modifier=" + getModifier() +
            ", modifinum=" + getModifinum() +
            ", modifitime='" + getModifitime() + "'" +
            ", logicDelete=" + getLogicDelete() +
            ", other='" + getOther() + "'" +
            ", orderid=" + getOrderid() +
            "}";
    }
}
