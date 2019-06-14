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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGoalJudgement} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGoalJudgementResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-goal-judgements?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGoalJudgementCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter judgementId;

    private IntegerFilter encountersType;

    private IntegerFilter successRate;

    private IntegerFilter goalPostRate;

    private IntegerFilter ballPushRate;

    private IntegerFilter clearRate;

    public MGoalJudgementCriteria(){
    }

    public MGoalJudgementCriteria(MGoalJudgementCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.judgementId = other.judgementId == null ? null : other.judgementId.copy();
        this.encountersType = other.encountersType == null ? null : other.encountersType.copy();
        this.successRate = other.successRate == null ? null : other.successRate.copy();
        this.goalPostRate = other.goalPostRate == null ? null : other.goalPostRate.copy();
        this.ballPushRate = other.ballPushRate == null ? null : other.ballPushRate.copy();
        this.clearRate = other.clearRate == null ? null : other.clearRate.copy();
    }

    @Override
    public MGoalJudgementCriteria copy() {
        return new MGoalJudgementCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getJudgementId() {
        return judgementId;
    }

    public void setJudgementId(IntegerFilter judgementId) {
        this.judgementId = judgementId;
    }

    public IntegerFilter getEncountersType() {
        return encountersType;
    }

    public void setEncountersType(IntegerFilter encountersType) {
        this.encountersType = encountersType;
    }

    public IntegerFilter getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(IntegerFilter successRate) {
        this.successRate = successRate;
    }

    public IntegerFilter getGoalPostRate() {
        return goalPostRate;
    }

    public void setGoalPostRate(IntegerFilter goalPostRate) {
        this.goalPostRate = goalPostRate;
    }

    public IntegerFilter getBallPushRate() {
        return ballPushRate;
    }

    public void setBallPushRate(IntegerFilter ballPushRate) {
        this.ballPushRate = ballPushRate;
    }

    public IntegerFilter getClearRate() {
        return clearRate;
    }

    public void setClearRate(IntegerFilter clearRate) {
        this.clearRate = clearRate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGoalJudgementCriteria that = (MGoalJudgementCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(judgementId, that.judgementId) &&
            Objects.equals(encountersType, that.encountersType) &&
            Objects.equals(successRate, that.successRate) &&
            Objects.equals(goalPostRate, that.goalPostRate) &&
            Objects.equals(ballPushRate, that.ballPushRate) &&
            Objects.equals(clearRate, that.clearRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        judgementId,
        encountersType,
        successRate,
        goalPostRate,
        ballPushRate,
        clearRate
        );
    }

    @Override
    public String toString() {
        return "MGoalJudgementCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (judgementId != null ? "judgementId=" + judgementId + ", " : "") +
                (encountersType != null ? "encountersType=" + encountersType + ", " : "") +
                (successRate != null ? "successRate=" + successRate + ", " : "") +
                (goalPostRate != null ? "goalPostRate=" + goalPostRate + ", " : "") +
                (ballPushRate != null ? "ballPushRate=" + ballPushRate + ", " : "") +
                (clearRate != null ? "clearRate=" + clearRate + ", " : "") +
            "}";
    }

}
