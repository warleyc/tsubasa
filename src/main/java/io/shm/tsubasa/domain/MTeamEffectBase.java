package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTeamEffectBase.
 */
@Entity
@Table(name = "m_team_effect_base")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTeamEffectBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "rarity", nullable = false)
    private Integer rarity;

    @NotNull
    @Column(name = "effect_value_min", nullable = false)
    private Integer effectValueMin;

    @NotNull
    @Column(name = "effect_value_max", nullable = false)
    private Integer effectValueMax;

    @NotNull
    @Column(name = "trigger_probability_min", nullable = false)
    private Integer triggerProbabilityMin;

    @NotNull
    @Column(name = "trigger_probability_max", nullable = false)
    private Integer triggerProbabilityMax;

    @NotNull
    @Column(name = "effect_id", nullable = false)
    private Integer effectId;

    @NotNull
    @Column(name = "effect_value_min_2", nullable = false)
    private Integer effectValueMin2;

    @NotNull
    @Column(name = "effect_value_max_2", nullable = false)
    private Integer effectValueMax2;

    @NotNull
    @Column(name = "trigger_probability_min_2", nullable = false)
    private Integer triggerProbabilityMin2;

    @NotNull
    @Column(name = "trigger_probability_max_2", nullable = false)
    private Integer triggerProbabilityMax2;

    @Column(name = "effect_id_2")
    private Integer effectId2;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mTeamEffectBases")
    private MPassiveEffectRange mpassiveeffectrange;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MTeamEffectBase name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRarity() {
        return rarity;
    }

    public MTeamEffectBase rarity(Integer rarity) {
        this.rarity = rarity;
        return this;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getEffectValueMin() {
        return effectValueMin;
    }

    public MTeamEffectBase effectValueMin(Integer effectValueMin) {
        this.effectValueMin = effectValueMin;
        return this;
    }

    public void setEffectValueMin(Integer effectValueMin) {
        this.effectValueMin = effectValueMin;
    }

    public Integer getEffectValueMax() {
        return effectValueMax;
    }

    public MTeamEffectBase effectValueMax(Integer effectValueMax) {
        this.effectValueMax = effectValueMax;
        return this;
    }

    public void setEffectValueMax(Integer effectValueMax) {
        this.effectValueMax = effectValueMax;
    }

    public Integer getTriggerProbabilityMin() {
        return triggerProbabilityMin;
    }

    public MTeamEffectBase triggerProbabilityMin(Integer triggerProbabilityMin) {
        this.triggerProbabilityMin = triggerProbabilityMin;
        return this;
    }

    public void setTriggerProbabilityMin(Integer triggerProbabilityMin) {
        this.triggerProbabilityMin = triggerProbabilityMin;
    }

    public Integer getTriggerProbabilityMax() {
        return triggerProbabilityMax;
    }

    public MTeamEffectBase triggerProbabilityMax(Integer triggerProbabilityMax) {
        this.triggerProbabilityMax = triggerProbabilityMax;
        return this;
    }

    public void setTriggerProbabilityMax(Integer triggerProbabilityMax) {
        this.triggerProbabilityMax = triggerProbabilityMax;
    }

    public Integer getEffectId() {
        return effectId;
    }

    public MTeamEffectBase effectId(Integer effectId) {
        this.effectId = effectId;
        return this;
    }

    public void setEffectId(Integer effectId) {
        this.effectId = effectId;
    }

    public Integer getEffectValueMin2() {
        return effectValueMin2;
    }

    public MTeamEffectBase effectValueMin2(Integer effectValueMin2) {
        this.effectValueMin2 = effectValueMin2;
        return this;
    }

    public void setEffectValueMin2(Integer effectValueMin2) {
        this.effectValueMin2 = effectValueMin2;
    }

    public Integer getEffectValueMax2() {
        return effectValueMax2;
    }

    public MTeamEffectBase effectValueMax2(Integer effectValueMax2) {
        this.effectValueMax2 = effectValueMax2;
        return this;
    }

    public void setEffectValueMax2(Integer effectValueMax2) {
        this.effectValueMax2 = effectValueMax2;
    }

    public Integer getTriggerProbabilityMin2() {
        return triggerProbabilityMin2;
    }

    public MTeamEffectBase triggerProbabilityMin2(Integer triggerProbabilityMin2) {
        this.triggerProbabilityMin2 = triggerProbabilityMin2;
        return this;
    }

    public void setTriggerProbabilityMin2(Integer triggerProbabilityMin2) {
        this.triggerProbabilityMin2 = triggerProbabilityMin2;
    }

    public Integer getTriggerProbabilityMax2() {
        return triggerProbabilityMax2;
    }

    public MTeamEffectBase triggerProbabilityMax2(Integer triggerProbabilityMax2) {
        this.triggerProbabilityMax2 = triggerProbabilityMax2;
        return this;
    }

    public void setTriggerProbabilityMax2(Integer triggerProbabilityMax2) {
        this.triggerProbabilityMax2 = triggerProbabilityMax2;
    }

    public Integer getEffectId2() {
        return effectId2;
    }

    public MTeamEffectBase effectId2(Integer effectId2) {
        this.effectId2 = effectId2;
        return this;
    }

    public void setEffectId2(Integer effectId2) {
        this.effectId2 = effectId2;
    }

    public String getDescription() {
        return description;
    }

    public MTeamEffectBase description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MPassiveEffectRange getMpassiveeffectrange() {
        return mpassiveeffectrange;
    }

    public MTeamEffectBase mpassiveeffectrange(MPassiveEffectRange mPassiveEffectRange) {
        this.mpassiveeffectrange = mPassiveEffectRange;
        return this;
    }

    public void setMpassiveeffectrange(MPassiveEffectRange mPassiveEffectRange) {
        this.mpassiveeffectrange = mPassiveEffectRange;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTeamEffectBase)) {
            return false;
        }
        return id != null && id.equals(((MTeamEffectBase) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTeamEffectBase{" +
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
            "}";
    }
}
