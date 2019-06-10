package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MAction.
 */
@Entity
@Table(name = "m_action")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MAction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "short_name", nullable = false)
    private String shortName;

    
    @Lob
    @Column(name = "name_ruby", nullable = false)
    private String nameRuby;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    
    @Lob
    @Column(name = "effect_description", nullable = false)
    private String effectDescription;

    @NotNull
    @Column(name = "initial_command", nullable = false)
    private Integer initialCommand;

    @NotNull
    @Column(name = "rarity", nullable = false)
    private Integer rarity;

    @NotNull
    @Column(name = "command_type", nullable = false)
    private Integer commandType;

    @NotNull
    @Column(name = "ball_condition_ground", nullable = false)
    private Integer ballConditionGround;

    @NotNull
    @Column(name = "ball_condition_low", nullable = false)
    private Integer ballConditionLow;

    @NotNull
    @Column(name = "ball_condition_high", nullable = false)
    private Integer ballConditionHigh;

    @NotNull
    @Column(name = "stamina_lv_min", nullable = false)
    private Integer staminaLvMin;

    @NotNull
    @Column(name = "stamina_lv_max", nullable = false)
    private Integer staminaLvMax;

    @NotNull
    @Column(name = "power_lv_min", nullable = false)
    private Integer powerLvMin;

    @NotNull
    @Column(name = "power_lv_max", nullable = false)
    private Integer powerLvMax;

    @NotNull
    @Column(name = "blow_off_count", nullable = false)
    private Integer blowOffCount;

    @Column(name = "m_shoot_id")
    private Integer mShootId;

    @NotNull
    @Column(name = "foul_rate", nullable = false)
    private Integer foulRate;

    @NotNull
    @Column(name = "distance_decay_type", nullable = false)
    private Integer distanceDecayType;

    @NotNull
    @Column(name = "activate_character_count", nullable = false)
    private Integer activateCharacterCount;

    @NotNull
    @Column(name = "action_cut_id", nullable = false)
    private Integer actionCutId;

    @Column(name = "powerup_group")
    private Integer powerupGroup;

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MTargetActionGroup> mTargetActionGroups = new HashSet<>();

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

    public MAction name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public MAction shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getNameRuby() {
        return nameRuby;
    }

    public MAction nameRuby(String nameRuby) {
        this.nameRuby = nameRuby;
        return this;
    }

    public void setNameRuby(String nameRuby) {
        this.nameRuby = nameRuby;
    }

    public String getDescription() {
        return description;
    }

    public MAction description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffectDescription() {
        return effectDescription;
    }

    public MAction effectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
        return this;
    }

    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    public Integer getInitialCommand() {
        return initialCommand;
    }

    public MAction initialCommand(Integer initialCommand) {
        this.initialCommand = initialCommand;
        return this;
    }

    public void setInitialCommand(Integer initialCommand) {
        this.initialCommand = initialCommand;
    }

    public Integer getRarity() {
        return rarity;
    }

    public MAction rarity(Integer rarity) {
        this.rarity = rarity;
        return this;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getCommandType() {
        return commandType;
    }

    public MAction commandType(Integer commandType) {
        this.commandType = commandType;
        return this;
    }

    public void setCommandType(Integer commandType) {
        this.commandType = commandType;
    }

    public Integer getBallConditionGround() {
        return ballConditionGround;
    }

    public MAction ballConditionGround(Integer ballConditionGround) {
        this.ballConditionGround = ballConditionGround;
        return this;
    }

    public void setBallConditionGround(Integer ballConditionGround) {
        this.ballConditionGround = ballConditionGround;
    }

    public Integer getBallConditionLow() {
        return ballConditionLow;
    }

    public MAction ballConditionLow(Integer ballConditionLow) {
        this.ballConditionLow = ballConditionLow;
        return this;
    }

    public void setBallConditionLow(Integer ballConditionLow) {
        this.ballConditionLow = ballConditionLow;
    }

    public Integer getBallConditionHigh() {
        return ballConditionHigh;
    }

    public MAction ballConditionHigh(Integer ballConditionHigh) {
        this.ballConditionHigh = ballConditionHigh;
        return this;
    }

    public void setBallConditionHigh(Integer ballConditionHigh) {
        this.ballConditionHigh = ballConditionHigh;
    }

    public Integer getStaminaLvMin() {
        return staminaLvMin;
    }

    public MAction staminaLvMin(Integer staminaLvMin) {
        this.staminaLvMin = staminaLvMin;
        return this;
    }

    public void setStaminaLvMin(Integer staminaLvMin) {
        this.staminaLvMin = staminaLvMin;
    }

    public Integer getStaminaLvMax() {
        return staminaLvMax;
    }

    public MAction staminaLvMax(Integer staminaLvMax) {
        this.staminaLvMax = staminaLvMax;
        return this;
    }

    public void setStaminaLvMax(Integer staminaLvMax) {
        this.staminaLvMax = staminaLvMax;
    }

    public Integer getPowerLvMin() {
        return powerLvMin;
    }

    public MAction powerLvMin(Integer powerLvMin) {
        this.powerLvMin = powerLvMin;
        return this;
    }

    public void setPowerLvMin(Integer powerLvMin) {
        this.powerLvMin = powerLvMin;
    }

    public Integer getPowerLvMax() {
        return powerLvMax;
    }

    public MAction powerLvMax(Integer powerLvMax) {
        this.powerLvMax = powerLvMax;
        return this;
    }

    public void setPowerLvMax(Integer powerLvMax) {
        this.powerLvMax = powerLvMax;
    }

    public Integer getBlowOffCount() {
        return blowOffCount;
    }

    public MAction blowOffCount(Integer blowOffCount) {
        this.blowOffCount = blowOffCount;
        return this;
    }

    public void setBlowOffCount(Integer blowOffCount) {
        this.blowOffCount = blowOffCount;
    }

    public Integer getmShootId() {
        return mShootId;
    }

    public MAction mShootId(Integer mShootId) {
        this.mShootId = mShootId;
        return this;
    }

    public void setmShootId(Integer mShootId) {
        this.mShootId = mShootId;
    }

    public Integer getFoulRate() {
        return foulRate;
    }

    public MAction foulRate(Integer foulRate) {
        this.foulRate = foulRate;
        return this;
    }

    public void setFoulRate(Integer foulRate) {
        this.foulRate = foulRate;
    }

    public Integer getDistanceDecayType() {
        return distanceDecayType;
    }

    public MAction distanceDecayType(Integer distanceDecayType) {
        this.distanceDecayType = distanceDecayType;
        return this;
    }

    public void setDistanceDecayType(Integer distanceDecayType) {
        this.distanceDecayType = distanceDecayType;
    }

    public Integer getActivateCharacterCount() {
        return activateCharacterCount;
    }

    public MAction activateCharacterCount(Integer activateCharacterCount) {
        this.activateCharacterCount = activateCharacterCount;
        return this;
    }

    public void setActivateCharacterCount(Integer activateCharacterCount) {
        this.activateCharacterCount = activateCharacterCount;
    }

    public Integer getActionCutId() {
        return actionCutId;
    }

    public MAction actionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
        return this;
    }

    public void setActionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
    }

    public Integer getPowerupGroup() {
        return powerupGroup;
    }

    public MAction powerupGroup(Integer powerupGroup) {
        this.powerupGroup = powerupGroup;
        return this;
    }

    public void setPowerupGroup(Integer powerupGroup) {
        this.powerupGroup = powerupGroup;
    }

    public Set<MTargetActionGroup> getMTargetActionGroups() {
        return mTargetActionGroups;
    }

    public MAction mTargetActionGroups(Set<MTargetActionGroup> mTargetActionGroups) {
        this.mTargetActionGroups = mTargetActionGroups;
        return this;
    }

    public MAction addMTargetActionGroup(MTargetActionGroup mTargetActionGroup) {
        this.mTargetActionGroups.add(mTargetActionGroup);
        mTargetActionGroup.setId(this);
        return this;
    }

    public MAction removeMTargetActionGroup(MTargetActionGroup mTargetActionGroup) {
        this.mTargetActionGroups.remove(mTargetActionGroup);
        mTargetActionGroup.setId(null);
        return this;
    }

    public void setMTargetActionGroups(Set<MTargetActionGroup> mTargetActionGroups) {
        this.mTargetActionGroups = mTargetActionGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MAction)) {
            return false;
        }
        return id != null && id.equals(((MAction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MAction{" +
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
