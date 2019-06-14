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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTriggerEffectRange} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTriggerEffectRangeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-trigger-effect-ranges?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTriggerEffectRangeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter effectType;

    private IntegerFilter targetPositionGk;

    private IntegerFilter targetPositionFw;

    private IntegerFilter targetPositionOmf;

    private IntegerFilter targetPositionDmf;

    private IntegerFilter targetPositionDf;

    private IntegerFilter targetAttribute;

    private IntegerFilter targetNationalityGroupId;

    private IntegerFilter targetTeamGroupId;

    private IntegerFilter targetCharacterGroupId;

    private IntegerFilter targetFormationGroupId;

    private IntegerFilter targetActionCommand;

    private LongFilter mTriggerEffectBaseId;

    public MTriggerEffectRangeCriteria(){
    }

    public MTriggerEffectRangeCriteria(MTriggerEffectRangeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.effectType = other.effectType == null ? null : other.effectType.copy();
        this.targetPositionGk = other.targetPositionGk == null ? null : other.targetPositionGk.copy();
        this.targetPositionFw = other.targetPositionFw == null ? null : other.targetPositionFw.copy();
        this.targetPositionOmf = other.targetPositionOmf == null ? null : other.targetPositionOmf.copy();
        this.targetPositionDmf = other.targetPositionDmf == null ? null : other.targetPositionDmf.copy();
        this.targetPositionDf = other.targetPositionDf == null ? null : other.targetPositionDf.copy();
        this.targetAttribute = other.targetAttribute == null ? null : other.targetAttribute.copy();
        this.targetNationalityGroupId = other.targetNationalityGroupId == null ? null : other.targetNationalityGroupId.copy();
        this.targetTeamGroupId = other.targetTeamGroupId == null ? null : other.targetTeamGroupId.copy();
        this.targetCharacterGroupId = other.targetCharacterGroupId == null ? null : other.targetCharacterGroupId.copy();
        this.targetFormationGroupId = other.targetFormationGroupId == null ? null : other.targetFormationGroupId.copy();
        this.targetActionCommand = other.targetActionCommand == null ? null : other.targetActionCommand.copy();
        this.mTriggerEffectBaseId = other.mTriggerEffectBaseId == null ? null : other.mTriggerEffectBaseId.copy();
    }

    @Override
    public MTriggerEffectRangeCriteria copy() {
        return new MTriggerEffectRangeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getEffectType() {
        return effectType;
    }

    public void setEffectType(IntegerFilter effectType) {
        this.effectType = effectType;
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

    public IntegerFilter getTargetCharacterGroupId() {
        return targetCharacterGroupId;
    }

    public void setTargetCharacterGroupId(IntegerFilter targetCharacterGroupId) {
        this.targetCharacterGroupId = targetCharacterGroupId;
    }

    public IntegerFilter getTargetFormationGroupId() {
        return targetFormationGroupId;
    }

    public void setTargetFormationGroupId(IntegerFilter targetFormationGroupId) {
        this.targetFormationGroupId = targetFormationGroupId;
    }

    public IntegerFilter getTargetActionCommand() {
        return targetActionCommand;
    }

    public void setTargetActionCommand(IntegerFilter targetActionCommand) {
        this.targetActionCommand = targetActionCommand;
    }

    public LongFilter getMTriggerEffectBaseId() {
        return mTriggerEffectBaseId;
    }

    public void setMTriggerEffectBaseId(LongFilter mTriggerEffectBaseId) {
        this.mTriggerEffectBaseId = mTriggerEffectBaseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTriggerEffectRangeCriteria that = (MTriggerEffectRangeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(effectType, that.effectType) &&
            Objects.equals(targetPositionGk, that.targetPositionGk) &&
            Objects.equals(targetPositionFw, that.targetPositionFw) &&
            Objects.equals(targetPositionOmf, that.targetPositionOmf) &&
            Objects.equals(targetPositionDmf, that.targetPositionDmf) &&
            Objects.equals(targetPositionDf, that.targetPositionDf) &&
            Objects.equals(targetAttribute, that.targetAttribute) &&
            Objects.equals(targetNationalityGroupId, that.targetNationalityGroupId) &&
            Objects.equals(targetTeamGroupId, that.targetTeamGroupId) &&
            Objects.equals(targetCharacterGroupId, that.targetCharacterGroupId) &&
            Objects.equals(targetFormationGroupId, that.targetFormationGroupId) &&
            Objects.equals(targetActionCommand, that.targetActionCommand) &&
            Objects.equals(mTriggerEffectBaseId, that.mTriggerEffectBaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        effectType,
        targetPositionGk,
        targetPositionFw,
        targetPositionOmf,
        targetPositionDmf,
        targetPositionDf,
        targetAttribute,
        targetNationalityGroupId,
        targetTeamGroupId,
        targetCharacterGroupId,
        targetFormationGroupId,
        targetActionCommand,
        mTriggerEffectBaseId
        );
    }

    @Override
    public String toString() {
        return "MTriggerEffectRangeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (effectType != null ? "effectType=" + effectType + ", " : "") +
                (targetPositionGk != null ? "targetPositionGk=" + targetPositionGk + ", " : "") +
                (targetPositionFw != null ? "targetPositionFw=" + targetPositionFw + ", " : "") +
                (targetPositionOmf != null ? "targetPositionOmf=" + targetPositionOmf + ", " : "") +
                (targetPositionDmf != null ? "targetPositionDmf=" + targetPositionDmf + ", " : "") +
                (targetPositionDf != null ? "targetPositionDf=" + targetPositionDf + ", " : "") +
                (targetAttribute != null ? "targetAttribute=" + targetAttribute + ", " : "") +
                (targetNationalityGroupId != null ? "targetNationalityGroupId=" + targetNationalityGroupId + ", " : "") +
                (targetTeamGroupId != null ? "targetTeamGroupId=" + targetTeamGroupId + ", " : "") +
                (targetCharacterGroupId != null ? "targetCharacterGroupId=" + targetCharacterGroupId + ", " : "") +
                (targetFormationGroupId != null ? "targetFormationGroupId=" + targetFormationGroupId + ", " : "") +
                (targetActionCommand != null ? "targetActionCommand=" + targetActionCommand + ", " : "") +
                (mTriggerEffectBaseId != null ? "mTriggerEffectBaseId=" + mTriggerEffectBaseId + ", " : "") +
            "}";
    }

}
