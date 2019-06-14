package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MPvpWatcherStamp.
 */
@Entity
@Table(name = "m_pvp_watcher_stamp")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MPvpWatcherStamp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    @NotNull
    @Column(name = "master_id", nullable = false)
    private Integer masterId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public MPvpWatcherStamp orderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public MPvpWatcherStamp masterId(Integer masterId) {
        this.masterId = masterId;
        return this;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MPvpWatcherStamp)) {
            return false;
        }
        return id != null && id.equals(((MPvpWatcherStamp) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MPvpWatcherStamp{" +
            "id=" + getId() +
            ", orderNum=" + getOrderNum() +
            ", masterId=" + getMasterId() +
            "}";
    }
}
