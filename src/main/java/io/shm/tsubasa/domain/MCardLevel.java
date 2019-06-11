package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MCardLevel.
 */
@Entity
@Table(name = "m_card_level")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCardLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rarity", nullable = false)
    private Integer rarity;

    @NotNull
    @Column(name = "level", nullable = false)
    private Integer level;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "exp", nullable = false)
    private Integer exp;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRarity() {
        return rarity;
    }

    public MCardLevel rarity(Integer rarity) {
        this.rarity = rarity;
        return this;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getLevel() {
        return level;
    }

    public MCardLevel level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public MCardLevel groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getExp() {
        return exp;
    }

    public MCardLevel exp(Integer exp) {
        this.exp = exp;
        return this;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCardLevel)) {
            return false;
        }
        return id != null && id.equals(((MCardLevel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCardLevel{" +
            "id=" + getId() +
            ", rarity=" + getRarity() +
            ", level=" + getLevel() +
            ", groupId=" + getGroupId() +
            ", exp=" + getExp() +
            "}";
    }
}
