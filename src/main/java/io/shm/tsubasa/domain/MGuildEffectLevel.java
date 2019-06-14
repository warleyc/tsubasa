package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGuildEffectLevel.
 */
@Entity
@Table(name = "m_guild_effect_level")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGuildEffectLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "effect_type", nullable = false)
    private Integer effectType;

    @NotNull
    @Column(name = "jhi_level", nullable = false)
    private Integer level;

    @NotNull
    @Column(name = "rate", nullable = false)
    private Integer rate;

    
    @Lob
    @Column(name = "rate_str", nullable = false)
    private String rateStr;

    @NotNull
    @Column(name = "coin", nullable = false)
    private Integer coin;

    @NotNull
    @Column(name = "guild_level", nullable = false)
    private Integer guildLevel;

    @NotNull
    @Column(name = "guild_medal", nullable = false)
    private Integer guildMedal;

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

    public MGuildEffectLevel effectType(Integer effectType) {
        this.effectType = effectType;
        return this;
    }

    public void setEffectType(Integer effectType) {
        this.effectType = effectType;
    }

    public Integer getLevel() {
        return level;
    }

    public MGuildEffectLevel level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRate() {
        return rate;
    }

    public MGuildEffectLevel rate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getRateStr() {
        return rateStr;
    }

    public MGuildEffectLevel rateStr(String rateStr) {
        this.rateStr = rateStr;
        return this;
    }

    public void setRateStr(String rateStr) {
        this.rateStr = rateStr;
    }

    public Integer getCoin() {
        return coin;
    }

    public MGuildEffectLevel coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getGuildLevel() {
        return guildLevel;
    }

    public MGuildEffectLevel guildLevel(Integer guildLevel) {
        this.guildLevel = guildLevel;
        return this;
    }

    public void setGuildLevel(Integer guildLevel) {
        this.guildLevel = guildLevel;
    }

    public Integer getGuildMedal() {
        return guildMedal;
    }

    public MGuildEffectLevel guildMedal(Integer guildMedal) {
        this.guildMedal = guildMedal;
        return this;
    }

    public void setGuildMedal(Integer guildMedal) {
        this.guildMedal = guildMedal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGuildEffectLevel)) {
            return false;
        }
        return id != null && id.equals(((MGuildEffectLevel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGuildEffectLevel{" +
            "id=" + getId() +
            ", effectType=" + getEffectType() +
            ", level=" + getLevel() +
            ", rate=" + getRate() +
            ", rateStr='" + getRateStr() + "'" +
            ", coin=" + getCoin() +
            ", guildLevel=" + getGuildLevel() +
            ", guildMedal=" + getGuildMedal() +
            "}";
    }
}
