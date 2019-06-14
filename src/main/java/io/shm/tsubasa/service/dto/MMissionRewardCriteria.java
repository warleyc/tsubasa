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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMissionReward} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMissionRewardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-mission-rewards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMissionRewardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter contentType;

    private IntegerFilter contentId;

    private IntegerFilter contentAmount;

    private LongFilter mGuildMissionId;

    private LongFilter mMissionId;

    public MMissionRewardCriteria(){
    }

    public MMissionRewardCriteria(MMissionRewardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.contentType = other.contentType == null ? null : other.contentType.copy();
        this.contentId = other.contentId == null ? null : other.contentId.copy();
        this.contentAmount = other.contentAmount == null ? null : other.contentAmount.copy();
        this.mGuildMissionId = other.mGuildMissionId == null ? null : other.mGuildMissionId.copy();
        this.mMissionId = other.mMissionId == null ? null : other.mMissionId.copy();
    }

    @Override
    public MMissionRewardCriteria copy() {
        return new MMissionRewardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public LongFilter getMGuildMissionId() {
        return mGuildMissionId;
    }

    public void setMGuildMissionId(LongFilter mGuildMissionId) {
        this.mGuildMissionId = mGuildMissionId;
    }

    public LongFilter getMMissionId() {
        return mMissionId;
    }

    public void setMMissionId(LongFilter mMissionId) {
        this.mMissionId = mMissionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMissionRewardCriteria that = (MMissionRewardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(contentType, that.contentType) &&
            Objects.equals(contentId, that.contentId) &&
            Objects.equals(contentAmount, that.contentAmount) &&
            Objects.equals(mGuildMissionId, that.mGuildMissionId) &&
            Objects.equals(mMissionId, that.mMissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        contentType,
        contentId,
        contentAmount,
        mGuildMissionId,
        mMissionId
        );
    }

    @Override
    public String toString() {
        return "MMissionRewardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (contentType != null ? "contentType=" + contentType + ", " : "") +
                (contentId != null ? "contentId=" + contentId + ", " : "") +
                (contentAmount != null ? "contentAmount=" + contentAmount + ", " : "") +
                (mGuildMissionId != null ? "mGuildMissionId=" + mGuildMissionId + ", " : "") +
                (mMissionId != null ? "mMissionId=" + mMissionId + ", " : "") +
            "}";
    }

}
