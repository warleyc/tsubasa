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
 * Criteria class for the {@link io.shm.tsubasa.domain.MQuestAchievementGroup} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MQuestAchievementGroupResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-quest-achievement-groups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MQuestAchievementGroupCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter groupId;

    private IntegerFilter achievementType;

    private IntegerFilter rank;

    private IntegerFilter weight;

    private IntegerFilter contentType;

    private IntegerFilter contentId;

    private IntegerFilter contentAmount;

    public MQuestAchievementGroupCriteria(){
    }

    public MQuestAchievementGroupCriteria(MQuestAchievementGroupCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.achievementType = other.achievementType == null ? null : other.achievementType.copy();
        this.rank = other.rank == null ? null : other.rank.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
        this.contentType = other.contentType == null ? null : other.contentType.copy();
        this.contentId = other.contentId == null ? null : other.contentId.copy();
        this.contentAmount = other.contentAmount == null ? null : other.contentAmount.copy();
    }

    @Override
    public MQuestAchievementGroupCriteria copy() {
        return new MQuestAchievementGroupCriteria(this);
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

    public IntegerFilter getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(IntegerFilter achievementType) {
        this.achievementType = achievementType;
    }

    public IntegerFilter getRank() {
        return rank;
    }

    public void setRank(IntegerFilter rank) {
        this.rank = rank;
    }

    public IntegerFilter getWeight() {
        return weight;
    }

    public void setWeight(IntegerFilter weight) {
        this.weight = weight;
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
        final MQuestAchievementGroupCriteria that = (MQuestAchievementGroupCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(achievementType, that.achievementType) &&
            Objects.equals(rank, that.rank) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(contentType, that.contentType) &&
            Objects.equals(contentId, that.contentId) &&
            Objects.equals(contentAmount, that.contentAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        groupId,
        achievementType,
        rank,
        weight,
        contentType,
        contentId,
        contentAmount
        );
    }

    @Override
    public String toString() {
        return "MQuestAchievementGroupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (groupId != null ? "groupId=" + groupId + ", " : "") +
                (achievementType != null ? "achievementType=" + achievementType + ", " : "") +
                (rank != null ? "rank=" + rank + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (contentType != null ? "contentType=" + contentType + ", " : "") +
                (contentId != null ? "contentId=" + contentId + ", " : "") +
                (contentAmount != null ? "contentAmount=" + contentAmount + ", " : "") +
            "}";
    }

}
