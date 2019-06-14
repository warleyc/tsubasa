package io.shm.tsubasa.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.shm.tsubasa.domain.MQuestStageCondition} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MQuestStageConditionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-quest-stage-conditions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MQuestStageConditionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter successConditionType;

    private IntegerFilter successConditionDetailType;

    private IntegerFilter successConditionValue;

    private IntegerFilter targetCharacterGroupId;

    private IntegerFilter failureConditionType;

    public MQuestStageConditionCriteria(){
    }

    public MQuestStageConditionCriteria(MQuestStageConditionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.successConditionType = other.successConditionType == null ? null : other.successConditionType.copy();
        this.successConditionDetailType = other.successConditionDetailType == null ? null : other.successConditionDetailType.copy();
        this.successConditionValue = other.successConditionValue == null ? null : other.successConditionValue.copy();
        this.targetCharacterGroupId = other.targetCharacterGroupId == null ? null : other.targetCharacterGroupId.copy();
        this.failureConditionType = other.failureConditionType == null ? null : other.failureConditionType.copy();
    }

    @Override
    public MQuestStageConditionCriteria copy() {
        return new MQuestStageConditionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSuccessConditionType() {
        return successConditionType;
    }

    public void setSuccessConditionType(IntegerFilter successConditionType) {
        this.successConditionType = successConditionType;
    }

    public IntegerFilter getSuccessConditionDetailType() {
        return successConditionDetailType;
    }

    public void setSuccessConditionDetailType(IntegerFilter successConditionDetailType) {
        this.successConditionDetailType = successConditionDetailType;
    }

    public IntegerFilter getSuccessConditionValue() {
        return successConditionValue;
    }

    public void setSuccessConditionValue(IntegerFilter successConditionValue) {
        this.successConditionValue = successConditionValue;
    }

    public IntegerFilter getTargetCharacterGroupId() {
        return targetCharacterGroupId;
    }

    public void setTargetCharacterGroupId(IntegerFilter targetCharacterGroupId) {
        this.targetCharacterGroupId = targetCharacterGroupId;
    }

    public IntegerFilter getFailureConditionType() {
        return failureConditionType;
    }

    public void setFailureConditionType(IntegerFilter failureConditionType) {
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
        final MQuestStageConditionCriteria that = (MQuestStageConditionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(successConditionType, that.successConditionType) &&
            Objects.equals(successConditionDetailType, that.successConditionDetailType) &&
            Objects.equals(successConditionValue, that.successConditionValue) &&
            Objects.equals(targetCharacterGroupId, that.targetCharacterGroupId) &&
            Objects.equals(failureConditionType, that.failureConditionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        successConditionType,
        successConditionDetailType,
        successConditionValue,
        targetCharacterGroupId,
        failureConditionType
        );
    }

    @Override
    public String toString() {
        return "MQuestStageConditionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (successConditionType != null ? "successConditionType=" + successConditionType + ", " : "") +
                (successConditionDetailType != null ? "successConditionDetailType=" + successConditionDetailType + ", " : "") +
                (successConditionValue != null ? "successConditionValue=" + successConditionValue + ", " : "") +
                (targetCharacterGroupId != null ? "targetCharacterGroupId=" + targetCharacterGroupId + ", " : "") +
                (failureConditionType != null ? "failureConditionType=" + failureConditionType + ", " : "") +
            "}";
    }

}
