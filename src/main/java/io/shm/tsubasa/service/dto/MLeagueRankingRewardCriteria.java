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
 * Criteria class for the {@link io.shm.tsubasa.domain.MLeagueRankingReward} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MLeagueRankingRewardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-league-ranking-rewards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MLeagueRankingRewardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter leagueHierarchy;

    private IntegerFilter rankTo;

    private IntegerFilter rewardGroupId;

    private IntegerFilter startAt;

    private IntegerFilter endAt;

    public MLeagueRankingRewardCriteria(){
    }

    public MLeagueRankingRewardCriteria(MLeagueRankingRewardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.leagueHierarchy = other.leagueHierarchy == null ? null : other.leagueHierarchy.copy();
        this.rankTo = other.rankTo == null ? null : other.rankTo.copy();
        this.rewardGroupId = other.rewardGroupId == null ? null : other.rewardGroupId.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.endAt = other.endAt == null ? null : other.endAt.copy();
    }

    @Override
    public MLeagueRankingRewardCriteria copy() {
        return new MLeagueRankingRewardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getLeagueHierarchy() {
        return leagueHierarchy;
    }

    public void setLeagueHierarchy(IntegerFilter leagueHierarchy) {
        this.leagueHierarchy = leagueHierarchy;
    }

    public IntegerFilter getRankTo() {
        return rankTo;
    }

    public void setRankTo(IntegerFilter rankTo) {
        this.rankTo = rankTo;
    }

    public IntegerFilter getRewardGroupId() {
        return rewardGroupId;
    }

    public void setRewardGroupId(IntegerFilter rewardGroupId) {
        this.rewardGroupId = rewardGroupId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MLeagueRankingRewardCriteria that = (MLeagueRankingRewardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(leagueHierarchy, that.leagueHierarchy) &&
            Objects.equals(rankTo, that.rankTo) &&
            Objects.equals(rewardGroupId, that.rewardGroupId) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        leagueHierarchy,
        rankTo,
        rewardGroupId,
        startAt,
        endAt
        );
    }

    @Override
    public String toString() {
        return "MLeagueRankingRewardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (leagueHierarchy != null ? "leagueHierarchy=" + leagueHierarchy + ", " : "") +
                (rankTo != null ? "rankTo=" + rankTo + ", " : "") +
                (rewardGroupId != null ? "rewardGroupId=" + rewardGroupId + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (endAt != null ? "endAt=" + endAt + ", " : "") +
            "}";
    }

}
