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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTeamEffectLevel} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTeamEffectLevelResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-team-effect-levels?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTeamEffectLevelCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter rarity;

    private IntegerFilter level;

    private IntegerFilter exp;

    public MTeamEffectLevelCriteria(){
    }

    public MTeamEffectLevelCriteria(MTeamEffectLevelCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.rarity = other.rarity == null ? null : other.rarity.copy();
        this.level = other.level == null ? null : other.level.copy();
        this.exp = other.exp == null ? null : other.exp.copy();
    }

    @Override
    public MTeamEffectLevelCriteria copy() {
        return new MTeamEffectLevelCriteria(this);
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

    public IntegerFilter getLevel() {
        return level;
    }

    public void setLevel(IntegerFilter level) {
        this.level = level;
    }

    public IntegerFilter getExp() {
        return exp;
    }

    public void setExp(IntegerFilter exp) {
        this.exp = exp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTeamEffectLevelCriteria that = (MTeamEffectLevelCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(rarity, that.rarity) &&
            Objects.equals(level, that.level) &&
            Objects.equals(exp, that.exp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        rarity,
        level,
        exp
        );
    }

    @Override
    public String toString() {
        return "MTeamEffectLevelCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (rarity != null ? "rarity=" + rarity + ", " : "") +
                (level != null ? "level=" + level + ", " : "") +
                (exp != null ? "exp=" + exp + ", " : "") +
            "}";
    }

}
