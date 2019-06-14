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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMission} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMissionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-missions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMissionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter term;

    private IntegerFilter roundNum;

    private IntegerFilter missionType;

    private IntegerFilter absolute;

    private IntegerFilter param1;

    private IntegerFilter param2;

    private IntegerFilter param3;

    private IntegerFilter param4;

    private IntegerFilter param5;

    private IntegerFilter trigger;

    private IntegerFilter triggerCondition;

    private IntegerFilter rewardId;

    private IntegerFilter startAt;

    private IntegerFilter endAt;

    private IntegerFilter link;

    private IntegerFilter pickup;

    private IntegerFilter orderNum;

    private LongFilter idId;

    private LongFilter mAchievementId;

    public MMissionCriteria(){
    }

    public MMissionCriteria(MMissionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.term = other.term == null ? null : other.term.copy();
        this.roundNum = other.roundNum == null ? null : other.roundNum.copy();
        this.missionType = other.missionType == null ? null : other.missionType.copy();
        this.absolute = other.absolute == null ? null : other.absolute.copy();
        this.param1 = other.param1 == null ? null : other.param1.copy();
        this.param2 = other.param2 == null ? null : other.param2.copy();
        this.param3 = other.param3 == null ? null : other.param3.copy();
        this.param4 = other.param4 == null ? null : other.param4.copy();
        this.param5 = other.param5 == null ? null : other.param5.copy();
        this.trigger = other.trigger == null ? null : other.trigger.copy();
        this.triggerCondition = other.triggerCondition == null ? null : other.triggerCondition.copy();
        this.rewardId = other.rewardId == null ? null : other.rewardId.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.endAt = other.endAt == null ? null : other.endAt.copy();
        this.link = other.link == null ? null : other.link.copy();
        this.pickup = other.pickup == null ? null : other.pickup.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
        this.mAchievementId = other.mAchievementId == null ? null : other.mAchievementId.copy();
    }

    @Override
    public MMissionCriteria copy() {
        return new MMissionCriteria(this);
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

    public IntegerFilter getRoundNum() {
        return roundNum;
    }

    public void setRoundNum(IntegerFilter roundNum) {
        this.roundNum = roundNum;
    }

    public IntegerFilter getMissionType() {
        return missionType;
    }

    public void setMissionType(IntegerFilter missionType) {
        this.missionType = missionType;
    }

    public IntegerFilter getAbsolute() {
        return absolute;
    }

    public void setAbsolute(IntegerFilter absolute) {
        this.absolute = absolute;
    }

    public IntegerFilter getParam1() {
        return param1;
    }

    public void setParam1(IntegerFilter param1) {
        this.param1 = param1;
    }

    public IntegerFilter getParam2() {
        return param2;
    }

    public void setParam2(IntegerFilter param2) {
        this.param2 = param2;
    }

    public IntegerFilter getParam3() {
        return param3;
    }

    public void setParam3(IntegerFilter param3) {
        this.param3 = param3;
    }

    public IntegerFilter getParam4() {
        return param4;
    }

    public void setParam4(IntegerFilter param4) {
        this.param4 = param4;
    }

    public IntegerFilter getParam5() {
        return param5;
    }

    public void setParam5(IntegerFilter param5) {
        this.param5 = param5;
    }

    public IntegerFilter getTrigger() {
        return trigger;
    }

    public void setTrigger(IntegerFilter trigger) {
        this.trigger = trigger;
    }

    public IntegerFilter getTriggerCondition() {
        return triggerCondition;
    }

    public void setTriggerCondition(IntegerFilter triggerCondition) {
        this.triggerCondition = triggerCondition;
    }

    public IntegerFilter getRewardId() {
        return rewardId;
    }

    public void setRewardId(IntegerFilter rewardId) {
        this.rewardId = rewardId;
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

    public IntegerFilter getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(IntegerFilter orderNum) {
        this.orderNum = orderNum;
    }

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }

    public LongFilter getMAchievementId() {
        return mAchievementId;
    }

    public void setMAchievementId(LongFilter mAchievementId) {
        this.mAchievementId = mAchievementId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMissionCriteria that = (MMissionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(term, that.term) &&
            Objects.equals(roundNum, that.roundNum) &&
            Objects.equals(missionType, that.missionType) &&
            Objects.equals(absolute, that.absolute) &&
            Objects.equals(param1, that.param1) &&
            Objects.equals(param2, that.param2) &&
            Objects.equals(param3, that.param3) &&
            Objects.equals(param4, that.param4) &&
            Objects.equals(param5, that.param5) &&
            Objects.equals(trigger, that.trigger) &&
            Objects.equals(triggerCondition, that.triggerCondition) &&
            Objects.equals(rewardId, that.rewardId) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(endAt, that.endAt) &&
            Objects.equals(link, that.link) &&
            Objects.equals(pickup, that.pickup) &&
            Objects.equals(orderNum, that.orderNum) &&
            Objects.equals(idId, that.idId) &&
            Objects.equals(mAchievementId, that.mAchievementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        term,
        roundNum,
        missionType,
        absolute,
        param1,
        param2,
        param3,
        param4,
        param5,
        trigger,
        triggerCondition,
        rewardId,
        startAt,
        endAt,
        link,
        pickup,
        orderNum,
        idId,
        mAchievementId
        );
    }

    @Override
    public String toString() {
        return "MMissionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (term != null ? "term=" + term + ", " : "") +
                (roundNum != null ? "roundNum=" + roundNum + ", " : "") +
                (missionType != null ? "missionType=" + missionType + ", " : "") +
                (absolute != null ? "absolute=" + absolute + ", " : "") +
                (param1 != null ? "param1=" + param1 + ", " : "") +
                (param2 != null ? "param2=" + param2 + ", " : "") +
                (param3 != null ? "param3=" + param3 + ", " : "") +
                (param4 != null ? "param4=" + param4 + ", " : "") +
                (param5 != null ? "param5=" + param5 + ", " : "") +
                (trigger != null ? "trigger=" + trigger + ", " : "") +
                (triggerCondition != null ? "triggerCondition=" + triggerCondition + ", " : "") +
                (rewardId != null ? "rewardId=" + rewardId + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (endAt != null ? "endAt=" + endAt + ", " : "") +
                (link != null ? "link=" + link + ", " : "") +
                (pickup != null ? "pickup=" + pickup + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
                (mAchievementId != null ? "mAchievementId=" + mAchievementId + ", " : "") +
            "}";
    }

}
