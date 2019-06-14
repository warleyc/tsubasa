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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTargetNationalityGroup} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTargetNationalityGroupResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-target-nationality-groups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTargetNationalityGroupCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter groupId;

    private IntegerFilter nationalityId;

    private LongFilter mnationalityId;

    public MTargetNationalityGroupCriteria(){
    }

    public MTargetNationalityGroupCriteria(MTargetNationalityGroupCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.nationalityId = other.nationalityId == null ? null : other.nationalityId.copy();
        this.mnationalityId = other.mnationalityId == null ? null : other.mnationalityId.copy();
    }

    @Override
    public MTargetNationalityGroupCriteria copy() {
        return new MTargetNationalityGroupCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getGroupId() {
        return groupId;
    }

    public void setGroupId(IntegerFilter groupId) {
        this.groupId = groupId;
    }

    public IntegerFilter getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(IntegerFilter nationalityId) {
        this.nationalityId = nationalityId;
    }

    public LongFilter getMnationalityId() {
        return mnationalityId;
    }

    public void setMnationalityId(LongFilter mnationalityId) {
        this.mnationalityId = mnationalityId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTargetNationalityGroupCriteria that = (MTargetNationalityGroupCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(nationalityId, that.nationalityId) &&
            Objects.equals(mnationalityId, that.mnationalityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        groupId,
        nationalityId,
        mnationalityId
        );
    }

    @Override
    public String toString() {
        return "MTargetNationalityGroupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (groupId != null ? "groupId=" + groupId + ", " : "") +
                (nationalityId != null ? "nationalityId=" + nationalityId + ", " : "") +
                (mnationalityId != null ? "mnationalityId=" + mnationalityId + ", " : "") +
            "}";
    }

}
