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
 * Criteria class for the {@link io.shm.tsubasa.domain.MFullPowerPoint} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MFullPowerPointResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-full-power-points?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MFullPowerPointCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter pointType;

    private IntegerFilter value;

    public MFullPowerPointCriteria(){
    }

    public MFullPowerPointCriteria(MFullPowerPointCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.pointType = other.pointType == null ? null : other.pointType.copy();
        this.value = other.value == null ? null : other.value.copy();
    }

    @Override
    public MFullPowerPointCriteria copy() {
        return new MFullPowerPointCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getPointType() {
        return pointType;
    }

    public void setPointType(IntegerFilter pointType) {
        this.pointType = pointType;
    }

    public IntegerFilter getValue() {
        return value;
    }

    public void setValue(IntegerFilter value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MFullPowerPointCriteria that = (MFullPowerPointCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(pointType, that.pointType) &&
            Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        pointType,
        value
        );
    }

    @Override
    public String toString() {
        return "MFullPowerPointCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (pointType != null ? "pointType=" + pointType + ", " : "") +
                (value != null ? "value=" + value + ", " : "") +
            "}";
    }

}
