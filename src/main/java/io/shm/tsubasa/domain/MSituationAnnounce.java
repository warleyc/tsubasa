package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MSituationAnnounce.
 */
@Entity
@Table(name = "m_situation_announce")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MSituationAnnounce implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "situation_id", nullable = false)
    private Integer situationId;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSituationId() {
        return situationId;
    }

    public MSituationAnnounce situationId(Integer situationId) {
        this.situationId = situationId;
        return this;
    }

    public void setSituationId(Integer situationId) {
        this.situationId = situationId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public MSituationAnnounce groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MSituationAnnounce)) {
            return false;
        }
        return id != null && id.equals(((MSituationAnnounce) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MSituationAnnounce{" +
            "id=" + getId() +
            ", situationId=" + getSituationId() +
            ", groupId=" + getGroupId() +
            "}";
    }
}
