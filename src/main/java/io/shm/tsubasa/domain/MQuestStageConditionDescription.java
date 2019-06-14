package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MQuestStageConditionDescription.
 */
@Entity
@Table(name = "m_quest_stage_condition_description")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MQuestStageConditionDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "success_condition_type", nullable = false)
    private Integer successConditionType;

    @NotNull
    @Column(name = "success_condition_detail_type_value", nullable = false)
    private Integer successConditionDetailTypeValue;

    @NotNull
    @Column(name = "has_exist_target_character_group", nullable = false)
    private Integer hasExistTargetCharacterGroup;

    @NotNull
    @Column(name = "has_success_condition_value_one_only", nullable = false)
    private Integer hasSuccessConditionValueOneOnly;

    @NotNull
    @Column(name = "failure_condition_type_value", nullable = false)
    private Integer failureConditionTypeValue;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

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

    public MQuestStageConditionDescription successConditionType(Integer successConditionType) {
        this.successConditionType = successConditionType;
        return this;
    }

    public void setSuccessConditionType(Integer successConditionType) {
        this.successConditionType = successConditionType;
    }

    public Integer getSuccessConditionDetailTypeValue() {
        return successConditionDetailTypeValue;
    }

    public MQuestStageConditionDescription successConditionDetailTypeValue(Integer successConditionDetailTypeValue) {
        this.successConditionDetailTypeValue = successConditionDetailTypeValue;
        return this;
    }

    public void setSuccessConditionDetailTypeValue(Integer successConditionDetailTypeValue) {
        this.successConditionDetailTypeValue = successConditionDetailTypeValue;
    }

    public Integer getHasExistTargetCharacterGroup() {
        return hasExistTargetCharacterGroup;
    }

    public MQuestStageConditionDescription hasExistTargetCharacterGroup(Integer hasExistTargetCharacterGroup) {
        this.hasExistTargetCharacterGroup = hasExistTargetCharacterGroup;
        return this;
    }

    public void setHasExistTargetCharacterGroup(Integer hasExistTargetCharacterGroup) {
        this.hasExistTargetCharacterGroup = hasExistTargetCharacterGroup;
    }

    public Integer getHasSuccessConditionValueOneOnly() {
        return hasSuccessConditionValueOneOnly;
    }

    public MQuestStageConditionDescription hasSuccessConditionValueOneOnly(Integer hasSuccessConditionValueOneOnly) {
        this.hasSuccessConditionValueOneOnly = hasSuccessConditionValueOneOnly;
        return this;
    }

    public void setHasSuccessConditionValueOneOnly(Integer hasSuccessConditionValueOneOnly) {
        this.hasSuccessConditionValueOneOnly = hasSuccessConditionValueOneOnly;
    }

    public Integer getFailureConditionTypeValue() {
        return failureConditionTypeValue;
    }

    public MQuestStageConditionDescription failureConditionTypeValue(Integer failureConditionTypeValue) {
        this.failureConditionTypeValue = failureConditionTypeValue;
        return this;
    }

    public void setFailureConditionTypeValue(Integer failureConditionTypeValue) {
        this.failureConditionTypeValue = failureConditionTypeValue;
    }

    public String getDescription() {
        return description;
    }

    public MQuestStageConditionDescription description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MQuestStageConditionDescription)) {
            return false;
        }
        return id != null && id.equals(((MQuestStageConditionDescription) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MQuestStageConditionDescription{" +
            "id=" + getId() +
            ", successConditionType=" + getSuccessConditionType() +
            ", successConditionDetailTypeValue=" + getSuccessConditionDetailTypeValue() +
            ", hasExistTargetCharacterGroup=" + getHasExistTargetCharacterGroup() +
            ", hasSuccessConditionValueOneOnly=" + getHasSuccessConditionValueOneOnly() +
            ", failureConditionTypeValue=" + getFailureConditionTypeValue() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
