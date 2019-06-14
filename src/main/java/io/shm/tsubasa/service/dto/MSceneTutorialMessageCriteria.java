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
 * Criteria class for the {@link io.shm.tsubasa.domain.MSceneTutorialMessage} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MSceneTutorialMessageResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-scene-tutorial-messages?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MSceneTutorialMessageCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter target;

    private IntegerFilter orderNum;

    private IntegerFilter position;

    public MSceneTutorialMessageCriteria(){
    }

    public MSceneTutorialMessageCriteria(MSceneTutorialMessageCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.target = other.target == null ? null : other.target.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
        this.position = other.position == null ? null : other.position.copy();
    }

    @Override
    public MSceneTutorialMessageCriteria copy() {
        return new MSceneTutorialMessageCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getTarget() {
        return target;
    }

    public void setTarget(IntegerFilter target) {
        this.target = target;
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
        final MSceneTutorialMessageCriteria that = (MSceneTutorialMessageCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(target, that.target) &&
            Objects.equals(orderNum, that.orderNum) &&
            Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        target,
        orderNum,
        position
        );
    }

    @Override
    public String toString() {
        return "MSceneTutorialMessageCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (target != null ? "target=" + target + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
                (position != null ? "position=" + position + ", " : "") +
            "}";
    }

}
