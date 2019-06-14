package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MQuestCoop.
 */
@Entity
@Table(name = "m_quest_coop")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MQuestCoop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "effect_rarity", nullable = false)
    private Integer effectRarity;

    @NotNull
    @Column(name = "effect_level_from", nullable = false)
    private Integer effectLevelFrom;

    @NotNull
    @Column(name = "effect_level_to", nullable = false)
    private Integer effectLevelTo;

    @NotNull
    @Column(name = "choose_1_weight", nullable = false)
    private Integer choose1Weight;

    @NotNull
    @Column(name = "choose_2_weight", nullable = false)
    private Integer choose2Weight;

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

    public MQuestCoop groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getEffectRarity() {
        return effectRarity;
    }

    public MQuestCoop effectRarity(Integer effectRarity) {
        this.effectRarity = effectRarity;
        return this;
    }

    public void setEffectRarity(Integer effectRarity) {
        this.effectRarity = effectRarity;
    }

    public Integer getEffectLevelFrom() {
        return effectLevelFrom;
    }

    public MQuestCoop effectLevelFrom(Integer effectLevelFrom) {
        this.effectLevelFrom = effectLevelFrom;
        return this;
    }

    public void setEffectLevelFrom(Integer effectLevelFrom) {
        this.effectLevelFrom = effectLevelFrom;
    }

    public Integer getEffectLevelTo() {
        return effectLevelTo;
    }

    public MQuestCoop effectLevelTo(Integer effectLevelTo) {
        this.effectLevelTo = effectLevelTo;
        return this;
    }

    public void setEffectLevelTo(Integer effectLevelTo) {
        this.effectLevelTo = effectLevelTo;
    }

    public Integer getChoose1Weight() {
        return choose1Weight;
    }

    public MQuestCoop choose1Weight(Integer choose1Weight) {
        this.choose1Weight = choose1Weight;
        return this;
    }

    public void setChoose1Weight(Integer choose1Weight) {
        this.choose1Weight = choose1Weight;
    }

    public Integer getChoose2Weight() {
        return choose2Weight;
    }

    public MQuestCoop choose2Weight(Integer choose2Weight) {
        this.choose2Weight = choose2Weight;
        return this;
    }

    public void setChoose2Weight(Integer choose2Weight) {
        this.choose2Weight = choose2Weight;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MQuestCoop)) {
            return false;
        }
        return id != null && id.equals(((MQuestCoop) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MQuestCoop{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", effectRarity=" + getEffectRarity() +
            ", effectLevelFrom=" + getEffectLevelFrom() +
            ", effectLevelTo=" + getEffectLevelTo() +
            ", choose1Weight=" + getChoose1Weight() +
            ", choose2Weight=" + getChoose2Weight() +
            "}";
    }
}
