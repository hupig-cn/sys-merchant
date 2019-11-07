package com.weisen.www.code.yjf.merchant.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ManageMenus.
 */
@Entity
@Table(name = "manage_menus")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ManageMenus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pid")
    private Long pid;

    @Column(name = "title")
    private String title;

    @Column(name = "icon")
    private String icon;

    @Column(name = "href")
    private String href;

    @Column(name = "state")
    private Long state;

    @Column(name = "csort")
    private Long csort;

    @Column(name = "notice")
    private String notice;

    @Column(name = "target")
    private String target;

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

    public ManageMenus pid(Long pid) {
        this.pid = pid;
        return this;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public ManageMenus title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public ManageMenus icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public ManageMenus href(String href) {
        this.href = href;
        return this;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Long getState() {
        return state;
    }

    public ManageMenus state(Long state) {
        this.state = state;
        return this;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Long getCsort() {
        return csort;
    }

    public ManageMenus csort(Long csort) {
        this.csort = csort;
        return this;
    }

    public void setCsort(Long csort) {
        this.csort = csort;
    }

    public String getNotice() {
        return notice;
    }

    public ManageMenus notice(String notice) {
        this.notice = notice;
        return this;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getTarget() {
        return target;
    }

    public ManageMenus target(String target) {
        this.target = target;
        return this;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ManageMenus)) {
            return false;
        }
        return id != null && id.equals(((ManageMenus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ManageMenus{" +
            "id=" + getId() +
            ", pid=" + getPid() +
            ", title='" + getTitle() + "'" +
            ", icon='" + getIcon() + "'" +
            ", href='" + getHref() + "'" +
            ", state=" + getState() +
            ", csort=" + getCsort() +
            ", notice='" + getNotice() + "'" +
            ", target='" + getTarget() + "'" +
            "}";
    }
}
