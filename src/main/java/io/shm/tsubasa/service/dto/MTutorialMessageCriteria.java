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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTutorialMessage} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTutorialMessageResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-tutorial-messages?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTutorialMessageCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter step;

    private IntegerFilter orderNum;

    private IntegerFilter position;

    public MTutorialMessageCriteria(){
    }

    public MTutorialMessageCriteria(MTutorialMessageCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.step = other.step == null ? null : other.step.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
        this.position = other.position == null ? null : other.position.copy();
    }

    @Override
    public MTutorialMessageCriteria copy() {
        return new MTutorialMessageCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getStep() {
        return step;
    }

    public void setStep(IntegerFilter step) {
        this.step = step;
    }

    public IntegerFilter getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(IntegerFilter orderNum) {
        this.orderNum = orderNum;
    }

    public IntegerFilter getPosition() {
        return position;
    }

    public void setPosition(IntegerFilter position) {
        this.position = position;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTutorialMessageCriteria that = (MTutorialMessageCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(step, that.step) &&
            Objects.equals(orderNum, that.orderNum) &&
            Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        step,
        orderNum,
        position
        );
    }

    @Override
    public String toString() {
        return "MTutorialMessageCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (step != null ? "step=" + step + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
                (position != null ? "position=" + position + ", " : "") +
            "}";
    }

}
