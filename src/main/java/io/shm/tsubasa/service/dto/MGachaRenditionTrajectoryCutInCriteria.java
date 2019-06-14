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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGachaRenditionTrajectoryCutIn} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGachaRenditionTrajectoryCutInResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-gacha-rendition-trajectory-cut-ins?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGachaRenditionTrajectoryCutInCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter renditionId;

    private IntegerFilter trajectoryType;

    private IntegerFilter weight;

    private IntegerFilter exceptKickerId;

    public MGachaRenditionTrajectoryCutInCriteria(){
    }

    public MGachaRenditionTrajectoryCutInCriteria(MGachaRenditionTrajectoryCutInCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.renditionId = other.renditionId == null ? null : other.renditionId.copy();
        this.trajectoryType = other.trajectoryType == null ? null : other.trajectoryType.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
        this.exceptKickerId = other.exceptKickerId == null ? null : other.exceptKickerId.copy();
    }

    @Override
    public MGachaRenditionTrajectoryCutInCriteria copy() {
        return new MGachaRenditionTrajectoryCutInCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRenditionId() {
        return renditionId;
    }

    public void setRenditionId(IntegerFilter renditionId) {
        this.renditionId = renditionId;
    }

    public IntegerFilter getTrajectoryType() {
        return trajectoryType;
    }

    public void setTrajectoryType(IntegerFilter trajectoryType) {
        this.trajectoryType = trajectoryType;
    }

    public IntegerFilter getWeight() {
        return weight;
    }

    public void setWeight(IntegerFilter weight) {
        this.weight = weight;
    }

    public IntegerFilter getExceptKickerId() {
        return exceptKickerId;
    }

    public void setExceptKickerId(IntegerFilter exceptKickerId) {
        this.exceptKickerId = exceptKickerId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGachaRenditionTrajectoryCutInCriteria that = (MGachaRenditionTrajectoryCutInCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(renditionId, that.renditionId) &&
            Objects.equals(trajectoryType, that.trajectoryType) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(exceptKickerId, that.exceptKickerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        renditionId,
        trajectoryType,
        weight,
        exceptKickerId
        );
    }

    @Override
    public String toString() {
        return "MGachaRenditionTrajectoryCutInCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (renditionId != null ? "renditionId=" + renditionId + ", " : "") +
                (trajectoryType != null ? "trajectoryType=" + trajectoryType + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (exceptKickerId != null ? "exceptKickerId=" + exceptKickerId + ", " : "") +
            "}";
    }

}
