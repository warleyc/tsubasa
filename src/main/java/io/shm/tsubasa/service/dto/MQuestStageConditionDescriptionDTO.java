package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MQuestStageConditionDescription} entity.
 */
public class MQuestStageConditionDescriptionDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer successConditionType;

    @NotNull
    private Integer successConditionDetailTypeValue;

    @NotNull
    private Integer hasExistTargetCharacterGroup;

    @NotNull
    private Integer hasSuccessConditionValueOneOnly;

    @NotNull
    private Integer failureConditionTypeValue;

    
    @Lob
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSuccessConditionType() {
        return successConditionType;
    }

    public void setSuccessConditionType(Integer successConditionType) {
        this.successConditionType = successConditionType;
    }

    public Integer getSuccessConditionDetailTypeValue() {
        return successConditionDetailTypeValue;
    }

    public void setSuccessConditionDetailTypeValue(Integer successConditionDetailTypeValue) {
        this.successConditionDetailTypeValue = successConditionDetailTypeValue;
    }

    public Integer getHasExistTargetCharacterGroup() {
        return hasExistTargetCharacterGroup;
    }

    public void setHasExistTargetCharacterGroup(Integer hasExistTargetCharacterGroup) {
        this.hasExistTargetCharacterGroup = hasExistTargetCharacterGroup;
    }

    public Integer getHasSuccessConditionValueOneOnly() {
        return hasSuccessConditionValueOneOnly;
    }

    public void setHasSuccessConditionValueOneOnly(Integer hasSuccessConditionValueOneOnly) {
        this.hasSuccessConditionValueOneOnly = hasSuccessConditionValueOneOnly;
    }

    public Integer getFailureConditionTypeValue() {
        return failureConditionTypeValue;
    }

    public void setFailureConditionTypeValue(Integer failureConditionTypeValue) {
        this.failureConditionTypeValue = failureConditionTypeValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO = (MQuestStageConditionDescriptionDTO) o;
        if (mQuestStageConditionDescriptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mQuestStageConditionDescriptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MQuestStageConditionDescriptionDTO{" +
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
