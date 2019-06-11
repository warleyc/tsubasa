package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MCardRarity.
 */
@Entity
@Table(name = "m_card_rarity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCardRarity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rarity", nullable = false)
    private Integer rarity;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

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

    public MCardRarity rarity(Integer rarity) {
        this.rarity = rarity;
        return this;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public MCardRarity name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCardRarity)) {
            return false;
        }
        return id != null && id.equals(((MCardRarity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCardRarity{" +
            "id=" + getId() +
            ", rarity=" + getRarity() +
            ", name='" + getName() + "'" +
            "}";
    }
}
