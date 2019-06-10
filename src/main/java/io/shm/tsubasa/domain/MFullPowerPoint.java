package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MFullPowerPoint.
 */
@Entity
@Table(name = "m_full_power_point")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MFullPowerPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "point_type", nullable = false)
    private Integer pointType;

    @NotNull
    @Column(name = "jhi_value", nullable = false)
    private Integer value;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPointType() {
        return pointType;
    }

    public MFullPowerPoint pointType(Integer pointType) {
        this.pointType = pointType;
        return this;
    }

    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }

    public Integer getValue() {
        return value;
    }

    public MFullPowerPoint value(Integer value) {
        this.value = value;
        return this;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MFullPowerPoint)) {
            return false;
        }
        return id != null && id.equals(((MFullPowerPoint) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MFullPowerPoint{" +
            "id=" + getId() +
            ", pointType=" + getPointType() +
            ", value=" + getValue() +
            "}";
    }
}
