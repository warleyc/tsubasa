package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MCoopRoomStamp.
 */
@Entity
@Table(name = "m_coop_room_stamp")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCoopRoomStamp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "role", nullable = false)
    private Integer role;

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

    public Integer getRole() {
        return role;
    }

    public MCoopRoomStamp role(Integer role) {
        this.role = role;
        return this;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public MCoopRoomStamp orderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public MCoopRoomStamp masterId(Integer masterId) {
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
        if (!(o instanceof MCoopRoomStamp)) {
            return false;
        }
        return id != null && id.equals(((MCoopRoomStamp) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCoopRoomStamp{" +
            "id=" + getId() +
            ", role=" + getRole() +
            ", orderNum=" + getOrderNum() +
            ", masterId=" + getMasterId() +
            "}";
    }
}
