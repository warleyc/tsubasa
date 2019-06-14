package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMarathonBoostSchedule} entity.
 */
public class MMarathonBoostScheduleDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer eventId;

    @NotNull
    private Integer boostRatio;

    @NotNull
    private Integer startAt;

    @NotNull
    private Integer endAt;


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

    public Integer getBoostRatio() {
        return boostRatio;
    }

    public void setBoostRatio(Integer boostRatio) {
        this.boostRatio = boostRatio;
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

        MMarathonBoostScheduleDTO mMarathonBoostScheduleDTO = (MMarathonBoostScheduleDTO) o;
        if (mMarathonBoostScheduleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMarathonBoostScheduleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMarathonBoostScheduleDTO{" +
            "id=" + getId() +
            ", eventId=" + getEventId() +
            ", boostRatio=" + getBoostRatio() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            "}";
    }
}
