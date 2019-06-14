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
 * A MMatchOption.
 */
@Entity
@Table(name = "m_match_option")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMatchOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passive_effect_id")
    private Integer passiveEffectId;

    @NotNull
    @Column(name = "passive_effect_value", nullable = false)
    private Integer passiveEffectValue;

    @NotNull
    @Column(name = "activate_full_power_type", nullable = false)
    private Integer activateFullPowerType;

    @NotNull
    @Column(name = "attribute_magnification", nullable = false)
    private Integer attributeMagnification;

    @NotNull
    @Column(name = "use_stamina_magnification", nullable = false)
    private Integer useStaminaMagnification;

    @NotNull
    @Column(name = "is_ignore_team_skill", nullable = false)
    private Integer isIgnoreTeamSkill;

    @NotNull
    @Column(name = "stamina_infinity_type", nullable = false)
    private Integer staminaInfinityType;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mMatchOptions")
    private MPassiveEffectRange id;

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MLeagueRegulation> mLeagueRegulations = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MPvpRegulation> mPvpRegulations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPassiveEffectId() {
        return passiveEffectId;
    }

    public MMatchOption passiveEffectId(Integer passiveEffectId) {
        this.passiveEffectId = passiveEffectId;
        return this;
    }

    public void setPassiveEffectId(Integer passiveEffectId) {
        this.passiveEffectId = passiveEffectId;
    }

    public Integer getPassiveEffectValue() {
        return passiveEffectValue;
    }

    public MMatchOption passiveEffectValue(Integer passiveEffectValue) {
        this.passiveEffectValue = passiveEffectValue;
        return this;
    }

    public void setPassiveEffectValue(Integer passiveEffectValue) {
        this.passiveEffectValue = passiveEffectValue;
    }

    public Integer getActivateFullPowerType() {
        return activateFullPowerType;
    }

    public MMatchOption activateFullPowerType(Integer activateFullPowerType) {
        this.activateFullPowerType = activateFullPowerType;
        return this;
    }

    public void setActivateFullPowerType(Integer activateFullPowerType) {
        this.activateFullPowerType = activateFullPowerType;
    }

    public Integer getAttributeMagnification() {
        return attributeMagnification;
    }

    public MMatchOption attributeMagnification(Integer attributeMagnification) {
        this.attributeMagnification = attributeMagnification;
        return this;
    }

    public void setAttributeMagnification(Integer attributeMagnification) {
        this.attributeMagnification = attributeMagnification;
    }

    public Integer getUseStaminaMagnification() {
        return useStaminaMagnification;
    }

    public MMatchOption useStaminaMagnification(Integer useStaminaMagnification) {
        this.useStaminaMagnification = useStaminaMagnification;
        return this;
    }

    public void setUseStaminaMagnification(Integer useStaminaMagnification) {
        this.useStaminaMagnification = useStaminaMagnification;
    }

    public Integer getIsIgnoreTeamSkill() {
        return isIgnoreTeamSkill;
    }

    public MMatchOption isIgnoreTeamSkill(Integer isIgnoreTeamSkill) {
        this.isIgnoreTeamSkill = isIgnoreTeamSkill;
        return this;
    }

    public void setIsIgnoreTeamSkill(Integer isIgnoreTeamSkill) {
        this.isIgnoreTeamSkill = isIgnoreTeamSkill;
    }

    public Integer getStaminaInfinityType() {
        return staminaInfinityType;
    }

    public MMatchOption staminaInfinityType(Integer staminaInfinityType) {
        this.staminaInfinityType = staminaInfinityType;
        return this;
    }

    public void setStaminaInfinityType(Integer staminaInfinityType) {
        this.staminaInfinityType = staminaInfinityType;
    }

    public MPassiveEffectRange getId() {
        return id;
    }

    public MMatchOption id(MPassiveEffectRange mPassiveEffectRange) {
        this.id = mPassiveEffectRange;
        return this;
    }

    public void setId(MPassiveEffectRange mPassiveEffectRange) {
        this.id = mPassiveEffectRange;
    }

    public Set<MLeagueRegulation> getMLeagueRegulations() {
        return mLeagueRegulations;
    }

    public MMatchOption mLeagueRegulations(Set<MLeagueRegulation> mLeagueRegulations) {
        this.mLeagueRegulations = mLeagueRegulations;
        return this;
    }

    public MMatchOption addMLeagueRegulation(MLeagueRegulation mLeagueRegulation) {
        this.mLeagueRegulations.add(mLeagueRegulation);
        mLeagueRegulation.setId(this);
        return this;
    }

    public MMatchOption removeMLeagueRegulation(MLeagueRegulation mLeagueRegulation) {
        this.mLeagueRegulations.remove(mLeagueRegulation);
        mLeagueRegulation.setId(null);
        return this;
    }

    public void setMLeagueRegulations(Set<MLeagueRegulation> mLeagueRegulations) {
        this.mLeagueRegulations = mLeagueRegulations;
    }

    public Set<MPvpRegulation> getMPvpRegulations() {
        return mPvpRegulations;
    }

    public MMatchOption mPvpRegulations(Set<MPvpRegulation> mPvpRegulations) {
        this.mPvpRegulations = mPvpRegulations;
        return this;
    }

    public MMatchOption addMPvpRegulation(MPvpRegulation mPvpRegulation) {
        this.mPvpRegulations.add(mPvpRegulation);
        mPvpRegulation.setId(this);
        return this;
    }

    public MMatchOption removeMPvpRegulation(MPvpRegulation mPvpRegulation) {
        this.mPvpRegulations.remove(mPvpRegulation);
        mPvpRegulation.setId(null);
        return this;
    }

    public void setMPvpRegulations(Set<MPvpRegulation> mPvpRegulations) {
        this.mPvpRegulations = mPvpRegulations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MMatchOption)) {
            return false;
        }
        return id != null && id.equals(((MMatchOption) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMatchOption{" +
            "id=" + getId() +
            ", passiveEffectId=" + getPassiveEffectId() +
            ", passiveEffectValue=" + getPassiveEffectValue() +
            ", activateFullPowerType=" + getActivateFullPowerType() +
            ", attributeMagnification=" + getAttributeMagnification() +
            ", useStaminaMagnification=" + getUseStaminaMagnification() +
            ", isIgnoreTeamSkill=" + getIsIgnoreTeamSkill() +
            ", staminaInfinityType=" + getStaminaInfinityType() +
            "}";
    }
}
