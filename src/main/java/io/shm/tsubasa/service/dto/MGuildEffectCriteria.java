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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGuildEffect} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGuildEffectResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-guild-effects?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGuildEffectCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter effectType;

    private IntegerFilter effectId;

    public MGuildEffectCriteria(){
    }

    public MGuildEffectCriteria(MGuildEffectCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.effectType = other.effectType == null ? null : other.effectType.copy();
        this.effectId = other.effectId == null ? null : other.effectId.copy();
    }

    @Override
    public MGuildEffectCriteria copy() {
        return new MGuildEffectCriteria(this);
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

    public IntegerFilter getEffectId() {
        return effectId;
    }

    public void setEffectId(IntegerFilter effectId) {
        this.effectId = effectId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGuildEffectCriteria that = (MGuildEffectCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(effectType, that.effectType) &&
            Objects.equals(effectId, that.effectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        effectType,
        effectId
        );
    }

    @Override
    public String toString() {
        return "MGuildEffectCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (effectType != null ? "effectType=" + effectType + ", " : "") +
                (effectId != null ? "effectId=" + effectId + ", " : "") +
            "}";
    }

}
