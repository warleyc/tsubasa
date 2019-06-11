package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MEncountersBonus.
 */
@Entity
@Table(name = "m_encounters_bonus")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MEncountersBonus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "encounters_type", nullable = false)
    private Integer encountersType;

    @NotNull
    @Column(name = "is_skill_success", nullable = false)
    private Integer isSkillSuccess;

    @NotNull
    @Column(name = "threshold", nullable = false)
    private Integer threshold;

    @NotNull
    @Column(name = "probability_bonus_value", nullable = false)
    private Integer probabilityBonusValue;

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

    public MEncountersBonus encountersType(Integer encountersType) {
        this.encountersType = encountersType;
        return this;
    }

    public void setEncountersType(Integer encountersType) {
        this.encountersType = encountersType;
    }

    public Integer getIsSkillSuccess() {
        return isSkillSuccess;
    }

    public MEncountersBonus isSkillSuccess(Integer isSkillSuccess) {
        this.isSkillSuccess = isSkillSuccess;
        return this;
    }

    public void setIsSkillSuccess(Integer isSkillSuccess) {
        this.isSkillSuccess = isSkillSuccess;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public MEncountersBonus threshold(Integer threshold) {
        this.threshold = threshold;
        return this;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getProbabilityBonusValue() {
        return probabilityBonusValue;
    }

    public MEncountersBonus probabilityBonusValue(Integer probabilityBonusValue) {
        this.probabilityBonusValue = probabilityBonusValue;
        return this;
    }

    public void setProbabilityBonusValue(Integer probabilityBonusValue) {
        this.probabilityBonusValue = probabilityBonusValue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MEncountersBonus)) {
            return false;
        }
        return id != null && id.equals(((MEncountersBonus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MEncountersBonus{" +
            "id=" + getId() +
            ", encountersType=" + getEncountersType() +
            ", isSkillSuccess=" + getIsSkillSuccess() +
            ", threshold=" + getThreshold() +
            ", probabilityBonusValue=" + getProbabilityBonusValue() +
            "}";
    }
}
