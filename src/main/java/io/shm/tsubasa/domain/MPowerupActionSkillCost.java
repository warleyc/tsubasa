package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MPowerupActionSkillCost.
 */
@Entity
@Table(name = "m_powerup_action_skill_cost")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MPowerupActionSkillCost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "action_rarity", nullable = false)
    private Integer actionRarity;

    @NotNull
    @Column(name = "action_level", nullable = false)
    private Integer actionLevel;

    @NotNull
    @Column(name = "jhi_cost", nullable = false)
    private Integer cost;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActionRarity() {
        return actionRarity;
    }

    public MPowerupActionSkillCost actionRarity(Integer actionRarity) {
        this.actionRarity = actionRarity;
        return this;
    }

    public void setActionRarity(Integer actionRarity) {
        this.actionRarity = actionRarity;
    }

    public Integer getActionLevel() {
        return actionLevel;
    }

    public MPowerupActionSkillCost actionLevel(Integer actionLevel) {
        this.actionLevel = actionLevel;
        return this;
    }

    public void setActionLevel(Integer actionLevel) {
        this.actionLevel = actionLevel;
    }

    public Integer getCost() {
        return cost;
    }

    public MPowerupActionSkillCost cost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MPowerupActionSkillCost)) {
            return false;
        }
        return id != null && id.equals(((MPowerupActionSkillCost) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MPowerupActionSkillCost{" +
            "id=" + getId() +
            ", actionRarity=" + getActionRarity() +
            ", actionLevel=" + getActionLevel() +
            ", cost=" + getCost() +
            "}";
    }
}
