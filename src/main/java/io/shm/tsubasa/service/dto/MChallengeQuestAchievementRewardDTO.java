package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MChallengeQuestAchievementReward} entity.
 */
public class MChallengeQuestAchievementRewardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer worldId;

    @NotNull
    private Integer stageId;

    @NotNull
    private Integer rewardGroupId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWorldId() {
        return worldId;
    }

    public void setWorldId(Integer worldId) {
        this.worldId = worldId;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Integer getRewardGroupId() {
        return rewardGroupId;
    }

    public void setRewardGroupId(Integer rewardGroupId) {
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

        MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO = (MChallengeQuestAchievementRewardDTO) o;
        if (mChallengeQuestAchievementRewardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mChallengeQuestAchievementRewardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MChallengeQuestAchievementRewardDTO{" +
            "id=" + getId() +
            ", worldId=" + getWorldId() +
            ", stageId=" + getStageId() +
            ", rewardGroupId=" + getRewardGroupId() +
            "}";
    }
}
