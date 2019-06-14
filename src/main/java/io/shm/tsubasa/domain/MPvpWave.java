package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MPvpWave.
 */
@Entity
@Table(name = "m_pvp_wave")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MPvpWave implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "start_at", nullable = false)
    private Integer startAt;

    @NotNull
    @Column(name = "end_at", nullable = false)
    private Integer endAt;

    @NotNull
    @Column(name = "is_ranking", nullable = false)
    private Integer isRanking;

    @OneToMany(mappedBy = "mpvpwave")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MPvpRankingReward> mPvpRankingRewards = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MPvpWave startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public MPvpWave endAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public Integer getIsRanking() {
        return isRanking;
    }

    public MPvpWave isRanking(Integer isRanking) {
        this.isRanking = isRanking;
        return this;
    }

    public void setIsRanking(Integer isRanking) {
        this.isRanking = isRanking;
    }

    public Set<MPvpRankingReward> getMPvpRankingRewards() {
        return mPvpRankingRewards;
    }

    public MPvpWave mPvpRankingRewards(Set<MPvpRankingReward> mPvpRankingRewards) {
        this.mPvpRankingRewards = mPvpRankingRewards;
        return this;
    }

    public MPvpWave addMPvpRankingReward(MPvpRankingReward mPvpRankingReward) {
        this.mPvpRankingRewards.add(mPvpRankingReward);
        mPvpRankingReward.setMpvpwave(this);
        return this;
    }

    public MPvpWave removeMPvpRankingReward(MPvpRankingReward mPvpRankingReward) {
        this.mPvpRankingRewards.remove(mPvpRankingReward);
        mPvpRankingReward.setMpvpwave(null);
        return this;
    }

    public void setMPvpRankingRewards(Set<MPvpRankingReward> mPvpRankingRewards) {
        this.mPvpRankingRewards = mPvpRankingRewards;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MPvpWave)) {
            return false;
        }
        return id != null && id.equals(((MPvpWave) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MPvpWave{" +
            "id=" + getId() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            ", isRanking=" + getIsRanking() +
            "}";
    }
}
