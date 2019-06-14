package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MPassiveEffectRange} entity.
 */
public class MPassiveEffectRangeDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    @NotNull
    private Integer effectParamType;

    @NotNull
    private Integer targetPositionGk;

    @NotNull
    private Integer targetPositionFw;

    @NotNull
    private Integer targetPositionOmf;

    @NotNull
    private Integer targetPositionDmf;

    @NotNull
    private Integer targetPositionDf;

    @NotNull
    private Integer targetAttribute;

    private Integer targetNationalityGroupId;

    private Integer targetTeamGroupId;

    private Integer targetPlayableCardGroupId;

    
    @Lob
    private String description;


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

    public Integer getEffectParamType() {
        return effectParamType;
    }

    public void setEffectParamType(Integer effectParamType) {
        this.effectParamType = effectParamType;
    }

    public Integer getTargetPositionGk() {
        return targetPositionGk;
    }

    public void setTargetPositionGk(Integer targetPositionGk) {
        this.targetPositionGk = targetPositionGk;
    }

    public Integer getTargetPositionFw() {
        return targetPositionFw;
    }

    public void setTargetPositionFw(Integer targetPositionFw) {
        this.targetPositionFw = targetPositionFw;
    }

    public Integer getTargetPositionOmf() {
        return targetPositionOmf;
    }

    public void setTargetPositionOmf(Integer targetPositionOmf) {
        this.targetPositionOmf = targetPositionOmf;
    }

    public Integer getTargetPositionDmf() {
        return targetPositionDmf;
    }

    public void setTargetPositionDmf(Integer targetPositionDmf) {
        this.targetPositionDmf = targetPositionDmf;
    }

    public Integer getTargetPositionDf() {
        return targetPositionDf;
    }

    public void setTargetPositionDf(Integer targetPositionDf) {
        this.targetPositionDf = targetPositionDf;
    }

    public Integer getTargetAttribute() {
        return targetAttribute;
    }

    public void setTargetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public Integer getTargetNationalityGroupId() {
        return targetNationalityGroupId;
    }

    public void setTargetNationalityGroupId(Integer targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
    }

    public Integer getTargetTeamGroupId() {
        return targetTeamGroupId;
    }

    public void setTargetTeamGroupId(Integer targetTeamGroupId) {
        this.targetTeamGroupId = targetTeamGroupId;
    }

    public Integer getTargetPlayableCardGroupId() {
        return targetPlayableCardGroupId;
    }

    public void setTargetPlayableCardGroupId(Integer targetPlayableCardGroupId) {
        this.targetPlayableCardGroupId = targetPlayableCardGroupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MPassiveEffectRangeDTO mPassiveEffectRangeDTO = (MPassiveEffectRangeDTO) o;
        if (mPassiveEffectRangeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mPassiveEffectRangeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MPassiveEffectRangeDTO{" +
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
