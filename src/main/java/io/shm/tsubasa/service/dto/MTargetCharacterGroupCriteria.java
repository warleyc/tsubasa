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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTargetCharacterGroup} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTargetCharacterGroupResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-target-character-groups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTargetCharacterGroupCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter groupId;

    private IntegerFilter characterId;

    private LongFilter mcharacterId;

    public MTargetCharacterGroupCriteria(){
    }

    public MTargetCharacterGroupCriteria(MTargetCharacterGroupCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.characterId = other.characterId == null ? null : other.characterId.copy();
        this.mcharacterId = other.mcharacterId == null ? null : other.mcharacterId.copy();
    }

    @Override
    public MTargetCharacterGroupCriteria copy() {
        return new MTargetCharacterGroupCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getGroupId() {
        return groupId;
    }

    public void setGroupId(IntegerFilter groupId) {
        this.groupId = groupId;
    }

    public IntegerFilter getCharacterId() {
        return characterId;
    }

    public void setCharacterId(IntegerFilter characterId) {
        this.characterId = characterId;
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
        final MTargetCharacterGroupCriteria that = (MTargetCharacterGroupCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(characterId, that.characterId) &&
            Objects.equals(mcharacterId, that.mcharacterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        groupId,
        characterId,
        mcharacterId
        );
    }

    @Override
    public String toString() {
        return "MTargetCharacterGroupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (groupId != null ? "groupId=" + groupId + ", " : "") +
                (characterId != null ? "characterId=" + characterId + ", " : "") +
                (mcharacterId != null ? "mcharacterId=" + mcharacterId + ", " : "") +
            "}";
    }

}
