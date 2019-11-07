package com.weisen.www.code.yjf.merchant.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ManageOrg.
 */
@Entity
@Table(name = "manage_org")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ManageOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pid")
    private Long pid;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private Long state;

    @Column(name = "csort")
    private Long csort;

    @Column(name = "ctime")
    private String ctime;

    @Column(name = "jhi_desc")
    private String desc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public ManageOrg pid(Long pid) {
        this.pid = pid;
        return this;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public ManageOrg name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getState() {
        return state;
    }

    public ManageOrg state(Long state) {
        this.state = state;
        return this;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Long getCsort() {
        return csort;
    }

    public ManageOrg csort(Long csort) {
        this.csort = csort;
        return this;
    }

    public void setCsort(Long csort) {
        this.csort = csort;
    }

    public String getCtime() {
        return ctime;
    }

    public ManageOrg ctime(String ctime) {
        this.ctime = ctime;
        return this;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getDesc() {
        return desc;
    }

    public ManageOrg desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ManageOrg)) {
            return false;
        }
        return id != null && id.equals(((ManageOrg) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ManageOrg{" +
            "id=" + getId() +
            ", pid=" + getPid() +
            ", name='" + getName() + "'" +
            ", state=" + getState() +
            ", csort=" + getCsort() +
            ", ctime='" + getCtime() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
