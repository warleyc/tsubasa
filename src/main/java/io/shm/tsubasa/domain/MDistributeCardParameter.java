package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MDistributeCardParameter.
 */
@Entity
@Table(name = "m_distribute_card_parameter")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MDistributeCardParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rarity", nullable = false)
    private Integer rarity;

    @NotNull
    @Column(name = "is_gk", nullable = false)
    private Integer isGk;

    @NotNull
    @Column(name = "max_step_count", nullable = false)
    private Integer maxStepCount;

    @NotNull
    @Column(name = "max_total_step_count", nullable = false)
    private Integer maxTotalStepCount;

    @NotNull
    @Column(name = "distribute_point_by_step", nullable = false)
    private Integer distributePointByStep;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRarity() {
        return rarity;
    }

    public MDistributeCardParameter rarity(Integer rarity) {
        this.rarity = rarity;
        return this;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getIsGk() {
        return isGk;
    }

    public MDistributeCardParameter isGk(Integer isGk) {
        this.isGk = isGk;
        return this;
    }

    public void setIsGk(Integer isGk) {
        this.isGk = isGk;
    }

    public Integer getMaxStepCount() {
        return maxStepCount;
    }

    public MDistributeCardParameter maxStepCount(Integer maxStepCount) {
        this.maxStepCount = maxStepCount;
        return this;
    }

    public void setMaxStepCount(Integer maxStepCount) {
        this.maxStepCount = maxStepCount;
    }

    public Integer getMaxTotalStepCount() {
        return maxTotalStepCount;
    }

    public MDistributeCardParameter maxTotalStepCount(Integer maxTotalStepCount) {
        this.maxTotalStepCount = maxTotalStepCount;
        return this;
    }

    public void setMaxTotalStepCount(Integer maxTotalStepCount) {
        this.maxTotalStepCount = maxTotalStepCount;
    }

    public Integer getDistributePointByStep() {
        return distributePointByStep;
    }

    public MDistributeCardParameter distributePointByStep(Integer distributePointByStep) {
        this.distributePointByStep = distributePointByStep;
        return this;
    }

    public void setDistributePointByStep(Integer distributePointByStep) {
        this.distributePointByStep = distributePointByStep;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MDistributeCardParameter)) {
            return false;
        }
        return id != null && id.equals(((MDistributeCardParameter) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MDistributeCardParameter{" +
            "id=" + getId() +
            ", rarity=" + getRarity() +
            ", isGk=" + getIsGk() +
            ", maxStepCount=" + getMaxStepCount() +
            ", maxTotalStepCount=" + getMaxTotalStepCount() +
            ", distributePointByStep=" + getDistributePointByStep() +
            "}";
    }
}
