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
 * Criteria class for the {@link io.shm.tsubasa.domain.MCombinationCutPosition} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MCombinationCutPositionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-combination-cut-positions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MCombinationCutPositionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter actionCutId;

    private IntegerFilter characterId;

    private IntegerFilter activatorPosition;

    private IntegerFilter participantPosition1;

    private IntegerFilter participantPosition2;

    private IntegerFilter participantPosition3;

    private IntegerFilter participantPosition4;

    private IntegerFilter participantPosition5;

    private LongFilter mcharacterId;

    public MCombinationCutPositionCriteria(){
    }

    public MCombinationCutPositionCriteria(MCombinationCutPositionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.actionCutId = other.actionCutId == null ? null : other.actionCutId.copy();
        this.characterId = other.characterId == null ? null : other.characterId.copy();
        this.activatorPosition = other.activatorPosition == null ? null : other.activatorPosition.copy();
        this.participantPosition1 = other.participantPosition1 == null ? null : other.participantPosition1.copy();
        this.participantPosition2 = other.participantPosition2 == null ? null : other.participantPosition2.copy();
        this.participantPosition3 = other.participantPosition3 == null ? null : other.participantPosition3.copy();
        this.participantPosition4 = other.participantPosition4 == null ? null : other.participantPosition4.copy();
        this.participantPosition5 = other.participantPosition5 == null ? null : other.participantPosition5.copy();
        this.mcharacterId = other.mcharacterId == null ? null : other.mcharacterId.copy();
    }

    @Override
    public MCombinationCutPositionCriteria copy() {
        return new MCombinationCutPositionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getActionCutId() {
        return actionCutId;
    }

    public void setActionCutId(IntegerFilter actionCutId) {
        this.actionCutId = actionCutId;
    }

    public IntegerFilter getCharacterId() {
        return characterId;
    }

    public void setCharacterId(IntegerFilter characterId) {
        this.characterId = characterId;
    }

    public IntegerFilter getActivatorPosition() {
        return activatorPosition;
    }

    public void setActivatorPosition(IntegerFilter activatorPosition) {
        this.activatorPosition = activatorPosition;
    }

    public IntegerFilter getParticipantPosition1() {
        return participantPosition1;
    }

    public void setParticipantPosition1(IntegerFilter participantPosition1) {
        this.participantPosition1 = participantPosition1;
    }

    public IntegerFilter getParticipantPosition2() {
        return participantPosition2;
    }

    public void setParticipantPosition2(IntegerFilter participantPosition2) {
        this.participantPosition2 = participantPosition2;
    }

    public IntegerFilter getParticipantPosition3() {
        return participantPosition3;
    }

    public void setParticipantPosition3(IntegerFilter participantPosition3) {
        this.participantPosition3 = participantPosition3;
    }

    public IntegerFilter getParticipantPosition4() {
        return participantPosition4;
    }

    public void setParticipantPosition4(IntegerFilter participantPosition4) {
        this.participantPosition4 = participantPosition4;
    }

    public IntegerFilter getParticipantPosition5() {
        return participantPosition5;
    }

    public void setParticipantPosition5(IntegerFilter participantPosition5) {
        this.participantPosition5 = participantPosition5;
    }

    public LongFilter getMcharacterId() {
        return mcharacterId;
    }

    public void setMcharacterId(LongFilter mcharacterId) {
        this.mcharacterId = mcharacterId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MCombinationCutPositionCriteria that = (MCombinationCutPositionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(actionCutId, that.actionCutId) &&
            Objects.equals(characterId, that.characterId) &&
            Objects.equals(activatorPosition, that.activatorPosition) &&
            Objects.equals(participantPosition1, that.participantPosition1) &&
            Objects.equals(participantPosition2, that.participantPosition2) &&
            Objects.equals(participantPosition3, that.participantPosition3) &&
            Objects.equals(participantPosition4, that.participantPosition4) &&
            Objects.equals(participantPosition5, that.participantPosition5) &&
            Objects.equals(mcharacterId, that.mcharacterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        actionCutId,
        characterId,
        activatorPosition,
        participantPosition1,
        participantPosition2,
        participantPosition3,
        participantPosition4,
        participantPosition5,
        mcharacterId
        );
    }

    @Override
    public String toString() {
        return "MCombinationCutPositionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (actionCutId != null ? "actionCutId=" + actionCutId + ", " : "") +
                (characterId != null ? "characterId=" + characterId + ", " : "") +
                (activatorPosition != null ? "activatorPosition=" + activatorPosition + ", " : "") +
                (participantPosition1 != null ? "participantPosition1=" + participantPosition1 + ", " : "") +
                (participantPosition2 != null ? "participantPosition2=" + participantPosition2 + ", " : "") +
                (participantPosition3 != null ? "participantPosition3=" + participantPosition3 + ", " : "") +
                (participantPosition4 != null ? "participantPosition4=" + participantPosition4 + ", " : "") +
                (participantPosition5 != null ? "participantPosition5=" + participantPosition5 + ", " : "") +
                (mcharacterId != null ? "mcharacterId=" + mcharacterId + ", " : "") +
            "}";
    }

}
