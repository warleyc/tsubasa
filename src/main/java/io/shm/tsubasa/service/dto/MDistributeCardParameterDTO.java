package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MDistributeCardParameter} entity.
 */
public class MDistributeCardParameterDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer rarity;

    @NotNull
    private Integer isGk;

    @NotNull
    private Integer maxStepCount;

    @NotNull
    private Integer maxTotalStepCount;

    @NotNull
    private Integer distributePointByStep;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getIsGk() {
        return isGk;
    }

    public void setIsGk(Integer isGk) {
        this.isGk = isGk;
    }

    public Integer getMaxStepCount() {
        return maxStepCount;
    }

    public void setMaxStepCount(Integer maxStepCount) {
        this.maxStepCount = maxStepCount;
    }

    public Integer getMaxTotalStepCount() {
        return maxTotalStepCount;
    }

    public void setMaxTotalStepCount(Integer maxTotalStepCount) {
        this.maxTotalStepCount = maxTotalStepCount;
    }

    public Integer getDistributePointByStep() {
        return distributePointByStep;
    }

    public void setDistributePointByStep(Integer distributePointByStep) {
        this.distributePointByStep = distributePointByStep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MDistributeCardParameterDTO mDistributeCardParameterDTO = (MDistributeCardParameterDTO) o;
        if (mDistributeCardParameterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mDistributeCardParameterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MDistributeCardParameterDTO{" +
            "id=" + getId() +
            ", rarity=" + getRarity() +
            ", isGk=" + getIsGk() +
            ", maxStepCount=" + getMaxStepCount() +
            ", maxTotalStepCount=" + getMaxTotalStepCount() +
            ", distributePointByStep=" + getDistributePointByStep() +
            "}";
    }
}
