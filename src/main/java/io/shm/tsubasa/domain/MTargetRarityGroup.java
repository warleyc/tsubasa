package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTargetRarityGroup.
 */
@Entity
@Table(name = "m_target_rarity_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTargetRarityGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "card_rarity", nullable = false)
    private Integer cardRarity;

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

    public MTargetRarityGroup groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCardRarity() {
        return cardRarity;
    }

    public MTargetRarityGroup cardRarity(Integer cardRarity) {
        this.cardRarity = cardRarity;
        return this;
    }

    public void setCardRarity(Integer cardRarity) {
        this.cardRarity = cardRarity;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTargetRarityGroup)) {
            return false;
        }
        return id != null && id.equals(((MTargetRarityGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTargetRarityGroup{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", cardRarity=" + getCardRarity() +
            "}";
    }
}
