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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMarathonAchievementReward} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMarathonAchievementRewardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-marathon-achievement-rewards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMarathonAchievementRewardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter eventId;

    private IntegerFilter eventPoint;

    private IntegerFilter rewardGroupId;

    public MMarathonAchievementRewardCriteria(){
    }

    public MMarathonAchievementRewardCriteria(MMarathonAchievementRewardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.eventId = other.eventId == null ? null : other.eventId.copy();
        this.eventPoint = other.eventPoint == null ? null : other.eventPoint.copy();
        this.rewardGroupId = other.rewardGroupId == null ? null : other.rewardGroupId.copy();
    }

    @Override
    public MMarathonAchievementRewardCriteria copy() {
        return new MMarathonAchievementRewardCriteria(this);
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

    public IntegerFilter getEventPoint() {
        return eventPoint;
    }

    public void setEventPoint(IntegerFilter eventPoint) {
        this.eventPoint = eventPoint;
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
        final MMarathonAchievementRewardCriteria that = (MMarathonAchievementRewardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(eventId, that.eventId) &&
            Objects.equals(eventPoint, that.eventPoint) &&
            Objects.equals(rewardGroupId, that.rewardGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        eventId,
        eventPoint,
        rewardGroupId
        );
    }

    @Override
    public String toString() {
        return "MMarathonAchievementRewardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (eventId != null ? "eventId=" + eventId + ", " : "") +
                (eventPoint != null ? "eventPoint=" + eventPoint + ", " : "") +
                (rewardGroupId != null ? "rewardGroupId=" + rewardGroupId + ", " : "") +
            "}";
    }

}
