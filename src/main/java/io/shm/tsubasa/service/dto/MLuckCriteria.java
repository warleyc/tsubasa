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
 * Criteria class for the {@link io.shm.tsubasa.domain.MLuck} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MLuckResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-lucks?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MLuckCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter targetAttribute;

    private IntegerFilter targetCharacterGroupId;

    private IntegerFilter targetTeamGroupId;

    private IntegerFilter targetNationalityGroupId;

    private IntegerFilter luckRateGroupId;

    public MLuckCriteria(){
    }

    public MLuckCriteria(MLuckCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.targetAttribute = other.targetAttribute == null ? null : other.targetAttribute.copy();
        this.targetCharacterGroupId = other.targetCharacterGroupId == null ? null : other.targetCharacterGroupId.copy();
        this.targetTeamGroupId = other.targetTeamGroupId == null ? null : other.targetTeamGroupId.copy();
        this.targetNationalityGroupId = other.targetNationalityGroupId == null ? null : other.targetNationalityGroupId.copy();
        this.luckRateGroupId = other.luckRateGroupId == null ? null : other.luckRateGroupId.copy();
    }

    @Override
    public MLuckCriteria copy() {
        return new MLuckCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getTargetAttribute() {
        return targetAttribute;
    }

    public void setTargetAttribute(IntegerFilter targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public IntegerFilter getTargetCharacterGroupId() {
        return targetCharacterGroupId;
    }

    public void setTargetCharacterGroupId(IntegerFilter targetCharacterGroupId) {
        this.targetCharacterGroupId = targetCharacterGroupId;
    }

    public IntegerFilter getTargetTeamGroupId() {
        return targetTeamGroupId;
    }

    public void setTargetTeamGroupId(IntegerFilter targetTeamGroupId) {
        this.targetTeamGroupId = targetTeamGroupId;
    }

    public IntegerFilter getTargetNationalityGroupId() {
        return targetNationalityGroupId;
    }

    public void setTargetNationalityGroupId(IntegerFilter targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
    }

    public IntegerFilter getLuckRateGroupId() {
        return luckRateGroupId;
    }

    public void setLuckRateGroupId(IntegerFilter luckRateGroupId) {
        this.luckRateGroupId = luckRateGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MLuckCriteria that = (MLuckCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(targetAttribute, that.targetAttribute) &&
            Objects.equals(targetCharacterGroupId, that.targetCharacterGroupId) &&
            Objects.equals(targetTeamGroupId, that.targetTeamGroupId) &&
            Objects.equals(targetNationalityGroupId, that.targetNationalityGroupId) &&
            Objects.equals(luckRateGroupId, that.luckRateGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        targetAttribute,
        targetCharacterGroupId,
        targetTeamGroupId,
        targetNationalityGroupId,
        luckRateGroupId
        );
    }

    @Override
    public String toString() {
        return "MLuckCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (targetAttribute != null ? "targetAttribute=" + targetAttribute + ", " : "") +
                (targetCharacterGroupId != null ? "targetCharacterGroupId=" + targetCharacterGroupId + ", " : "") +
                (targetTeamGroupId != null ? "targetTeamGroupId=" + targetTeamGroupId + ", " : "") +
                (targetNationalityGroupId != null ? "targetNationalityGroupId=" + targetNationalityGroupId + ", " : "") +
                (luckRateGroupId != null ? "luckRateGroupId=" + luckRateGroupId + ", " : "") +
            "}";
    }

}
