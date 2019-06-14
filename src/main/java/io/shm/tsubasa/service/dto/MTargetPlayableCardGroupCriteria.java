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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTargetPlayableCardGroup} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTargetPlayableCardGroupResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-target-playable-card-groups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTargetPlayableCardGroupCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter groupId;

    private IntegerFilter cardId;

    private IntegerFilter isShowThumbnail;

    private LongFilter mplayablecardId;

    public MTargetPlayableCardGroupCriteria(){
    }

    public MTargetPlayableCardGroupCriteria(MTargetPlayableCardGroupCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.cardId = other.cardId == null ? null : other.cardId.copy();
        this.isShowThumbnail = other.isShowThumbnail == null ? null : other.isShowThumbnail.copy();
        this.mplayablecardId = other.mplayablecardId == null ? null : other.mplayablecardId.copy();
    }

    @Override
    public MTargetPlayableCardGroupCriteria copy() {
        return new MTargetPlayableCardGroupCriteria(this);
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

    public IntegerFilter getCardId() {
        return cardId;
    }

    public void setCardId(IntegerFilter cardId) {
        this.cardId = cardId;
    }

    public IntegerFilter getIsShowThumbnail() {
        return isShowThumbnail;
    }

    public void setIsShowThumbnail(IntegerFilter isShowThumbnail) {
        this.isShowThumbnail = isShowThumbnail;
    }

    public LongFilter getMplayablecardId() {
        return mplayablecardId;
    }

    public void setMplayablecardId(LongFilter mplayablecardId) {
        this.mplayablecardId = mplayablecardId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTargetPlayableCardGroupCriteria that = (MTargetPlayableCardGroupCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(cardId, that.cardId) &&
            Objects.equals(isShowThumbnail, that.isShowThumbnail) &&
            Objects.equals(mplayablecardId, that.mplayablecardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        groupId,
        cardId,
        isShowThumbnail,
        mplayablecardId
        );
    }

    @Override
    public String toString() {
        return "MTargetPlayableCardGroupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (groupId != null ? "groupId=" + groupId + ", " : "") +
                (cardId != null ? "cardId=" + cardId + ", " : "") +
                (isShowThumbnail != null ? "isShowThumbnail=" + isShowThumbnail + ", " : "") +
                (mplayablecardId != null ? "mplayablecardId=" + mplayablecardId + ", " : "") +
            "}";
    }

}
