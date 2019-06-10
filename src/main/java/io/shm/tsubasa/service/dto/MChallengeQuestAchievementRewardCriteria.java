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
 * Criteria class for the {@link io.shm.tsubasa.domain.MChallengeQuestAchievementReward} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MChallengeQuestAchievementRewardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-challenge-quest-achievement-rewards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MChallengeQuestAchievementRewardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter worldId;

    private IntegerFilter stageId;

    private IntegerFilter rewardGroupId;

    public MChallengeQuestAchievementRewardCriteria(){
    }

    public MChallengeQuestAchievementRewardCriteria(MChallengeQuestAchievementRewardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.worldId = other.worldId == null ? null : other.worldId.copy();
        this.stageId = other.stageId == null ? null : other.stageId.copy();
        this.rewardGroupId = other.rewardGroupId == null ? null : other.rewardGroupId.copy();
    }

    @Override
    public MChallengeQuestAchievementRewardCriteria copy() {
        return new MChallengeQuestAchievementRewardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getWorldId() {
        return worldId;
    }

    public void setWorldId(IntegerFilter worldId) {
        this.worldId = worldId;
    }

    public IntegerFilter getStageId() {
        return stageId;
    }

    public void setStageId(IntegerFilter stageId) {
        this.stageId = stageId;
    }

    public IntegerFilter getRewardGroupId() {
        return rewardGroupId;
    }

    public void setRewardGroupId(IntegerFilter rewardGroupId) {
        this.rewardGroupId = rewardGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MChallengeQuestAchievementRewardCriteria that = (MChallengeQuestAchievementRewardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(worldId, that.worldId) &&
            Objects.equals(stageId, that.stageId) &&
            Objects.equals(rewardGroupId, that.rewardGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        worldId,
        stageId,
        rewardGroupId
        );
    }

    @Override
    public String toString() {
        return "MChallengeQuestAchievementRewardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (worldId != null ? "worldId=" + worldId + ", " : "") +
                (stageId != null ? "stageId=" + stageId + ", " : "") +
                (rewardGroupId != null ? "rewardGroupId=" + rewardGroupId + ", " : "") +
            "}";
    }

}
