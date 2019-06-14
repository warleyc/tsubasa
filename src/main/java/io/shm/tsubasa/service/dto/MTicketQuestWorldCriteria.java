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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTicketQuestWorld} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTicketQuestWorldResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-ticket-quest-worlds?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTicketQuestWorldCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter setId;

    private IntegerFilter number;

    private IntegerFilter stageUnlockPattern;

    private IntegerFilter specialRewardContentType;

    private IntegerFilter specialRewardContentId;

    private IntegerFilter isEnableCoop;

    private IntegerFilter isHideDoNotHavingTicket;

    private LongFilter mTicketQuestStageId;

    public MTicketQuestWorldCriteria(){
    }

    public MTicketQuestWorldCriteria(MTicketQuestWorldCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.setId = other.setId == null ? null : other.setId.copy();
        this.number = other.number == null ? null : other.number.copy();
        this.stageUnlockPattern = other.stageUnlockPattern == null ? null : other.stageUnlockPattern.copy();
        this.specialRewardContentType = other.specialRewardContentType == null ? null : other.specialRewardContentType.copy();
        this.specialRewardContentId = other.specialRewardContentId == null ? null : other.specialRewardContentId.copy();
        this.isEnableCoop = other.isEnableCoop == null ? null : other.isEnableCoop.copy();
        this.isHideDoNotHavingTicket = other.isHideDoNotHavingTicket == null ? null : other.isHideDoNotHavingTicket.copy();
        this.mTicketQuestStageId = other.mTicketQuestStageId == null ? null : other.mTicketQuestStageId.copy();
    }

    @Override
    public MTicketQuestWorldCriteria copy() {
        return new MTicketQuestWorldCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSetId() {
        return setId;
    }

    public void setSetId(IntegerFilter setId) {
        this.setId = setId;
    }

    public IntegerFilter getNumber() {
        return number;
    }

    public void setNumber(IntegerFilter number) {
        this.number = number;
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

    public IntegerFilter getIsHideDoNotHavingTicket() {
        return isHideDoNotHavingTicket;
    }

    public void setIsHideDoNotHavingTicket(IntegerFilter isHideDoNotHavingTicket) {
        this.isHideDoNotHavingTicket = isHideDoNotHavingTicket;
    }

    public LongFilter getMTicketQuestStageId() {
        return mTicketQuestStageId;
    }

    public void setMTicketQuestStageId(LongFilter mTicketQuestStageId) {
        this.mTicketQuestStageId = mTicketQuestStageId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTicketQuestWorldCriteria that = (MTicketQuestWorldCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(setId, that.setId) &&
            Objects.equals(number, that.number) &&
            Objects.equals(stageUnlockPattern, that.stageUnlockPattern) &&
            Objects.equals(specialRewardContentType, that.specialRewardContentType) &&
            Objects.equals(specialRewardContentId, that.specialRewardContentId) &&
            Objects.equals(isEnableCoop, that.isEnableCoop) &&
            Objects.equals(isHideDoNotHavingTicket, that.isHideDoNotHavingTicket) &&
            Objects.equals(mTicketQuestStageId, that.mTicketQuestStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        setId,
        number,
        stageUnlockPattern,
        specialRewardContentType,
        specialRewardContentId,
        isEnableCoop,
        isHideDoNotHavingTicket,
        mTicketQuestStageId
        );
    }

    @Override
    public String toString() {
        return "MTicketQuestWorldCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (setId != null ? "setId=" + setId + ", " : "") +
                (number != null ? "number=" + number + ", " : "") +
                (stageUnlockPattern != null ? "stageUnlockPattern=" + stageUnlockPattern + ", " : "") +
                (specialRewardContentType != null ? "specialRewardContentType=" + specialRewardContentType + ", " : "") +
                (specialRewardContentId != null ? "specialRewardContentId=" + specialRewardContentId + ", " : "") +
                (isEnableCoop != null ? "isEnableCoop=" + isEnableCoop + ", " : "") +
                (isHideDoNotHavingTicket != null ? "isHideDoNotHavingTicket=" + isHideDoNotHavingTicket + ", " : "") +
                (mTicketQuestStageId != null ? "mTicketQuestStageId=" + mTicketQuestStageId + ", " : "") +
            "}";
    }

}
