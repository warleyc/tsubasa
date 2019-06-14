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
 * Criteria class for the {@link io.shm.tsubasa.domain.MPowerupActionSkillCost} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MPowerupActionSkillCostResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-powerup-action-skill-costs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MPowerupActionSkillCostCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter actionRarity;

    private IntegerFilter actionLevel;

    private IntegerFilter cost;

    public MPowerupActionSkillCostCriteria(){
    }

    public MPowerupActionSkillCostCriteria(MPowerupActionSkillCostCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.actionRarity = other.actionRarity == null ? null : other.actionRarity.copy();
        this.actionLevel = other.actionLevel == null ? null : other.actionLevel.copy();
        this.cost = other.cost == null ? null : other.cost.copy();
    }

    @Override
    public MPowerupActionSkillCostCriteria copy() {
        return new MPowerupActionSkillCostCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getActionRarity() {
        return actionRarity;
    }

    public void setActionRarity(IntegerFilter actionRarity) {
        this.actionRarity = actionRarity;
    }

    public IntegerFilter getActionLevel() {
        return actionLevel;
    }

    public void setActionLevel(IntegerFilter actionLevel) {
        this.actionLevel = actionLevel;
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
        final MPowerupActionSkillCostCriteria that = (MPowerupActionSkillCostCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(actionRarity, that.actionRarity) &&
            Objects.equals(actionLevel, that.actionLevel) &&
            Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        actionRarity,
        actionLevel,
        cost
        );
    }

    @Override
    public String toString() {
        return "MPowerupActionSkillCostCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (actionRarity != null ? "actionRarity=" + actionRarity + ", " : "") +
                (actionLevel != null ? "actionLevel=" + actionLevel + ", " : "") +
                (cost != null ? "cost=" + cost + ", " : "") +
            "}";
    }

}
