package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MQuestStageCondition.
 */
@Entity
@Table(name = "m_quest_stage_condition")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MQuestStageCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "success_condition_type", nullable = false)
    private Integer successConditionType;

    @Column(name = "success_condition_detail_type")
    private Integer successConditionDetailType;

    @NotNull
    @Column(name = "success_condition_value", nullable = false)
    private Integer successConditionValue;

    @Column(name = "target_character_group_id")
    private Integer targetCharacterGroupId;

    @Column(name = "failure_condition_type")
    private Integer failureConditionType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSuccessConditionType() {
        return successConditionType;
    }

    public MQuestStageCondition successConditionType(Integer successConditionType) {
        this.successConditionType = successConditionType;
        return this;
    }

    public void setSuccessConditionType(Integer successConditionType) {
        this.successConditionType = successConditionType;
    }

    public Integer getSuccessConditionDetailType() {
        return successConditionDetailType;
    }

    public MQuestStageCondition successConditionDetailType(Integer successConditionDetailType) {
        this.successConditionDetailType = successConditionDetailType;
        return this;
    }

    public void setSuccessConditionDetailType(Integer successConditionDetailType) {
        this.successConditionDetailType = successConditionDetailType;
    }

    public Integer getSuccessConditionValue() {
        return successConditionValue;
    }

    public MQuestStageCondition successConditionValue(Integer successConditionValue) {
        this.successConditionValue = successConditionValue;
        return this;
    }

    public void setSuccessConditionValue(Integer successConditionValue) {
        this.successConditionValue = successConditionValue;
    }

    public Integer getTargetCharacterGroupId() {
        return targetCharacterGroupId;
    }

    public MQuestStageCondition targetCharacterGroupId(Integer targetCharacterGroupId) {
        this.targetCharacterGroupId = targetCharacterGroupId;
        return this;
    }

    public void setTargetCharacterGroupId(Integer targetCharacterGroupId) {
        this.targetCharacterGroupId = targetCharacterGroupId;
    }

    public Integer getFailureConditionType() {
        return failureConditionType;
    }

    public MQuestStageCondition failureConditionType(Integer failureConditionType) {
        this.failureConditionType = failureConditionType;
        return this;
    }

    public void setFailureConditionType(Integer failureConditionType) {
        this.failureConditionType = failureConditionType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MQuestStageCondition)) {
            return false;
        }
        return id != null && id.equals(((MQuestStageCondition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MQuestStageCondition{" +
            "id=" + getId() +
            ", successConditionType=" + getSuccessConditionType() +
            ", successConditionDetailType=" + getSuccessConditionDetailType() +
            ", successConditionValue=" + getSuccessConditionValue() +
            ", targetCharacterGroupId=" + getTargetCharacterGroupId() +
            ", failureConditionType=" + getFailureConditionType() +
            "}";
    }
}
