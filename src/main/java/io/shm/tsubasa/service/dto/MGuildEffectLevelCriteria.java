package io.shm.tsubasa.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.shm.tsubasa.domain.MGuildEffectLevel} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGuildEffectLevelResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-guild-effect-levels?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGuildEffectLevelCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter effectType;

    private IntegerFilter level;

    private IntegerFilter rate;

    private IntegerFilter coin;

    private IntegerFilter guildLevel;

    private IntegerFilter guildMedal;

    public MGuildEffectLevelCriteria(){
    }

    public MGuildEffectLevelCriteria(MGuildEffectLevelCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.effectType = other.effectType == null ? null : other.effectType.copy();
        this.level = other.level == null ? null : other.level.copy();
        this.rate = other.rate == null ? null : other.rate.copy();
        this.coin = other.coin == null ? null : other.coin.copy();
        this.guildLevel = other.guildLevel == null ? null : other.guildLevel.copy();
        this.guildMedal = other.guildMedal == null ? null : other.guildMedal.copy();
    }

    @Override
    public MGuildEffectLevelCriteria copy() {
        return new MGuildEffectLevelCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getEffectType() {
        return effectType;
    }

    public void setEffectType(IntegerFilter effectType) {
        this.effectType = effectType;
    }

    public IntegerFilter getLevel() {
        return level;
    }

    public void setLevel(IntegerFilter level) {
        this.level = level;
    }

    public IntegerFilter getRate() {
        return rate;
    }

    public void setRate(IntegerFilter rate) {
        this.rate = rate;
    }

    public IntegerFilter getCoin() {
        return coin;
    }

    public void setCoin(IntegerFilter coin) {
        this.coin = coin;
    }

    public IntegerFilter getGuildLevel() {
        return guildLevel;
    }

    public void setGuildLevel(IntegerFilter guildLevel) {
        this.guildLevel = guildLevel;
    }

    public IntegerFilter getGuildMedal() {
        return guildMedal;
    }

    public void setGuildMedal(IntegerFilter guildMedal) {
        this.guildMedal = guildMedal;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGuildEffectLevelCriteria that = (MGuildEffectLevelCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(effectType, that.effectType) &&
            Objects.equals(level, that.level) &&
            Objects.equals(rate, that.rate) &&
            Objects.equals(coin, that.coin) &&
            Objects.equals(guildLevel, that.guildLevel) &&
            Objects.equals(guildMedal, that.guildMedal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        effectType,
        level,
        rate,
        coin,
        guildLevel,
        guildMedal
        );
    }

    @Override
    public String toString() {
        return "MGuildEffectLevelCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (effectType != null ? "effectType=" + effectType + ", " : "") +
                (level != null ? "level=" + level + ", " : "") +
                (rate != null ? "rate=" + rate + ", " : "") +
                (coin != null ? "coin=" + coin + ", " : "") +
                (guildLevel != null ? "guildLevel=" + guildLevel + ", " : "") +
                (guildMedal != null ? "guildMedal=" + guildMedal + ", " : "") +
            "}";
    }

}
