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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMarathonEffectiveCard} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMarathonEffectiveCardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-marathon-effective-cards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMarathonEffectiveCardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter eventId;

    private IntegerFilter playableCardId;

    private IntegerFilter rate;

    public MMarathonEffectiveCardCriteria(){
    }

    public MMarathonEffectiveCardCriteria(MMarathonEffectiveCardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.eventId = other.eventId == null ? null : other.eventId.copy();
        this.playableCardId = other.playableCardId == null ? null : other.playableCardId.copy();
        this.rate = other.rate == null ? null : other.rate.copy();
    }

    @Override
    public MMarathonEffectiveCardCriteria copy() {
        return new MMarathonEffectiveCardCriteria(this);
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

    public IntegerFilter getPlayableCardId() {
        return playableCardId;
    }

    public void setPlayableCardId(IntegerFilter playableCardId) {
        this.playableCardId = playableCardId;
    }

    public IntegerFilter getRate() {
        return rate;
    }

    public void setRate(IntegerFilter rate) {
        this.rate = rate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMarathonEffectiveCardCriteria that = (MMarathonEffectiveCardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(eventId, that.eventId) &&
            Objects.equals(playableCardId, that.playableCardId) &&
            Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        eventId,
        playableCardId,
        rate
        );
    }

    @Override
    public String toString() {
        return "MMarathonEffectiveCardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (eventId != null ? "eventId=" + eventId + ", " : "") +
                (playableCardId != null ? "playableCardId=" + playableCardId + ", " : "") +
                (rate != null ? "rate=" + rate + ", " : "") +
            "}";
    }

}
