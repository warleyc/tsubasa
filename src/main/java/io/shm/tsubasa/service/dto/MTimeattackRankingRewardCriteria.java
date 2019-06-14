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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTimeattackRankingReward} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTimeattackRankingRewardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-timeattack-ranking-rewards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTimeattackRankingRewardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter eventId;

    private IntegerFilter rankingFrom;

    private IntegerFilter rankingTo;

    private IntegerFilter rewardGroupId;

    public MTimeattackRankingRewardCriteria(){
    }

    public MTimeattackRankingRewardCriteria(MTimeattackRankingRewardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.eventId = other.eventId == null ? null : other.eventId.copy();
        this.rankingFrom = other.rankingFrom == null ? null : other.rankingFrom.copy();
        this.rankingTo = other.rankingTo == null ? null : other.rankingTo.copy();
        this.rewardGroupId = other.rewardGroupId == null ? null : other.rewardGroupId.copy();
    }

    @Override
    public MTimeattackRankingRewardCriteria copy() {
        return new MTimeattackRankingRewardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getEventId() {
        return eventId;
    }

    public void setEventId(IntegerFilter eventId) {
        this.eventId = eventId;
    }

    public IntegerFilter getRankingFrom() {
        return rankingFrom;
    }

    public void setRankingFrom(IntegerFilter rankingFrom) {
        this.rankingFrom = rankingFrom;
    }

    public IntegerFilter getRankingTo() {
        return rankingTo;
    }

    public void setRankingTo(IntegerFilter rankingTo) {
        this.rankingTo = rankingTo;
    }

    public IntegerFilter getRewardGroupId() {
        return rewardGroupId;
    }

    public void setRewardGroupId(IntegerFilter rewardGroupId) {
        this.rewardGroupId = rewardGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTimeattackRankingRewardCriteria that = (MTimeattackRankingRewardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(eventId, that.eventId) &&
            Objects.equals(rankingFrom, that.rankingFrom) &&
            Objects.equals(rankingTo, that.rankingTo) &&
            Objects.equals(rewardGroupId, that.rewardGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        eventId,
        rankingFrom,
        rankingTo,
        rewardGroupId
        );
    }

    @Override
    public String toString() {
        return "MTimeattackRankingRewardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (eventId != null ? "eventId=" + eventId + ", " : "") +
                (rankingFrom != null ? "rankingFrom=" + rankingFrom + ", " : "") +
                (rankingTo != null ? "rankingTo=" + rankingTo + ", " : "") +
                (rewardGroupId != null ? "rewardGroupId=" + rewardGroupId + ", " : "") +
            "}";
    }

}
