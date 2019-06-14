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
 * Criteria class for the {@link io.shm.tsubasa.domain.MUserPolicyUpdateDate} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MUserPolicyUpdateDateResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-user-policy-update-dates?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MUserPolicyUpdateDateCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter updateDate;

    public MUserPolicyUpdateDateCriteria(){
    }

    public MUserPolicyUpdateDateCriteria(MUserPolicyUpdateDateCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.updateDate = other.updateDate == null ? null : other.updateDate.copy();
    }

    @Override
    public MUserPolicyUpdateDateCriteria copy() {
        return new MUserPolicyUpdateDateCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(IntegerFilter updateDate) {
        this.updateDate = updateDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MUserPolicyUpdateDateCriteria that = (MUserPolicyUpdateDateCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        updateDate
        );
    }

    @Override
    public String toString() {
        return "MUserPolicyUpdateDateCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (updateDate != null ? "updateDate=" + updateDate + ", " : "") +
            "}";
    }

}
