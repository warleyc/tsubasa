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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGachaRenditionTrajectory} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGachaRenditionTrajectoryResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-gacha-rendition-trajectories?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGachaRenditionTrajectoryCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter weight;

    private IntegerFilter trajectoryType;

    public MGachaRenditionTrajectoryCriteria(){
    }

    public MGachaRenditionTrajectoryCriteria(MGachaRenditionTrajectoryCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
        this.trajectoryType = other.trajectoryType == null ? null : other.trajectoryType.copy();
    }

    @Override
    public MGachaRenditionTrajectoryCriteria copy() {
        return new MGachaRenditionTrajectoryCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getWeight() {
        return weight;
    }

    public void setWeight(IntegerFilter weight) {
        this.weight = weight;
    }

    public IntegerFilter getTrajectoryType() {
        return trajectoryType;
    }

    public void setTrajectoryType(IntegerFilter trajectoryType) {
        this.trajectoryType = trajectoryType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGachaRenditionTrajectoryCriteria that = (MGachaRenditionTrajectoryCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(trajectoryType, that.trajectoryType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        weight,
        trajectoryType
        );
    }

    @Override
    public String toString() {
        return "MGachaRenditionTrajectoryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (trajectoryType != null ? "trajectoryType=" + trajectoryType + ", " : "") +
            "}";
    }

}
