package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTargetActionTypeGroup.
 */
@Entity
@Table(name = "m_target_action_type_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTargetActionTypeGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "command_type", nullable = false)
    private Integer commandType;

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

    public MTargetActionTypeGroup groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCommandType() {
        return commandType;
    }

    public MTargetActionTypeGroup commandType(Integer commandType) {
        this.commandType = commandType;
        return this;
    }

    public void setCommandType(Integer commandType) {
        this.commandType = commandType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTargetActionTypeGroup)) {
            return false;
        }
        return id != null && id.equals(((MTargetActionTypeGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTargetActionTypeGroup{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", commandType=" + getCommandType() +
            "}";
    }
}
