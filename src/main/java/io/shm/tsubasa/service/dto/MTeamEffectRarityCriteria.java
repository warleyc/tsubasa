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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTeamEffectRarity} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTeamEffectRarityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-team-effect-rarities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTeamEffectRarityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter rarity;

    private IntegerFilter maxLevel;

    public MTeamEffectRarityCriteria(){
    }

    public MTeamEffectRarityCriteria(MTeamEffectRarityCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.rarity = other.rarity == null ? null : other.rarity.copy();
        this.maxLevel = other.maxLevel == null ? null : other.maxLevel.copy();
    }

    @Override
    public MTeamEffectRarityCriteria copy() {
        return new MTeamEffectRarityCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRarity() {
        return rarity;
    }

    public void setRarity(IntegerFilter rarity) {
        this.rarity = rarity;
    }

    public IntegerFilter getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(IntegerFilter maxLevel) {
        this.maxLevel = maxLevel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTeamEffectRarityCriteria that = (MTeamEffectRarityCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(rarity, that.rarity) &&
            Objects.equals(maxLevel, that.maxLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        rarity,
        maxLevel
        );
    }

    @Override
    public String toString() {
        return "MTeamEffectRarityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (rarity != null ? "rarity=" + rarity + ", " : "") +
                (maxLevel != null ? "maxLevel=" + maxLevel + ", " : "") +
            "}";
    }

}
