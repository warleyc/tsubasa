package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MPvpRankingReward.
 */
@Entity
@Table(name = "m_pvp_ranking_reward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MPvpRankingReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "wave_id", nullable = false)
    private Integer waveId;

    @NotNull
    @Column(name = "difficulty", nullable = false)
    private Integer difficulty;

    @NotNull
    @Column(name = "ranking_from", nullable = false)
    private Integer rankingFrom;

    @NotNull
    @Column(name = "ranking_to", nullable = false)
    private Integer rankingTo;

    @NotNull
    @Column(name = "reward_group_id", nullable = false)
    private Integer rewardGroupId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mPvpRankingRewards")
    private MPvpWave id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWaveId() {
        return waveId;
    }

    public MPvpRankingReward waveId(Integer waveId) {
        this.waveId = waveId;
        return this;
    }

    public void setWaveId(Integer waveId) {
        this.waveId = waveId;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public MPvpRankingReward difficulty(Integer difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getRankingFrom() {
        return rankingFrom;
    }

    public MPvpRankingReward rankingFrom(Integer rankingFrom) {
        this.rankingFrom = rankingFrom;
        return this;
    }

    public void setRankingFrom(Integer rankingFrom) {
        this.rankingFrom = rankingFrom;
    }

    public Integer getRankingTo() {
        return rankingTo;
    }

    public MPvpRankingReward rankingTo(Integer rankingTo) {
        this.rankingTo = rankingTo;
        return this;
    }

    public void setRankingTo(Integer rankingTo) {
        this.rankingTo = rankingTo;
    }

    public Integer getRewardGroupId() {
        return rewardGroupId;
    }

    public MPvpRankingReward rewardGroupId(Integer rewardGroupId) {
        this.rewardGroupId = rewardGroupId;
        return this;
    }

    public void setRewardGroupId(Integer rewardGroupId) {
        this.rewardGroupId = rewardGroupId;
    }

    public MPvpWave getId() {
        return id;
    }

    public MPvpRankingReward id(MPvpWave mPvpWave) {
        this.id = mPvpWave;
        return this;
    }

    public void setId(MPvpWave mPvpWave) {
        this.id = mPvpWave;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MPvpRankingReward)) {
            return false;
        }
        return id != null && id.equals(((MPvpRankingReward) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MPvpRankingReward{" +
            "id=" + getId() +
            ", waveId=" + getWaveId() +
            ", difficulty=" + getDifficulty() +
            ", rankingFrom=" + getRankingFrom() +
            ", rankingTo=" + getRankingTo() +
            ", rewardGroupId=" + getRewardGroupId() +
            "}";
    }
}
