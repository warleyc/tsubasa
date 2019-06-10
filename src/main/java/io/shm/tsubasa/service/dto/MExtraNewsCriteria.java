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
 * Criteria class for the {@link io.shm.tsubasa.domain.MExtraNews} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MExtraNewsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-extra-news?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MExtraNewsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter orderNum;

    private IntegerFilter startAt;

    private IntegerFilter endAt;

    public MExtraNewsCriteria(){
    }

    public MExtraNewsCriteria(MExtraNewsCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.endAt = other.endAt == null ? null : other.endAt.copy();
    }

    @Override
    public MExtraNewsCriteria copy() {
        return new MExtraNewsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(IntegerFilter orderNum) {
        this.orderNum = orderNum;
    }

    public IntegerFilter getStartAt() {
        return startAt;
    }

    public void setStartAt(IntegerFilter startAt) {
        this.startAt = startAt;
    }

    public IntegerFilter getEndAt() {
        return endAt;
    }

    public void setEndAt(IntegerFilter endAt) {
        this.endAt = endAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MExtraNewsCriteria that = (MExtraNewsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(orderNum, that.orderNum) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        orderNum,
        startAt,
        endAt
        );
    }

    @Override
    public String toString() {
        return "MExtraNewsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (endAt != null ? "endAt=" + endAt + ", " : "") +
            "}";
    }

}
