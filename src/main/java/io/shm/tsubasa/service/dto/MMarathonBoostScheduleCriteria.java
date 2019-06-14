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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMarathonBoostSchedule} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMarathonBoostScheduleResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-marathon-boost-schedules?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMarathonBoostScheduleCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter eventId;

    private IntegerFilter boostRatio;

    private IntegerFilter startAt;

    private IntegerFilter endAt;

    public MMarathonBoostScheduleCriteria(){
    }

    public MMarathonBoostScheduleCriteria(MMarathonBoostScheduleCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.eventId = other.eventId == null ? null : other.eventId.copy();
        this.boostRatio = other.boostRatio == null ? null : other.boostRatio.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.endAt = other.endAt == null ? null : other.endAt.copy();
    }

    @Override
    public MMarathonBoostScheduleCriteria copy() {
        return new MMarathonBoostScheduleCriteria(this);
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

    public IntegerFilter getBoostRatio() {
        return boostRatio;
    }

    public void setBoostRatio(IntegerFilter boostRatio) {
        this.boostRatio = boostRatio;
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
        final MMarathonBoostScheduleCriteria that = (MMarathonBoostScheduleCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(eventId, that.eventId) &&
            Objects.equals(boostRatio, that.boostRatio) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        eventId,
        boostRatio,
        startAt,
        endAt
        );
    }

    @Override
    public String toString() {
        return "MMarathonBoostScheduleCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (eventId != null ? "eventId=" + eventId + ", " : "") +
                (boostRatio != null ? "boostRatio=" + boostRatio + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (endAt != null ? "endAt=" + endAt + ", " : "") +
            "}";
    }

}
