package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MMarathonRankingReward.
 */
@Entity
@Table(name = "m_marathon_ranking_reward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMarathonRankingReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @NotNull
    @Column(name = "ranking_from", nullable = false)
    private Integer rankingFrom;

    @NotNull
    @Column(name = "ranking_to", nullable = false)
    private Integer rankingTo;

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

    public Integer getEventId() {
        return eventId;
    }

    public MMarathonRankingReward eventId(Integer eventId) {
        this.eventId = eventId;
        return this;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getRankingFrom() {
        return rankingFrom;
    }

    public MMarathonRankingReward rankingFrom(Integer rankingFrom) {
        this.rankingFrom = rankingFrom;
        return this;
    }

    public void setRankingFrom(Integer rankingFrom) {
        this.rankingFrom = rankingFrom;
    }

    public Integer getRankingTo() {
        return rankingTo;
    }

    public MMarathonRankingReward rankingTo(Integer rankingTo) {
        this.rankingTo = rankingTo;
        return this;
    }

    public void setRankingTo(Integer rankingTo) {
        this.rankingTo = rankingTo;
    }

    public Integer getRewardGroupId() {
        return rewardGroupId;
    }

    public MMarathonRankingReward rewardGroupId(Integer rewardGroupId) {
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
        if (!(o instanceof MMarathonRankingReward)) {
            return false;
        }
        return id != null && id.equals(((MMarathonRankingReward) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMarathonRankingReward{" +
            "id=" + getId() +
            ", eventId=" + getEventId() +
            ", rankingFrom=" + getRankingFrom() +
            ", rankingTo=" + getRankingTo() +
            ", rewardGroupId=" + getRewardGroupId() +
            "}";
    }
}
