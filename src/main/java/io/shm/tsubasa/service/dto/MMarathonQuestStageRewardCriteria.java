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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMarathonQuestStageReward} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMarathonQuestStageRewardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-marathon-quest-stage-rewards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMarathonQuestStageRewardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter stageId;

    private IntegerFilter exp;

    private IntegerFilter coin;

    private IntegerFilter guildPoint;

    private IntegerFilter clearRewardGroupId;

    private IntegerFilter clearRewardWeightId;

    private IntegerFilter achievementRewardGroupId;

    private IntegerFilter coopGroupId;

    private IntegerFilter specialRewardGroupId;

    private IntegerFilter specialRewardAmount;

    private IntegerFilter goalRewardGroupId;

    public MMarathonQuestStageRewardCriteria(){
    }

    public MMarathonQuestStageRewardCriteria(MMarathonQuestStageRewardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.stageId = other.stageId == null ? null : other.stageId.copy();
        this.exp = other.exp == null ? null : other.exp.copy();
        this.coin = other.coin == null ? null : other.coin.copy();
        this.guildPoint = other.guildPoint == null ? null : other.guildPoint.copy();
        this.clearRewardGroupId = other.clearRewardGroupId == null ? null : other.clearRewardGroupId.copy();
        this.clearRewardWeightId = other.clearRewardWeightId == null ? null : other.clearRewardWeightId.copy();
        this.achievementRewardGroupId = other.achievementRewardGroupId == null ? null : other.achievementRewardGroupId.copy();
        this.coopGroupId = other.coopGroupId == null ? null : other.coopGroupId.copy();
        this.specialRewardGroupId = other.specialRewardGroupId == null ? null : other.specialRewardGroupId.copy();
        this.specialRewardAmount = other.specialRewardAmount == null ? null : other.specialRewardAmount.copy();
        this.goalRewardGroupId = other.goalRewardGroupId == null ? null : other.goalRewardGroupId.copy();
    }

    @Override
    public MMarathonQuestStageRewardCriteria copy() {
        return new MMarathonQuestStageRewardCriteria(this);
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

    public IntegerFilter getExp() {
        return exp;
    }

    public void setExp(IntegerFilter exp) {
        this.exp = exp;
    }

    public IntegerFilter getCoin() {
        return coin;
    }

    public void setCoin(IntegerFilter coin) {
        this.coin = coin;
    }

    public IntegerFilter getGuildPoint() {
        return guildPoint;
    }

    public void setGuildPoint(IntegerFilter guildPoint) {
        this.guildPoint = guildPoint;
    }

    public IntegerFilter getClearRewardGroupId() {
        return clearRewardGroupId;
    }

    public void setClearRewardGroupId(IntegerFilter clearRewardGroupId) {
        this.clearRewardGroupId = clearRewardGroupId;
    }

    public IntegerFilter getClearRewardWeightId() {
        return clearRewardWeightId;
    }

    public void setClearRewardWeightId(IntegerFilter clearRewardWeightId) {
        this.clearRewardWeightId = clearRewardWeightId;
    }

    public IntegerFilter getAchievementRewardGroupId() {
        return achievementRewardGroupId;
    }

    public void setAchievementRewardGroupId(IntegerFilter achievementRewardGroupId) {
        this.achievementRewardGroupId = achievementRewardGroupId;
    }

    public IntegerFilter getCoopGroupId() {
        return coopGroupId;
    }

    public void setCoopGroupId(IntegerFilter coopGroupId) {
        this.coopGroupId = coopGroupId;
    }

    public IntegerFilter getSpecialRewardGroupId() {
        return specialRewardGroupId;
    }

    public void setSpecialRewardGroupId(IntegerFilter specialRewardGroupId) {
        this.specialRewardGroupId = specialRewardGroupId;
    }

    public IntegerFilter getSpecialRewardAmount() {
        return specialRewardAmount;
    }

    public void setSpecialRewardAmount(IntegerFilter specialRewardAmount) {
        this.specialRewardAmount = specialRewardAmount;
    }

    public IntegerFilter getGoalRewardGroupId() {
        return goalRewardGroupId;
    }

    public void setGoalRewardGroupId(IntegerFilter goalRewardGroupId) {
        this.goalRewardGroupId = goalRewardGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMarathonQuestStageRewardCriteria that = (MMarathonQuestStageRewardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(stageId, that.stageId) &&
            Objects.equals(exp, that.exp) &&
            Objects.equals(coin, that.coin) &&
            Objects.equals(guildPoint, that.guildPoint) &&
            Objects.equals(clearRewardGroupId, that.clearRewardGroupId) &&
            Objects.equals(clearRewardWeightId, that.clearRewardWeightId) &&
            Objects.equals(achievementRewardGroupId, that.achievementRewardGroupId) &&
            Objects.equals(coopGroupId, that.coopGroupId) &&
            Objects.equals(specialRewardGroupId, that.specialRewardGroupId) &&
            Objects.equals(specialRewardAmount, that.specialRewardAmount) &&
            Objects.equals(goalRewardGroupId, that.goalRewardGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        stageId,
        exp,
        coin,
        guildPoint,
        clearRewardGroupId,
        clearRewardWeightId,
        achievementRewardGroupId,
        coopGroupId,
        specialRewardGroupId,
        specialRewardAmount,
        goalRewardGroupId
        );
    }

    @Override
    public String toString() {
        return "MMarathonQuestStageRewardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (stageId != null ? "stageId=" + stageId + ", " : "") +
                (exp != null ? "exp=" + exp + ", " : "") +
                (coin != null ? "coin=" + coin + ", " : "") +
                (guildPoint != null ? "guildPoint=" + guildPoint + ", " : "") +
                (clearRewardGroupId != null ? "clearRewardGroupId=" + clearRewardGroupId + ", " : "") +
                (clearRewardWeightId != null ? "clearRewardWeightId=" + clearRewardWeightId + ", " : "") +
                (achievementRewardGroupId != null ? "achievementRewardGroupId=" + achievementRewardGroupId + ", " : "") +
                (coopGroupId != null ? "coopGroupId=" + coopGroupId + ", " : "") +
                (specialRewardGroupId != null ? "specialRewardGroupId=" + specialRewardGroupId + ", " : "") +
                (specialRewardAmount != null ? "specialRewardAmount=" + specialRewardAmount + ", " : "") +
                (goalRewardGroupId != null ? "goalRewardGroupId=" + goalRewardGroupId + ", " : "") +
            "}";
    }

}
