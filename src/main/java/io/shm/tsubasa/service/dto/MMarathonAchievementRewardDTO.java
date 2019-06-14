package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMarathonAchievementReward} entity.
 */
public class MMarathonAchievementRewardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer eventId;

    @NotNull
    private Integer eventPoint;

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

    public Integer getEventPoint() {
        return eventPoint;
    }

    public void setEventPoint(Integer eventPoint) {
        this.eventPoint = eventPoint;
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

        MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO = (MMarathonAchievementRewardDTO) o;
        if (mMarathonAchievementRewardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMarathonAchievementRewardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMarathonAchievementRewardDTO{" +
            "id=" + getId() +
            ", eventId=" + getEventId() +
            ", eventPoint=" + getEventPoint() +
            ", rewardGroupId=" + getRewardGroupId() +
            "}";
    }
}
