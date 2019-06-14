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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMarathonQuestTsubasaPointReward} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMarathonQuestTsubasaPointRewardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-marathon-quest-tsubasa-point-rewards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMarathonQuestTsubasaPointRewardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter stageId;

    private IntegerFilter tsubasaPoint;

    private IntegerFilter contentType;

    private IntegerFilter contentId;

    private IntegerFilter contentAmount;

    public MMarathonQuestTsubasaPointRewardCriteria(){
    }

    public MMarathonQuestTsubasaPointRewardCriteria(MMarathonQuestTsubasaPointRewardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.stageId = other.stageId == null ? null : other.stageId.copy();
        this.tsubasaPoint = other.tsubasaPoint == null ? null : other.tsubasaPoint.copy();
        this.contentType = other.contentType == null ? null : other.contentType.copy();
        this.contentId = other.contentId == null ? null : other.contentId.copy();
        this.contentAmount = other.contentAmount == null ? null : other.contentAmount.copy();
    }

    @Override
    public MMarathonQuestTsubasaPointRewardCriteria copy() {
        return new MMarathonQuestTsubasaPointRewardCriteria(this);
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

    public IntegerFilter getTsubasaPoint() {
        return tsubasaPoint;
    }

    public void setTsubasaPoint(IntegerFilter tsubasaPoint) {
        this.tsubasaPoint = tsubasaPoint;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMarathonQuestTsubasaPointRewardCriteria that = (MMarathonQuestTsubasaPointRewardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(stageId, that.stageId) &&
            Objects.equals(tsubasaPoint, that.tsubasaPoint) &&
            Objects.equals(contentType, that.contentType) &&
            Objects.equals(contentId, that.contentId) &&
            Objects.equals(contentAmount, that.contentAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        stageId,
        tsubasaPoint,
        contentType,
        contentId,
        contentAmount
        );
    }

    @Override
    public String toString() {
        return "MMarathonQuestTsubasaPointRewardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (stageId != null ? "stageId=" + stageId + ", " : "") +
                (tsubasaPoint != null ? "tsubasaPoint=" + tsubasaPoint + ", " : "") +
                (contentType != null ? "contentType=" + contentType + ", " : "") +
                (contentId != null ? "contentId=" + contentId + ", " : "") +
                (contentAmount != null ? "contentAmount=" + contentAmount + ", " : "") +
            "}";
    }

}
