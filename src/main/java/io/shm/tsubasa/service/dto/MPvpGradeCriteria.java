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
 * Criteria class for the {@link io.shm.tsubasa.domain.MPvpGrade} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MPvpGradeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-pvp-grades?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MPvpGradeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter waveId;

    private IntegerFilter grade;

    private IntegerFilter isHigher;

    private IntegerFilter isLower;

    private IntegerFilter look;

    private IntegerFilter upRequirementId;

    private IntegerFilter downRequirementId;

    private IntegerFilter winPoint;

    private IntegerFilter losePoint;

    private IntegerFilter totalParameterRangeUpper;

    private IntegerFilter totalParameterRangeLower;

    public MPvpGradeCriteria(){
    }

    public MPvpGradeCriteria(MPvpGradeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.waveId = other.waveId == null ? null : other.waveId.copy();
        this.grade = other.grade == null ? null : other.grade.copy();
        this.isHigher = other.isHigher == null ? null : other.isHigher.copy();
        this.isLower = other.isLower == null ? null : other.isLower.copy();
        this.look = other.look == null ? null : other.look.copy();
        this.upRequirementId = other.upRequirementId == null ? null : other.upRequirementId.copy();
        this.downRequirementId = other.downRequirementId == null ? null : other.downRequirementId.copy();
        this.winPoint = other.winPoint == null ? null : other.winPoint.copy();
        this.losePoint = other.losePoint == null ? null : other.losePoint.copy();
        this.totalParameterRangeUpper = other.totalParameterRangeUpper == null ? null : other.totalParameterRangeUpper.copy();
        this.totalParameterRangeLower = other.totalParameterRangeLower == null ? null : other.totalParameterRangeLower.copy();
    }

    @Override
    public MPvpGradeCriteria copy() {
        return new MPvpGradeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getWaveId() {
        return waveId;
    }

    public void setWaveId(IntegerFilter waveId) {
        this.waveId = waveId;
    }

    public IntegerFilter getGrade() {
        return grade;
    }

    public void setGrade(IntegerFilter grade) {
        this.grade = grade;
    }

    public IntegerFilter getIsHigher() {
        return isHigher;
    }

    public void setIsHigher(IntegerFilter isHigher) {
        this.isHigher = isHigher;
    }

    public IntegerFilter getIsLower() {
        return isLower;
    }

    public void setIsLower(IntegerFilter isLower) {
        this.isLower = isLower;
    }

    public IntegerFilter getLook() {
        return look;
    }

    public void setLook(IntegerFilter look) {
        this.look = look;
    }

    public IntegerFilter getUpRequirementId() {
        return upRequirementId;
    }

    public void setUpRequirementId(IntegerFilter upRequirementId) {
        this.upRequirementId = upRequirementId;
    }

    public IntegerFilter getDownRequirementId() {
        return downRequirementId;
    }

    public void setDownRequirementId(IntegerFilter downRequirementId) {
        this.downRequirementId = downRequirementId;
    }

    public IntegerFilter getWinPoint() {
        return winPoint;
    }

    public void setWinPoint(IntegerFilter winPoint) {
        this.winPoint = winPoint;
    }

    public IntegerFilter getLosePoint() {
        return losePoint;
    }

    public void setLosePoint(IntegerFilter losePoint) {
        this.losePoint = losePoint;
    }

    public IntegerFilter getTotalParameterRangeUpper() {
        return totalParameterRangeUpper;
    }

    public void setTotalParameterRangeUpper(IntegerFilter totalParameterRangeUpper) {
        this.totalParameterRangeUpper = totalParameterRangeUpper;
    }

    public IntegerFilter getTotalParameterRangeLower() {
        return totalParameterRangeLower;
    }

    public void setTotalParameterRangeLower(IntegerFilter totalParameterRangeLower) {
        this.totalParameterRangeLower = totalParameterRangeLower;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MPvpGradeCriteria that = (MPvpGradeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(waveId, that.waveId) &&
            Objects.equals(grade, that.grade) &&
            Objects.equals(isHigher, that.isHigher) &&
            Objects.equals(isLower, that.isLower) &&
            Objects.equals(look, that.look) &&
            Objects.equals(upRequirementId, that.upRequirementId) &&
            Objects.equals(downRequirementId, that.downRequirementId) &&
            Objects.equals(winPoint, that.winPoint) &&
            Objects.equals(losePoint, that.losePoint) &&
            Objects.equals(totalParameterRangeUpper, that.totalParameterRangeUpper) &&
            Objects.equals(totalParameterRangeLower, that.totalParameterRangeLower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        waveId,
        grade,
        isHigher,
        isLower,
        look,
        upRequirementId,
        downRequirementId,
        winPoint,
        losePoint,
        totalParameterRangeUpper,
        totalParameterRangeLower
        );
    }

    @Override
    public String toString() {
        return "MPvpGradeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (waveId != null ? "waveId=" + waveId + ", " : "") +
                (grade != null ? "grade=" + grade + ", " : "") +
                (isHigher != null ? "isHigher=" + isHigher + ", " : "") +
                (isLower != null ? "isLower=" + isLower + ", " : "") +
                (look != null ? "look=" + look + ", " : "") +
                (upRequirementId != null ? "upRequirementId=" + upRequirementId + ", " : "") +
                (downRequirementId != null ? "downRequirementId=" + downRequirementId + ", " : "") +
                (winPoint != null ? "winPoint=" + winPoint + ", " : "") +
                (losePoint != null ? "losePoint=" + losePoint + ", " : "") +
                (totalParameterRangeUpper != null ? "totalParameterRangeUpper=" + totalParameterRangeUpper + ", " : "") +
                (totalParameterRangeLower != null ? "totalParameterRangeLower=" + totalParameterRangeLower + ", " : "") +
            "}";
    }

}
