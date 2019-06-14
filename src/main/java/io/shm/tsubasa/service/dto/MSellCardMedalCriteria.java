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
 * Criteria class for the {@link io.shm.tsubasa.domain.MSellCardMedal} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MSellCardMedalResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-sell-card-medals?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MSellCardMedalCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter medalId;

    private IntegerFilter amount;

    public MSellCardMedalCriteria(){
    }

    public MSellCardMedalCriteria(MSellCardMedalCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.medalId = other.medalId == null ? null : other.medalId.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
    }

    @Override
    public MSellCardMedalCriteria copy() {
        return new MSellCardMedalCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getMedalId() {
        return medalId;
    }

    public void setMedalId(IntegerFilter medalId) {
        this.medalId = medalId;
    }

    public IntegerFilter getAmount() {
        return amount;
    }

    public void setAmount(IntegerFilter amount) {
        this.amount = amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MSellCardMedalCriteria that = (MSellCardMedalCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(medalId, that.medalId) &&
            Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        medalId,
        amount
        );
    }

    @Override
    public String toString() {
        return "MSellCardMedalCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (medalId != null ? "medalId=" + medalId + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
            "}";
    }

}
