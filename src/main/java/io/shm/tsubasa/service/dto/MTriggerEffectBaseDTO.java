package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTriggerEffectBase} entity.
 */
public class MTriggerEffectBaseDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    @NotNull
    private Integer rarity;

    @NotNull
    private Integer effectValue;

    @NotNull
    private Integer triggerProbability;

    private Integer targetCardParameter;

    @NotNull
    private Integer effectId;

    
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

    public Integer getEffectValue() {
        return effectValue;
    }

    public void setEffectValue(Integer effectValue) {
        this.effectValue = effectValue;
    }

    public Integer getTriggerProbability() {
        return triggerProbability;
    }

    public void setTriggerProbability(Integer triggerProbability) {
        this.triggerProbability = triggerProbability;
    }

    public Integer getTargetCardParameter() {
        return targetCardParameter;
    }

    public void setTargetCardParameter(Integer targetCardParameter) {
        this.targetCardParameter = targetCardParameter;
    }

    public Integer getEffectId() {
        return effectId;
    }

    public void setEffectId(Integer effectId) {
        this.effectId = effectId;
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

    public void setIdId(Long mTriggerEffectRangeId) {
        this.idId = mTriggerEffectRangeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTriggerEffectBaseDTO mTriggerEffectBaseDTO = (MTriggerEffectBaseDTO) o;
        if (mTriggerEffectBaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTriggerEffectBaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTriggerEffectBaseDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", rarity=" + getRarity() +
            ", effectValue=" + getEffectValue() +
            ", triggerProbability=" + getTriggerProbability() +
            ", targetCardParameter=" + getTargetCardParameter() +
            ", effectId=" + getEffectId() +
            ", description='" + getDescription() + "'" +
            ", id=" + getIdId() +
            "}";
    }
}
