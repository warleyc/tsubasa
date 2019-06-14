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
 * Criteria class for the {@link io.shm.tsubasa.domain.MQuestWorld} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MQuestWorldResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-quest-worlds?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MQuestWorldCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter number;

    private IntegerFilter startAt;

    private IntegerFilter stageUnlockPattern;

    private IntegerFilter specialRewardContentType;

    private IntegerFilter specialRewardContentId;

    private IntegerFilter isEnableCoop;

    private LongFilter mQuestStageId;

    public MQuestWorldCriteria(){
    }

    public MQuestWorldCriteria(MQuestWorldCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.number = other.number == null ? null : other.number.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.stageUnlockPattern = other.stageUnlockPattern == null ? null : other.stageUnlockPattern.copy();
        this.specialRewardContentType = other.specialRewardContentType == null ? null : other.specialRewardContentType.copy();
        this.specialRewardContentId = other.specialRewardContentId == null ? null : other.specialRewardContentId.copy();
        this.isEnableCoop = other.isEnableCoop == null ? null : other.isEnableCoop.copy();
        this.mQuestStageId = other.mQuestStageId == null ? null : other.mQuestStageId.copy();
    }

    @Override
    public MQuestWorldCriteria copy() {
        return new MQuestWorldCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getNumber() {
        return number;
    }

    public void setNumber(IntegerFilter number) {
        this.number = number;
    }

    public IntegerFilter getStartAt() {
        return startAt;
    }

    public void setStartAt(IntegerFilter startAt) {
        this.startAt = startAt;
    }

    public IntegerFilter getStageUnlockPattern() {
        return stageUnlockPattern;
    }

    public void setStageUnlockPattern(IntegerFilter stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
    }

    public IntegerFilter getSpecialRewardContentType() {
        return specialRewardContentType;
    }

    public void setSpecialRewardContentType(IntegerFilter specialRewardContentType) {
        this.specialRewardContentType = specialRewardContentType;
    }

    public IntegerFilter getSpecialRewardContentId() {
        return specialRewardContentId;
    }

    public void setSpecialRewardContentId(IntegerFilter specialRewardContentId) {
        this.specialRewardContentId = specialRewardContentId;
    }

    public IntegerFilter getIsEnableCoop() {
        return isEnableCoop;
    }

    public void setIsEnableCoop(IntegerFilter isEnableCoop) {
        this.isEnableCoop = isEnableCoop;
    }

    public LongFilter getMQuestStageId() {
        return mQuestStageId;
    }

    public void setMQuestStageId(LongFilter mQuestStageId) {
        this.mQuestStageId = mQuestStageId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MQuestWorldCriteria that = (MQuestWorldCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(number, that.number) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(stageUnlockPattern, that.stageUnlockPattern) &&
            Objects.equals(specialRewardContentType, that.specialRewardContentType) &&
            Objects.equals(specialRewardContentId, that.specialRewardContentId) &&
            Objects.equals(isEnableCoop, that.isEnableCoop) &&
            Objects.equals(mQuestStageId, that.mQuestStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        number,
        startAt,
        stageUnlockPattern,
        specialRewardContentType,
        specialRewardContentId,
        isEnableCoop,
        mQuestStageId
        );
    }

    @Override
    public String toString() {
        return "MQuestWorldCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (number != null ? "number=" + number + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (stageUnlockPattern != null ? "stageUnlockPattern=" + stageUnlockPattern + ", " : "") +
                (specialRewardContentType != null ? "specialRewardContentType=" + specialRewardContentType + ", " : "") +
                (specialRewardContentId != null ? "specialRewardContentId=" + specialRewardContentId + ", " : "") +
                (isEnableCoop != null ? "isEnableCoop=" + isEnableCoop + ", " : "") +
                (mQuestStageId != null ? "mQuestStageId=" + mQuestStageId + ", " : "") +
            "}";
    }

}
