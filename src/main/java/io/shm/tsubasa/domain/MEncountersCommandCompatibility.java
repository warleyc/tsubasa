package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MEncountersCommandCompatibility.
 */
@Entity
@Table(name = "m_encounters_command_compatibility")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MEncountersCommandCompatibility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "encounters_type", nullable = false)
    private Integer encountersType;

    @NotNull
    @Column(name = "offense_command_type", nullable = false)
    private Integer offenseCommandType;

    @NotNull
    @Column(name = "defense_command_type", nullable = false)
    private Integer defenseCommandType;

    @NotNull
    @Column(name = "offense_magnification", nullable = false)
    private Integer offenseMagnification;

    @NotNull
    @Column(name = "defense_magnification", nullable = false)
    private Integer defenseMagnification;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEncountersType() {
        return encountersType;
    }

    public MEncountersCommandCompatibility encountersType(Integer encountersType) {
        this.encountersType = encountersType;
        return this;
    }

    public void setEncountersType(Integer encountersType) {
        this.encountersType = encountersType;
    }

    public Integer getOffenseCommandType() {
        return offenseCommandType;
    }

    public MEncountersCommandCompatibility offenseCommandType(Integer offenseCommandType) {
        this.offenseCommandType = offenseCommandType;
        return this;
    }

    public void setOffenseCommandType(Integer offenseCommandType) {
        this.offenseCommandType = offenseCommandType;
    }

    public Integer getDefenseCommandType() {
        return defenseCommandType;
    }

    public MEncountersCommandCompatibility defenseCommandType(Integer defenseCommandType) {
        this.defenseCommandType = defenseCommandType;
        return this;
    }

    public void setDefenseCommandType(Integer defenseCommandType) {
        this.defenseCommandType = defenseCommandType;
    }

    public Integer getOffenseMagnification() {
        return offenseMagnification;
    }

    public MEncountersCommandCompatibility offenseMagnification(Integer offenseMagnification) {
        this.offenseMagnification = offenseMagnification;
        return this;
    }

    public void setOffenseMagnification(Integer offenseMagnification) {
        this.offenseMagnification = offenseMagnification;
    }

    public Integer getDefenseMagnification() {
        return defenseMagnification;
    }

    public MEncountersCommandCompatibility defenseMagnification(Integer defenseMagnification) {
        this.defenseMagnification = defenseMagnification;
        return this;
    }

    public void setDefenseMagnification(Integer defenseMagnification) {
        this.defenseMagnification = defenseMagnification;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MEncountersCommandCompatibility)) {
            return false;
        }
        return id != null && id.equals(((MEncountersCommandCompatibility) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MEncountersCommandCompatibility{" +
            "id=" + getId() +
            ", encountersType=" + getEncountersType() +
            ", offenseCommandType=" + getOffenseCommandType() +
            ", defenseCommandType=" + getDefenseCommandType() +
            ", offenseMagnification=" + getOffenseMagnification() +
            ", defenseMagnification=" + getDefenseMagnification() +
            "}";
    }
}
