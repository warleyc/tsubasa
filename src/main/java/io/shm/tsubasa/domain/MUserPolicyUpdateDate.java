package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MUserPolicyUpdateDate.
 */
@Entity
@Table(name = "m_user_policy_update_date")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MUserPolicyUpdateDate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "update_date", nullable = false)
    private Integer updateDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUpdateDate() {
        return updateDate;
    }

    public MUserPolicyUpdateDate updateDate(Integer updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Integer updateDate) {
        this.updateDate = updateDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MUserPolicyUpdateDate)) {
            return false;
        }
        return id != null && id.equals(((MUserPolicyUpdateDate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MUserPolicyUpdateDate{" +
            "id=" + getId() +
            ", updateDate=" + getUpdateDate() +
            "}";
    }
}
