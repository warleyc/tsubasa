package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MChallengeQuestStageReward} entity.
 */
public class MChallengeQuestStageRewardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer stageId;

    @NotNull
    private Integer exp;

    @NotNull
    private Integer coin;

    @NotNull
    private Integer guildPoint;

    @NotNull
    private Integer clearRewardGroupId;

    @NotNull
    private Integer clearRewardWeightId;

    @NotNull
    private Integer achievementRewardGroupId;

    @NotNull
    private Integer coopGroupId;

    private Integer specialRewardGroupId;

    @NotNull
    private Integer specialRewardAmount;

    private Integer goalRewardGroupId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getGuildPoint() {
        return guildPoint;
    }

    public void setGuildPoint(Integer guildPoint) {
        this.guildPoint = guildPoint;
    }

    public Integer getClearRewardGroupId() {
        return clearRewardGroupId;
    }

    public void setClearRewardGroupId(Integer clearRewardGroupId) {
        this.clearRewardGroupId = clearRewardGroupId;
    }

    public Integer getClearRewardWeightId() {
        return clearRewardWeightId;
    }

    public void setClearRewardWeightId(Integer clearRewardWeightId) {
        this.clearRewardWeightId = clearRewardWeightId;
    }

    public Integer getAchievementRewardGroupId() {
        return achievementRewardGroupId;
    }

    public void setAchievementRewardGroupId(Integer achievementRewardGroupId) {
        this.achievementRewardGroupId = achievementRewardGroupId;
    }

    public Integer getCoopGroupId() {
        return coopGroupId;
    }

    public void setCoopGroupId(Integer coopGroupId) {
        this.coopGroupId = coopGroupId;
    }

    public Integer getSpecialRewardGroupId() {
        return specialRewardGroupId;
    }

    public void setSpecialRewardGroupId(Integer specialRewardGroupId) {
        this.specialRewardGroupId = specialRewardGroupId;
    }

    public Integer getSpecialRewardAmount() {
        return specialRewardAmount;
    }

    public void setSpecialRewardAmount(Integer specialRewardAmount) {
        this.specialRewardAmount = specialRewardAmount;
    }

    public Integer getGoalRewardGroupId() {
        return goalRewardGroupId;
    }

    public void setGoalRewardGroupId(Integer goalRewardGroupId) {
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

        MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO = (MChallengeQuestStageRewardDTO) o;
        if (mChallengeQuestStageRewardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mChallengeQuestStageRewardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MChallengeQuestStageRewardDTO{" +
            "id=" + getId() +
            ", stageId=" + getStageId() +
            ", exp=" + getExp() +
            ", coin=" + getCoin() +
            ", guildPoint=" + getGuildPoint() +
            ", clearRewardGroupId=" + getClearRewardGroupId() +
            ", clearRewardWeightId=" + getClearRewardWeightId() +
            ", achievementRewardGroupId=" + getAchievementRewardGroupId() +
            ", coopGroupId=" + getCoopGroupId() +
            ", specialRewardGroupId=" + getSpecialRewardGroupId() +
            ", specialRewardAmount=" + getSpecialRewardAmount() +
            ", goalRewardGroupId=" + getGoalRewardGroupId() +
            "}";
    }
}
