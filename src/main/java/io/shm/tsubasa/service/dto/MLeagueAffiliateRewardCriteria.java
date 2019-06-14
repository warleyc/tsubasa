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
 * Criteria class for the {@link io.shm.tsubasa.domain.MLeagueAffiliateReward} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MLeagueAffiliateRewardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-league-affiliate-rewards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MLeagueAffiliateRewardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter hierarchy;

    private IntegerFilter contentType;

    private IntegerFilter contentId;

    private IntegerFilter contentAmount;

    public MLeagueAffiliateRewardCriteria(){
    }

    public MLeagueAffiliateRewardCriteria(MLeagueAffiliateRewardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.hierarchy = other.hierarchy == null ? null : other.hierarchy.copy();
        this.contentType = other.contentType == null ? null : other.contentType.copy();
        this.contentId = other.contentId == null ? null : other.contentId.copy();
        this.contentAmount = other.contentAmount == null ? null : other.contentAmount.copy();
    }

    @Override
    public MLeagueAffiliateRewardCriteria copy() {
        return new MLeagueAffiliateRewardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(IntegerFilter hierarchy) {
        this.hierarchy = hierarchy;
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
        final MLeagueAffiliateRewardCriteria that = (MLeagueAffiliateRewardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(hierarchy, that.hierarchy) &&
            Objects.equals(contentType, that.contentType) &&
            Objects.equals(contentId, that.contentId) &&
            Objects.equals(contentAmount, that.contentAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        hierarchy,
        contentType,
        contentId,
        contentAmount
        );
    }

    @Override
    public String toString() {
        return "MLeagueAffiliateRewardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (hierarchy != null ? "hierarchy=" + hierarchy + ", " : "") +
                (contentType != null ? "contentType=" + contentType + ", " : "") +
                (contentId != null ? "contentId=" + contentId + ", " : "") +
                (contentAmount != null ? "contentAmount=" + contentAmount + ", " : "") +
            "}";
    }

}
