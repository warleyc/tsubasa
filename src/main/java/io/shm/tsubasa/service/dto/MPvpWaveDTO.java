package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MPvpWave} entity.
 */
public class MPvpWaveDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer startAt;

    @NotNull
    private Integer endAt;

    @NotNull
    private Integer isRanking;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getIsRanking() {
        return isRanking;
    }

    public void setIsRanking(Integer isRanking) {
        this.isRanking = isRanking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MPvpWaveDTO mPvpWaveDTO = (MPvpWaveDTO) o;
        if (mPvpWaveDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mPvpWaveDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MPvpWaveDTO{" +
            "id=" + getId() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            ", isRanking=" + getIsRanking() +
            "}";
    }
}
