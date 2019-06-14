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
 * A MPassiveEffectRange.
 */
@Entity
@Table(name = "m_passive_effect_range")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MPassiveEffectRange implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "effect_param_type", nullable = false)
    private Integer effectParamType;

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

    @NotNull
    @Column(name = "target_attribute", nullable = false)
    private Integer targetAttribute;

    @Column(name = "target_nationality_group_id")
    private Integer targetNationalityGroupId;

    @Column(name = "target_team_group_id")
    private Integer targetTeamGroupId;

    @Column(name = "target_playable_card_group_id")
    private Integer targetPlayableCardGroupId;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "mpassiveeffectrange")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MFormation> mFormations = new HashSet<>();

    @OneToMany(mappedBy = "mpassiveeffectrange")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MMatchOption> mMatchOptions = new HashSet<>();

    @OneToMany(mappedBy = "mpassiveeffectrange")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MTeamEffectBase> mTeamEffectBases = new HashSet<>();

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

    public MPassiveEffectRange name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEffectParamType() {
        return effectParamType;
    }

    public MPassiveEffectRange effectParamType(Integer effectParamType) {
        this.effectParamType = effectParamType;
        return this;
    }

    public void setEffectParamType(Integer effectParamType) {
        this.effectParamType = effectParamType;
    }

    public Integer getTargetPositionGk() {
        return targetPositionGk;
    }

    public MPassiveEffectRange targetPositionGk(Integer targetPositionGk) {
        this.targetPositionGk = targetPositionGk;
        return this;
    }

    public void setTargetPositionGk(Integer targetPositionGk) {
        this.targetPositionGk = targetPositionGk;
    }

    public Integer getTargetPositionFw() {
        return targetPositionFw;
    }

    public MPassiveEffectRange targetPositionFw(Integer targetPositionFw) {
        this.targetPositionFw = targetPositionFw;
        return this;
    }

    public void setTargetPositionFw(Integer targetPositionFw) {
        this.targetPositionFw = targetPositionFw;
    }

    public Integer getTargetPositionOmf() {
        return targetPositionOmf;
    }

    public MPassiveEffectRange targetPositionOmf(Integer targetPositionOmf) {
        this.targetPositionOmf = targetPositionOmf;
        return this;
    }

    public void setTargetPositionOmf(Integer targetPositionOmf) {
        this.targetPositionOmf = targetPositionOmf;
    }

    public Integer getTargetPositionDmf() {
        return targetPositionDmf;
    }

    public MPassiveEffectRange targetPositionDmf(Integer targetPositionDmf) {
        this.targetPositionDmf = targetPositionDmf;
        return this;
    }

    public void setTargetPositionDmf(Integer targetPositionDmf) {
        this.targetPositionDmf = targetPositionDmf;
    }

    public Integer getTargetPositionDf() {
        return targetPositionDf;
    }

    public MPassiveEffectRange targetPositionDf(Integer targetPositionDf) {
        this.targetPositionDf = targetPositionDf;
        return this;
    }

    public void setTargetPositionDf(Integer targetPositionDf) {
        this.targetPositionDf = targetPositionDf;
    }

    public Integer getTargetAttribute() {
        return targetAttribute;
    }

    public MPassiveEffectRange targetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
        return this;
    }

    public void setTargetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public Integer getTargetNationalityGroupId() {
        return targetNationalityGroupId;
    }

    public MPassiveEffectRange targetNationalityGroupId(Integer targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
        return this;
    }

    public void setTargetNationalityGroupId(Integer targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
    }

    public Integer getTargetTeamGroupId() {
        return targetTeamGroupId;
    }

    public MPassiveEffectRange targetTeamGroupId(Integer targetTeamGroupId) {
        this.targetTeamGroupId = targetTeamGroupId;
        return this;
    }

    public void setTargetTeamGroupId(Integer targetTeamGroupId) {
        this.targetTeamGroupId = targetTeamGroupId;
    }

    public Integer getTargetPlayableCardGroupId() {
        return targetPlayableCardGroupId;
    }

    public MPassiveEffectRange targetPlayableCardGroupId(Integer targetPlayableCardGroupId) {
        this.targetPlayableCardGroupId = targetPlayableCardGroupId;
        return this;
    }

    public void setTargetPlayableCardGroupId(Integer targetPlayableCardGroupId) {
        this.targetPlayableCardGroupId = targetPlayableCardGroupId;
    }

    public String getDescription() {
        return description;
    }

    public MPassiveEffectRange description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<MFormation> getMFormations() {
        return mFormations;
    }

    public MPassiveEffectRange mFormations(Set<MFormation> mFormations) {
        this.mFormations = mFormations;
        return this;
    }

    public MPassiveEffectRange addMFormation(MFormation mFormation) {
        this.mFormations.add(mFormation);
        mFormation.setMpassiveeffectrange(this);
        return this;
    }

    public MPassiveEffectRange removeMFormation(MFormation mFormation) {
        this.mFormations.remove(mFormation);
        mFormation.setMpassiveeffectrange(null);
        return this;
    }

    public void setMFormations(Set<MFormation> mFormations) {
        this.mFormations = mFormations;
    }

    public Set<MMatchOption> getMMatchOptions() {
        return mMatchOptions;
    }

    public MPassiveEffectRange mMatchOptions(Set<MMatchOption> mMatchOptions) {
        this.mMatchOptions = mMatchOptions;
        return this;
    }

    public MPassiveEffectRange addMMatchOption(MMatchOption mMatchOption) {
        this.mMatchOptions.add(mMatchOption);
        mMatchOption.setMpassiveeffectrange(this);
        return this;
    }

    public MPassiveEffectRange removeMMatchOption(MMatchOption mMatchOption) {
        this.mMatchOptions.remove(mMatchOption);
        mMatchOption.setMpassiveeffectrange(null);
        return this;
    }

    public void setMMatchOptions(Set<MMatchOption> mMatchOptions) {
        this.mMatchOptions = mMatchOptions;
    }

    public Set<MTeamEffectBase> getMTeamEffectBases() {
        return mTeamEffectBases;
    }

    public MPassiveEffectRange mTeamEffectBases(Set<MTeamEffectBase> mTeamEffectBases) {
        this.mTeamEffectBases = mTeamEffectBases;
        return this;
    }

    public MPassiveEffectRange addMTeamEffectBase(MTeamEffectBase mTeamEffectBase) {
        this.mTeamEffectBases.add(mTeamEffectBase);
        mTeamEffectBase.setMpassiveeffectrange(this);
        return this;
    }

    public MPassiveEffectRange removeMTeamEffectBase(MTeamEffectBase mTeamEffectBase) {
        this.mTeamEffectBases.remove(mTeamEffectBase);
        mTeamEffectBase.setMpassiveeffectrange(null);
        return this;
    }

    public void setMTeamEffectBases(Set<MTeamEffectBase> mTeamEffectBases) {
        this.mTeamEffectBases = mTeamEffectBases;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MPassiveEffectRange)) {
            return false;
        }
        return id != null && id.equals(((MPassiveEffectRange) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MPassiveEffectRange{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", effectParamType=" + getEffectParamType() +
            ", targetPositionGk=" + getTargetPositionGk() +
            ", targetPositionFw=" + getTargetPositionFw() +
            ", targetPositionOmf=" + getTargetPositionOmf() +
            ", targetPositionDmf=" + getTargetPositionDmf() +
            ", targetPositionDf=" + getTargetPositionDf() +
            ", targetAttribute=" + getTargetAttribute() +
            ", targetNationalityGroupId=" + getTargetNationalityGroupId() +
            ", targetTeamGroupId=" + getTargetTeamGroupId() +
            ", targetPlayableCardGroupId=" + getTargetPlayableCardGroupId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
