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
 * Criteria class for the {@link io.shm.tsubasa.domain.MLoginBonusRound} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MLoginBonusRoundResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-login-bonus-rounds?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MLoginBonusRoundCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter roundId;

    private IntegerFilter bonusType;

    private IntegerFilter startAt;

    private IntegerFilter endAt;

    private IntegerFilter nextId;

    public MLoginBonusRoundCriteria(){
    }

    public MLoginBonusRoundCriteria(MLoginBonusRoundCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.roundId = other.roundId == null ? null : other.roundId.copy();
        this.bonusType = other.bonusType == null ? null : other.bonusType.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.endAt = other.endAt == null ? null : other.endAt.copy();
        this.nextId = other.nextId == null ? null : other.nextId.copy();
    }

    @Override
    public MLoginBonusRoundCriteria copy() {
        return new MLoginBonusRoundCriteria(this);
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

    public IntegerFilter getBonusType() {
        return bonusType;
    }

    public void setBonusType(IntegerFilter bonusType) {
        this.bonusType = bonusType;
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

    public IntegerFilter getNextId() {
        return nextId;
    }

    public void setNextId(IntegerFilter nextId) {
        this.nextId = nextId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MLoginBonusRoundCriteria that = (MLoginBonusRoundCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(roundId, that.roundId) &&
            Objects.equals(bonusType, that.bonusType) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(endAt, that.endAt) &&
            Objects.equals(nextId, that.nextId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        roundId,
        bonusType,
        startAt,
        endAt,
        nextId
        );
    }

    @Override
    public String toString() {
        return "MLoginBonusRoundCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (roundId != null ? "roundId=" + roundId + ", " : "") +
                (bonusType != null ? "bonusType=" + bonusType + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (endAt != null ? "endAt=" + endAt + ", " : "") +
                (nextId != null ? "nextId=" + nextId + ", " : "") +
            "}";
    }

}
