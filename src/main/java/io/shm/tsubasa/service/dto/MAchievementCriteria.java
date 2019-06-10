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
 * Criteria class for the {@link io.shm.tsubasa.domain.MAchievement} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MAchievementResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-achievements?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MAchievementCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter type;

    private IntegerFilter missionId;

    private LongFilter idId;

    public MAchievementCriteria(){
    }

    public MAchievementCriteria(MAchievementCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.missionId = other.missionId == null ? null : other.missionId.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
    }

    @Override
    public MAchievementCriteria copy() {
        return new MAchievementCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getType() {
        return type;
    }

    public void setType(IntegerFilter type) {
        this.type = type;
    }

    public IntegerFilter getMissionId() {
        return missionId;
    }

    public void setMissionId(IntegerFilter missionId) {
        this.missionId = missionId;
    }

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MAchievementCriteria that = (MAchievementCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(type, that.type) &&
            Objects.equals(missionId, that.missionId) &&
            Objects.equals(idId, that.idId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        type,
        missionId,
        idId
        );
    }

    @Override
    public String toString() {
        return "MAchievementCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (missionId != null ? "missionId=" + missionId + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
            "}";
    }

}
