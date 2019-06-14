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
 * Criteria class for the {@link io.shm.tsubasa.domain.MRegulationMatchTutorialMessage} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MRegulationMatchTutorialMessageResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-regulation-match-tutorial-messages?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MRegulationMatchTutorialMessageCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter ruleId;

    private IntegerFilter orderNum;

    private IntegerFilter position;

    public MRegulationMatchTutorialMessageCriteria(){
    }

    public MRegulationMatchTutorialMessageCriteria(MRegulationMatchTutorialMessageCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ruleId = other.ruleId == null ? null : other.ruleId.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
        this.position = other.position == null ? null : other.position.copy();
    }

    @Override
    public MRegulationMatchTutorialMessageCriteria copy() {
        return new MRegulationMatchTutorialMessageCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRuleId() {
        return ruleId;
    }

    public void setRuleId(IntegerFilter ruleId) {
        this.ruleId = ruleId;
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
        final MRegulationMatchTutorialMessageCriteria that = (MRegulationMatchTutorialMessageCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ruleId, that.ruleId) &&
            Objects.equals(orderNum, that.orderNum) &&
            Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ruleId,
        orderNum,
        position
        );
    }

    @Override
    public String toString() {
        return "MRegulationMatchTutorialMessageCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ruleId != null ? "ruleId=" + ruleId + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
                (position != null ? "position=" + position + ", " : "") +
            "}";
    }

}
