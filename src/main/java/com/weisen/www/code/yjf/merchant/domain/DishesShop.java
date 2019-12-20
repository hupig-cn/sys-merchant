package com.weisen.www.code.yjf.merchant.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DishesShop.
 */
@Entity
@Table(name = "dishes_shop")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DishesShop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ioc")
    private String ioc;

    @Column(name = "filesid")
    private Long filesid;

    @Column(name = "num")
    private Integer num;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "merchatid")
    private Integer merchatid;

    @Column(name = "url")
    private String url;

    @Column(name = "price")
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getMerchatid() {
        return merchatid;
    }

    public void setMerchatid(Integer merchatid) {
        this.merchatid = merchatid;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DishesShop name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIoc() {
        return ioc;
    }

    public DishesShop ioc(String ioc) {
        this.ioc = ioc;
        return this;
    }

    public void setIoc(String ioc) {
        this.ioc = ioc;
    }

    public Long getFilesid() {
        return filesid;
    }

    public DishesShop filesid(Long filesid) {
        this.filesid = filesid;
        return this;
    }

    public void setFilesid(Long filesid) {
        this.filesid = filesid;
    }

    public Integer getNum() {
        return num;
    }

    public DishesShop num(Integer num) {
        this.num = num;
        return this;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCreateDate() {
        return createDate;
    }

    public DishesShop createDate(String createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DishesShop)) {
            return false;
        }
        return id != null && id.equals(((DishesShop) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DishesShop{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", ioc='" + getIoc() + "'" +
            ", filesid=" + getFilesid() +
            ", num=" + getNum() +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
