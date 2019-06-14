package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MQuestStageReward.
 */
@Entity
@Table(name = "m_quest_stage_reward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MQuestStageReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "stage_id", nullable = false)
    private Integer stageId;

    @NotNull
    @Column(name = "exp", nullable = false)
    private Integer exp;

    @NotNull
    @Column(name = "coin", nullable = false)
    private Integer coin;

    @NotNull
    @Column(name = "guild_point", nullable = false)
    private Integer guildPoint;

    @NotNull
    @Column(name = "clear_reward_group_id", nullable = false)
    private Integer clearRewardGroupId;

    @Column(name = "clear_reward_weight_id")
    private Integer clearRewardWeightId;

    @NotNull
    @Column(name = "achievement_reward_group_id", nullable = false)
    private Integer achievementRewardGroupId;

    @NotNull
    @Column(name = "coop_group_id", nullable = false)
    private Integer coopGroupId;

    @Column(name = "special_reward_group_id")
    private Integer specialRewardGroupId;

    @NotNull
    @Column(name = "special_reward_amount", nullable = false)
    private Integer specialRewardAmount;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStageId() {
        return stageId;
    }

    public MQuestStageReward stageId(Integer stageId) {
        this.stageId = stageId;
        return this;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Integer getExp() {
        return exp;
    }

    public MQuestStageReward exp(Integer exp) {
        this.exp = exp;
        return this;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getCoin() {
        return coin;
    }

    public MQuestStageReward coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getGuildPoint() {
        return guildPoint;
    }

    public MQuestStageReward guildPoint(Integer guildPoint) {
        this.guildPoint = guildPoint;
        return this;
    }

    public void setGuildPoint(Integer guildPoint) {
        this.guildPoint = guildPoint;
    }

    public Integer getClearRewardGroupId() {
        return clearRewardGroupId;
    }

    public MQuestStageReward clearRewardGroupId(Integer clearRewardGroupId) {
        this.clearRewardGroupId = clearRewardGroupId;
        return this;
    }

    public void setClearRewardGroupId(Integer clearRewardGroupId) {
        this.clearRewardGroupId = clearRewardGroupId;
    }

    public Integer getClearRewardWeightId() {
        return clearRewardWeightId;
    }

    public MQuestStageReward clearRewardWeightId(Integer clearRewardWeightId) {
        this.clearRewardWeightId = clearRewardWeightId;
        return this;
    }

    public void setClearRewardWeightId(Integer clearRewardWeightId) {
        this.clearRewardWeightId = clearRewardWeightId;
    }

    public Integer getAchievementRewardGroupId() {
        return achievementRewardGroupId;
    }

    public MQuestStageReward achievementRewardGroupId(Integer achievementRewardGroupId) {
        this.achievementRewardGroupId = achievementRewardGroupId;
        return this;
    }

    public void setAchievementRewardGroupId(Integer achievementRewardGroupId) {
        this.achievementRewardGroupId = achievementRewardGroupId;
    }

    public Integer getCoopGroupId() {
        return coopGroupId;
    }

    public MQuestStageReward coopGroupId(Integer coopGroupId) {
        this.coopGroupId = coopGroupId;
        return this;
    }

    public void setCoopGroupId(Integer coopGroupId) {
        this.coopGroupId = coopGroupId;
    }

    public Integer getSpecialRewardGroupId() {
        return specialRewardGroupId;
    }

    public MQuestStageReward specialRewardGroupId(Integer specialRewardGroupId) {
        this.specialRewardGroupId = specialRewardGroupId;
        return this;
    }

    public void setSpecialRewardGroupId(Integer specialRewardGroupId) {
        this.specialRewardGroupId = specialRewardGroupId;
    }

    public Integer getSpecialRewardAmount() {
        return specialRewardAmount;
    }

    public MQuestStageReward specialRewardAmount(Integer specialRewardAmount) {
        this.specialRewardAmount = specialRewardAmount;
        return this;
    }

    public void setSpecialRewardAmount(Integer specialRewardAmount) {
        this.specialRewardAmount = specialRewardAmount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MQuestStageReward)) {
            return false;
        }
        return id != null && id.equals(((MQuestStageReward) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MQuestStageReward{" +
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
            "}";
    }
}
