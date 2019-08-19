package com.weisen.www.code.yjf.merchant.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Dishesorder.
 */
@Entity
@Table(name = "dishesorder")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dishesorder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bigorder")
    private String bigorder;

    @Column(name = "littleorder")
    private String littleorder;

    @Column(name = "merchantid")
    private String merchantid;

    @Column(name = "location")
    private String location;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

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

    @Column(name = "other")
    private String other;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBigorder() {
        return bigorder;
    }

    public Dishesorder bigorder(String bigorder) {
        this.bigorder = bigorder;
        return this;
    }

    public void setBigorder(String bigorder) {
        this.bigorder = bigorder;
    }

    public String getLittleorder() {
        return littleorder;
    }

    public Dishesorder littleorder(String littleorder) {
        this.littleorder = littleorder;
        return this;
    }

    public void setLittleorder(String littleorder) {
        this.littleorder = littleorder;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public Dishesorder merchantid(String merchantid) {
        this.merchantid = merchantid;
        return this;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public String getLocation() {
        return location;
    }

    public Dishesorder location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Dishesorder name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public Dishesorder state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public Dishesorder creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Dishesorder createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Dishesorder modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Dishesorder modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Dishesorder modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Dishesorder logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Dishesorder other(String other) {
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
        if (!(o instanceof Dishesorder)) {
            return false;
        }
        return id != null && id.equals(((Dishesorder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dishesorder{" +
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
