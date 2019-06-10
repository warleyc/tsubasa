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
 * Criteria class for the {@link io.shm.tsubasa.domain.MApRecoveryItem} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MApRecoveryItemResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-ap-recovery-items?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MApRecoveryItemCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter apRecoveryItemType;

    public MApRecoveryItemCriteria(){
    }

    public MApRecoveryItemCriteria(MApRecoveryItemCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.apRecoveryItemType = other.apRecoveryItemType == null ? null : other.apRecoveryItemType.copy();
    }

    @Override
    public MApRecoveryItemCriteria copy() {
        return new MApRecoveryItemCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getApRecoveryItemType() {
        return apRecoveryItemType;
    }

    public void setApRecoveryItemType(IntegerFilter apRecoveryItemType) {
        this.apRecoveryItemType = apRecoveryItemType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MApRecoveryItemCriteria that = (MApRecoveryItemCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(apRecoveryItemType, that.apRecoveryItemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        apRecoveryItemType
        );
    }

    @Override
    public String toString() {
        return "MApRecoveryItemCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (apRecoveryItemType != null ? "apRecoveryItemType=" + apRecoveryItemType + ", " : "") +
            "}";
    }

}
