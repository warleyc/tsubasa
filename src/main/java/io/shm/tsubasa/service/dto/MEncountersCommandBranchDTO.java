package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MEncountersCommandBranch} entity.
 */
public class MEncountersCommandBranchDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer ballFloatCondition;

    @NotNull
    private Integer condition;

    @NotNull
    private Integer encountersType;

    @NotNull
    private Integer isSuccess;

    @NotNull
    private Integer commandType;

    @NotNull
    private Integer successRate;

    @NotNull
    private Integer looseBallRate;

    @NotNull
    private Integer touchLightlyRate;

    @NotNull
    private Integer postRate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBallFloatCondition() {
        return ballFloatCondition;
    }

    public void setBallFloatCondition(Integer ballFloatCondition) {
        this.ballFloatCondition = ballFloatCondition;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Integer getEncountersType() {
        return encountersType;
    }

    public void setEncountersType(Integer encountersType) {
        this.encountersType = encountersType;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getCommandType() {
        return commandType;
    }

    public void setCommandType(Integer commandType) {
        this.commandType = commandType;
    }

    public Integer getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Integer successRate) {
        this.successRate = successRate;
    }

    public Integer getLooseBallRate() {
        return looseBallRate;
    }

    public void setLooseBallRate(Integer looseBallRate) {
        this.looseBallRate = looseBallRate;
    }

    public Integer getTouchLightlyRate() {
        return touchLightlyRate;
    }

    public void setTouchLightlyRate(Integer touchLightlyRate) {
        this.touchLightlyRate = touchLightlyRate;
    }

    public Integer getPostRate() {
        return postRate;
    }

    public void setPostRate(Integer postRate) {
        this.postRate = postRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MEncountersCommandBranchDTO mEncountersCommandBranchDTO = (MEncountersCommandBranchDTO) o;
        if (mEncountersCommandBranchDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mEncountersCommandBranchDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MEncountersCommandBranchDTO{" +
            "id=" + getId() +
            ", ballFloatCondition=" + getBallFloatCondition() +
            ", condition=" + getCondition() +
            ", encountersType=" + getEncountersType() +
            ", isSuccess=" + getIsSuccess() +
            ", commandType=" + getCommandType() +
            ", successRate=" + getSuccessRate() +
            ", looseBallRate=" + getLooseBallRate() +
            ", touchLightlyRate=" + getTouchLightlyRate() +
            ", postRate=" + getPostRate() +
            "}";
    }
}
