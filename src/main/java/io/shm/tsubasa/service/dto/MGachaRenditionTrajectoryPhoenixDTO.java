package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRenditionTrajectoryPhoenix} entity.
 */
public class MGachaRenditionTrajectoryPhoenixDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer isPhoenix;

    @NotNull
    private Integer weight;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsPhoenix() {
        return isPhoenix;
    }

    public void setIsPhoenix(Integer isPhoenix) {
        this.isPhoenix = isPhoenix;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionTrajectoryPhoenixDTO mGachaRenditionTrajectoryPhoenixDTO = (MGachaRenditionTrajectoryPhoenixDTO) o;
        if (mGachaRenditionTrajectoryPhoenixDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionTrajectoryPhoenixDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionTrajectoryPhoenixDTO{" +
            "id=" + getId() +
            ", isPhoenix=" + getIsPhoenix() +
            ", weight=" + getWeight() +
            "}";
    }
}
