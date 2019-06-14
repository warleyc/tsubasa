package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MMarathonAchievementReward.
 */
@Entity
@Table(name = "m_marathon_achievement_reward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMarathonAchievementReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @NotNull
    @Column(name = "event_point", nullable = false)
    private Integer eventPoint;

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

    public MMarathonAchievementReward eventId(Integer eventId) {
        this.eventId = eventId;
        return this;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getEventPoint() {
        return eventPoint;
    }

    public MMarathonAchievementReward eventPoint(Integer eventPoint) {
        this.eventPoint = eventPoint;
        return this;
    }

    public void setEventPoint(Integer eventPoint) {
        this.eventPoint = eventPoint;
    }

    public Integer getRewardGroupId() {
        return rewardGroupId;
    }

    public MMarathonAchievementReward rewardGroupId(Integer rewardGroupId) {
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
        if (!(o instanceof MMarathonAchievementReward)) {
            return false;
        }
        return id != null && id.equals(((MMarathonAchievementReward) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMarathonAchievementReward{" +
            "id=" + getId() +
            ", eventId=" + getEventId() +
            ", eventPoint=" + getEventPoint() +
            ", rewardGroupId=" + getRewardGroupId() +
            "}";
    }
}
