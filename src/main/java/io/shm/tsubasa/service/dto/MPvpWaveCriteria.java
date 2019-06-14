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
 * Criteria class for the {@link io.shm.tsubasa.domain.MPvpWave} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MPvpWaveResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-pvp-waves?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MPvpWaveCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter startAt;

    private IntegerFilter endAt;

    private IntegerFilter isRanking;

    private LongFilter mPvpRankingRewardId;

    public MPvpWaveCriteria(){
    }

    public MPvpWaveCriteria(MPvpWaveCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.endAt = other.endAt == null ? null : other.endAt.copy();
        this.isRanking = other.isRanking == null ? null : other.isRanking.copy();
        this.mPvpRankingRewardId = other.mPvpRankingRewardId == null ? null : other.mPvpRankingRewardId.copy();
    }

    @Override
    public MPvpWaveCriteria copy() {
        return new MPvpWaveCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public IntegerFilter getIsRanking() {
        return isRanking;
    }

    public void setIsRanking(IntegerFilter isRanking) {
        this.isRanking = isRanking;
    }

    public LongFilter getMPvpRankingRewardId() {
        return mPvpRankingRewardId;
    }

    public void setMPvpRankingRewardId(LongFilter mPvpRankingRewardId) {
        this.mPvpRankingRewardId = mPvpRankingRewardId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MPvpWaveCriteria that = (MPvpWaveCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(endAt, that.endAt) &&
            Objects.equals(isRanking, that.isRanking) &&
            Objects.equals(mPvpRankingRewardId, that.mPvpRankingRewardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        startAt,
        endAt,
        isRanking,
        mPvpRankingRewardId
        );
    }

    @Override
    public String toString() {
        return "MPvpWaveCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (endAt != null ? "endAt=" + endAt + ", " : "") +
                (isRanking != null ? "isRanking=" + isRanking + ", " : "") +
                (mPvpRankingRewardId != null ? "mPvpRankingRewardId=" + mPvpRankingRewardId + ", " : "") +
            "}";
    }

}
