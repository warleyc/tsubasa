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
 * Criteria class for the {@link io.shm.tsubasa.domain.MPvpRankingReward} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MPvpRankingRewardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-pvp-ranking-rewards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MPvpRankingRewardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter waveId;

    private IntegerFilter difficulty;

    private IntegerFilter rankingFrom;

    private IntegerFilter rankingTo;

    private IntegerFilter rewardGroupId;

    private LongFilter mpvpwaveId;

    public MPvpRankingRewardCriteria(){
    }

    public MPvpRankingRewardCriteria(MPvpRankingRewardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.waveId = other.waveId == null ? null : other.waveId.copy();
        this.difficulty = other.difficulty == null ? null : other.difficulty.copy();
        this.rankingFrom = other.rankingFrom == null ? null : other.rankingFrom.copy();
        this.rankingTo = other.rankingTo == null ? null : other.rankingTo.copy();
        this.rewardGroupId = other.rewardGroupId == null ? null : other.rewardGroupId.copy();
        this.mpvpwaveId = other.mpvpwaveId == null ? null : other.mpvpwaveId.copy();
    }

    @Override
    public MPvpRankingRewardCriteria copy() {
        return new MPvpRankingRewardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getWaveId() {
        return waveId;
    }

    public void setWaveId(IntegerFilter waveId) {
        this.waveId = waveId;
    }

    public IntegerFilter getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(IntegerFilter difficulty) {
        this.difficulty = difficulty;
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

    public LongFilter getMpvpwaveId() {
        return mpvpwaveId;
    }

    public void setMpvpwaveId(LongFilter mpvpwaveId) {
        this.mpvpwaveId = mpvpwaveId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MPvpRankingRewardCriteria that = (MPvpRankingRewardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(waveId, that.waveId) &&
            Objects.equals(difficulty, that.difficulty) &&
            Objects.equals(rankingFrom, that.rankingFrom) &&
            Objects.equals(rankingTo, that.rankingTo) &&
            Objects.equals(rewardGroupId, that.rewardGroupId) &&
            Objects.equals(mpvpwaveId, that.mpvpwaveId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        waveId,
        difficulty,
        rankingFrom,
        rankingTo,
        rewardGroupId,
        mpvpwaveId
        );
    }

    @Override
    public String toString() {
        return "MPvpRankingRewardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (waveId != null ? "waveId=" + waveId + ", " : "") +
                (difficulty != null ? "difficulty=" + difficulty + ", " : "") +
                (rankingFrom != null ? "rankingFrom=" + rankingFrom + ", " : "") +
                (rankingTo != null ? "rankingTo=" + rankingTo + ", " : "") +
                (rewardGroupId != null ? "rewardGroupId=" + rewardGroupId + ", " : "") +
                (mpvpwaveId != null ? "mpvpwaveId=" + mpvpwaveId + ", " : "") +
            "}";
    }

}
