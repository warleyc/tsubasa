package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTeamEffectBase} entity.
 */
public class MTeamEffectBaseDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    @NotNull
    private Integer rarity;

    @NotNull
    private Integer effectValueMin;

    @NotNull
    private Integer effectValueMax;

    @NotNull
    private Integer triggerProbabilityMin;

    @NotNull
    private Integer triggerProbabilityMax;

    @NotNull
    private Integer effectId;

    @NotNull
    private Integer effectValueMin2;

    @NotNull
    private Integer effectValueMax2;

    @NotNull
    private Integer triggerProbabilityMin2;

    @NotNull
    private Integer triggerProbabilityMax2;

    private Integer effectId2;

    
    @Lob
    private String description;


    private Long idId;

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

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getEffectValueMin() {
        return effectValueMin;
    }

    public void setEffectValueMin(Integer effectValueMin) {
        this.effectValueMin = effectValueMin;
    }

    public Integer getEffectValueMax() {
        return effectValueMax;
    }

    public void setEffectValueMax(Integer effectValueMax) {
        this.effectValueMax = effectValueMax;
    }

    public Integer getTriggerProbabilityMin() {
        return triggerProbabilityMin;
    }

    public void setTriggerProbabilityMin(Integer triggerProbabilityMin) {
        this.triggerProbabilityMin = triggerProbabilityMin;
    }

    public Integer getTriggerProbabilityMax() {
        return triggerProbabilityMax;
    }

    public void setTriggerProbabilityMax(Integer triggerProbabilityMax) {
        this.triggerProbabilityMax = triggerProbabilityMax;
    }

    public Integer getEffectId() {
        return effectId;
    }

    public void setEffectId(Integer effectId) {
        this.effectId = effectId;
    }

    public Integer getEffectValueMin2() {
        return effectValueMin2;
    }

    public void setEffectValueMin2(Integer effectValueMin2) {
        this.effectValueMin2 = effectValueMin2;
    }

    public Integer getEffectValueMax2() {
        return effectValueMax2;
    }

    public void setEffectValueMax2(Integer effectValueMax2) {
        this.effectValueMax2 = effectValueMax2;
    }

    public Integer getTriggerProbabilityMin2() {
        return triggerProbabilityMin2;
    }

    public void setTriggerProbabilityMin2(Integer triggerProbabilityMin2) {
        this.triggerProbabilityMin2 = triggerProbabilityMin2;
    }

    public Integer getTriggerProbabilityMax2() {
        return triggerProbabilityMax2;
    }

    public void setTriggerProbabilityMax2(Integer triggerProbabilityMax2) {
        this.triggerProbabilityMax2 = triggerProbabilityMax2;
    }

    public Integer getEffectId2() {
        return effectId2;
    }

    public void setEffectId2(Integer effectId2) {
        this.effectId2 = effectId2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long mPassiveEffectRangeId) {
        this.idId = mPassiveEffectRangeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTeamEffectBaseDTO mTeamEffectBaseDTO = (MTeamEffectBaseDTO) o;
        if (mTeamEffectBaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTeamEffectBaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTeamEffectBaseDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", rarity=" + getRarity() +
            ", effectValueMin=" + getEffectValueMin() +
            ", effectValueMax=" + getEffectValueMax() +
            ", triggerProbabilityMin=" + getTriggerProbabilityMin() +
            ", triggerProbabilityMax=" + getTriggerProbabilityMax() +
            ", effectId=" + getEffectId() +
            ", effectValueMin2=" + getEffectValueMin2() +
            ", effectValueMax2=" + getEffectValueMax2() +
            ", triggerProbabilityMin2=" + getTriggerProbabilityMin2() +
            ", triggerProbabilityMax2=" + getTriggerProbabilityMax2() +
            ", effectId2=" + getEffectId2() +
            ", description='" + getDescription() + "'" +
            ", id=" + getIdId() +
            "}";
    }
}
