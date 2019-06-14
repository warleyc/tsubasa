package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MRegulatedLeagueRankingReward} entity.
 */
public class MRegulatedLeagueRankingRewardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer regulatedLeagueId;

    @NotNull
    private Integer leagueHierarchy;

    @NotNull
    private Integer rankTo;

    @NotNull
    private Integer rewardGroupId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRegulatedLeagueId() {
        return regulatedLeagueId;
    }

    public void setRegulatedLeagueId(Integer regulatedLeagueId) {
        this.regulatedLeagueId = regulatedLeagueId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO = (MRegulatedLeagueRankingRewardDTO) o;
        if (mRegulatedLeagueRankingRewardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mRegulatedLeagueRankingRewardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MRegulatedLeagueRankingRewardDTO{" +
            "id=" + getId() +
            ", regulatedLeagueId=" + getRegulatedLeagueId() +
            ", leagueHierarchy=" + getLeagueHierarchy() +
            ", rankTo=" + getRankTo() +
            ", rewardGroupId=" + getRewardGroupId() +
            "}";
    }
}
