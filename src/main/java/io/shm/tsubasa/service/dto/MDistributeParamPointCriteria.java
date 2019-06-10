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
 * Criteria class for the {@link io.shm.tsubasa.domain.MDistributeParamPoint} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MDistributeParamPointResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-distribute-param-points?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MDistributeParamPointCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter targetAttribute;

    private IntegerFilter targetNationalityGroupId;

    public MDistributeParamPointCriteria(){
    }

    public MDistributeParamPointCriteria(MDistributeParamPointCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.targetAttribute = other.targetAttribute == null ? null : other.targetAttribute.copy();
        this.targetNationalityGroupId = other.targetNationalityGroupId == null ? null : other.targetNationalityGroupId.copy();
    }

    @Override
    public MDistributeParamPointCriteria copy() {
        return new MDistributeParamPointCriteria(this);
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

    public IntegerFilter getTargetNationalityGroupId() {
        return targetNationalityGroupId;
    }

    public void setTargetNationalityGroupId(IntegerFilter targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MDistributeParamPointCriteria that = (MDistributeParamPointCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(targetAttribute, that.targetAttribute) &&
            Objects.equals(targetNationalityGroupId, that.targetNationalityGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        targetAttribute,
        targetNationalityGroupId
        );
    }

    @Override
    public String toString() {
        return "MDistributeParamPointCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (targetAttribute != null ? "targetAttribute=" + targetAttribute + ", " : "") +
                (targetNationalityGroupId != null ? "targetNationalityGroupId=" + targetNationalityGroupId + ", " : "") +
            "}";
    }

}
