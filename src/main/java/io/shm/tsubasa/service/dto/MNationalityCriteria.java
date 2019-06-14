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
 * Criteria class for the {@link io.shm.tsubasa.domain.MNationality} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MNationalityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-nationalities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MNationalityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter mTargetNationalityGroupId;

    public MNationalityCriteria(){
    }

    public MNationalityCriteria(MNationalityCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.mTargetNationalityGroupId = other.mTargetNationalityGroupId == null ? null : other.mTargetNationalityGroupId.copy();
    }

    @Override
    public MNationalityCriteria copy() {
        return new MNationalityCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getMTargetNationalityGroupId() {
        return mTargetNationalityGroupId;
    }

    public void setMTargetNationalityGroupId(LongFilter mTargetNationalityGroupId) {
        this.mTargetNationalityGroupId = mTargetNationalityGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MNationalityCriteria that = (MNationalityCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(mTargetNationalityGroupId, that.mTargetNationalityGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        mTargetNationalityGroupId
        );
    }

    @Override
    public String toString() {
        return "MNationalityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (mTargetNationalityGroupId != null ? "mTargetNationalityGroupId=" + mTargetNationalityGroupId + ", " : "") +
            "}";
    }

}
