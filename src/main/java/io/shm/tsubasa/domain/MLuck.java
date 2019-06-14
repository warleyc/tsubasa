package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MLuck.
 */
@Entity
@Table(name = "m_luck")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MLuck implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "target_attribute")
    private Integer targetAttribute;

    @Column(name = "target_character_group_id")
    private Integer targetCharacterGroupId;

    @Column(name = "target_team_group_id")
    private Integer targetTeamGroupId;

    @Column(name = "target_nationality_group_id")
    private Integer targetNationalityGroupId;

    @NotNull
    @Column(name = "luck_rate_group_id", nullable = false)
    private Integer luckRateGroupId;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTargetAttribute() {
        return targetAttribute;
    }

    public MLuck targetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
        return this;
    }

    public void setTargetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public Integer getTargetCharacterGroupId() {
        return targetCharacterGroupId;
    }

    public MLuck targetCharacterGroupId(Integer targetCharacterGroupId) {
        this.targetCharacterGroupId = targetCharacterGroupId;
        return this;
    }

    public void setTargetCharacterGroupId(Integer targetCharacterGroupId) {
        this.targetCharacterGroupId = targetCharacterGroupId;
    }

    public Integer getTargetTeamGroupId() {
        return targetTeamGroupId;
    }

    public MLuck targetTeamGroupId(Integer targetTeamGroupId) {
        this.targetTeamGroupId = targetTeamGroupId;
        return this;
    }

    public void setTargetTeamGroupId(Integer targetTeamGroupId) {
        this.targetTeamGroupId = targetTeamGroupId;
    }

    public Integer getTargetNationalityGroupId() {
        return targetNationalityGroupId;
    }

    public MLuck targetNationalityGroupId(Integer targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
        return this;
    }

    public void setTargetNationalityGroupId(Integer targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
    }

    public Integer getLuckRateGroupId() {
        return luckRateGroupId;
    }

    public MLuck luckRateGroupId(Integer luckRateGroupId) {
        this.luckRateGroupId = luckRateGroupId;
        return this;
    }

    public void setLuckRateGroupId(Integer luckRateGroupId) {
        this.luckRateGroupId = luckRateGroupId;
    }

    public String getDescription() {
        return description;
    }

    public MLuck description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MLuck)) {
            return false;
        }
        return id != null && id.equals(((MLuck) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MLuck{" +
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
