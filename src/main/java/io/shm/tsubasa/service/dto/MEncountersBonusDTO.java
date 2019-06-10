package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MEncountersBonus} entity.
 */
public class MEncountersBonusDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer encountersType;

    @NotNull
    private Integer isSkillSuccess;

    @NotNull
    private Integer threshold;

    @NotNull
    private Integer probabilityBonusValue;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEncountersType() {
        return encountersType;
    }

    public void setEncountersType(Integer encountersType) {
        this.encountersType = encountersType;
    }

    public Integer getIsSkillSuccess() {
        return isSkillSuccess;
    }

    public void setIsSkillSuccess(Integer isSkillSuccess) {
        this.isSkillSuccess = isSkillSuccess;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getProbabilityBonusValue() {
        return probabilityBonusValue;
    }

    public void setProbabilityBonusValue(Integer probabilityBonusValue) {
        this.probabilityBonusValue = probabilityBonusValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MEncountersBonusDTO mEncountersBonusDTO = (MEncountersBonusDTO) o;
        if (mEncountersBonusDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mEncountersBonusDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MEncountersBonusDTO{" +
            "id=" + getId() +
            ", encountersType=" + getEncountersType() +
            ", isSkillSuccess=" + getIsSkillSuccess() +
            ", threshold=" + getThreshold() +
            ", probabilityBonusValue=" + getProbabilityBonusValue() +
            "}";
    }
}
