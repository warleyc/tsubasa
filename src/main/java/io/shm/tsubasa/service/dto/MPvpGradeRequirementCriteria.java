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
 * Criteria class for the {@link io.shm.tsubasa.domain.MPvpGradeRequirement} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MPvpGradeRequirementResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-pvp-grade-requirements?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MPvpGradeRequirementCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter targetType;

    private IntegerFilter targetWave;

    private IntegerFilter targetLowerGrade;

    private IntegerFilter targetPoint;

    public MPvpGradeRequirementCriteria(){
    }

    public MPvpGradeRequirementCriteria(MPvpGradeRequirementCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.targetType = other.targetType == null ? null : other.targetType.copy();
        this.targetWave = other.targetWave == null ? null : other.targetWave.copy();
        this.targetLowerGrade = other.targetLowerGrade == null ? null : other.targetLowerGrade.copy();
        this.targetPoint = other.targetPoint == null ? null : other.targetPoint.copy();
    }

    @Override
    public MPvpGradeRequirementCriteria copy() {
        return new MPvpGradeRequirementCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getTargetType() {
        return targetType;
    }

    public void setTargetType(IntegerFilter targetType) {
        this.targetType = targetType;
    }

    public IntegerFilter getTargetWave() {
        return targetWave;
    }

    public void setTargetWave(IntegerFilter targetWave) {
        this.targetWave = targetWave;
    }

    public IntegerFilter getTargetLowerGrade() {
        return targetLowerGrade;
    }

    public void setTargetLowerGrade(IntegerFilter targetLowerGrade) {
        this.targetLowerGrade = targetLowerGrade;
    }

    public IntegerFilter getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(IntegerFilter targetPoint) {
        this.targetPoint = targetPoint;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MPvpGradeRequirementCriteria that = (MPvpGradeRequirementCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(targetType, that.targetType) &&
            Objects.equals(targetWave, that.targetWave) &&
            Objects.equals(targetLowerGrade, that.targetLowerGrade) &&
            Objects.equals(targetPoint, that.targetPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        targetType,
        targetWave,
        targetLowerGrade,
        targetPoint
        );
    }

    @Override
    public String toString() {
        return "MPvpGradeRequirementCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (targetType != null ? "targetType=" + targetType + ", " : "") +
                (targetWave != null ? "targetWave=" + targetWave + ", " : "") +
                (targetLowerGrade != null ? "targetLowerGrade=" + targetLowerGrade + ", " : "") +
                (targetPoint != null ? "targetPoint=" + targetPoint + ", " : "") +
            "}";
    }

}
