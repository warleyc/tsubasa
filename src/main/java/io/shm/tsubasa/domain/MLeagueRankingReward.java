package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MLeagueRankingReward.
 */
@Entity
@Table(name = "m_league_ranking_reward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MLeagueRankingReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "league_hierarchy", nullable = false)
    private Integer leagueHierarchy;

    @NotNull
    @Column(name = "rank_to", nullable = false)
    private Integer rankTo;

    @NotNull
    @Column(name = "reward_group_id", nullable = false)
    private Integer rewardGroupId;

    @Column(name = "start_at")
    private Integer startAt;

    @Column(name = "end_at")
    private Integer endAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLeagueHierarchy() {
        return leagueHierarchy;
    }

    public MLeagueRankingReward leagueHierarchy(Integer leagueHierarchy) {
        this.leagueHierarchy = leagueHierarchy;
        return this;
    }

    public void setLeagueHierarchy(Integer leagueHierarchy) {
        this.leagueHierarchy = leagueHierarchy;
    }

    public Integer getRankTo() {
        return rankTo;
    }

    public MLeagueRankingReward rankTo(Integer rankTo) {
        this.rankTo = rankTo;
        return this;
    }

    public void setRankTo(Integer rankTo) {
        this.rankTo = rankTo;
    }

    public Integer getRewardGroupId() {
        return rewardGroupId;
    }

    public MLeagueRankingReward rewardGroupId(Integer rewardGroupId) {
        this.rewardGroupId = rewardGroupId;
        return this;
    }

    public void setRewardGroupId(Integer rewardGroupId) {
        this.rewardGroupId = rewardGroupId;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MLeagueRankingReward startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public MLeagueRankingReward endAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MLeagueRankingReward)) {
            return false;
        }
        return id != null && id.equals(((MLeagueRankingReward) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MLeagueRankingReward{" +
            "id=" + getId() +
            ", leagueHierarchy=" + getLeagueHierarchy() +
            ", rankTo=" + getRankTo() +
            ", rewardGroupId=" + getRewardGroupId() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            "}";
    }
}
