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
 * Criteria class for the {@link io.shm.tsubasa.domain.MQuestStageConditionDescription} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MQuestStageConditionDescriptionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-quest-stage-condition-descriptions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MQuestStageConditionDescriptionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter successConditionType;

    private IntegerFilter successConditionDetailTypeValue;

    private IntegerFilter hasExistTargetCharacterGroup;

    private IntegerFilter hasSuccessConditionValueOneOnly;

    private IntegerFilter failureConditionTypeValue;

    public MQuestStageConditionDescriptionCriteria(){
    }

    public MQuestStageConditionDescriptionCriteria(MQuestStageConditionDescriptionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.successConditionType = other.successConditionType == null ? null : other.successConditionType.copy();
        this.successConditionDetailTypeValue = other.successConditionDetailTypeValue == null ? null : other.successConditionDetailTypeValue.copy();
        this.hasExistTargetCharacterGroup = other.hasExistTargetCharacterGroup == null ? null : other.hasExistTargetCharacterGroup.copy();
        this.hasSuccessConditionValueOneOnly = other.hasSuccessConditionValueOneOnly == null ? null : other.hasSuccessConditionValueOneOnly.copy();
        this.failureConditionTypeValue = other.failureConditionTypeValue == null ? null : other.failureConditionTypeValue.copy();
    }

    @Override
    public MQuestStageConditionDescriptionCriteria copy() {
        return new MQuestStageConditionDescriptionCriteria(this);
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

    public IntegerFilter getSuccessConditionDetailTypeValue() {
        return successConditionDetailTypeValue;
    }

    public void setSuccessConditionDetailTypeValue(IntegerFilter successConditionDetailTypeValue) {
        this.successConditionDetailTypeValue = successConditionDetailTypeValue;
    }

    public IntegerFilter getHasExistTargetCharacterGroup() {
        return hasExistTargetCharacterGroup;
    }

    public void setHasExistTargetCharacterGroup(IntegerFilter hasExistTargetCharacterGroup) {
        this.hasExistTargetCharacterGroup = hasExistTargetCharacterGroup;
    }

    public IntegerFilter getHasSuccessConditionValueOneOnly() {
        return hasSuccessConditionValueOneOnly;
    }

    public void setHasSuccessConditionValueOneOnly(IntegerFilter hasSuccessConditionValueOneOnly) {
        this.hasSuccessConditionValueOneOnly = hasSuccessConditionValueOneOnly;
    }

    public IntegerFilter getFailureConditionTypeValue() {
        return failureConditionTypeValue;
    }

    public void setFailureConditionTypeValue(IntegerFilter failureConditionTypeValue) {
        this.failureConditionTypeValue = failureConditionTypeValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MQuestStageConditionDescriptionCriteria that = (MQuestStageConditionDescriptionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(successConditionType, that.successConditionType) &&
            Objects.equals(successConditionDetailTypeValue, that.successConditionDetailTypeValue) &&
            Objects.equals(hasExistTargetCharacterGroup, that.hasExistTargetCharacterGroup) &&
            Objects.equals(hasSuccessConditionValueOneOnly, that.hasSuccessConditionValueOneOnly) &&
            Objects.equals(failureConditionTypeValue, that.failureConditionTypeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        successConditionType,
        successConditionDetailTypeValue,
        hasExistTargetCharacterGroup,
        hasSuccessConditionValueOneOnly,
        failureConditionTypeValue
        );
    }

    @Override
    public String toString() {
        return "MQuestStageConditionDescriptionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (successConditionType != null ? "successConditionType=" + successConditionType + ", " : "") +
                (successConditionDetailTypeValue != null ? "successConditionDetailTypeValue=" + successConditionDetailTypeValue + ", " : "") +
                (hasExistTargetCharacterGroup != null ? "hasExistTargetCharacterGroup=" + hasExistTargetCharacterGroup + ", " : "") +
                (hasSuccessConditionValueOneOnly != null ? "hasSuccessConditionValueOneOnly=" + hasSuccessConditionValueOneOnly + ", " : "") +
                (failureConditionTypeValue != null ? "failureConditionTypeValue=" + failureConditionTypeValue + ", " : "") +
            "}";
    }

}
