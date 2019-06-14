package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MNationality.
 */
@Entity
@Table(name = "m_nationality")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MNationality implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "icon", nullable = false)
    private String icon;

    @OneToMany(mappedBy = "mnationality")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MTargetNationalityGroup> mTargetNationalityGroups = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MNationality name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public MNationality icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Set<MTargetNationalityGroup> getMTargetNationalityGroups() {
        return mTargetNationalityGroups;
    }

    public MNationality mTargetNationalityGroups(Set<MTargetNationalityGroup> mTargetNationalityGroups) {
        this.mTargetNationalityGroups = mTargetNationalityGroups;
        return this;
    }

    public MNationality addMTargetNationalityGroup(MTargetNationalityGroup mTargetNationalityGroup) {
        this.mTargetNationalityGroups.add(mTargetNationalityGroup);
        mTargetNationalityGroup.setMnationality(this);
        return this;
    }

    public MNationality removeMTargetNationalityGroup(MTargetNationalityGroup mTargetNationalityGroup) {
        this.mTargetNationalityGroups.remove(mTargetNationalityGroup);
        mTargetNationalityGroup.setMnationality(null);
        return this;
    }

    public void setMTargetNationalityGroups(Set<MTargetNationalityGroup> mTargetNationalityGroups) {
        this.mTargetNationalityGroups = mTargetNationalityGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MNationality)) {
            return false;
        }
        return id != null && id.equals(((MNationality) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MNationality{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", icon='" + getIcon() + "'" +
            "}";
    }
}
