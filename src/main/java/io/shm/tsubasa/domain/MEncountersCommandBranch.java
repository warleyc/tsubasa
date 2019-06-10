package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MEncountersCommandBranch.
 */
@Entity
@Table(name = "m_encounters_command_branch")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MEncountersCommandBranch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ball_float_condition", nullable = false)
    private Integer ballFloatCondition;

    @NotNull
    @Column(name = "jhi_condition", nullable = false)
    private Integer condition;

    @NotNull
    @Column(name = "encounters_type", nullable = false)
    private Integer encountersType;

    @NotNull
    @Column(name = "is_success", nullable = false)
    private Integer isSuccess;

    @NotNull
    @Column(name = "command_type", nullable = false)
    private Integer commandType;

    @NotNull
    @Column(name = "success_rate", nullable = false)
    private Integer successRate;

    @NotNull
    @Column(name = "loose_ball_rate", nullable = false)
    private Integer looseBallRate;

    @NotNull
    @Column(name = "touch_lightly_rate", nullable = false)
    private Integer touchLightlyRate;

    @NotNull
    @Column(name = "post_rate", nullable = false)
    private Integer postRate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBallFloatCondition() {
        return ballFloatCondition;
    }

    public MEncountersCommandBranch ballFloatCondition(Integer ballFloatCondition) {
        this.ballFloatCondition = ballFloatCondition;
        return this;
    }

    public void setBallFloatCondition(Integer ballFloatCondition) {
        this.ballFloatCondition = ballFloatCondition;
    }

    public Integer getCondition() {
        return condition;
    }

    public MEncountersCommandBranch condition(Integer condition) {
        this.condition = condition;
        return this;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Integer getEncountersType() {
        return encountersType;
    }

    public MEncountersCommandBranch encountersType(Integer encountersType) {
        this.encountersType = encountersType;
        return this;
    }

    public void setEncountersType(Integer encountersType) {
        this.encountersType = encountersType;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public MEncountersCommandBranch isSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
        return this;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getCommandType() {
        return commandType;
    }

    public MEncountersCommandBranch commandType(Integer commandType) {
        this.commandType = commandType;
        return this;
    }

    public void setCommandType(Integer commandType) {
        this.commandType = commandType;
    }

    public Integer getSuccessRate() {
        return successRate;
    }

    public MEncountersCommandBranch successRate(Integer successRate) {
        this.successRate = successRate;
        return this;
    }

    public void setSuccessRate(Integer successRate) {
        this.successRate = successRate;
    }

    public Integer getLooseBallRate() {
        return looseBallRate;
    }

    public MEncountersCommandBranch looseBallRate(Integer looseBallRate) {
        this.looseBallRate = looseBallRate;
        return this;
    }

    public void setLooseBallRate(Integer looseBallRate) {
        this.looseBallRate = looseBallRate;
    }

    public Integer getTouchLightlyRate() {
        return touchLightlyRate;
    }

    public MEncountersCommandBranch touchLightlyRate(Integer touchLightlyRate) {
        this.touchLightlyRate = touchLightlyRate;
        return this;
    }

    public void setTouchLightlyRate(Integer touchLightlyRate) {
        this.touchLightlyRate = touchLightlyRate;
    }

    public Integer getPostRate() {
        return postRate;
    }

    public MEncountersCommandBranch postRate(Integer postRate) {
        this.postRate = postRate;
        return this;
    }

    public void setPostRate(Integer postRate) {
        this.postRate = postRate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MEncountersCommandBranch)) {
            return false;
        }
        return id != null && id.equals(((MEncountersCommandBranch) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MEncountersCommandBranch{" +
            "id=" + getId() +
            ", ballFloatCondition=" + getBallFloatCondition() +
            ", condition=" + getCondition() +
            ", encountersType=" + getEncountersType() +
            ", isSuccess=" + getIsSuccess() +
            ", commandType=" + getCommandType() +
            ", successRate=" + getSuccessRate() +
            ", looseBallRate=" + getLooseBallRate() +
            ", touchLightlyRate=" + getTouchLightlyRate() +
            ", postRate=" + getPostRate() +
            "}";
    }
}
