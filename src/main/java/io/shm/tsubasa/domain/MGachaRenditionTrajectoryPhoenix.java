package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGachaRenditionTrajectoryPhoenix.
 */
@Entity
@Table(name = "m_gacha_rendition_trajectory_phoenix")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGachaRenditionTrajectoryPhoenix implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "is_phoenix", nullable = false)
    private Integer isPhoenix;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsPhoenix() {
        return isPhoenix;
    }

    public MGachaRenditionTrajectoryPhoenix isPhoenix(Integer isPhoenix) {
        this.isPhoenix = isPhoenix;
        return this;
    }

    public void setIsPhoenix(Integer isPhoenix) {
        this.isPhoenix = isPhoenix;
    }

    public Integer getWeight() {
        return weight;
    }

    public MGachaRenditionTrajectoryPhoenix weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGachaRenditionTrajectoryPhoenix)) {
            return false;
        }
        return id != null && id.equals(((MGachaRenditionTrajectoryPhoenix) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGachaRenditionTrajectoryPhoenix{" +
            "id=" + getId() +
            ", isPhoenix=" + getIsPhoenix() +
            ", weight=" + getWeight() +
            "}";
    }
}
