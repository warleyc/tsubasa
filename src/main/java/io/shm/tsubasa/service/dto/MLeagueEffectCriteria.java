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
 * Criteria class for the {@link io.shm.tsubasa.domain.MLeagueEffect} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MLeagueEffectResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-league-effects?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MLeagueEffectCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter effectType;

    private IntegerFilter leagueHierarchy;

    private IntegerFilter rate;

    private IntegerFilter price;

    public MLeagueEffectCriteria(){
    }

    public MLeagueEffectCriteria(MLeagueEffectCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.effectType = other.effectType == null ? null : other.effectType.copy();
        this.leagueHierarchy = other.leagueHierarchy == null ? null : other.leagueHierarchy.copy();
        this.rate = other.rate == null ? null : other.rate.copy();
        this.price = other.price == null ? null : other.price.copy();
    }

    @Override
    public MLeagueEffectCriteria copy() {
        return new MLeagueEffectCriteria(this);
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

    public IntegerFilter getLeagueHierarchy() {
        return leagueHierarchy;
    }

    public void setLeagueHierarchy(IntegerFilter leagueHierarchy) {
        this.leagueHierarchy = leagueHierarchy;
    }

    public IntegerFilter getRate() {
        return rate;
    }

    public void setRate(IntegerFilter rate) {
        this.rate = rate;
    }

    public IntegerFilter getPrice() {
        return price;
    }

    public void setPrice(IntegerFilter price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MLeagueEffectCriteria that = (MLeagueEffectCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(effectType, that.effectType) &&
            Objects.equals(leagueHierarchy, that.leagueHierarchy) &&
            Objects.equals(rate, that.rate) &&
            Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        effectType,
        leagueHierarchy,
        rate,
        price
        );
    }

    @Override
    public String toString() {
        return "MLeagueEffectCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (effectType != null ? "effectType=" + effectType + ", " : "") +
                (leagueHierarchy != null ? "leagueHierarchy=" + leagueHierarchy + ", " : "") +
                (rate != null ? "rate=" + rate + ", " : "") +
                (price != null ? "price=" + price + ", " : "") +
            "}";
    }

}
