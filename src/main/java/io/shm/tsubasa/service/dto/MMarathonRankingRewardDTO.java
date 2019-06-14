package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMarathonRankingReward} entity.
 */
public class MMarathonRankingRewardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer eventId;

    @NotNull
    private Integer rankingFrom;

    @NotNull
    private Integer rankingTo;

    @NotNull
    private Integer rewardGroupId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MMarathonRankingRewardDTO mMarathonRankingRewardDTO = (MMarathonRankingRewardDTO) o;
        if (mMarathonRankingRewardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMarathonRankingRewardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMarathonRankingRewardDTO{" +
            "id=" + getId() +
            ", eventId=" + getEventId() +
            ", rankingFrom=" + getRankingFrom() +
            ", rankingTo=" + getRankingTo() +
            ", rewardGroupId=" + getRewardGroupId() +
            "}";
    }
}
