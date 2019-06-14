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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGoalEffectiveCard} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGoalEffectiveCardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-goal-effective-cards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGoalEffectiveCardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter eventType;

    private IntegerFilter eventId;

    private IntegerFilter playableCardId;

    private IntegerFilter rate;

    public MGoalEffectiveCardCriteria(){
    }

    public MGoalEffectiveCardCriteria(MGoalEffectiveCardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.eventType = other.eventType == null ? null : other.eventType.copy();
        this.eventId = other.eventId == null ? null : other.eventId.copy();
        this.playableCardId = other.playableCardId == null ? null : other.playableCardId.copy();
        this.rate = other.rate == null ? null : other.rate.copy();
    }

    @Override
    public MGoalEffectiveCardCriteria copy() {
        return new MGoalEffectiveCardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getEventType() {
        return eventType;
    }

    public void setEventType(IntegerFilter eventType) {
        this.eventType = eventType;
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
        final MGoalEffectiveCardCriteria that = (MGoalEffectiveCardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(eventType, that.eventType) &&
            Objects.equals(eventId, that.eventId) &&
            Objects.equals(playableCardId, that.playableCardId) &&
            Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        eventType,
        eventId,
        playableCardId,
        rate
        );
    }

    @Override
    public String toString() {
        return "MGoalEffectiveCardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (eventType != null ? "eventType=" + eventType + ", " : "") +
                (eventId != null ? "eventId=" + eventId + ", " : "") +
                (playableCardId != null ? "playableCardId=" + playableCardId + ", " : "") +
                (rate != null ? "rate=" + rate + ", " : "") +
            "}";
    }

}
