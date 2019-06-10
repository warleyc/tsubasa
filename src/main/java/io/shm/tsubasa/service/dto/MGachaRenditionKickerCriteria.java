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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGachaRenditionKicker} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGachaRenditionKickerResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-gacha-rendition-kickers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGachaRenditionKickerCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter renditionId;

    private IntegerFilter isManySsr;

    private IntegerFilter isSsr;

    private IntegerFilter weight;

    private IntegerFilter leftModelId;

    private IntegerFilter leftUniformUpId;

    private IntegerFilter leftUniformBottomId;

    private IntegerFilter leftUniformNumber;

    private IntegerFilter rightModelId;

    private IntegerFilter rightUniformUpId;

    private IntegerFilter rightUniformBottomId;

    private IntegerFilter rightUniformNumber;

    private IntegerFilter kickerId;

    public MGachaRenditionKickerCriteria(){
    }

    public MGachaRenditionKickerCriteria(MGachaRenditionKickerCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.renditionId = other.renditionId == null ? null : other.renditionId.copy();
        this.isManySsr = other.isManySsr == null ? null : other.isManySsr.copy();
        this.isSsr = other.isSsr == null ? null : other.isSsr.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
        this.leftModelId = other.leftModelId == null ? null : other.leftModelId.copy();
        this.leftUniformUpId = other.leftUniformUpId == null ? null : other.leftUniformUpId.copy();
        this.leftUniformBottomId = other.leftUniformBottomId == null ? null : other.leftUniformBottomId.copy();
        this.leftUniformNumber = other.leftUniformNumber == null ? null : other.leftUniformNumber.copy();
        this.rightModelId = other.rightModelId == null ? null : other.rightModelId.copy();
        this.rightUniformUpId = other.rightUniformUpId == null ? null : other.rightUniformUpId.copy();
        this.rightUniformBottomId = other.rightUniformBottomId == null ? null : other.rightUniformBottomId.copy();
        this.rightUniformNumber = other.rightUniformNumber == null ? null : other.rightUniformNumber.copy();
        this.kickerId = other.kickerId == null ? null : other.kickerId.copy();
    }

    @Override
    public MGachaRenditionKickerCriteria copy() {
        return new MGachaRenditionKickerCriteria(this);
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

    public IntegerFilter getIsManySsr() {
        return isManySsr;
    }

    public void setIsManySsr(IntegerFilter isManySsr) {
        this.isManySsr = isManySsr;
    }

    public IntegerFilter getIsSsr() {
        return isSsr;
    }

    public void setIsSsr(IntegerFilter isSsr) {
        this.isSsr = isSsr;
    }

    public IntegerFilter getWeight() {
        return weight;
    }

    public void setWeight(IntegerFilter weight) {
        this.weight = weight;
    }

    public IntegerFilter getLeftModelId() {
        return leftModelId;
    }

    public void setLeftModelId(IntegerFilter leftModelId) {
        this.leftModelId = leftModelId;
    }

    public IntegerFilter getLeftUniformUpId() {
        return leftUniformUpId;
    }

    public void setLeftUniformUpId(IntegerFilter leftUniformUpId) {
        this.leftUniformUpId = leftUniformUpId;
    }

    public IntegerFilter getLeftUniformBottomId() {
        return leftUniformBottomId;
    }

    public void setLeftUniformBottomId(IntegerFilter leftUniformBottomId) {
        this.leftUniformBottomId = leftUniformBottomId;
    }

    public IntegerFilter getLeftUniformNumber() {
        return leftUniformNumber;
    }

    public void setLeftUniformNumber(IntegerFilter leftUniformNumber) {
        this.leftUniformNumber = leftUniformNumber;
    }

    public IntegerFilter getRightModelId() {
        return rightModelId;
    }

    public void setRightModelId(IntegerFilter rightModelId) {
        this.rightModelId = rightModelId;
    }

    public IntegerFilter getRightUniformUpId() {
        return rightUniformUpId;
    }

    public void setRightUniformUpId(IntegerFilter rightUniformUpId) {
        this.rightUniformUpId = rightUniformUpId;
    }

    public IntegerFilter getRightUniformBottomId() {
        return rightUniformBottomId;
    }

    public void setRightUniformBottomId(IntegerFilter rightUniformBottomId) {
        this.rightUniformBottomId = rightUniformBottomId;
    }

    public IntegerFilter getRightUniformNumber() {
        return rightUniformNumber;
    }

    public void setRightUniformNumber(IntegerFilter rightUniformNumber) {
        this.rightUniformNumber = rightUniformNumber;
    }

    public IntegerFilter getKickerId() {
        return kickerId;
    }

    public void setKickerId(IntegerFilter kickerId) {
        this.kickerId = kickerId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGachaRenditionKickerCriteria that = (MGachaRenditionKickerCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(renditionId, that.renditionId) &&
            Objects.equals(isManySsr, that.isManySsr) &&
            Objects.equals(isSsr, that.isSsr) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(leftModelId, that.leftModelId) &&
            Objects.equals(leftUniformUpId, that.leftUniformUpId) &&
            Objects.equals(leftUniformBottomId, that.leftUniformBottomId) &&
            Objects.equals(leftUniformNumber, that.leftUniformNumber) &&
            Objects.equals(rightModelId, that.rightModelId) &&
            Objects.equals(rightUniformUpId, that.rightUniformUpId) &&
            Objects.equals(rightUniformBottomId, that.rightUniformBottomId) &&
            Objects.equals(rightUniformNumber, that.rightUniformNumber) &&
            Objects.equals(kickerId, that.kickerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        renditionId,
        isManySsr,
        isSsr,
        weight,
        leftModelId,
        leftUniformUpId,
        leftUniformBottomId,
        leftUniformNumber,
        rightModelId,
        rightUniformUpId,
        rightUniformBottomId,
        rightUniformNumber,
        kickerId
        );
    }

    @Override
    public String toString() {
        return "MGachaRenditionKickerCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (renditionId != null ? "renditionId=" + renditionId + ", " : "") +
                (isManySsr != null ? "isManySsr=" + isManySsr + ", " : "") +
                (isSsr != null ? "isSsr=" + isSsr + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (leftModelId != null ? "leftModelId=" + leftModelId + ", " : "") +
                (leftUniformUpId != null ? "leftUniformUpId=" + leftUniformUpId + ", " : "") +
                (leftUniformBottomId != null ? "leftUniformBottomId=" + leftUniformBottomId + ", " : "") +
                (leftUniformNumber != null ? "leftUniformNumber=" + leftUniformNumber + ", " : "") +
                (rightModelId != null ? "rightModelId=" + rightModelId + ", " : "") +
                (rightUniformUpId != null ? "rightUniformUpId=" + rightUniformUpId + ", " : "") +
                (rightUniformBottomId != null ? "rightUniformBottomId=" + rightUniformBottomId + ", " : "") +
                (rightUniformNumber != null ? "rightUniformNumber=" + rightUniformNumber + ", " : "") +
                (kickerId != null ? "kickerId=" + kickerId + ", " : "") +
            "}";
    }

}
