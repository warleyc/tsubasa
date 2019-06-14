package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTargetFormationGroup.
 */
@Entity
@Table(name = "m_target_formation_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTargetFormationGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "formation_id", nullable = false)
    private Integer formationId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mTargetFormationGroups")
    private MFormation id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public MTargetFormationGroup groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getFormationId() {
        return formationId;
    }

    public MTargetFormationGroup formationId(Integer formationId) {
        this.formationId = formationId;
        return this;
    }

    public void setFormationId(Integer formationId) {
        this.formationId = formationId;
    }

    public MFormation getId() {
        return id;
    }

    public MTargetFormationGroup id(MFormation mFormation) {
        this.id = mFormation;
        return this;
    }

    public void setId(MFormation mFormation) {
        this.id = mFormation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTargetFormationGroup)) {
            return false;
        }
        return id != null && id.equals(((MTargetFormationGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTargetFormationGroup{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", formationId=" + getFormationId() +
            "}";
    }
}
