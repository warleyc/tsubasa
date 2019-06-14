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
 * Criteria class for the {@link io.shm.tsubasa.domain.MPvpRegulation} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MPvpRegulationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-pvp-regulations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MPvpRegulationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter startAt;

    private IntegerFilter endAt;

    private IntegerFilter matchOptionId;

    private IntegerFilter deckConditionId;

    private IntegerFilter ruleTutorialId;

    private LongFilter idId;

    public MPvpRegulationCriteria(){
    }

    public MPvpRegulationCriteria(MPvpRegulationCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.endAt = other.endAt == null ? null : other.endAt.copy();
        this.matchOptionId = other.matchOptionId == null ? null : other.matchOptionId.copy();
        this.deckConditionId = other.deckConditionId == null ? null : other.deckConditionId.copy();
        this.ruleTutorialId = other.ruleTutorialId == null ? null : other.ruleTutorialId.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
    }

    @Override
    public MPvpRegulationCriteria copy() {
        return new MPvpRegulationCriteria(this);
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

    public IntegerFilter getMatchOptionId() {
        return matchOptionId;
    }

    public void setMatchOptionId(IntegerFilter matchOptionId) {
        this.matchOptionId = matchOptionId;
    }

    public IntegerFilter getDeckConditionId() {
        return deckConditionId;
    }

    public void setDeckConditionId(IntegerFilter deckConditionId) {
        this.deckConditionId = deckConditionId;
    }

    public IntegerFilter getRuleTutorialId() {
        return ruleTutorialId;
    }

    public void setRuleTutorialId(IntegerFilter ruleTutorialId) {
        this.ruleTutorialId = ruleTutorialId;
    }

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MPvpRegulationCriteria that = (MPvpRegulationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(endAt, that.endAt) &&
            Objects.equals(matchOptionId, that.matchOptionId) &&
            Objects.equals(deckConditionId, that.deckConditionId) &&
            Objects.equals(ruleTutorialId, that.ruleTutorialId) &&
            Objects.equals(idId, that.idId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        startAt,
        endAt,
        matchOptionId,
        deckConditionId,
        ruleTutorialId,
        idId
        );
    }

    @Override
    public String toString() {
        return "MPvpRegulationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (endAt != null ? "endAt=" + endAt + ", " : "") +
                (matchOptionId != null ? "matchOptionId=" + matchOptionId + ", " : "") +
                (deckConditionId != null ? "deckConditionId=" + deckConditionId + ", " : "") +
                (ruleTutorialId != null ? "ruleTutorialId=" + ruleTutorialId + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
            "}";
    }

}
