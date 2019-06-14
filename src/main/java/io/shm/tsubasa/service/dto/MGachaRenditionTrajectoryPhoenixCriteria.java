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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGachaRenditionTrajectoryPhoenix} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGachaRenditionTrajectoryPhoenixResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-gacha-rendition-trajectory-phoenixes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGachaRenditionTrajectoryPhoenixCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter isPhoenix;

    private IntegerFilter weight;

    public MGachaRenditionTrajectoryPhoenixCriteria(){
    }

    public MGachaRenditionTrajectoryPhoenixCriteria(MGachaRenditionTrajectoryPhoenixCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.isPhoenix = other.isPhoenix == null ? null : other.isPhoenix.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
    }

    @Override
    public MGachaRenditionTrajectoryPhoenixCriteria copy() {
        return new MGachaRenditionTrajectoryPhoenixCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getIsPhoenix() {
        return isPhoenix;
    }

    public void setIsPhoenix(IntegerFilter isPhoenix) {
        this.isPhoenix = isPhoenix;
    }

    public IntegerFilter getWeight() {
        return weight;
    }

    public void setWeight(IntegerFilter weight) {
        this.weight = weight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGachaRenditionTrajectoryPhoenixCriteria that = (MGachaRenditionTrajectoryPhoenixCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(isPhoenix, that.isPhoenix) &&
            Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        isPhoenix,
        weight
        );
    }

    @Override
    public String toString() {
        return "MGachaRenditionTrajectoryPhoenixCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (isPhoenix != null ? "isPhoenix=" + isPhoenix + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
            "}";
    }

}
