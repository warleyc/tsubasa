package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MAction} entity.
 */
public class MActionDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    
    @Lob
    private String shortName;

    
    @Lob
    private String nameRuby;

    
    @Lob
    private String description;

    
    @Lob
    private String effectDescription;

    @NotNull
    private Integer initialCommand;

    @NotNull
    private Integer rarity;

    @NotNull
    private Integer commandType;

    @NotNull
    private Integer ballConditionGround;

    @NotNull
    private Integer ballConditionLow;

    @NotNull
    private Integer ballConditionHigh;

    @NotNull
    private Integer staminaLvMin;

    @NotNull
    private Integer staminaLvMax;

    @NotNull
    private Integer powerLvMin;

    @NotNull
    private Integer powerLvMax;

    @NotNull
    private Integer blowOffCount;

    private Integer mShootId;

    @NotNull
    private Integer foulRate;

    @NotNull
    private Integer distanceDecayType;

    @NotNull
    private Integer activateCharacterCount;

    @NotNull
    private Integer actionCutId;

    private Integer powerupGroup;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getNameRuby() {
        return nameRuby;
    }

    public void setNameRuby(String nameRuby) {
        this.nameRuby = nameRuby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffectDescription() {
        return effectDescription;
    }

    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    public Integer getInitialCommand() {
        return initialCommand;
    }

    public void setInitialCommand(Integer initialCommand) {
        this.initialCommand = initialCommand;
    }

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getCommandType() {
        return commandType;
    }

    public void setCommandType(Integer commandType) {
        this.commandType = commandType;
    }

    public Integer getBallConditionGround() {
        return ballConditionGround;
    }

    public void setBallConditionGround(Integer ballConditionGround) {
        this.ballConditionGround = ballConditionGround;
    }

    public Integer getBallConditionLow() {
        return ballConditionLow;
    }

    public void setBallConditionLow(Integer ballConditionLow) {
        this.ballConditionLow = ballConditionLow;
    }

    public Integer getBallConditionHigh() {
        return ballConditionHigh;
    }

    public void setBallConditionHigh(Integer ballConditionHigh) {
        this.ballConditionHigh = ballConditionHigh;
    }

    public Integer getStaminaLvMin() {
        return staminaLvMin;
    }

    public void setStaminaLvMin(Integer staminaLvMin) {
        this.staminaLvMin = staminaLvMin;
    }

    public Integer getStaminaLvMax() {
        return staminaLvMax;
    }

    public void setStaminaLvMax(Integer staminaLvMax) {
        this.staminaLvMax = staminaLvMax;
    }

    public Integer getPowerLvMin() {
        return powerLvMin;
    }

    public void setPowerLvMin(Integer powerLvMin) {
        this.powerLvMin = powerLvMin;
    }

    public Integer getPowerLvMax() {
        return powerLvMax;
    }

    public void setPowerLvMax(Integer powerLvMax) {
        this.powerLvMax = powerLvMax;
    }

    public Integer getBlowOffCount() {
        return blowOffCount;
    }

    public void setBlowOffCount(Integer blowOffCount) {
        this.blowOffCount = blowOffCount;
    }

    public Integer getmShootId() {
        return mShootId;
    }

    public void setmShootId(Integer mShootId) {
        this.mShootId = mShootId;
    }

    public Integer getFoulRate() {
        return foulRate;
    }

    public void setFoulRate(Integer foulRate) {
        this.foulRate = foulRate;
    }

    public Integer getDistanceDecayType() {
        return distanceDecayType;
    }

    public void setDistanceDecayType(Integer distanceDecayType) {
        this.distanceDecayType = distanceDecayType;
    }

    public Integer getActivateCharacterCount() {
        return activateCharacterCount;
    }

    public void setActivateCharacterCount(Integer activateCharacterCount) {
        this.activateCharacterCount = activateCharacterCount;
    }

    public Integer getActionCutId() {
        return actionCutId;
    }

    public void setActionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
    }

    public Integer getPowerupGroup() {
        return powerupGroup;
    }

    public void setPowerupGroup(Integer powerupGroup) {
        this.powerupGroup = powerupGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MActionDTO mActionDTO = (MActionDTO) o;
        if (mActionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mActionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MActionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", nameRuby='" + getNameRuby() + "'" +
            ", description='" + getDescription() + "'" +
            ", effectDescription='" + getEffectDescription() + "'" +
            ", initialCommand=" + getInitialCommand() +
            ", rarity=" + getRarity() +
            ", commandType=" + getCommandType() +
            ", ballConditionGround=" + getBallConditionGround() +
            ", ballConditionLow=" + getBallConditionLow() +
            ", ballConditionHigh=" + getBallConditionHigh() +
            ", staminaLvMin=" + getStaminaLvMin() +
            ", staminaLvMax=" + getStaminaLvMax() +
            ", powerLvMin=" + getPowerLvMin() +
            ", powerLvMax=" + getPowerLvMax() +
            ", blowOffCount=" + getBlowOffCount() +
            ", mShootId=" + getmShootId() +
            ", foulRate=" + getFoulRate() +
            ", distanceDecayType=" + getDistanceDecayType() +
            ", activateCharacterCount=" + getActivateCharacterCount() +
            ", actionCutId=" + getActionCutId() +
            ", powerupGroup=" + getPowerupGroup() +
            "}";
    }
}
