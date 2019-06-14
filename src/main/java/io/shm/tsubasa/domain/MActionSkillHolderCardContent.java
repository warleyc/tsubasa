package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MActionSkillHolderCardContent.
 */
@Entity
@Table(name = "m_action_skill_holder_card_ct")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MActionSkillHolderCardContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "target_chara_id", nullable = false)
    private Integer targetCharaId;

    @NotNull
    @Column(name = "action_master_id", nullable = false)
    private Integer actionMasterId;

    @NotNull
    @Column(name = "action_skill_exp", nullable = false)
    private Integer actionSkillExp;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mActionSkillHolderCardContents")
    private MCharacter mcharacter;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTargetCharaId() {
        return targetCharaId;
    }

    public MActionSkillHolderCardContent targetCharaId(Integer targetCharaId) {
        this.targetCharaId = targetCharaId;
        return this;
    }

    public void setTargetCharaId(Integer targetCharaId) {
        this.targetCharaId = targetCharaId;
    }

    public Integer getActionMasterId() {
        return actionMasterId;
    }

    public MActionSkillHolderCardContent actionMasterId(Integer actionMasterId) {
        this.actionMasterId = actionMasterId;
        return this;
    }

    public void setActionMasterId(Integer actionMasterId) {
        this.actionMasterId = actionMasterId;
    }

    public Integer getActionSkillExp() {
        return actionSkillExp;
    }

    public MActionSkillHolderCardContent actionSkillExp(Integer actionSkillExp) {
        this.actionSkillExp = actionSkillExp;
        return this;
    }

    public void setActionSkillExp(Integer actionSkillExp) {
        this.actionSkillExp = actionSkillExp;
    }

    public String getName() {
        return name;
    }

    public MActionSkillHolderCardContent name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public MActionSkillHolderCardContent description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MCharacter getMcharacter() {
        return mcharacter;
    }

    public MActionSkillHolderCardContent mcharacter(MCharacter mCharacter) {
        this.mcharacter = mCharacter;
        return this;
    }

    public void setMcharacter(MCharacter mCharacter) {
        this.mcharacter = mCharacter;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MActionSkillHolderCardContent)) {
            return false;
        }
        return id != null && id.equals(((MActionSkillHolderCardContent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MActionSkillHolderCardContent{" +
            "id=" + getId() +
            ", targetCharaId=" + getTargetCharaId() +
            ", actionMasterId=" + getActionMasterId() +
            ", actionSkillExp=" + getActionSkillExp() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
