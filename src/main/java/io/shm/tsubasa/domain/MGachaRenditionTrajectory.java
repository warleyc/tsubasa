package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRenditionTrajectory.
 */
@Entity
@Table(name = "m_gacha_rendition_trajectory")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRenditionTrajectory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @NotNull
    @Column(name = "trajectory_type", nullable = false)
    private Integer trajectoryType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public MGachaRenditionTrajectory weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getTrajectoryType() {
        return trajectoryType;
    }

    public MGachaRenditionTrajectory trajectoryType(Integer trajectoryType) {
        this.trajectoryType = trajectoryType;
        return this;
    }

    public void setTrajectoryType(Integer trajectoryType) {
        this.trajectoryType = trajectoryType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRenditionTrajectory)) {
            return false;
        }
        return id != null && id.equals(((MGachaRenditionTrajectory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRenditionTrajectory{" +
            "id=" + getId() +
            ", weight=" + getWeight() +
            ", trajectoryType=" + getTrajectoryType() +
            "}";
    }
}
