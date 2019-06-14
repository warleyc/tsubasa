package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTargetActionGroup.
 */
@Entity
@Table(name = "m_target_action_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTargetActionGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "action_id", nullable = false)
    private Integer actionId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mTargetActionGroups")
    private MAction maction;

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

    public MTargetActionGroup groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public MTargetActionGroup actionId(Integer actionId) {
        this.actionId = actionId;
        return this;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public MAction getMaction() {
        return maction;
    }

    public MTargetActionGroup maction(MAction mAction) {
        this.maction = mAction;
        return this;
    }

    public void setMaction(MAction mAction) {
        this.maction = mAction;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTargetActionGroup)) {
            return false;
        }
        return id != null && id.equals(((MTargetActionGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTargetActionGroup{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", actionId=" + getActionId() +
            "}";
    }
}
