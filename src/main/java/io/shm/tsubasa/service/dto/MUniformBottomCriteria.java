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
 * Criteria class for the {@link io.shm.tsubasa.domain.MUniformBottom} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MUniformBottomResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-uniform-bottoms?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MUniformBottomCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter modelId;

    private IntegerFilter uniformType;

    private IntegerFilter isDefault;

    private IntegerFilter orderNum;

    public MUniformBottomCriteria(){
    }

    public MUniformBottomCriteria(MUniformBottomCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.modelId = other.modelId == null ? null : other.modelId.copy();
        this.uniformType = other.uniformType == null ? null : other.uniformType.copy();
        this.isDefault = other.isDefault == null ? null : other.isDefault.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
    }

    @Override
    public MUniformBottomCriteria copy() {
        return new MUniformBottomCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getModelId() {
        return modelId;
    }

    public void setModelId(IntegerFilter modelId) {
        this.modelId = modelId;
    }

    public IntegerFilter getUniformType() {
        return uniformType;
    }

    public void setUniformType(IntegerFilter uniformType) {
        this.uniformType = uniformType;
    }

    public IntegerFilter getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(IntegerFilter isDefault) {
        this.isDefault = isDefault;
    }

    public IntegerFilter getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(IntegerFilter orderNum) {
        this.orderNum = orderNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MUniformBottomCriteria that = (MUniformBottomCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(modelId, that.modelId) &&
            Objects.equals(uniformType, that.uniformType) &&
            Objects.equals(isDefault, that.isDefault) &&
            Objects.equals(orderNum, that.orderNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        modelId,
        uniformType,
        isDefault,
        orderNum
        );
    }

    @Override
    public String toString() {
        return "MUniformBottomCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (modelId != null ? "modelId=" + modelId + ", " : "") +
                (uniformType != null ? "uniformType=" + uniformType + ", " : "") +
                (isDefault != null ? "isDefault=" + isDefault + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
            "}";
    }

}
