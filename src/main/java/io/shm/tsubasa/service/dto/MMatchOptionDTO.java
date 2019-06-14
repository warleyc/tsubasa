package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMatchOption} entity.
 */
public class MMatchOptionDTO implements Serializable {

    private Long id;

    private Integer passiveEffectId;

    @NotNull
    private Integer passiveEffectValue;

    @NotNull
    private Integer activateFullPowerType;

    @NotNull
    private Integer attributeMagnification;

    @NotNull
    private Integer useStaminaMagnification;

    @NotNull
    private Integer isIgnoreTeamSkill;

    @NotNull
    private Integer staminaInfinityType;


    private Long mpassiveeffectrangeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPassiveEffectId() {
        return passiveEffectId;
    }

    public void setPassiveEffectId(Integer passiveEffectId) {
        this.passiveEffectId = passiveEffectId;
    }

    public Integer getPassiveEffectValue() {
        return passiveEffectValue;
    }

    public void setPassiveEffectValue(Integer passiveEffectValue) {
        this.passiveEffectValue = passiveEffectValue;
    }

    public Integer getActivateFullPowerType() {
        return activateFullPowerType;
    }

    public void setActivateFullPowerType(Integer activateFullPowerType) {
        this.activateFullPowerType = activateFullPowerType;
    }

    public Integer getAttributeMagnification() {
        return attributeMagnification;
    }

    public void setAttributeMagnification(Integer attributeMagnification) {
        this.attributeMagnification = attributeMagnification;
    }

    public Integer getUseStaminaMagnification() {
        return useStaminaMagnification;
    }

    public void setUseStaminaMagnification(Integer useStaminaMagnification) {
        this.useStaminaMagnification = useStaminaMagnification;
    }

    public Integer getIsIgnoreTeamSkill() {
        return isIgnoreTeamSkill;
    }

    public void setIsIgnoreTeamSkill(Integer isIgnoreTeamSkill) {
        this.isIgnoreTeamSkill = isIgnoreTeamSkill;
    }

    public Integer getStaminaInfinityType() {
        return staminaInfinityType;
    }

    public void setStaminaInfinityType(Integer staminaInfinityType) {
        this.staminaInfinityType = staminaInfinityType;
    }

    public Long getMpassiveeffectrangeId() {
        return mpassiveeffectrangeId;
    }

    public void setMpassiveeffectrangeId(Long mPassiveEffectRangeId) {
        this.mpassiveeffectrangeId = mPassiveEffectRangeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MMatchOptionDTO mMatchOptionDTO = (MMatchOptionDTO) o;
        if (mMatchOptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMatchOptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMatchOptionDTO{" +
            "id=" + getId() +
            ", passiveEffectId=" + getPassiveEffectId() +
            ", passiveEffectValue=" + getPassiveEffectValue() +
            ", activateFullPowerType=" + getActivateFullPowerType() +
            ", attributeMagnification=" + getAttributeMagnification() +
            ", useStaminaMagnification=" + getUseStaminaMagnification() +
            ", isIgnoreTeamSkill=" + getIsIgnoreTeamSkill() +
            ", staminaInfinityType=" + getStaminaInfinityType() +
            ", mpassiveeffectrange=" + getMpassiveeffectrangeId() +
            "}";
    }
}
