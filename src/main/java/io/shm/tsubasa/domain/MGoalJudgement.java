package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGoalJudgement.
 */
@Entity
@Table(name = "m_goal_judgement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGoalJudgement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "judgement_id", nullable = false)
    private Integer judgementId;

    @NotNull
    @Column(name = "encounters_type", nullable = false)
    private Integer encountersType;

    @NotNull
    @Column(name = "success_rate", nullable = false)
    private Integer successRate;

    @NotNull
    @Column(name = "goal_post_rate", nullable = false)
    private Integer goalPostRate;

    @NotNull
    @Column(name = "ball_push_rate", nullable = false)
    private Integer ballPushRate;

    @NotNull
    @Column(name = "clear_rate", nullable = false)
    private Integer clearRate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJudgementId() {
        return judgementId;
    }

    public MGoalJudgement judgementId(Integer judgementId) {
        this.judgementId = judgementId;
        return this;
    }

    public void setJudgementId(Integer judgementId) {
        this.judgementId = judgementId;
    }

    public Integer getEncountersType() {
        return encountersType;
    }

    public MGoalJudgement encountersType(Integer encountersType) {
        this.encountersType = encountersType;
        return this;
    }

    public void setEncountersType(Integer encountersType) {
        this.encountersType = encountersType;
    }

    public Integer getSuccessRate() {
        return successRate;
    }

    public MGoalJudgement successRate(Integer successRate) {
        this.successRate = successRate;
        return this;
    }

    public void setSuccessRate(Integer successRate) {
        this.successRate = successRate;
    }

    public Integer getGoalPostRate() {
        return goalPostRate;
    }

    public MGoalJudgement goalPostRate(Integer goalPostRate) {
        this.goalPostRate = goalPostRate;
        return this;
    }

    public void setGoalPostRate(Integer goalPostRate) {
        this.goalPostRate = goalPostRate;
    }

    public Integer getBallPushRate() {
        return ballPushRate;
    }

    public MGoalJudgement ballPushRate(Integer ballPushRate) {
        this.ballPushRate = ballPushRate;
        return this;
    }

    public void setBallPushRate(Integer ballPushRate) {
        this.ballPushRate = ballPushRate;
    }

    public Integer getClearRate() {
        return clearRate;
    }

    public MGoalJudgement clearRate(Integer clearRate) {
        this.clearRate = clearRate;
        return this;
    }

    public void setClearRate(Integer clearRate) {
        this.clearRate = clearRate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGoalJudgement)) {
            return false;
        }
        return id != null && id.equals(((MGoalJudgement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGoalJudgement{" +
            "id=" + getId() +
            ", judgementId=" + getJudgementId() +
            ", encountersType=" + getEncountersType() +
            ", successRate=" + getSuccessRate() +
            ", goalPostRate=" + getGoalPostRate() +
            ", ballPushRate=" + getBallPushRate() +
            ", clearRate=" + getClearRate() +
            "}";
    }
}
