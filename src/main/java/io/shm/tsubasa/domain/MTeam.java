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
 * A MTeam.
 */
@Entity
@Table(name = "m_team")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "mteam")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MTargetTeamGroup> mTargetTeamGroups = new HashSet<>();

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

    public MTeam name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MTargetTeamGroup> getMTargetTeamGroups() {
        return mTargetTeamGroups;
    }

    public MTeam mTargetTeamGroups(Set<MTargetTeamGroup> mTargetTeamGroups) {
        this.mTargetTeamGroups = mTargetTeamGroups;
        return this;
    }

    public MTeam addMTargetTeamGroup(MTargetTeamGroup mTargetTeamGroup) {
        this.mTargetTeamGroups.add(mTargetTeamGroup);
        mTargetTeamGroup.setMteam(this);
        return this;
    }

    public MTeam removeMTargetTeamGroup(MTargetTeamGroup mTargetTeamGroup) {
        this.mTargetTeamGroups.remove(mTargetTeamGroup);
        mTargetTeamGroup.setMteam(null);
        return this;
    }

    public void setMTargetTeamGroups(Set<MTargetTeamGroup> mTargetTeamGroups) {
        this.mTargetTeamGroups = mTargetTeamGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTeam)) {
            return false;
        }
        return id != null && id.equals(((MTeam) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTeam{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
