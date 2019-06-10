package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MDistributeCardParameterStep} entity.
 */
public class MDistributeCardParameterStepDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer isGk;

    @NotNull
    private Integer step;

    @NotNull
    private Integer param;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsGk() {
        return isGk;
    }

    public void setIsGk(Integer isGk) {
        this.isGk = isGk;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getParam() {
        return param;
    }

    public void setParam(Integer param) {
        this.param = param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO = (MDistributeCardParameterStepDTO) o;
        if (mDistributeCardParameterStepDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mDistributeCardParameterStepDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MDistributeCardParameterStepDTO{" +
            "id=" + getId() +
            ", isGk=" + getIsGk() +
            ", step=" + getStep() +
            ", param=" + getParam() +
            "}";
    }
}
