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
 * Criteria class for the {@link io.shm.tsubasa.domain.MFormation} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MFormationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-formations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MFormationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter effectValue;

    private IntegerFilter effectProbability;

    private IntegerFilter effectId;

    private IntegerFilter exType;

    private IntegerFilter matchFormationId;

    private LongFilter idId;

    private LongFilter mNpcDeckId;

    private LongFilter mTargetFormationGroupId;

    public MFormationCriteria(){
    }

    public MFormationCriteria(MFormationCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.effectValue = other.effectValue == null ? null : other.effectValue.copy();
        this.effectProbability = other.effectProbability == null ? null : other.effectProbability.copy();
        this.effectId = other.effectId == null ? null : other.effectId.copy();
        this.exType = other.exType == null ? null : other.exType.copy();
        this.matchFormationId = other.matchFormationId == null ? null : other.matchFormationId.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
        this.mNpcDeckId = other.mNpcDeckId == null ? null : other.mNpcDeckId.copy();
        this.mTargetFormationGroupId = other.mTargetFormationGroupId == null ? null : other.mTargetFormationGroupId.copy();
    }

    @Override
    public MFormationCriteria copy() {
        return new MFormationCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getEffectValue() {
        return effectValue;
    }

    public void setEffectValue(IntegerFilter effectValue) {
        this.effectValue = effectValue;
    }

    public IntegerFilter getEffectProbability() {
        return effectProbability;
    }

    public void setEffectProbability(IntegerFilter effectProbability) {
        this.effectProbability = effectProbability;
    }

    public IntegerFilter getEffectId() {
        return effectId;
    }

    public void setEffectId(IntegerFilter effectId) {
        this.effectId = effectId;
    }

    public IntegerFilter getExType() {
        return exType;
    }

    public void setExType(IntegerFilter exType) {
        this.exType = exType;
    }

    public IntegerFilter getMatchFormationId() {
        return matchFormationId;
    }

    public void setMatchFormationId(IntegerFilter matchFormationId) {
        this.matchFormationId = matchFormationId;
    }

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }

    public LongFilter getMNpcDeckId() {
        return mNpcDeckId;
    }

    public void setMNpcDeckId(LongFilter mNpcDeckId) {
        this.mNpcDeckId = mNpcDeckId;
    }

    public LongFilter getMTargetFormationGroupId() {
        return mTargetFormationGroupId;
    }

    public void setMTargetFormationGroupId(LongFilter mTargetFormationGroupId) {
        this.mTargetFormationGroupId = mTargetFormationGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MFormationCriteria that = (MFormationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(effectValue, that.effectValue) &&
            Objects.equals(effectProbability, that.effectProbability) &&
            Objects.equals(effectId, that.effectId) &&
            Objects.equals(exType, that.exType) &&
            Objects.equals(matchFormationId, that.matchFormationId) &&
            Objects.equals(idId, that.idId) &&
            Objects.equals(mNpcDeckId, that.mNpcDeckId) &&
            Objects.equals(mTargetFormationGroupId, that.mTargetFormationGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        effectValue,
        effectProbability,
        effectId,
        exType,
        matchFormationId,
        idId,
        mNpcDeckId,
        mTargetFormationGroupId
        );
    }

    @Override
    public String toString() {
        return "MFormationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (effectValue != null ? "effectValue=" + effectValue + ", " : "") +
                (effectProbability != null ? "effectProbability=" + effectProbability + ", " : "") +
                (effectId != null ? "effectId=" + effectId + ", " : "") +
                (exType != null ? "exType=" + exType + ", " : "") +
                (matchFormationId != null ? "matchFormationId=" + matchFormationId + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
                (mNpcDeckId != null ? "mNpcDeckId=" + mNpcDeckId + ", " : "") +
                (mTargetFormationGroupId != null ? "mTargetFormationGroupId=" + mTargetFormationGroupId + ", " : "") +
            "}";
    }

}
