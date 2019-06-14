package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MShoot.
 */
@Entity
@Table(name = "m_shoot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MShoot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "angle_decay_type", nullable = false)
    private Integer angleDecayType;

    @NotNull
    @Column(name = "shoot_orbit", nullable = false)
    private Integer shootOrbit;

    @NotNull
    @Column(name = "judgement_id", nullable = false)
    private Integer judgementId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAngleDecayType() {
        return angleDecayType;
    }

    public MShoot angleDecayType(Integer angleDecayType) {
        this.angleDecayType = angleDecayType;
        return this;
    }

    public void setAngleDecayType(Integer angleDecayType) {
        this.angleDecayType = angleDecayType;
    }

    public Integer getShootOrbit() {
        return shootOrbit;
    }

    public MShoot shootOrbit(Integer shootOrbit) {
        this.shootOrbit = shootOrbit;
        return this;
    }

    public void setShootOrbit(Integer shootOrbit) {
        this.shootOrbit = shootOrbit;
    }

    public Integer getJudgementId() {
        return judgementId;
    }

    public MShoot judgementId(Integer judgementId) {
        this.judgementId = judgementId;
        return this;
    }

    public void setJudgementId(Integer judgementId) {
        this.judgementId = judgementId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MShoot)) {
            return false;
        }
        return id != null && id.equals(((MShoot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MShoot{" +
            "id=" + getId() +
            ", angleDecayType=" + getAngleDecayType() +
            ", shootOrbit=" + getShootOrbit() +
            ", judgementId=" + getJudgementId() +
            "}";
    }
}
