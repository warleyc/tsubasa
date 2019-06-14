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
 * Criteria class for the {@link io.shm.tsubasa.domain.MPassiveEffectRange} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MPassiveEffectRangeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-passive-effect-ranges?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MPassiveEffectRangeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter effectParamType;

    private IntegerFilter targetPositionGk;

    private IntegerFilter targetPositionFw;

    private IntegerFilter targetPositionOmf;

    private IntegerFilter targetPositionDmf;

    private IntegerFilter targetPositionDf;

    private IntegerFilter targetAttribute;

    private IntegerFilter targetNationalityGroupId;

    private IntegerFilter targetTeamGroupId;

    private IntegerFilter targetPlayableCardGroupId;

    private LongFilter mFormationId;

    private LongFilter mMatchOptionId;

    private LongFilter mTeamEffectBaseId;

    public MPassiveEffectRangeCriteria(){
    }

    public MPassiveEffectRangeCriteria(MPassiveEffectRangeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.effectParamType = other.effectParamType == null ? null : other.effectParamType.copy();
        this.targetPositionGk = other.targetPositionGk == null ? null : other.targetPositionGk.copy();
        this.targetPositionFw = other.targetPositionFw == null ? null : other.targetPositionFw.copy();
        this.targetPositionOmf = other.targetPositionOmf == null ? null : other.targetPositionOmf.copy();
        this.targetPositionDmf = other.targetPositionDmf == null ? null : other.targetPositionDmf.copy();
        this.targetPositionDf = other.targetPositionDf == null ? null : other.targetPositionDf.copy();
        this.targetAttribute = other.targetAttribute == null ? null : other.targetAttribute.copy();
        this.targetNationalityGroupId = other.targetNationalityGroupId == null ? null : other.targetNationalityGroupId.copy();
        this.targetTeamGroupId = other.targetTeamGroupId == null ? null : other.targetTeamGroupId.copy();
        this.targetPlayableCardGroupId = other.targetPlayableCardGroupId == null ? null : other.targetPlayableCardGroupId.copy();
        this.mFormationId = other.mFormationId == null ? null : other.mFormationId.copy();
        this.mMatchOptionId = other.mMatchOptionId == null ? null : other.mMatchOptionId.copy();
        this.mTeamEffectBaseId = other.mTeamEffectBaseId == null ? null : other.mTeamEffectBaseId.copy();
    }

    @Override
    public MPassiveEffectRangeCriteria copy() {
        return new MPassiveEffectRangeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getEffectParamType() {
        return effectParamType;
    }

    public void setEffectParamType(IntegerFilter effectParamType) {
        this.effectParamType = effectParamType;
    }

    public IntegerFilter getTargetPositionGk() {
        return targetPositionGk;
    }

    public void setTargetPositionGk(IntegerFilter targetPositionGk) {
        this.targetPositionGk = targetPositionGk;
    }

    public IntegerFilter getTargetPositionFw() {
        return targetPositionFw;
    }

    public void setTargetPositionFw(IntegerFilter targetPositionFw) {
        this.targetPositionFw = targetPositionFw;
    }

    public IntegerFilter getTargetPositionOmf() {
        return targetPositionOmf;
    }

    public void setTargetPositionOmf(IntegerFilter targetPositionOmf) {
        this.targetPositionOmf = targetPositionOmf;
    }

    public IntegerFilter getTargetPositionDmf() {
        return targetPositionDmf;
    }

    public void setTargetPositionDmf(IntegerFilter targetPositionDmf) {
        this.targetPositionDmf = targetPositionDmf;
    }

    public IntegerFilter getTargetPositionDf() {
        return targetPositionDf;
    }

    public void setTargetPositionDf(IntegerFilter targetPositionDf) {
        this.targetPositionDf = targetPositionDf;
    }

    public IntegerFilter getTargetAttribute() {
        return targetAttribute;
    }

    public void setTargetAttribute(IntegerFilter targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public IntegerFilter getTargetNationalityGroupId() {
        return targetNationalityGroupId;
    }

    public void setTargetNationalityGroupId(IntegerFilter targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
    }

    public IntegerFilter getTargetTeamGroupId() {
        return targetTeamGroupId;
    }

    public void setTargetTeamGroupId(IntegerFilter targetTeamGroupId) {
        this.targetTeamGroupId = targetTeamGroupId;
    }

    public IntegerFilter getTargetPlayableCardGroupId() {
        return targetPlayableCardGroupId;
    }

    public void setTargetPlayableCardGroupId(IntegerFilter targetPlayableCardGroupId) {
        this.targetPlayableCardGroupId = targetPlayableCardGroupId;
    }

    public LongFilter getMFormationId() {
        return mFormationId;
    }

    public void setMFormationId(LongFilter mFormationId) {
        this.mFormationId = mFormationId;
    }

    public LongFilter getMMatchOptionId() {
        return mMatchOptionId;
    }

    public void setMMatchOptionId(LongFilter mMatchOptionId) {
        this.mMatchOptionId = mMatchOptionId;
    }

    public LongFilter getMTeamEffectBaseId() {
        return mTeamEffectBaseId;
    }

    public void setMTeamEffectBaseId(LongFilter mTeamEffectBaseId) {
        this.mTeamEffectBaseId = mTeamEffectBaseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MPassiveEffectRangeCriteria that = (MPassiveEffectRangeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(effectParamType, that.effectParamType) &&
            Objects.equals(targetPositionGk, that.targetPositionGk) &&
            Objects.equals(targetPositionFw, that.targetPositionFw) &&
            Objects.equals(targetPositionOmf, that.targetPositionOmf) &&
            Objects.equals(targetPositionDmf, that.targetPositionDmf) &&
            Objects.equals(targetPositionDf, that.targetPositionDf) &&
            Objects.equals(targetAttribute, that.targetAttribute) &&
            Objects.equals(targetNationalityGroupId, that.targetNationalityGroupId) &&
            Objects.equals(targetTeamGroupId, that.targetTeamGroupId) &&
            Objects.equals(targetPlayableCardGroupId, that.targetPlayableCardGroupId) &&
            Objects.equals(mFormationId, that.mFormationId) &&
            Objects.equals(mMatchOptionId, that.mMatchOptionId) &&
            Objects.equals(mTeamEffectBaseId, that.mTeamEffectBaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        effectParamType,
        targetPositionGk,
        targetPositionFw,
        targetPositionOmf,
        targetPositionDmf,
        targetPositionDf,
        targetAttribute,
        targetNationalityGroupId,
        targetTeamGroupId,
        targetPlayableCardGroupId,
        mFormationId,
        mMatchOptionId,
        mTeamEffectBaseId
        );
    }

    @Override
    public String toString() {
        return "MPassiveEffectRangeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (effectParamType != null ? "effectParamType=" + effectParamType + ", " : "") +
                (targetPositionGk != null ? "targetPositionGk=" + targetPositionGk + ", " : "") +
                (targetPositionFw != null ? "targetPositionFw=" + targetPositionFw + ", " : "") +
                (targetPositionOmf != null ? "targetPositionOmf=" + targetPositionOmf + ", " : "") +
                (targetPositionDmf != null ? "targetPositionDmf=" + targetPositionDmf + ", " : "") +
                (targetPositionDf != null ? "targetPositionDf=" + targetPositionDf + ", " : "") +
                (targetAttribute != null ? "targetAttribute=" + targetAttribute + ", " : "") +
                (targetNationalityGroupId != null ? "targetNationalityGroupId=" + targetNationalityGroupId + ", " : "") +
                (targetTeamGroupId != null ? "targetTeamGroupId=" + targetTeamGroupId + ", " : "") +
                (targetPlayableCardGroupId != null ? "targetPlayableCardGroupId=" + targetPlayableCardGroupId + ", " : "") +
                (mFormationId != null ? "mFormationId=" + mFormationId + ", " : "") +
                (mMatchOptionId != null ? "mMatchOptionId=" + mMatchOptionId + ", " : "") +
                (mTeamEffectBaseId != null ? "mTeamEffectBaseId=" + mTeamEffectBaseId + ", " : "") +
            "}";
    }

}
