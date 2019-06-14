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
 * A MTriggerEffectRange.
 */
@Entity
@Table(name = "m_trigger_effect_range")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTriggerEffectRange implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "effect_type", nullable = false)
    private Integer effectType;

    @NotNull
    @Column(name = "target_position_gk", nullable = false)
    private Integer targetPositionGk;

    @NotNull
    @Column(name = "target_position_fw", nullable = false)
    private Integer targetPositionFw;

    @NotNull
    @Column(name = "target_position_omf", nullable = false)
    private Integer targetPositionOmf;

    @NotNull
    @Column(name = "target_position_dmf", nullable = false)
    private Integer targetPositionDmf;

    @NotNull
    @Column(name = "target_position_df", nullable = false)
    private Integer targetPositionDf;

    @Column(name = "target_attribute")
    private Integer targetAttribute;

    @Column(name = "target_nationality_group_id")
    private Integer targetNationalityGroupId;

    @Column(name = "target_team_group_id")
    private Integer targetTeamGroupId;

    @Column(name = "target_character_group_id")
    private Integer targetCharacterGroupId;

    @Column(name = "target_formation_group_id")
    private Integer targetFormationGroupId;

    @Column(name = "target_action_command")
    private Integer targetActionCommand;

    @OneToMany(mappedBy = "mtriggereffectrange")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MTriggerEffectBase> mTriggerEffectBases = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEffectType() {
        return effectType;
    }

    public MTriggerEffectRange effectType(Integer effectType) {
        this.effectType = effectType;
        return this;
    }

    public void setEffectType(Integer effectType) {
        this.effectType = effectType;
    }

    public Integer getTargetPositionGk() {
        return targetPositionGk;
    }

    public MTriggerEffectRange targetPositionGk(Integer targetPositionGk) {
        this.targetPositionGk = targetPositionGk;
        return this;
    }

    public void setTargetPositionGk(Integer targetPositionGk) {
        this.targetPositionGk = targetPositionGk;
    }

    public Integer getTargetPositionFw() {
        return targetPositionFw;
    }

    public MTriggerEffectRange targetPositionFw(Integer targetPositionFw) {
        this.targetPositionFw = targetPositionFw;
        return this;
    }

    public void setTargetPositionFw(Integer targetPositionFw) {
        this.targetPositionFw = targetPositionFw;
    }

    public Integer getTargetPositionOmf() {
        return targetPositionOmf;
    }

    public MTriggerEffectRange targetPositionOmf(Integer targetPositionOmf) {
        this.targetPositionOmf = targetPositionOmf;
        return this;
    }

    public void setTargetPositionOmf(Integer targetPositionOmf) {
        this.targetPositionOmf = targetPositionOmf;
    }

    public Integer getTargetPositionDmf() {
        return targetPositionDmf;
    }

    public MTriggerEffectRange targetPositionDmf(Integer targetPositionDmf) {
        this.targetPositionDmf = targetPositionDmf;
        return this;
    }

    public void setTargetPositionDmf(Integer targetPositionDmf) {
        this.targetPositionDmf = targetPositionDmf;
    }

    public Integer getTargetPositionDf() {
        return targetPositionDf;
    }

    public MTriggerEffectRange targetPositionDf(Integer targetPositionDf) {
        this.targetPositionDf = targetPositionDf;
        return this;
    }

    public void setTargetPositionDf(Integer targetPositionDf) {
        this.targetPositionDf = targetPositionDf;
    }

    public Integer getTargetAttribute() {
        return targetAttribute;
    }

    public MTriggerEffectRange targetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
        return this;
    }

    public void setTargetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public Integer getTargetNationalityGroupId() {
        return targetNationalityGroupId;
    }

    public MTriggerEffectRange targetNationalityGroupId(Integer targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
        return this;
    }

    public void setTargetNationalityGroupId(Integer targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
    }

    public Integer getTargetTeamGroupId() {
        return targetTeamGroupId;
    }

    public MTriggerEffectRange targetTeamGroupId(Integer targetTeamGroupId) {
        this.targetTeamGroupId = targetTeamGroupId;
        return this;
    }

    public void setTargetTeamGroupId(Integer targetTeamGroupId) {
        this.targetTeamGroupId = targetTeamGroupId;
    }

    public Integer getTargetCharacterGroupId() {
        return targetCharacterGroupId;
    }

    public MTriggerEffectRange targetCharacterGroupId(Integer targetCharacterGroupId) {
        this.targetCharacterGroupId = targetCharacterGroupId;
        return this;
    }

    public void setTargetCharacterGroupId(Integer targetCharacterGroupId) {
        this.targetCharacterGroupId = targetCharacterGroupId;
    }

    public Integer getTargetFormationGroupId() {
        return targetFormationGroupId;
    }

    public MTriggerEffectRange targetFormationGroupId(Integer targetFormationGroupId) {
        this.targetFormationGroupId = targetFormationGroupId;
        return this;
    }

    public void setTargetFormationGroupId(Integer targetFormationGroupId) {
        this.targetFormationGroupId = targetFormationGroupId;
    }

    public Integer getTargetActionCommand() {
        return targetActionCommand;
    }

    public MTriggerEffectRange targetActionCommand(Integer targetActionCommand) {
        this.targetActionCommand = targetActionCommand;
        return this;
    }

    public void setTargetActionCommand(Integer targetActionCommand) {
        this.targetActionCommand = targetActionCommand;
    }

    public Set<MTriggerEffectBase> getMTriggerEffectBases() {
        return mTriggerEffectBases;
    }

    public MTriggerEffectRange mTriggerEffectBases(Set<MTriggerEffectBase> mTriggerEffectBases) {
        this.mTriggerEffectBases = mTriggerEffectBases;
        return this;
    }

    public MTriggerEffectRange addMTriggerEffectBase(MTriggerEffectBase mTriggerEffectBase) {
        this.mTriggerEffectBases.add(mTriggerEffectBase);
        mTriggerEffectBase.setMtriggereffectrange(this);
        return this;
    }

    public MTriggerEffectRange removeMTriggerEffectBase(MTriggerEffectBase mTriggerEffectBase) {
        this.mTriggerEffectBases.remove(mTriggerEffectBase);
        mTriggerEffectBase.setMtriggereffectrange(null);
        return this;
    }

    public void setMTriggerEffectBases(Set<MTriggerEffectBase> mTriggerEffectBases) {
        this.mTriggerEffectBases = mTriggerEffectBases;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTriggerEffectRange)) {
            return false;
        }
        return id != null && id.equals(((MTriggerEffectRange) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTriggerEffectRange{" +
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
