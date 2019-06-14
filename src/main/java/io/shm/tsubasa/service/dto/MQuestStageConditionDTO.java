package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MQuestStageCondition} entity.
 */
public class MQuestStageConditionDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer successConditionType;

    private Integer successConditionDetailType;

    @NotNull
    private Integer successConditionValue;

    private Integer targetCharacterGroupId;

    private Integer failureConditionType;


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

    public Integer getSuccessConditionDetailType() {
        return successConditionDetailType;
    }

    public void setSuccessConditionDetailType(Integer successConditionDetailType) {
        this.successConditionDetailType = successConditionDetailType;
    }

    public Integer getSuccessConditionValue() {
        return successConditionValue;
    }

    public void setSuccessConditionValue(Integer successConditionValue) {
        this.successConditionValue = successConditionValue;
    }

    public Integer getTargetCharacterGroupId() {
        return targetCharacterGroupId;
    }

    public void setTargetCharacterGroupId(Integer targetCharacterGroupId) {
        this.targetCharacterGroupId = targetCharacterGroupId;
    }

    public Integer getFailureConditionType() {
        return failureConditionType;
    }

    public void setFailureConditionType(Integer failureConditionType) {
        this.failureConditionType = failureConditionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MQuestStageConditionDTO mQuestStageConditionDTO = (MQuestStageConditionDTO) o;
        if (mQuestStageConditionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mQuestStageConditionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MQuestStageConditionDTO{" +
            "id=" + getId() +
            ", successConditionType=" + getSuccessConditionType() +
            ", successConditionDetailType=" + getSuccessConditionDetailType() +
            ", successConditionValue=" + getSuccessConditionValue() +
            ", targetCharacterGroupId=" + getTargetCharacterGroupId() +
            ", failureConditionType=" + getFailureConditionType() +
            "}";
    }
}
