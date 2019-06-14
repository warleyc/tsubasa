package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRenditionTrajectory} entity.
 */
public class MGachaRenditionTrajectoryDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer weight;

    @NotNull
    private Integer trajectoryType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getTrajectoryType() {
        return trajectoryType;
    }

    public void setTrajectoryType(Integer trajectoryType) {
        this.trajectoryType = trajectoryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionTrajectoryDTO mGachaRenditionTrajectoryDTO = (MGachaRenditionTrajectoryDTO) o;
        if (mGachaRenditionTrajectoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionTrajectoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionTrajectoryDTO{" +
            "id=" + getId() +
            ", weight=" + getWeight() +
            ", trajectoryType=" + getTrajectoryType() +
            "}";
    }
}
