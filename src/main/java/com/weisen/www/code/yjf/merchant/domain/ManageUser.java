package com.weisen.www.code.yjf.merchant.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ManageUser.
 */
@Entity
@Table(name = "manage_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ManageUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "jhi_password")
    private String password;

    @Column(name = "relname")
    private String relname;

    @Column(name = "state")
    private Long state;

    @Column(name = "ctime")
    private String ctime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public ManageUser username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public ManageUser password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRelname() {
        return relname;
    }

    public ManageUser relname(String relname) {
        this.relname = relname;
        return this;
    }

    public void setRelname(String relname) {
        this.relname = relname;
    }

    public Long getState() {
        return state;
    }

    public ManageUser state(Long state) {
        this.state = state;
        return this;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getCtime() {
        return ctime;
    }

    public ManageUser ctime(String ctime) {
        this.ctime = ctime;
        return this;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ManageUser)) {
            return false;
        }
        return id != null && id.equals(((ManageUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ManageUser{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", relname='" + getRelname() + "'" +
            ", state=" + getState() +
            ", ctime='" + getCtime() + "'" +
            "}";
    }
}
