package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MTriggerEffectBase.
 */
@Entity
@Table(name = "m_trigger_effect_base")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTriggerEffectBase implements Serializable {

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
    @Column(name = "effect_value", nullable = false)
    private Integer effectValue;

    @NotNull
    @Column(name = "trigger_probability", nullable = false)
    private Integer triggerProbability;

    @Column(name = "target_card_parameter")
    private Integer targetCardParameter;

    @NotNull
    @Column(name = "effect_id", nullable = false)
    private Integer effectId;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mTriggerEffectBases")
    private MTriggerEffectRange id;

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MTargetTriggerEffectGroup> mTargetTriggerEffectGroups = new HashSet<>();

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

    public MTriggerEffectBase name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRarity() {
        return rarity;
    }

    public MTriggerEffectBase rarity(Integer rarity) {
        this.rarity = rarity;
        return this;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getEffectValue() {
        return effectValue;
    }

    public MTriggerEffectBase effectValue(Integer effectValue) {
        this.effectValue = effectValue;
        return this;
    }

    public void setEffectValue(Integer effectValue) {
        this.effectValue = effectValue;
    }

    public Integer getTriggerProbability() {
        return triggerProbability;
    }

    public MTriggerEffectBase triggerProbability(Integer triggerProbability) {
        this.triggerProbability = triggerProbability;
        return this;
    }

    public void setTriggerProbability(Integer triggerProbability) {
        this.triggerProbability = triggerProbability;
    }

    public Integer getTargetCardParameter() {
        return targetCardParameter;
    }

    public MTriggerEffectBase targetCardParameter(Integer targetCardParameter) {
        this.targetCardParameter = targetCardParameter;
        return this;
    }

    public void setTargetCardParameter(Integer targetCardParameter) {
        this.targetCardParameter = targetCardParameter;
    }

    public Integer getEffectId() {
        return effectId;
    }

    public MTriggerEffectBase effectId(Integer effectId) {
        this.effectId = effectId;
        return this;
    }

    public void setEffectId(Integer effectId) {
        this.effectId = effectId;
    }

    public String getDescription() {
        return description;
    }

    public MTriggerEffectBase description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MTriggerEffectRange getId() {
        return id;
    }

    public MTriggerEffectBase id(MTriggerEffectRange mTriggerEffectRange) {
        this.id = mTriggerEffectRange;
        return this;
    }

    public void setId(MTriggerEffectRange mTriggerEffectRange) {
        this.id = mTriggerEffectRange;
    }

    public Set<MTargetTriggerEffectGroup> getMTargetTriggerEffectGroups() {
        return mTargetTriggerEffectGroups;
    }

    public MTriggerEffectBase mTargetTriggerEffectGroups(Set<MTargetTriggerEffectGroup> mTargetTriggerEffectGroups) {
        this.mTargetTriggerEffectGroups = mTargetTriggerEffectGroups;
        return this;
    }

    public MTriggerEffectBase addMTargetTriggerEffectGroup(MTargetTriggerEffectGroup mTargetTriggerEffectGroup) {
        this.mTargetTriggerEffectGroups.add(mTargetTriggerEffectGroup);
        mTargetTriggerEffectGroup.setId(this);
        return this;
    }

    public MTriggerEffectBase removeMTargetTriggerEffectGroup(MTargetTriggerEffectGroup mTargetTriggerEffectGroup) {
        this.mTargetTriggerEffectGroups.remove(mTargetTriggerEffectGroup);
        mTargetTriggerEffectGroup.setId(null);
        return this;
    }

    public void setMTargetTriggerEffectGroups(Set<MTargetTriggerEffectGroup> mTargetTriggerEffectGroups) {
        this.mTargetTriggerEffectGroups = mTargetTriggerEffectGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTriggerEffectBase)) {
            return false;
        }
        return id != null && id.equals(((MTriggerEffectBase) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTriggerEffectBase{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", rarity=" + getRarity() +
            ", effectValue=" + getEffectValue() +
            ", triggerProbability=" + getTriggerProbability() +
            ", targetCardParameter=" + getTargetCardParameter() +
            ", effectId=" + getEffectId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
