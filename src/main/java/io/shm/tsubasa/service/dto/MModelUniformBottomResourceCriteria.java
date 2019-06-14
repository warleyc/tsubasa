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
 * Criteria class for the {@link io.shm.tsubasa.domain.MModelUniformBottomResource} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MModelUniformBottomResourceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-model-uniform-bottom-resources?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MModelUniformBottomResourceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter uniformTypeId;

    private IntegerFilter dressingType;

    private IntegerFilter width;

    public MModelUniformBottomResourceCriteria(){
    }

    public MModelUniformBottomResourceCriteria(MModelUniformBottomResourceCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.uniformTypeId = other.uniformTypeId == null ? null : other.uniformTypeId.copy();
        this.dressingType = other.dressingType == null ? null : other.dressingType.copy();
        this.width = other.width == null ? null : other.width.copy();
    }

    @Override
    public MModelUniformBottomResourceCriteria copy() {
        return new MModelUniformBottomResourceCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getUniformTypeId() {
        return uniformTypeId;
    }

    public void setUniformTypeId(IntegerFilter uniformTypeId) {
        this.uniformTypeId = uniformTypeId;
    }

    public IntegerFilter getDressingType() {
        return dressingType;
    }

    public void setDressingType(IntegerFilter dressingType) {
        this.dressingType = dressingType;
    }

    public IntegerFilter getWidth() {
        return width;
    }

    public void setWidth(IntegerFilter width) {
        this.width = width;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MModelUniformBottomResourceCriteria that = (MModelUniformBottomResourceCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(uniformTypeId, that.uniformTypeId) &&
            Objects.equals(dressingType, that.dressingType) &&
            Objects.equals(width, that.width);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uniformTypeId,
        dressingType,
        width
        );
    }

    @Override
    public String toString() {
        return "MModelUniformBottomResourceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uniformTypeId != null ? "uniformTypeId=" + uniformTypeId + ", " : "") +
                (dressingType != null ? "dressingType=" + dressingType + ", " : "") +
                (width != null ? "width=" + width + ", " : "") +
            "}";
    }

}
