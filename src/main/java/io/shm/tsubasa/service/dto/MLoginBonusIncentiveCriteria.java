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
 * Criteria class for the {@link io.shm.tsubasa.domain.MLoginBonusIncentive} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MLoginBonusIncentiveResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-login-bonus-incentives?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MLoginBonusIncentiveCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter roundId;

    private IntegerFilter day;

    private IntegerFilter contentType;

    private IntegerFilter contentId;

    private IntegerFilter contentAmount;

    private IntegerFilter isDecorated;

    public MLoginBonusIncentiveCriteria(){
    }

    public MLoginBonusIncentiveCriteria(MLoginBonusIncentiveCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.roundId = other.roundId == null ? null : other.roundId.copy();
        this.day = other.day == null ? null : other.day.copy();
        this.contentType = other.contentType == null ? null : other.contentType.copy();
        this.contentId = other.contentId == null ? null : other.contentId.copy();
        this.contentAmount = other.contentAmount == null ? null : other.contentAmount.copy();
        this.isDecorated = other.isDecorated == null ? null : other.isDecorated.copy();
    }

    @Override
    public MLoginBonusIncentiveCriteria copy() {
        return new MLoginBonusIncentiveCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRoundId() {
        return roundId;
    }

    public void setRoundId(IntegerFilter roundId) {
        this.roundId = roundId;
    }

    public IntegerFilter getDay() {
        return day;
    }

    public void setDay(IntegerFilter day) {
        this.day = day;
    }

    public IntegerFilter getContentType() {
        return contentType;
    }

    public void setContentType(IntegerFilter contentType) {
        this.contentType = contentType;
    }

    public IntegerFilter getContentId() {
        return contentId;
    }

    public void setContentId(IntegerFilter contentId) {
        this.contentId = contentId;
    }

    public IntegerFilter getContentAmount() {
        return contentAmount;
    }

    public void setContentAmount(IntegerFilter contentAmount) {
        this.contentAmount = contentAmount;
    }

    public IntegerFilter getIsDecorated() {
        return isDecorated;
    }

    public void setIsDecorated(IntegerFilter isDecorated) {
        this.isDecorated = isDecorated;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MLoginBonusIncentiveCriteria that = (MLoginBonusIncentiveCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(roundId, that.roundId) &&
            Objects.equals(day, that.day) &&
            Objects.equals(contentType, that.contentType) &&
            Objects.equals(contentId, that.contentId) &&
            Objects.equals(contentAmount, that.contentAmount) &&
            Objects.equals(isDecorated, that.isDecorated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        roundId,
        day,
        contentType,
        contentId,
        contentAmount,
        isDecorated
        );
    }

    @Override
    public String toString() {
        return "MLoginBonusIncentiveCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (roundId != null ? "roundId=" + roundId + ", " : "") +
                (day != null ? "day=" + day + ", " : "") +
                (contentType != null ? "contentType=" + contentType + ", " : "") +
                (contentId != null ? "contentId=" + contentId + ", " : "") +
                (contentAmount != null ? "contentAmount=" + contentAmount + ", " : "") +
                (isDecorated != null ? "isDecorated=" + isDecorated + ", " : "") +
            "}";
    }

}
