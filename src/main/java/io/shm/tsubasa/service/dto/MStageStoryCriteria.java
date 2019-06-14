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
 * Criteria class for the {@link io.shm.tsubasa.domain.MStageStory} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MStageStoryResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-stage-stories?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MStageStoryCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter stageId;

    private IntegerFilter eventType;

    public MStageStoryCriteria(){
    }

    public MStageStoryCriteria(MStageStoryCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.stageId = other.stageId == null ? null : other.stageId.copy();
        this.eventType = other.eventType == null ? null : other.eventType.copy();
    }

    @Override
    public MStageStoryCriteria copy() {
        return new MStageStoryCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getStageId() {
        return stageId;
    }

    public void setStageId(IntegerFilter stageId) {
        this.stageId = stageId;
    }

    public IntegerFilter getEventType() {
        return eventType;
    }

    public void setEventType(IntegerFilter eventType) {
        this.eventType = eventType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MStageStoryCriteria that = (MStageStoryCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(stageId, that.stageId) &&
            Objects.equals(eventType, that.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        stageId,
        eventType
        );
    }

    @Override
    public String toString() {
        return "MStageStoryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (stageId != null ? "stageId=" + stageId + ", " : "") +
                (eventType != null ? "eventType=" + eventType + ", " : "") +
            "}";
    }

}
