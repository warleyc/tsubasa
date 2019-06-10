package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MFormation} entity.
 */
public class MFormationDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer effectValue;

    @NotNull
    private Integer effectProbability;

    
    @Lob
    private String formationArrangementFw;

    
    @Lob
    private String formationArrangementOmf;

    
    @Lob
    private String formationArrangementDmf;

    
    @Lob
    private String formationArrangementDf;

    private Integer effectId;

    
    @Lob
    private String description;

    
    @Lob
    private String shortDescription;

    
    @Lob
    private String name;

    
    @Lob
    private String thumbnailAssetName;

    
    @Lob
    private String startingUniformNos;

    
    @Lob
    private String subUniformNos;

    private Integer exType;

    @NotNull
    private Integer matchFormationId;


    private Long idId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEffectValue() {
        return effectValue;
    }

    public void setEffectValue(Integer effectValue) {
        this.effectValue = effectValue;
    }

    public Integer getEffectProbability() {
        return effectProbability;
    }

    public void setEffectProbability(Integer effectProbability) {
        this.effectProbability = effectProbability;
    }

    public String getFormationArrangementFw() {
        return formationArrangementFw;
    }

    public void setFormationArrangementFw(String formationArrangementFw) {
        this.formationArrangementFw = formationArrangementFw;
    }

    public String getFormationArrangementOmf() {
        return formationArrangementOmf;
    }

    public void setFormationArrangementOmf(String formationArrangementOmf) {
        this.formationArrangementOmf = formationArrangementOmf;
    }

    public String getFormationArrangementDmf() {
        return formationArrangementDmf;
    }

    public void setFormationArrangementDmf(String formationArrangementDmf) {
        this.formationArrangementDmf = formationArrangementDmf;
    }

    public String getFormationArrangementDf() {
        return formationArrangementDf;
    }

    public void setFormationArrangementDf(String formationArrangementDf) {
        this.formationArrangementDf = formationArrangementDf;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
    }

    public String getStartingUniformNos() {
        return startingUniformNos;
    }

    public void setStartingUniformNos(String startingUniformNos) {
        this.startingUniformNos = startingUniformNos;
    }

    public String getSubUniformNos() {
        return subUniformNos;
    }

    public void setSubUniformNos(String subUniformNos) {
        this.subUniformNos = subUniformNos;
    }

    public Integer getExType() {
        return exType;
    }

    public void setExType(Integer exType) {
        this.exType = exType;
    }

    public Integer getMatchFormationId() {
        return matchFormationId;
    }

    public void setMatchFormationId(Integer matchFormationId) {
        this.matchFormationId = matchFormationId;
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

        MFormationDTO mFormationDTO = (MFormationDTO) o;
        if (mFormationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mFormationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MFormationDTO{" +
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
            ", id=" + getIdId() +
            "}";
    }
}
