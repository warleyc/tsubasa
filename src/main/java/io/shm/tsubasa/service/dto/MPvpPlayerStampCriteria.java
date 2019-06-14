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
 * Criteria class for the {@link io.shm.tsubasa.domain.MPvpPlayerStamp} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MPvpPlayerStampResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-pvp-player-stamps?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MPvpPlayerStampCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter orderNum;

    private IntegerFilter masterId;

    public MPvpPlayerStampCriteria(){
    }

    public MPvpPlayerStampCriteria(MPvpPlayerStampCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
        this.masterId = other.masterId == null ? null : other.masterId.copy();
    }

    @Override
    public MPvpPlayerStampCriteria copy() {
        return new MPvpPlayerStampCriteria(this);
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

    public IntegerFilter getMasterId() {
        return masterId;
    }

    public void setMasterId(IntegerFilter masterId) {
        this.masterId = masterId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MPvpPlayerStampCriteria that = (MPvpPlayerStampCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(orderNum, that.orderNum) &&
            Objects.equals(masterId, that.masterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        orderNum,
        masterId
        );
    }

    @Override
    public String toString() {
        return "MPvpPlayerStampCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
                (masterId != null ? "masterId=" + masterId + ", " : "") +
            "}";
    }

}
