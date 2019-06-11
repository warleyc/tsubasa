package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MChallengeQuestAchievementReward.
 */
@Entity
@Table(name = "m_challenge_quest_achievement_reward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MChallengeQuestAchievementReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "world_id", nullable = false)
    private Integer worldId;

    @NotNull
    @Column(name = "stage_id", nullable = false)
    private Integer stageId;

    @NotNull
    @Column(name = "reward_group_id", nullable = false)
    private Integer rewardGroupId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWorldId() {
        return worldId;
    }

    public MChallengeQuestAchievementReward worldId(Integer worldId) {
        this.worldId = worldId;
        return this;
    }

    public void setWorldId(Integer worldId) {
        this.worldId = worldId;
    }

    public Integer getStageId() {
        return stageId;
    }

    public MChallengeQuestAchievementReward stageId(Integer stageId) {
        this.stageId = stageId;
        return this;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Integer getRewardGroupId() {
        return rewardGroupId;
    }

    public MChallengeQuestAchievementReward rewardGroupId(Integer rewardGroupId) {
        this.rewardGroupId = rewardGroupId;
        return this;
    }

    public void setRewardGroupId(Integer rewardGroupId) {
        this.rewardGroupId = rewardGroupId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MChallengeQuestAchievementReward)) {
            return false;
        }
        return id != null && id.equals(((MChallengeQuestAchievementReward) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MChallengeQuestAchievementReward{" +
            "id=" + getId() +
            ", worldId=" + getWorldId() +
            ", stageId=" + getStageId() +
            ", rewardGroupId=" + getRewardGroupId() +
            "}";
    }
}
