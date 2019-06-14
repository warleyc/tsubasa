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
 * Criteria class for the {@link io.shm.tsubasa.domain.MCharacter} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MCharacterResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-characters?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MCharacterCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter characterBookPriority;

    private LongFilter mActionSkillHolderCardContentId;

    private LongFilter mCombinationCutPositionId;

    private LongFilter mMatchResultCutinId;

    private LongFilter mNpcCardId;

    private LongFilter mTargetCharacterGroupId;

    public MCharacterCriteria(){
    }

    public MCharacterCriteria(MCharacterCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.characterBookPriority = other.characterBookPriority == null ? null : other.characterBookPriority.copy();
        this.mActionSkillHolderCardContentId = other.mActionSkillHolderCardContentId == null ? null : other.mActionSkillHolderCardContentId.copy();
        this.mCombinationCutPositionId = other.mCombinationCutPositionId == null ? null : other.mCombinationCutPositionId.copy();
        this.mMatchResultCutinId = other.mMatchResultCutinId == null ? null : other.mMatchResultCutinId.copy();
        this.mNpcCardId = other.mNpcCardId == null ? null : other.mNpcCardId.copy();
        this.mTargetCharacterGroupId = other.mTargetCharacterGroupId == null ? null : other.mTargetCharacterGroupId.copy();
    }

    @Override
    public MCharacterCriteria copy() {
        return new MCharacterCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getCharacterBookPriority() {
        return characterBookPriority;
    }

    public void setCharacterBookPriority(IntegerFilter characterBookPriority) {
        this.characterBookPriority = characterBookPriority;
    }

    public LongFilter getMActionSkillHolderCardContentId() {
        return mActionSkillHolderCardContentId;
    }

    public void setMActionSkillHolderCardContentId(LongFilter mActionSkillHolderCardContentId) {
        this.mActionSkillHolderCardContentId = mActionSkillHolderCardContentId;
    }

    public LongFilter getMCombinationCutPositionId() {
        return mCombinationCutPositionId;
    }

    public void setMCombinationCutPositionId(LongFilter mCombinationCutPositionId) {
        this.mCombinationCutPositionId = mCombinationCutPositionId;
    }

    public LongFilter getMMatchResultCutinId() {
        return mMatchResultCutinId;
    }

    public void setMMatchResultCutinId(LongFilter mMatchResultCutinId) {
        this.mMatchResultCutinId = mMatchResultCutinId;
    }

    public LongFilter getMNpcCardId() {
        return mNpcCardId;
    }

    public void setMNpcCardId(LongFilter mNpcCardId) {
        this.mNpcCardId = mNpcCardId;
    }

    public LongFilter getMTargetCharacterGroupId() {
        return mTargetCharacterGroupId;
    }

    public void setMTargetCharacterGroupId(LongFilter mTargetCharacterGroupId) {
        this.mTargetCharacterGroupId = mTargetCharacterGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MCharacterCriteria that = (MCharacterCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(characterBookPriority, that.characterBookPriority) &&
            Objects.equals(mActionSkillHolderCardContentId, that.mActionSkillHolderCardContentId) &&
            Objects.equals(mCombinationCutPositionId, that.mCombinationCutPositionId) &&
            Objects.equals(mMatchResultCutinId, that.mMatchResultCutinId) &&
            Objects.equals(mNpcCardId, that.mNpcCardId) &&
            Objects.equals(mTargetCharacterGroupId, that.mTargetCharacterGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        characterBookPriority,
        mActionSkillHolderCardContentId,
        mCombinationCutPositionId,
        mMatchResultCutinId,
        mNpcCardId,
        mTargetCharacterGroupId
        );
    }

    @Override
    public String toString() {
        return "MCharacterCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (characterBookPriority != null ? "characterBookPriority=" + characterBookPriority + ", " : "") +
                (mActionSkillHolderCardContentId != null ? "mActionSkillHolderCardContentId=" + mActionSkillHolderCardContentId + ", " : "") +
                (mCombinationCutPositionId != null ? "mCombinationCutPositionId=" + mCombinationCutPositionId + ", " : "") +
                (mMatchResultCutinId != null ? "mMatchResultCutinId=" + mMatchResultCutinId + ", " : "") +
                (mNpcCardId != null ? "mNpcCardId=" + mNpcCardId + ", " : "") +
                (mTargetCharacterGroupId != null ? "mTargetCharacterGroupId=" + mTargetCharacterGroupId + ", " : "") +
            "}";
    }

}
