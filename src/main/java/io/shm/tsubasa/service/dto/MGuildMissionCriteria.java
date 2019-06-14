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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGuildMission} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGuildMissionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-guild-missions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGuildMissionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter term;

    private IntegerFilter missionType;

    private IntegerFilter param1;

    private IntegerFilter rewardId;

    private IntegerFilter link;

    private IntegerFilter pickup;

    private IntegerFilter triggerMissionId;

    private IntegerFilter orderNum;

    private LongFilter mmissionrewardId;

    public MGuildMissionCriteria(){
    }

    public MGuildMissionCriteria(MGuildMissionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.term = other.term == null ? null : other.term.copy();
        this.missionType = other.missionType == null ? null : other.missionType.copy();
        this.param1 = other.param1 == null ? null : other.param1.copy();
        this.rewardId = other.rewardId == null ? null : other.rewardId.copy();
        this.link = other.link == null ? null : other.link.copy();
        this.pickup = other.pickup == null ? null : other.pickup.copy();
        this.triggerMissionId = other.triggerMissionId == null ? null : other.triggerMissionId.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
        this.mmissionrewardId = other.mmissionrewardId == null ? null : other.mmissionrewardId.copy();
    }

    @Override
    public MGuildMissionCriteria copy() {
        return new MGuildMissionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getTerm() {
        return term;
    }

    public void setTerm(IntegerFilter term) {
        this.term = term;
    }

    public IntegerFilter getMissionType() {
        return missionType;
    }

    public void setMissionType(IntegerFilter missionType) {
        this.missionType = missionType;
    }

    public IntegerFilter getParam1() {
        return param1;
    }

    public void setParam1(IntegerFilter param1) {
        this.param1 = param1;
    }

    public IntegerFilter getRewardId() {
        return rewardId;
    }

    public void setRewardId(IntegerFilter rewardId) {
        this.rewardId = rewardId;
    }

    public IntegerFilter getLink() {
        return link;
    }

    public void setLink(IntegerFilter link) {
        this.link = link;
    }

    public IntegerFilter getPickup() {
        return pickup;
    }

    public void setPickup(IntegerFilter pickup) {
        this.pickup = pickup;
    }

    public IntegerFilter getTriggerMissionId() {
        return triggerMissionId;
    }

    public void setTriggerMissionId(IntegerFilter triggerMissionId) {
        this.triggerMissionId = triggerMissionId;
    }

    public IntegerFilter getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(IntegerFilter orderNum) {
        this.orderNum = orderNum;
    }

    public LongFilter getMmissionrewardId() {
        return mmissionrewardId;
    }

    public void setMmissionrewardId(LongFilter mmissionrewardId) {
        this.mmissionrewardId = mmissionrewardId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGuildMissionCriteria that = (MGuildMissionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(term, that.term) &&
            Objects.equals(missionType, that.missionType) &&
            Objects.equals(param1, that.param1) &&
            Objects.equals(rewardId, that.rewardId) &&
            Objects.equals(link, that.link) &&
            Objects.equals(pickup, that.pickup) &&
            Objects.equals(triggerMissionId, that.triggerMissionId) &&
            Objects.equals(orderNum, that.orderNum) &&
            Objects.equals(mmissionrewardId, that.mmissionrewardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        term,
        missionType,
        param1,
        rewardId,
        link,
        pickup,
        triggerMissionId,
        orderNum,
        mmissionrewardId
        );
    }

    @Override
    public String toString() {
        return "MGuildMissionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (term != null ? "term=" + term + ", " : "") +
                (missionType != null ? "missionType=" + missionType + ", " : "") +
                (param1 != null ? "param1=" + param1 + ", " : "") +
                (rewardId != null ? "rewardId=" + rewardId + ", " : "") +
                (link != null ? "link=" + link + ", " : "") +
                (pickup != null ? "pickup=" + pickup + ", " : "") +
                (triggerMissionId != null ? "triggerMissionId=" + triggerMissionId + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
                (mmissionrewardId != null ? "mmissionrewardId=" + mmissionrewardId + ", " : "") +
            "}";
    }

}
