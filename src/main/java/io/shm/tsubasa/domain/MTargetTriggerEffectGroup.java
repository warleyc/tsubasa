package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTargetTriggerEffectGroup.
 */
@Entity
@Table(name = "m_target_trigger_effect_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTargetTriggerEffectGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "trigger_effect_id", nullable = false)
    private Integer triggerEffectId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mTargetTriggerEffectGroups")
    private MTriggerEffectBase mtriggereffectbase;

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

    public MTargetTriggerEffectGroup groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getTriggerEffectId() {
        return triggerEffectId;
    }

    public MTargetTriggerEffectGroup triggerEffectId(Integer triggerEffectId) {
        this.triggerEffectId = triggerEffectId;
        return this;
    }

    public void setTriggerEffectId(Integer triggerEffectId) {
        this.triggerEffectId = triggerEffectId;
    }

    public MTriggerEffectBase getMtriggereffectbase() {
        return mtriggereffectbase;
    }

    public MTargetTriggerEffectGroup mtriggereffectbase(MTriggerEffectBase mTriggerEffectBase) {
        this.mtriggereffectbase = mTriggerEffectBase;
        return this;
    }

    public void setMtriggereffectbase(MTriggerEffectBase mTriggerEffectBase) {
        this.mtriggereffectbase = mTriggerEffectBase;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTargetTriggerEffectGroup)) {
            return false;
        }
        return id != null && id.equals(((MTargetTriggerEffectGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTargetTriggerEffectGroup{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", triggerEffectId=" + getTriggerEffectId() +
            "}";
    }
}
