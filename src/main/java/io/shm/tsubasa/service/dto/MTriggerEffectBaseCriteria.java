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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTriggerEffectBase} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTriggerEffectBaseResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-trigger-effect-bases?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTriggerEffectBaseCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter rarity;

    private IntegerFilter effectValue;

    private IntegerFilter triggerProbability;

    private IntegerFilter targetCardParameter;

    private IntegerFilter effectId;

    private LongFilter idId;

    private LongFilter mTargetTriggerEffectGroupId;

    public MTriggerEffectBaseCriteria(){
    }

    public MTriggerEffectBaseCriteria(MTriggerEffectBaseCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.rarity = other.rarity == null ? null : other.rarity.copy();
        this.effectValue = other.effectValue == null ? null : other.effectValue.copy();
        this.triggerProbability = other.triggerProbability == null ? null : other.triggerProbability.copy();
        this.targetCardParameter = other.targetCardParameter == null ? null : other.targetCardParameter.copy();
        this.effectId = other.effectId == null ? null : other.effectId.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
        this.mTargetTriggerEffectGroupId = other.mTargetTriggerEffectGroupId == null ? null : other.mTargetTriggerEffectGroupId.copy();
    }

    @Override
    public MTriggerEffectBaseCriteria copy() {
        return new MTriggerEffectBaseCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRarity() {
        return rarity;
    }

    public void setRarity(IntegerFilter rarity) {
        this.rarity = rarity;
    }

    public IntegerFilter getEffectValue() {
        return effectValue;
    }

    public void setEffectValue(IntegerFilter effectValue) {
        this.effectValue = effectValue;
    }

    public IntegerFilter getTriggerProbability() {
        return triggerProbability;
    }

    public void setTriggerProbability(IntegerFilter triggerProbability) {
        this.triggerProbability = triggerProbability;
    }

    public IntegerFilter getTargetCardParameter() {
        return targetCardParameter;
    }

    public void setTargetCardParameter(IntegerFilter targetCardParameter) {
        this.targetCardParameter = targetCardParameter;
    }

    public IntegerFilter getEffectId() {
        return effectId;
    }

    public void setEffectId(IntegerFilter effectId) {
        this.effectId = effectId;
    }

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }

    public LongFilter getMTargetTriggerEffectGroupId() {
        return mTargetTriggerEffectGroupId;
    }

    public void setMTargetTriggerEffectGroupId(LongFilter mTargetTriggerEffectGroupId) {
        this.mTargetTriggerEffectGroupId = mTargetTriggerEffectGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTriggerEffectBaseCriteria that = (MTriggerEffectBaseCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(rarity, that.rarity) &&
            Objects.equals(effectValue, that.effectValue) &&
            Objects.equals(triggerProbability, that.triggerProbability) &&
            Objects.equals(targetCardParameter, that.targetCardParameter) &&
            Objects.equals(effectId, that.effectId) &&
            Objects.equals(idId, that.idId) &&
            Objects.equals(mTargetTriggerEffectGroupId, that.mTargetTriggerEffectGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        rarity,
        effectValue,
        triggerProbability,
        targetCardParameter,
        effectId,
        idId,
        mTargetTriggerEffectGroupId
        );
    }

    @Override
    public String toString() {
        return "MTriggerEffectBaseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (rarity != null ? "rarity=" + rarity + ", " : "") +
                (effectValue != null ? "effectValue=" + effectValue + ", " : "") +
                (triggerProbability != null ? "triggerProbability=" + triggerProbability + ", " : "") +
                (targetCardParameter != null ? "targetCardParameter=" + targetCardParameter + ", " : "") +
                (effectId != null ? "effectId=" + effectId + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
                (mTargetTriggerEffectGroupId != null ? "mTargetTriggerEffectGroupId=" + mTargetTriggerEffectGroupId + ", " : "") +
            "}";
    }

}
