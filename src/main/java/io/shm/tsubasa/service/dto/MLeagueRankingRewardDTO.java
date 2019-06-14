package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MLeagueRankingReward} entity.
 */
public class MLeagueRankingRewardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer leagueHierarchy;

    @NotNull
    private Integer rankTo;

    @NotNull
    private Integer rewardGroupId;

    private Integer startAt;

    private Integer endAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLeagueHierarchy() {
        return leagueHierarchy;
    }

    public void setLeagueHierarchy(Integer leagueHierarchy) {
        this.leagueHierarchy = leagueHierarchy;
    }

    public Integer getRankTo() {
        return rankTo;
    }

    public void setRankTo(Integer rankTo) {
        this.rankTo = rankTo;
    }

    public Integer getRewardGroupId() {
        return rewardGroupId;
    }

    public void setRewardGroupId(Integer rewardGroupId) {
        this.rewardGroupId = rewardGroupId;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MLeagueRankingRewardDTO mLeagueRankingRewardDTO = (MLeagueRankingRewardDTO) o;
        if (mLeagueRankingRewardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mLeagueRankingRewardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MLeagueRankingRewardDTO{" +
            "id=" + getId() +
            ", leagueHierarchy=" + getLeagueHierarchy() +
            ", rankTo=" + getRankTo() +
            ", rewardGroupId=" + getRewardGroupId() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            "}";
    }
}
