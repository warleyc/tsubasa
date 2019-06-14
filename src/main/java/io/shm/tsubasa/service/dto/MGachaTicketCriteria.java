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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGachaTicket} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGachaTicketResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-gacha-tickets?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGachaTicketCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter maxAmount;

    private IntegerFilter endAt;

    public MGachaTicketCriteria(){
    }

    public MGachaTicketCriteria(MGachaTicketCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.maxAmount = other.maxAmount == null ? null : other.maxAmount.copy();
        this.endAt = other.endAt == null ? null : other.endAt.copy();
    }

    @Override
    public MGachaTicketCriteria copy() {
        return new MGachaTicketCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(IntegerFilter maxAmount) {
        this.maxAmount = maxAmount;
    }

    public IntegerFilter getEndAt() {
        return endAt;
    }

    public void setEndAt(IntegerFilter endAt) {
        this.endAt = endAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGachaTicketCriteria that = (MGachaTicketCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(maxAmount, that.maxAmount) &&
            Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        maxAmount,
        endAt
        );
    }

    @Override
    public String toString() {
        return "MGachaTicketCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (maxAmount != null ? "maxAmount=" + maxAmount + ", " : "") +
                (endAt != null ? "endAt=" + endAt + ", " : "") +
            "}";
    }

}
