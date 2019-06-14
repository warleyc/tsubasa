package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMarathonLoopReward} entity.
 */
public class MMarathonLoopRewardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer eventId;

    @NotNull
    private Integer loopPoint;


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

    public Integer getLoopPoint() {
        return loopPoint;
    }

    public void setLoopPoint(Integer loopPoint) {
        this.loopPoint = loopPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MMarathonLoopRewardDTO mMarathonLoopRewardDTO = (MMarathonLoopRewardDTO) o;
        if (mMarathonLoopRewardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMarathonLoopRewardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMarathonLoopRewardDTO{" +
            "id=" + getId() +
            ", eventId=" + getEventId() +
            ", loopPoint=" + getLoopPoint() +
            "}";
    }
}
