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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMedal} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMedalResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-medals?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMedalCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter medalType;

    private IntegerFilter maxAmount;

    public MMedalCriteria(){
    }

    public MMedalCriteria(MMedalCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.medalType = other.medalType == null ? null : other.medalType.copy();
        this.maxAmount = other.maxAmount == null ? null : other.maxAmount.copy();
    }

    @Override
    public MMedalCriteria copy() {
        return new MMedalCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getMedalType() {
        return medalType;
    }

    public void setMedalType(IntegerFilter medalType) {
        this.medalType = medalType;
    }

    public IntegerFilter getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(IntegerFilter maxAmount) {
        this.maxAmount = maxAmount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMedalCriteria that = (MMedalCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(medalType, that.medalType) &&
            Objects.equals(maxAmount, that.maxAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        medalType,
        maxAmount
        );
    }

    @Override
    public String toString() {
        return "MMedalCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (medalType != null ? "medalType=" + medalType + ", " : "") +
                (maxAmount != null ? "maxAmount=" + maxAmount + ", " : "") +
            "}";
    }

}
