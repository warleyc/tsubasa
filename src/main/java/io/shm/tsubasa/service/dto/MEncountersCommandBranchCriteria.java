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
 * Criteria class for the {@link io.shm.tsubasa.domain.MEncountersCommandBranch} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MEncountersCommandBranchResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-encounters-command-branches?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MEncountersCommandBranchCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter ballFloatCondition;

    private IntegerFilter condition;

    private IntegerFilter encountersType;

    private IntegerFilter isSuccess;

    private IntegerFilter commandType;

    private IntegerFilter successRate;

    private IntegerFilter looseBallRate;

    private IntegerFilter touchLightlyRate;

    private IntegerFilter postRate;

    public MEncountersCommandBranchCriteria(){
    }

    public MEncountersCommandBranchCriteria(MEncountersCommandBranchCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ballFloatCondition = other.ballFloatCondition == null ? null : other.ballFloatCondition.copy();
        this.condition = other.condition == null ? null : other.condition.copy();
        this.encountersType = other.encountersType == null ? null : other.encountersType.copy();
        this.isSuccess = other.isSuccess == null ? null : other.isSuccess.copy();
        this.commandType = other.commandType == null ? null : other.commandType.copy();
        this.successRate = other.successRate == null ? null : other.successRate.copy();
        this.looseBallRate = other.looseBallRate == null ? null : other.looseBallRate.copy();
        this.touchLightlyRate = other.touchLightlyRate == null ? null : other.touchLightlyRate.copy();
        this.postRate = other.postRate == null ? null : other.postRate.copy();
    }

    @Override
    public MEncountersCommandBranchCriteria copy() {
        return new MEncountersCommandBranchCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getBallFloatCondition() {
        return ballFloatCondition;
    }

    public void setBallFloatCondition(IntegerFilter ballFloatCondition) {
        this.ballFloatCondition = ballFloatCondition;
    }

    public IntegerFilter getCondition() {
        return condition;
    }

    public void setCondition(IntegerFilter condition) {
        this.condition = condition;
    }

    public IntegerFilter getEncountersType() {
        return encountersType;
    }

    public void setEncountersType(IntegerFilter encountersType) {
        this.encountersType = encountersType;
    }

    public IntegerFilter getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(IntegerFilter isSuccess) {
        this.isSuccess = isSuccess;
    }

    public IntegerFilter getCommandType() {
        return commandType;
    }

    public void setCommandType(IntegerFilter commandType) {
        this.commandType = commandType;
    }

    public IntegerFilter getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(IntegerFilter successRate) {
        this.successRate = successRate;
    }

    public IntegerFilter getLooseBallRate() {
        return looseBallRate;
    }

    public void setLooseBallRate(IntegerFilter looseBallRate) {
        this.looseBallRate = looseBallRate;
    }

    public IntegerFilter getTouchLightlyRate() {
        return touchLightlyRate;
    }

    public void setTouchLightlyRate(IntegerFilter touchLightlyRate) {
        this.touchLightlyRate = touchLightlyRate;
    }

    public IntegerFilter getPostRate() {
        return postRate;
    }

    public void setPostRate(IntegerFilter postRate) {
        this.postRate = postRate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MEncountersCommandBranchCriteria that = (MEncountersCommandBranchCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ballFloatCondition, that.ballFloatCondition) &&
            Objects.equals(condition, that.condition) &&
            Objects.equals(encountersType, that.encountersType) &&
            Objects.equals(isSuccess, that.isSuccess) &&
            Objects.equals(commandType, that.commandType) &&
            Objects.equals(successRate, that.successRate) &&
            Objects.equals(looseBallRate, that.looseBallRate) &&
            Objects.equals(touchLightlyRate, that.touchLightlyRate) &&
            Objects.equals(postRate, that.postRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ballFloatCondition,
        condition,
        encountersType,
        isSuccess,
        commandType,
        successRate,
        looseBallRate,
        touchLightlyRate,
        postRate
        );
    }

    @Override
    public String toString() {
        return "MEncountersCommandBranchCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ballFloatCondition != null ? "ballFloatCondition=" + ballFloatCondition + ", " : "") +
                (condition != null ? "condition=" + condition + ", " : "") +
                (encountersType != null ? "encountersType=" + encountersType + ", " : "") +
                (isSuccess != null ? "isSuccess=" + isSuccess + ", " : "") +
                (commandType != null ? "commandType=" + commandType + ", " : "") +
                (successRate != null ? "successRate=" + successRate + ", " : "") +
                (looseBallRate != null ? "looseBallRate=" + looseBallRate + ", " : "") +
                (touchLightlyRate != null ? "touchLightlyRate=" + touchLightlyRate + ", " : "") +
                (postRate != null ? "postRate=" + postRate + ", " : "") +
            "}";
    }

}
