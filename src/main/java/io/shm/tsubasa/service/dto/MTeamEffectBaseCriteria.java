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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTeamEffectBase} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTeamEffectBaseResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-team-effect-bases?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTeamEffectBaseCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter rarity;

    private IntegerFilter effectValueMin;

    private IntegerFilter effectValueMax;

    private IntegerFilter triggerProbabilityMin;

    private IntegerFilter triggerProbabilityMax;

    private IntegerFilter effectId;

    private IntegerFilter effectValueMin2;

    private IntegerFilter effectValueMax2;

    private IntegerFilter triggerProbabilityMin2;

    private IntegerFilter triggerProbabilityMax2;

    private IntegerFilter effectId2;

    private LongFilter mpassiveeffectrangeId;

    public MTeamEffectBaseCriteria(){
    }

    public MTeamEffectBaseCriteria(MTeamEffectBaseCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.rarity = other.rarity == null ? null : other.rarity.copy();
        this.effectValueMin = other.effectValueMin == null ? null : other.effectValueMin.copy();
        this.effectValueMax = other.effectValueMax == null ? null : other.effectValueMax.copy();
        this.triggerProbabilityMin = other.triggerProbabilityMin == null ? null : other.triggerProbabilityMin.copy();
        this.triggerProbabilityMax = other.triggerProbabilityMax == null ? null : other.triggerProbabilityMax.copy();
        this.effectId = other.effectId == null ? null : other.effectId.copy();
        this.effectValueMin2 = other.effectValueMin2 == null ? null : other.effectValueMin2.copy();
        this.effectValueMax2 = other.effectValueMax2 == null ? null : other.effectValueMax2.copy();
        this.triggerProbabilityMin2 = other.triggerProbabilityMin2 == null ? null : other.triggerProbabilityMin2.copy();
        this.triggerProbabilityMax2 = other.triggerProbabilityMax2 == null ? null : other.triggerProbabilityMax2.copy();
        this.effectId2 = other.effectId2 == null ? null : other.effectId2.copy();
        this.mpassiveeffectrangeId = other.mpassiveeffectrangeId == null ? null : other.mpassiveeffectrangeId.copy();
    }

    @Override
    public MTeamEffectBaseCriteria copy() {
        return new MTeamEffectBaseCriteria(this);
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

    public IntegerFilter getEffectValueMin() {
        return effectValueMin;
    }

    public void setEffectValueMin(IntegerFilter effectValueMin) {
        this.effectValueMin = effectValueMin;
    }

    public IntegerFilter getEffectValueMax() {
        return effectValueMax;
    }

    public void setEffectValueMax(IntegerFilter effectValueMax) {
        this.effectValueMax = effectValueMax;
    }

    public IntegerFilter getTriggerProbabilityMin() {
        return triggerProbabilityMin;
    }

    public void setTriggerProbabilityMin(IntegerFilter triggerProbabilityMin) {
        this.triggerProbabilityMin = triggerProbabilityMin;
    }

    public IntegerFilter getTriggerProbabilityMax() {
        return triggerProbabilityMax;
    }

    public void setTriggerProbabilityMax(IntegerFilter triggerProbabilityMax) {
        this.triggerProbabilityMax = triggerProbabilityMax;
    }

    public IntegerFilter getEffectId() {
        return effectId;
    }

    public void setEffectId(IntegerFilter effectId) {
        this.effectId = effectId;
    }

    public IntegerFilter getEffectValueMin2() {
        return effectValueMin2;
    }

    public void setEffectValueMin2(IntegerFilter effectValueMin2) {
        this.effectValueMin2 = effectValueMin2;
    }

    public IntegerFilter getEffectValueMax2() {
        return effectValueMax2;
    }

    public void setEffectValueMax2(IntegerFilter effectValueMax2) {
        this.effectValueMax2 = effectValueMax2;
    }

    public IntegerFilter getTriggerProbabilityMin2() {
        return triggerProbabilityMin2;
    }

    public void setTriggerProbabilityMin2(IntegerFilter triggerProbabilityMin2) {
        this.triggerProbabilityMin2 = triggerProbabilityMin2;
    }

    public IntegerFilter getTriggerProbabilityMax2() {
        return triggerProbabilityMax2;
    }

    public void setTriggerProbabilityMax2(IntegerFilter triggerProbabilityMax2) {
        this.triggerProbabilityMax2 = triggerProbabilityMax2;
    }

    public IntegerFilter getEffectId2() {
        return effectId2;
    }

    public void setEffectId2(IntegerFilter effectId2) {
        this.effectId2 = effectId2;
    }

    public LongFilter getMpassiveeffectrangeId() {
        return mpassiveeffectrangeId;
    }

    public void setMpassiveeffectrangeId(LongFilter mpassiveeffectrangeId) {
        this.mpassiveeffectrangeId = mpassiveeffectrangeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTeamEffectBaseCriteria that = (MTeamEffectBaseCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(rarity, that.rarity) &&
            Objects.equals(effectValueMin, that.effectValueMin) &&
            Objects.equals(effectValueMax, that.effectValueMax) &&
            Objects.equals(triggerProbabilityMin, that.triggerProbabilityMin) &&
            Objects.equals(triggerProbabilityMax, that.triggerProbabilityMax) &&
            Objects.equals(effectId, that.effectId) &&
            Objects.equals(effectValueMin2, that.effectValueMin2) &&
            Objects.equals(effectValueMax2, that.effectValueMax2) &&
            Objects.equals(triggerProbabilityMin2, that.triggerProbabilityMin2) &&
            Objects.equals(triggerProbabilityMax2, that.triggerProbabilityMax2) &&
            Objects.equals(effectId2, that.effectId2) &&
            Objects.equals(mpassiveeffectrangeId, that.mpassiveeffectrangeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        rarity,
        effectValueMin,
        effectValueMax,
        triggerProbabilityMin,
        triggerProbabilityMax,
        effectId,
        effectValueMin2,
        effectValueMax2,
        triggerProbabilityMin2,
        triggerProbabilityMax2,
        effectId2,
        mpassiveeffectrangeId
        );
    }

    @Override
    public String toString() {
        return "MTeamEffectBaseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (rarity != null ? "rarity=" + rarity + ", " : "") +
                (effectValueMin != null ? "effectValueMin=" + effectValueMin + ", " : "") +
                (effectValueMax != null ? "effectValueMax=" + effectValueMax + ", " : "") +
                (triggerProbabilityMin != null ? "triggerProbabilityMin=" + triggerProbabilityMin + ", " : "") +
                (triggerProbabilityMax != null ? "triggerProbabilityMax=" + triggerProbabilityMax + ", " : "") +
                (effectId != null ? "effectId=" + effectId + ", " : "") +
                (effectValueMin2 != null ? "effectValueMin2=" + effectValueMin2 + ", " : "") +
                (effectValueMax2 != null ? "effectValueMax2=" + effectValueMax2 + ", " : "") +
                (triggerProbabilityMin2 != null ? "triggerProbabilityMin2=" + triggerProbabilityMin2 + ", " : "") +
                (triggerProbabilityMax2 != null ? "triggerProbabilityMax2=" + triggerProbabilityMax2 + ", " : "") +
                (effectId2 != null ? "effectId2=" + effectId2 + ", " : "") +
                (mpassiveeffectrangeId != null ? "mpassiveeffectrangeId=" + mpassiveeffectrangeId + ", " : "") +
            "}";
    }

}
