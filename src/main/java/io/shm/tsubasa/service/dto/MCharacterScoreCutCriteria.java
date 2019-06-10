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
 * Criteria class for the {@link io.shm.tsubasa.domain.MCharacterScoreCut} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MCharacterScoreCutResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-character-score-cuts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MCharacterScoreCutCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter characterId;

    private IntegerFilter teamId;

    private IntegerFilter scoreCutType;

    public MCharacterScoreCutCriteria(){
    }

    public MCharacterScoreCutCriteria(MCharacterScoreCutCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.characterId = other.characterId == null ? null : other.characterId.copy();
        this.teamId = other.teamId == null ? null : other.teamId.copy();
        this.scoreCutType = other.scoreCutType == null ? null : other.scoreCutType.copy();
    }

    @Override
    public MCharacterScoreCutCriteria copy() {
        return new MCharacterScoreCutCriteria(this);
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

    public IntegerFilter getScoreCutType() {
        return scoreCutType;
    }

    public void setScoreCutType(IntegerFilter scoreCutType) {
        this.scoreCutType = scoreCutType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MCharacterScoreCutCriteria that = (MCharacterScoreCutCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(characterId, that.characterId) &&
            Objects.equals(teamId, that.teamId) &&
            Objects.equals(scoreCutType, that.scoreCutType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        characterId,
        teamId,
        scoreCutType
        );
    }

    @Override
    public String toString() {
        return "MCharacterScoreCutCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (characterId != null ? "characterId=" + characterId + ", " : "") +
                (teamId != null ? "teamId=" + teamId + ", " : "") +
                (scoreCutType != null ? "scoreCutType=" + scoreCutType + ", " : "") +
            "}";
    }

}
