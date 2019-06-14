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
 * A MFormation.
 */
@Entity
@Table(name = "m_formation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MFormation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "effect_value", nullable = false)
    private Integer effectValue;

    @NotNull
    @Column(name = "effect_probability", nullable = false)
    private Integer effectProbability;

    
    @Lob
    @Column(name = "formation_arrangement_fw", nullable = false)
    private String formationArrangementFw;

    
    @Lob
    @Column(name = "formation_arrangement_omf", nullable = false)
    private String formationArrangementOmf;

    
    @Lob
    @Column(name = "formation_arrangement_dmf", nullable = false)
    private String formationArrangementDmf;

    
    @Lob
    @Column(name = "formation_arrangement_df", nullable = false)
    private String formationArrangementDf;

    @Column(name = "effect_id")
    private Integer effectId;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    
    @Lob
    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "thumbnail_asset_name", nullable = false)
    private String thumbnailAssetName;

    
    @Lob
    @Column(name = "starting_uniform_nos", nullable = false)
    private String startingUniformNos;

    
    @Lob
    @Column(name = "sub_uniform_nos", nullable = false)
    private String subUniformNos;

    @Column(name = "ex_type")
    private Integer exType;

    @NotNull
    @Column(name = "match_formation_id", nullable = false)
    private Integer matchFormationId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mFormations")
    private MPassiveEffectRange mpassiveeffectrange;

    @OneToMany(mappedBy = "mformation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MNpcDeck> mNpcDecks = new HashSet<>();

    @OneToMany(mappedBy = "mformation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MTargetFormationGroup> mTargetFormationGroups = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEffectValue() {
        return effectValue;
    }

    public MFormation effectValue(Integer effectValue) {
        this.effectValue = effectValue;
        return this;
    }

    public void setEffectValue(Integer effectValue) {
        this.effectValue = effectValue;
    }

    public Integer getEffectProbability() {
        return effectProbability;
    }

    public MFormation effectProbability(Integer effectProbability) {
        this.effectProbability = effectProbability;
        return this;
    }

    public void setEffectProbability(Integer effectProbability) {
        this.effectProbability = effectProbability;
    }

    public String getFormationArrangementFw() {
        return formationArrangementFw;
    }

    public MFormation formationArrangementFw(String formationArrangementFw) {
        this.formationArrangementFw = formationArrangementFw;
        return this;
    }

    public void setFormationArrangementFw(String formationArrangementFw) {
        this.formationArrangementFw = formationArrangementFw;
    }

    public String getFormationArrangementOmf() {
        return formationArrangementOmf;
    }

    public MFormation formationArrangementOmf(String formationArrangementOmf) {
        this.formationArrangementOmf = formationArrangementOmf;
        return this;
    }

    public void setFormationArrangementOmf(String formationArrangementOmf) {
        this.formationArrangementOmf = formationArrangementOmf;
    }

    public String getFormationArrangementDmf() {
        return formationArrangementDmf;
    }

    public MFormation formationArrangementDmf(String formationArrangementDmf) {
        this.formationArrangementDmf = formationArrangementDmf;
        return this;
    }

    public void setFormationArrangementDmf(String formationArrangementDmf) {
        this.formationArrangementDmf = formationArrangementDmf;
    }

    public String getFormationArrangementDf() {
        return formationArrangementDf;
    }

    public MFormation formationArrangementDf(String formationArrangementDf) {
        this.formationArrangementDf = formationArrangementDf;
        return this;
    }

    public void setFormationArrangementDf(String formationArrangementDf) {
        this.formationArrangementDf = formationArrangementDf;
    }

    public Integer getEffectId() {
        return effectId;
    }

    public MFormation effectId(Integer effectId) {
        this.effectId = effectId;
        return this;
    }

    public void setEffectId(Integer effectId) {
        this.effectId = effectId;
    }

    public String getDescription() {
        return description;
    }

    public MFormation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public MFormation shortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getName() {
        return name;
    }

    public MFormation name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public MFormation thumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
        return this;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
    }

    public String getStartingUniformNos() {
        return startingUniformNos;
    }

    public MFormation startingUniformNos(String startingUniformNos) {
        this.startingUniformNos = startingUniformNos;
        return this;
    }

    public void setStartingUniformNos(String startingUniformNos) {
        this.startingUniformNos = startingUniformNos;
    }

    public String getSubUniformNos() {
        return subUniformNos;
    }

    public MFormation subUniformNos(String subUniformNos) {
        this.subUniformNos = subUniformNos;
        return this;
    }

    public void setSubUniformNos(String subUniformNos) {
        this.subUniformNos = subUniformNos;
    }

    public Integer getExType() {
        return exType;
    }

    public MFormation exType(Integer exType) {
        this.exType = exType;
        return this;
    }

    public void setExType(Integer exType) {
        this.exType = exType;
    }

    public Integer getMatchFormationId() {
        return matchFormationId;
    }

    public MFormation matchFormationId(Integer matchFormationId) {
        this.matchFormationId = matchFormationId;
        return this;
    }

    public void setMatchFormationId(Integer matchFormationId) {
        this.matchFormationId = matchFormationId;
    }

    public MPassiveEffectRange getMpassiveeffectrange() {
        return mpassiveeffectrange;
    }

    public MFormation mpassiveeffectrange(MPassiveEffectRange mPassiveEffectRange) {
        this.mpassiveeffectrange = mPassiveEffectRange;
        return this;
    }

    public void setMpassiveeffectrange(MPassiveEffectRange mPassiveEffectRange) {
        this.mpassiveeffectrange = mPassiveEffectRange;
    }

    public Set<MNpcDeck> getMNpcDecks() {
        return mNpcDecks;
    }

    public MFormation mNpcDecks(Set<MNpcDeck> mNpcDecks) {
        this.mNpcDecks = mNpcDecks;
        return this;
    }

    public MFormation addMNpcDeck(MNpcDeck mNpcDeck) {
        this.mNpcDecks.add(mNpcDeck);
        mNpcDeck.setMformation(this);
        return this;
    }

    public MFormation removeMNpcDeck(MNpcDeck mNpcDeck) {
        this.mNpcDecks.remove(mNpcDeck);
        mNpcDeck.setMformation(null);
        return this;
    }

    public void setMNpcDecks(Set<MNpcDeck> mNpcDecks) {
        this.mNpcDecks = mNpcDecks;
    }

    public Set<MTargetFormationGroup> getMTargetFormationGroups() {
        return mTargetFormationGroups;
    }

    public MFormation mTargetFormationGroups(Set<MTargetFormationGroup> mTargetFormationGroups) {
        this.mTargetFormationGroups = mTargetFormationGroups;
        return this;
    }

    public MFormation addMTargetFormationGroup(MTargetFormationGroup mTargetFormationGroup) {
        this.mTargetFormationGroups.add(mTargetFormationGroup);
        mTargetFormationGroup.setMformation(this);
        return this;
    }

    public MFormation removeMTargetFormationGroup(MTargetFormationGroup mTargetFormationGroup) {
        this.mTargetFormationGroups.remove(mTargetFormationGroup);
        mTargetFormationGroup.setMformation(null);
        return this;
    }

    public void setMTargetFormationGroups(Set<MTargetFormationGroup> mTargetFormationGroups) {
        this.mTargetFormationGroups = mTargetFormationGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MFormation)) {
            return false;
        }
        return id != null && id.equals(((MFormation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MFormation{" +
            "id=" + getId() +
            ", effectValue=" + getEffectValue() +
            ", effectProbability=" + getEffectProbability() +
            ", formationArrangementFw='" + getFormationArrangementFw() + "'" +
            ", formationArrangementOmf='" + getFormationArrangementOmf() + "'" +
            ", formationArrangementDmf='" + getFormationArrangementDmf() + "'" +
            ", formationArrangementDf='" + getFormationArrangementDf() + "'" +
            ", effectId=" + getEffectId() +
            ", description='" + getDescription() + "'" +
            ", shortDescription='" + getShortDescription() + "'" +
            ", name='" + getName() + "'" +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            ", startingUniformNos='" + getStartingUniformNos() + "'" +
            ", subUniformNos='" + getSubUniformNos() + "'" +
            ", exType=" + getExType() +
            ", matchFormationId=" + getMatchFormationId() +
            "}";
    }
}
