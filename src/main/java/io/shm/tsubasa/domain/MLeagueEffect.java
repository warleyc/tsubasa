package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MLeagueEffect.
 */
@Entity
@Table(name = "m_league_effect")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MLeagueEffect implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "effect_type", nullable = false)
    private Integer effectType;

    @NotNull
    @Column(name = "league_hierarchy", nullable = false)
    private Integer leagueHierarchy;

    @NotNull
    @Column(name = "rate", nullable = false)
    private Integer rate;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEffectType() {
        return effectType;
    }

    public MLeagueEffect effectType(Integer effectType) {
        this.effectType = effectType;
        return this;
    }

    public void setEffectType(Integer effectType) {
        this.effectType = effectType;
    }

    public Integer getLeagueHierarchy() {
        return leagueHierarchy;
    }

    public MLeagueEffect leagueHierarchy(Integer leagueHierarchy) {
        this.leagueHierarchy = leagueHierarchy;
        return this;
    }

    public void setLeagueHierarchy(Integer leagueHierarchy) {
        this.leagueHierarchy = leagueHierarchy;
    }

    public Integer getRate() {
        return rate;
    }

    public MLeagueEffect rate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getPrice() {
        return price;
    }

    public MLeagueEffect price(Integer price) {
        this.price = price;
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MLeagueEffect)) {
            return false;
        }
        return id != null && id.equals(((MLeagueEffect) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MLeagueEffect{" +
            "id=" + getId() +
            ", effectType=" + getEffectType() +
            ", leagueHierarchy=" + getLeagueHierarchy() +
            ", rate=" + getRate() +
            ", price=" + getPrice() +
            "}";
    }
}
