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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTsubasaPoint} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTsubasaPointResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-tsubasa-points?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTsubasaPointCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter matchType;

    private IntegerFilter pointType;

    private IntegerFilter calcType;

    private IntegerFilter aValue;

    private IntegerFilter bValue;

    private IntegerFilter orderNum;

    public MTsubasaPointCriteria(){
    }

    public MTsubasaPointCriteria(MTsubasaPointCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.matchType = other.matchType == null ? null : other.matchType.copy();
        this.pointType = other.pointType == null ? null : other.pointType.copy();
        this.calcType = other.calcType == null ? null : other.calcType.copy();
        this.aValue = other.aValue == null ? null : other.aValue.copy();
        this.bValue = other.bValue == null ? null : other.bValue.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
    }

    @Override
    public MTsubasaPointCriteria copy() {
        return new MTsubasaPointCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getMatchType() {
        return matchType;
    }

    public void setMatchType(IntegerFilter matchType) {
        this.matchType = matchType;
    }

    public IntegerFilter getPointType() {
        return pointType;
    }

    public void setPointType(IntegerFilter pointType) {
        this.pointType = pointType;
    }

    public IntegerFilter getCalcType() {
        return calcType;
    }

    public void setCalcType(IntegerFilter calcType) {
        this.calcType = calcType;
    }

    public IntegerFilter getaValue() {
        return aValue;
    }

    public void setaValue(IntegerFilter aValue) {
        this.aValue = aValue;
    }

    public IntegerFilter getbValue() {
        return bValue;
    }

    public void setbValue(IntegerFilter bValue) {
        this.bValue = bValue;
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
        final MTsubasaPointCriteria that = (MTsubasaPointCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(matchType, that.matchType) &&
            Objects.equals(pointType, that.pointType) &&
            Objects.equals(calcType, that.calcType) &&
            Objects.equals(aValue, that.aValue) &&
            Objects.equals(bValue, that.bValue) &&
            Objects.equals(orderNum, that.orderNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        matchType,
        pointType,
        calcType,
        aValue,
        bValue,
        orderNum
        );
    }

    @Override
    public String toString() {
        return "MTsubasaPointCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (matchType != null ? "matchType=" + matchType + ", " : "") +
                (pointType != null ? "pointType=" + pointType + ", " : "") +
                (calcType != null ? "calcType=" + calcType + ", " : "") +
                (aValue != null ? "aValue=" + aValue + ", " : "") +
                (bValue != null ? "bValue=" + bValue + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
            "}";
    }

}
