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
 * Criteria class for the {@link io.shm.tsubasa.domain.MCoopRoomStamp} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MCoopRoomStampResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-coop-room-stamps?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MCoopRoomStampCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter role;

    private IntegerFilter orderNum;

    private IntegerFilter masterId;

    public MCoopRoomStampCriteria(){
    }

    public MCoopRoomStampCriteria(MCoopRoomStampCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.role = other.role == null ? null : other.role.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
        this.masterId = other.masterId == null ? null : other.masterId.copy();
    }

    @Override
    public MCoopRoomStampCriteria copy() {
        return new MCoopRoomStampCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRole() {
        return role;
    }

    public void setRole(IntegerFilter role) {
        this.role = role;
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
        final MCoopRoomStampCriteria that = (MCoopRoomStampCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(role, that.role) &&
            Objects.equals(orderNum, that.orderNum) &&
            Objects.equals(masterId, that.masterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        role,
        orderNum,
        masterId
        );
    }

    @Override
    public String toString() {
        return "MCoopRoomStampCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (role != null ? "role=" + role + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
                (masterId != null ? "masterId=" + masterId + ", " : "") +
            "}";
    }

}
