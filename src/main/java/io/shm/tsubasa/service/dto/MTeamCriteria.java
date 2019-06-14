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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTeam} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTeamResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-teams?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTeamCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter mTargetTeamGroupId;

    public MTeamCriteria(){
    }

    public MTeamCriteria(MTeamCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.mTargetTeamGroupId = other.mTargetTeamGroupId == null ? null : other.mTargetTeamGroupId.copy();
    }

    @Override
    public MTeamCriteria copy() {
        return new MTeamCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getMTargetTeamGroupId() {
        return mTargetTeamGroupId;
    }

    public void setMTargetTeamGroupId(LongFilter mTargetTeamGroupId) {
        this.mTargetTeamGroupId = mTargetTeamGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTeamCriteria that = (MTeamCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(mTargetTeamGroupId, that.mTargetTeamGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        mTargetTeamGroupId
        );
    }

    @Override
    public String toString() {
        return "MTeamCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (mTargetTeamGroupId != null ? "mTargetTeamGroupId=" + mTargetTeamGroupId + ", " : "") +
            "}";
    }

}
