package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MEncountersCommandCompatibility} entity.
 */
public class MEncountersCommandCompatibilityDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer encountersType;

    @NotNull
    private Integer offenseCommandType;

    @NotNull
    private Integer defenseCommandType;

    @NotNull
    private Integer offenseMagnification;

    @NotNull
    private Integer defenseMagnification;


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

    public Integer getOffenseCommandType() {
        return offenseCommandType;
    }

    public void setOffenseCommandType(Integer offenseCommandType) {
        this.offenseCommandType = offenseCommandType;
    }

    public Integer getDefenseCommandType() {
        return defenseCommandType;
    }

    public void setDefenseCommandType(Integer defenseCommandType) {
        this.defenseCommandType = defenseCommandType;
    }

    public Integer getOffenseMagnification() {
        return offenseMagnification;
    }

    public void setOffenseMagnification(Integer offenseMagnification) {
        this.offenseMagnification = offenseMagnification;
    }

    public Integer getDefenseMagnification() {
        return defenseMagnification;
    }

    public void setDefenseMagnification(Integer defenseMagnification) {
        this.defenseMagnification = defenseMagnification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO = (MEncountersCommandCompatibilityDTO) o;
        if (mEncountersCommandCompatibilityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mEncountersCommandCompatibilityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MEncountersCommandCompatibilityDTO{" +
            "id=" + getId() +
            ", encountersType=" + getEncountersType() +
            ", offenseCommandType=" + getOffenseCommandType() +
            ", defenseCommandType=" + getDefenseCommandType() +
            ", offenseMagnification=" + getOffenseMagnification() +
            ", defenseMagnification=" + getDefenseMagnification() +
            "}";
    }
}
