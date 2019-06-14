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
 * Criteria class for the {@link io.shm.tsubasa.domain.MUniformOriginalSet} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MUniformOriginalSetResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-uniform-original-sets?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MUniformOriginalSetCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter upModelId;

    private IntegerFilter bottomModelId;

    private IntegerFilter uniformType;

    private IntegerFilter isDefault;

    private IntegerFilter orderNum;

    public MUniformOriginalSetCriteria(){
    }

    public MUniformOriginalSetCriteria(MUniformOriginalSetCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.upModelId = other.upModelId == null ? null : other.upModelId.copy();
        this.bottomModelId = other.bottomModelId == null ? null : other.bottomModelId.copy();
        this.uniformType = other.uniformType == null ? null : other.uniformType.copy();
        this.isDefault = other.isDefault == null ? null : other.isDefault.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
    }

    @Override
    public MUniformOriginalSetCriteria copy() {
        return new MUniformOriginalSetCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getUpModelId() {
        return upModelId;
    }

    public void setUpModelId(IntegerFilter upModelId) {
        this.upModelId = upModelId;
    }

    public IntegerFilter getBottomModelId() {
        return bottomModelId;
    }

    public void setBottomModelId(IntegerFilter bottomModelId) {
        this.bottomModelId = bottomModelId;
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
        final MUniformOriginalSetCriteria that = (MUniformOriginalSetCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(upModelId, that.upModelId) &&
            Objects.equals(bottomModelId, that.bottomModelId) &&
            Objects.equals(uniformType, that.uniformType) &&
            Objects.equals(isDefault, that.isDefault) &&
            Objects.equals(orderNum, that.orderNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        upModelId,
        bottomModelId,
        uniformType,
        isDefault,
        orderNum
        );
    }

    @Override
    public String toString() {
        return "MUniformOriginalSetCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (upModelId != null ? "upModelId=" + upModelId + ", " : "") +
                (bottomModelId != null ? "bottomModelId=" + bottomModelId + ", " : "") +
                (uniformType != null ? "uniformType=" + uniformType + ", " : "") +
                (isDefault != null ? "isDefault=" + isDefault + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
            "}";
    }

}
