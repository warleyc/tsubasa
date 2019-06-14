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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMarathonBoostItem} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMarathonBoostItemResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-marathon-boost-items?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMarathonBoostItemCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter eventId;

    private IntegerFilter boostRatio;

    public MMarathonBoostItemCriteria(){
    }

    public MMarathonBoostItemCriteria(MMarathonBoostItemCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.eventId = other.eventId == null ? null : other.eventId.copy();
        this.boostRatio = other.boostRatio == null ? null : other.boostRatio.copy();
    }

    @Override
    public MMarathonBoostItemCriteria copy() {
        return new MMarathonBoostItemCriteria(this);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMarathonBoostItemCriteria that = (MMarathonBoostItemCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(eventId, that.eventId) &&
            Objects.equals(boostRatio, that.boostRatio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        eventId,
        boostRatio
        );
    }

    @Override
    public String toString() {
        return "MMarathonBoostItemCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (eventId != null ? "eventId=" + eventId + ", " : "") +
                (boostRatio != null ? "boostRatio=" + boostRatio + ", " : "") +
            "}";
    }

}
