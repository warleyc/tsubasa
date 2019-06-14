package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTargetNationalityGroup.
 */
@Entity
@Table(name = "m_target_nationality_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTargetNationalityGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "nationality_id", nullable = false)
    private Integer nationalityId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mTargetNationalityGroups")
    private MNationality mnationality;

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

    public MTargetNationalityGroup groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getNationalityId() {
        return nationalityId;
    }

    public MTargetNationalityGroup nationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
        return this;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    public MNationality getMnationality() {
        return mnationality;
    }

    public MTargetNationalityGroup mnationality(MNationality mNationality) {
        this.mnationality = mNationality;
        return this;
    }

    public void setMnationality(MNationality mNationality) {
        this.mnationality = mNationality;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTargetNationalityGroup)) {
            return false;
        }
        return id != null && id.equals(((MTargetNationalityGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTargetNationalityGroup{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", nationalityId=" + getNationalityId() +
            "}";
    }
}
