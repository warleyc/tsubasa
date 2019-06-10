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
 * Criteria class for the {@link io.shm.tsubasa.domain.MDistributeCardParameterStep} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MDistributeCardParameterStepResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-distribute-card-parameter-steps?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MDistributeCardParameterStepCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter isGk;

    private IntegerFilter step;

    private IntegerFilter param;

    public MDistributeCardParameterStepCriteria(){
    }

    public MDistributeCardParameterStepCriteria(MDistributeCardParameterStepCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.isGk = other.isGk == null ? null : other.isGk.copy();
        this.step = other.step == null ? null : other.step.copy();
        this.param = other.param == null ? null : other.param.copy();
    }

    @Override
    public MDistributeCardParameterStepCriteria copy() {
        return new MDistributeCardParameterStepCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getIsGk() {
        return isGk;
    }

    public void setIsGk(IntegerFilter isGk) {
        this.isGk = isGk;
    }

    public IntegerFilter getStep() {
        return step;
    }

    public void setStep(IntegerFilter step) {
        this.step = step;
    }

    public IntegerFilter getParam() {
        return param;
    }

    public void setParam(IntegerFilter param) {
        this.param = param;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MDistributeCardParameterStepCriteria that = (MDistributeCardParameterStepCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(isGk, that.isGk) &&
            Objects.equals(step, that.step) &&
            Objects.equals(param, that.param);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        isGk,
        step,
        param
        );
    }

    @Override
    public String toString() {
        return "MDistributeCardParameterStepCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (isGk != null ? "isGk=" + isGk + ", " : "") +
                (step != null ? "step=" + step + ", " : "") +
                (param != null ? "param=" + param + ", " : "") +
            "}";
    }

}
