package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MPvpRankingReward} entity.
 */
public class MPvpRankingRewardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer waveId;

    @NotNull
    private Integer difficulty;

    @NotNull
    private Integer rankingFrom;

    @NotNull
    private Integer rankingTo;

    @NotNull
    private Integer rewardGroupId;


    private Long mpvpwaveId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWaveId() {
        return waveId;
    }

    public void setWaveId(Integer waveId) {
        this.waveId = waveId;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getRankingFrom() {
        return rankingFrom;
    }

    public void setRankingFrom(Integer rankingFrom) {
        this.rankingFrom = rankingFrom;
    }

    public Integer getRankingTo() {
        return rankingTo;
    }

    public void setRankingTo(Integer rankingTo) {
        this.rankingTo = rankingTo;
    }

    public Integer getRewardGroupId() {
        return rewardGroupId;
    }

    public void setRewardGroupId(Integer rewardGroupId) {
        this.rewardGroupId = rewardGroupId;
    }

    public Long getMpvpwaveId() {
        return mpvpwaveId;
    }

    public void setMpvpwaveId(Long mPvpWaveId) {
        this.mpvpwaveId = mPvpWaveId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MPvpRankingRewardDTO mPvpRankingRewardDTO = (MPvpRankingRewardDTO) o;
        if (mPvpRankingRewardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mPvpRankingRewardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MPvpRankingRewardDTO{" +
            "id=" + getId() +
            ", waveId=" + getWaveId() +
            ", difficulty=" + getDifficulty() +
            ", rankingFrom=" + getRankingFrom() +
            ", rankingTo=" + getRankingTo() +
            ", rewardGroupId=" + getRewardGroupId() +
            ", mpvpwave=" + getMpvpwaveId() +
            "}";
    }
}
