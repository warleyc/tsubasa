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
 * Criteria class for the {@link io.shm.tsubasa.domain.MDetachActionSkillCost} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MDetachActionSkillCostResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-detach-action-skill-costs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MDetachActionSkillCostCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter rarity;

    private IntegerFilter cost;

    public MDetachActionSkillCostCriteria(){
    }

    public MDetachActionSkillCostCriteria(MDetachActionSkillCostCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.rarity = other.rarity == null ? null : other.rarity.copy();
        this.cost = other.cost == null ? null : other.cost.copy();
    }

    @Override
    public MDetachActionSkillCostCriteria copy() {
        return new MDetachActionSkillCostCriteria(this);
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

    public IntegerFilter getCost() {
        return cost;
    }

    public void setCost(IntegerFilter cost) {
        this.cost = cost;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MDetachActionSkillCostCriteria that = (MDetachActionSkillCostCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(rarity, that.rarity) &&
            Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        rarity,
        cost
        );
    }

    @Override
    public String toString() {
        return "MDetachActionSkillCostCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (rarity != null ? "rarity=" + rarity + ", " : "") +
                (cost != null ? "cost=" + cost + ", " : "") +
            "}";
    }

}
