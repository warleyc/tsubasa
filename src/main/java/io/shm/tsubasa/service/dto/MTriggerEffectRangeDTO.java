package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTriggerEffectRange} entity.
 */
public class MTriggerEffectRangeDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer effectType;

    @NotNull
    private Integer targetPositionGk;

    @NotNull
    private Integer targetPositionFw;

    @NotNull
    private Integer targetPositionOmf;

    @NotNull
    private Integer targetPositionDmf;

    @NotNull
    private Integer targetPositionDf;

    private Integer targetAttribute;

    private Integer targetNationalityGroupId;

    private Integer targetTeamGroupId;

    private Integer targetCharacterGroupId;

    private Integer targetFormationGroupId;

    private Integer targetActionCommand;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEffectType() {
        return effectType;
    }

    public void setEffectType(Integer effectType) {
        this.effectType = effectType;
    }

    public Integer getTargetPositionGk() {
        return targetPositionGk;
    }

    public void setTargetPositionGk(Integer targetPositionGk) {
        this.targetPositionGk = targetPositionGk;
    }

    public Integer getTargetPositionFw() {
        return targetPositionFw;
    }

    public void setTargetPositionFw(Integer targetPositionFw) {
        this.targetPositionFw = targetPositionFw;
    }

    public Integer getTargetPositionOmf() {
        return targetPositionOmf;
    }

    public void setTargetPositionOmf(Integer targetPositionOmf) {
        this.targetPositionOmf = targetPositionOmf;
    }

    public Integer getTargetPositionDmf() {
        return targetPositionDmf;
    }

    public void setTargetPositionDmf(Integer targetPositionDmf) {
        this.targetPositionDmf = targetPositionDmf;
    }

    public Integer getTargetPositionDf() {
        return targetPositionDf;
    }

    public void setTargetPositionDf(Integer targetPositionDf) {
        this.targetPositionDf = targetPositionDf;
    }

    public Integer getTargetAttribute() {
        return targetAttribute;
    }

    public void setTargetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public Integer getTargetNationalityGroupId() {
        return targetNationalityGroupId;
    }

    public void setTargetNationalityGroupId(Integer targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
    }

    public Integer getTargetTeamGroupId() {
        return targetTeamGroupId;
    }

    public void setTargetTeamGroupId(Integer targetTeamGroupId) {
        this.targetTeamGroupId = targetTeamGroupId;
    }

    public Integer getTargetCharacterGroupId() {
        return targetCharacterGroupId;
    }

    public void setTargetCharacterGroupId(Integer targetCharacterGroupId) {
        this.targetCharacterGroupId = targetCharacterGroupId;
    }

    public Integer getTargetFormationGroupId() {
        return targetFormationGroupId;
    }

    public void setTargetFormationGroupId(Integer targetFormationGroupId) {
        this.targetFormationGroupId = targetFormationGroupId;
    }

    public Integer getTargetActionCommand() {
        return targetActionCommand;
    }

    public void setTargetActionCommand(Integer targetActionCommand) {
        this.targetActionCommand = targetActionCommand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTriggerEffectRangeDTO mTriggerEffectRangeDTO = (MTriggerEffectRangeDTO) o;
        if (mTriggerEffectRangeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTriggerEffectRangeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTriggerEffectRangeDTO{" +
            "id=" + getId() +
            ", effectType=" + getEffectType() +
            ", targetPositionGk=" + getTargetPositionGk() +
            ", targetPositionFw=" + getTargetPositionFw() +
            ", targetPositionOmf=" + getTargetPositionOmf() +
            ", targetPositionDmf=" + getTargetPositionDmf() +
            ", targetPositionDf=" + getTargetPositionDf() +
            ", targetAttribute=" + getTargetAttribute() +
            ", targetNationalityGroupId=" + getTargetNationalityGroupId() +
            ", targetTeamGroupId=" + getTargetTeamGroupId() +
            ", targetCharacterGroupId=" + getTargetCharacterGroupId() +
            ", targetFormationGroupId=" + getTargetFormationGroupId() +
            ", targetActionCommand=" + getTargetActionCommand() +
            "}";
    }
}
