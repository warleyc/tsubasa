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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMarathonLoopRewardGroup} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMarathonLoopRewardGroupResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-marathon-loop-reward-groups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMarathonLoopRewardGroupCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter eventId;

    private IntegerFilter contentType;

    private IntegerFilter contentId;

    private IntegerFilter contentAmount;

    private IntegerFilter weight;

    public MMarathonLoopRewardGroupCriteria(){
    }

    public MMarathonLoopRewardGroupCriteria(MMarathonLoopRewardGroupCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.eventId = other.eventId == null ? null : other.eventId.copy();
        this.contentType = other.contentType == null ? null : other.contentType.copy();
        this.contentId = other.contentId == null ? null : other.contentId.copy();
        this.contentAmount = other.contentAmount == null ? null : other.contentAmount.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
    }

    @Override
    public MMarathonLoopRewardGroupCriteria copy() {
        return new MMarathonLoopRewardGroupCriteria(this);
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

    public IntegerFilter getContentType() {
        return contentType;
    }

    public void setContentType(IntegerFilter contentType) {
        this.contentType = contentType;
    }

    public IntegerFilter getContentId() {
        return contentId;
    }

    public void setContentId(IntegerFilter contentId) {
        this.contentId = contentId;
    }

    public IntegerFilter getContentAmount() {
        return contentAmount;
    }

    public void setContentAmount(IntegerFilter contentAmount) {
        this.contentAmount = contentAmount;
    }

    public IntegerFilter getWeight() {
        return weight;
    }

    public void setWeight(IntegerFilter weight) {
        this.weight = weight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMarathonLoopRewardGroupCriteria that = (MMarathonLoopRewardGroupCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(eventId, that.eventId) &&
            Objects.equals(contentType, that.contentType) &&
            Objects.equals(contentId, that.contentId) &&
            Objects.equals(contentAmount, that.contentAmount) &&
            Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        eventId,
        contentType,
        contentId,
        contentAmount,
        weight
        );
    }

    @Override
    public String toString() {
        return "MMarathonLoopRewardGroupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (eventId != null ? "eventId=" + eventId + ", " : "") +
                (contentType != null ? "contentType=" + contentType + ", " : "") +
                (contentId != null ? "contentId=" + contentId + ", " : "") +
                (contentAmount != null ? "contentAmount=" + contentAmount + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
            "}";
    }

}
