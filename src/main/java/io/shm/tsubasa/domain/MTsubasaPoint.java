package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTsubasaPoint.
 */
@Entity
@Table(name = "m_tsubasa_point")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTsubasaPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "match_type", nullable = false)
    private Integer matchType;

    @NotNull
    @Column(name = "point_type", nullable = false)
    private Integer pointType;

    @NotNull
    @Column(name = "calc_type", nullable = false)
    private Integer calcType;

    @NotNull
    @Column(name = "a_value", nullable = false)
    private Integer aValue;

    @NotNull
    @Column(name = "b_value", nullable = false)
    private Integer bValue;

    @NotNull
    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMatchType() {
        return matchType;
    }

    public MTsubasaPoint matchType(Integer matchType) {
        this.matchType = matchType;
        return this;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    public Integer getPointType() {
        return pointType;
    }

    public MTsubasaPoint pointType(Integer pointType) {
        this.pointType = pointType;
        return this;
    }

    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }

    public Integer getCalcType() {
        return calcType;
    }

    public MTsubasaPoint calcType(Integer calcType) {
        this.calcType = calcType;
        return this;
    }

    public void setCalcType(Integer calcType) {
        this.calcType = calcType;
    }

    public Integer getaValue() {
        return aValue;
    }

    public MTsubasaPoint aValue(Integer aValue) {
        this.aValue = aValue;
        return this;
    }

    public void setaValue(Integer aValue) {
        this.aValue = aValue;
    }

    public Integer getbValue() {
        return bValue;
    }

    public MTsubasaPoint bValue(Integer bValue) {
        this.bValue = bValue;
        return this;
    }

    public void setbValue(Integer bValue) {
        this.bValue = bValue;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public MTsubasaPoint orderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTsubasaPoint)) {
            return false;
        }
        return id != null && id.equals(((MTsubasaPoint) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTsubasaPoint{" +
            "id=" + getId() +
            ", matchType=" + getMatchType() +
            ", pointType=" + getPointType() +
            ", calcType=" + getCalcType() +
            ", aValue=" + getaValue() +
            ", bValue=" + getbValue() +
            ", orderNum=" + getOrderNum() +
            "}";
    }
}
