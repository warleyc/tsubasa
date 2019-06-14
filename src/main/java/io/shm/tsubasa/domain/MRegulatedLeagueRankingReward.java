package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MRegulatedLeagueRankingReward.
 */
@Entity
@Table(name = "m_regulated_league_ranking_reward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MRegulatedLeagueRankingReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "regulated_league_id", nullable = false)
    private Integer regulatedLeagueId;

    @NotNull
    @Column(name = "league_hierarchy", nullable = false)
    private Integer leagueHierarchy;

    @NotNull
    @Column(name = "rank_to", nullable = false)
    private Integer rankTo;

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

    public Integer getRegulatedLeagueId() {
        return regulatedLeagueId;
    }

    public MRegulatedLeagueRankingReward regulatedLeagueId(Integer regulatedLeagueId) {
        this.regulatedLeagueId = regulatedLeagueId;
        return this;
    }

    public void setRegulatedLeagueId(Integer regulatedLeagueId) {
        this.regulatedLeagueId = regulatedLeagueId;
    }

    public Integer getLeagueHierarchy() {
        return leagueHierarchy;
    }

    public MRegulatedLeagueRankingReward leagueHierarchy(Integer leagueHierarchy) {
        this.leagueHierarchy = leagueHierarchy;
        return this;
    }

    public void setLeagueHierarchy(Integer leagueHierarchy) {
        this.leagueHierarchy = leagueHierarchy;
    }

    public Integer getRankTo() {
        return rankTo;
    }

    public MRegulatedLeagueRankingReward rankTo(Integer rankTo) {
        this.rankTo = rankTo;
        return this;
    }

    public void setRankTo(Integer rankTo) {
        this.rankTo = rankTo;
    }

    public Integer getRewardGroupId() {
        return rewardGroupId;
    }

    public MRegulatedLeagueRankingReward rewardGroupId(Integer rewardGroupId) {
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
        if (!(o instanceof MRegulatedLeagueRankingReward)) {
            return false;
        }
        return id != null && id.equals(((MRegulatedLeagueRankingReward) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MRegulatedLeagueRankingReward{" +
            "id=" + getId() +
            ", regulatedLeagueId=" + getRegulatedLeagueId() +
            ", leagueHierarchy=" + getLeagueHierarchy() +
            ", rankTo=" + getRankTo() +
            ", rewardGroupId=" + getRewardGroupId() +
            "}";
    }
}
