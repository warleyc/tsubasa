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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMarathonLoopReward} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMarathonLoopRewardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-marathon-loop-rewards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMarathonLoopRewardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter eventId;

    private IntegerFilter loopPoint;

    public MMarathonLoopRewardCriteria(){
    }

    public MMarathonLoopRewardCriteria(MMarathonLoopRewardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.eventId = other.eventId == null ? null : other.eventId.copy();
        this.loopPoint = other.loopPoint == null ? null : other.loopPoint.copy();
    }

    @Override
    public MMarathonLoopRewardCriteria copy() {
        return new MMarathonLoopRewardCriteria(this);
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

    public IntegerFilter getLoopPoint() {
        return loopPoint;
    }

    public void setLoopPoint(IntegerFilter loopPoint) {
        this.loopPoint = loopPoint;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMarathonLoopRewardCriteria that = (MMarathonLoopRewardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(eventId, that.eventId) &&
            Objects.equals(loopPoint, that.loopPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        eventId,
        loopPoint
        );
    }

    @Override
    public String toString() {
        return "MMarathonLoopRewardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (eventId != null ? "eventId=" + eventId + ", " : "") +
                (loopPoint != null ? "loopPoint=" + loopPoint + ", " : "") +
            "}";
    }

}
