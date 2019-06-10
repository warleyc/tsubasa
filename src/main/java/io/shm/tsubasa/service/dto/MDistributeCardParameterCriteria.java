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
 * Criteria class for the {@link io.shm.tsubasa.domain.MDistributeCardParameter} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MDistributeCardParameterResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-distribute-card-parameters?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MDistributeCardParameterCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter rarity;

    private IntegerFilter isGk;

    private IntegerFilter maxStepCount;

    private IntegerFilter maxTotalStepCount;

    private IntegerFilter distributePointByStep;

    public MDistributeCardParameterCriteria(){
    }

    public MDistributeCardParameterCriteria(MDistributeCardParameterCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.rarity = other.rarity == null ? null : other.rarity.copy();
        this.isGk = other.isGk == null ? null : other.isGk.copy();
        this.maxStepCount = other.maxStepCount == null ? null : other.maxStepCount.copy();
        this.maxTotalStepCount = other.maxTotalStepCount == null ? null : other.maxTotalStepCount.copy();
        this.distributePointByStep = other.distributePointByStep == null ? null : other.distributePointByStep.copy();
    }

    @Override
    public MDistributeCardParameterCriteria copy() {
        return new MDistributeCardParameterCriteria(this);
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

    public IntegerFilter getIsGk() {
        return isGk;
    }

    public void setIsGk(IntegerFilter isGk) {
        this.isGk = isGk;
    }

    public IntegerFilter getMaxStepCount() {
        return maxStepCount;
    }

    public void setMaxStepCount(IntegerFilter maxStepCount) {
        this.maxStepCount = maxStepCount;
    }

    public IntegerFilter getMaxTotalStepCount() {
        return maxTotalStepCount;
    }

    public void setMaxTotalStepCount(IntegerFilter maxTotalStepCount) {
        this.maxTotalStepCount = maxTotalStepCount;
    }

    public IntegerFilter getDistributePointByStep() {
        return distributePointByStep;
    }

    public void setDistributePointByStep(IntegerFilter distributePointByStep) {
        this.distributePointByStep = distributePointByStep;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MDistributeCardParameterCriteria that = (MDistributeCardParameterCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(rarity, that.rarity) &&
            Objects.equals(isGk, that.isGk) &&
            Objects.equals(maxStepCount, that.maxStepCount) &&
            Objects.equals(maxTotalStepCount, that.maxTotalStepCount) &&
            Objects.equals(distributePointByStep, that.distributePointByStep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        rarity,
        isGk,
        maxStepCount,
        maxTotalStepCount,
        distributePointByStep
        );
    }

    @Override
    public String toString() {
        return "MDistributeCardParameterCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (rarity != null ? "rarity=" + rarity + ", " : "") +
                (isGk != null ? "isGk=" + isGk + ", " : "") +
                (maxStepCount != null ? "maxStepCount=" + maxStepCount + ", " : "") +
                (maxTotalStepCount != null ? "maxTotalStepCount=" + maxTotalStepCount + ", " : "") +
                (distributePointByStep != null ? "distributePointByStep=" + distributePointByStep + ", " : "") +
            "}";
    }

}
