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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMatchResultCutin} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMatchResultCutinResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-match-result-cutins?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMatchResultCutinCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter characterId;

    private IntegerFilter teamId;

    private IntegerFilter isWin;

    private LongFilter idId;

    public MMatchResultCutinCriteria(){
    }

    public MMatchResultCutinCriteria(MMatchResultCutinCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.characterId = other.characterId == null ? null : other.characterId.copy();
        this.teamId = other.teamId == null ? null : other.teamId.copy();
        this.isWin = other.isWin == null ? null : other.isWin.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
    }

    @Override
    public MMatchResultCutinCriteria copy() {
        return new MMatchResultCutinCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getCharacterId() {
        return characterId;
    }

    public void setCharacterId(IntegerFilter characterId) {
        this.characterId = characterId;
    }

    public IntegerFilter getTeamId() {
        return teamId;
    }

    public void setTeamId(IntegerFilter teamId) {
        this.teamId = teamId;
    }

    public IntegerFilter getIsWin() {
        return isWin;
    }

    public void setIsWin(IntegerFilter isWin) {
        this.isWin = isWin;
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
        final MMatchResultCutinCriteria that = (MMatchResultCutinCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(characterId, that.characterId) &&
            Objects.equals(teamId, that.teamId) &&
            Objects.equals(isWin, that.isWin) &&
            Objects.equals(idId, that.idId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        characterId,
        teamId,
        isWin,
        idId
        );
    }

    @Override
    public String toString() {
        return "MMatchResultCutinCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (characterId != null ? "characterId=" + characterId + ", " : "") +
                (teamId != null ? "teamId=" + teamId + ", " : "") +
                (isWin != null ? "isWin=" + isWin + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
            "}";
    }

}
