package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MLuck} entity.
 */
public class MLuckDTO implements Serializable {

    private Long id;

    private Integer targetAttribute;

    private Integer targetCharacterGroupId;

    private Integer targetTeamGroupId;

    private Integer targetNationalityGroupId;

    @NotNull
    private Integer luckRateGroupId;

    
    @Lob
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTargetAttribute() {
        return targetAttribute;
    }

    public void setTargetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public Integer getTargetCharacterGroupId() {
        return targetCharacterGroupId;
    }

    public void setTargetCharacterGroupId(Integer targetCharacterGroupId) {
        this.targetCharacterGroupId = targetCharacterGroupId;
    }

    public Integer getTargetTeamGroupId() {
        return targetTeamGroupId;
    }

    public void setTargetTeamGroupId(Integer targetTeamGroupId) {
        this.targetTeamGroupId = targetTeamGroupId;
    }

    public Integer getTargetNationalityGroupId() {
        return targetNationalityGroupId;
    }

    public void setTargetNationalityGroupId(Integer targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
    }

    public Integer getLuckRateGroupId() {
        return luckRateGroupId;
    }

    public void setLuckRateGroupId(Integer luckRateGroupId) {
        this.luckRateGroupId = luckRateGroupId;
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

        MLuckDTO mLuckDTO = (MLuckDTO) o;
        if (mLuckDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mLuckDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MLuckDTO{" +
            "id=" + getId() +
            ", targetAttribute=" + getTargetAttribute() +
            ", targetCharacterGroupId=" + getTargetCharacterGroupId() +
            ", targetTeamGroupId=" + getTargetTeamGroupId() +
            ", targetNationalityGroupId=" + getTargetNationalityGroupId() +
            ", luckRateGroupId=" + getLuckRateGroupId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
