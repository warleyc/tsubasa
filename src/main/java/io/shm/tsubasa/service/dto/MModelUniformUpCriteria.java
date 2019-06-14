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
 * Criteria class for the {@link io.shm.tsubasa.domain.MModelUniformUp} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MModelUniformUpResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-model-uniform-ups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MModelUniformUpCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter uniformUpId;

    private IntegerFilter defaultDressingType;

    private IntegerFilter uniformModel;

    private IntegerFilter uniformTexture;

    private IntegerFilter uniformNoTexture;

    private IntegerFilter numberChest;

    private IntegerFilter numberBelly;

    private IntegerFilter numberBack;

    private IntegerFilter uniformNoPositionX;

    private IntegerFilter uniformNoPositionY;

    public MModelUniformUpCriteria(){
    }

    public MModelUniformUpCriteria(MModelUniformUpCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.uniformUpId = other.uniformUpId == null ? null : other.uniformUpId.copy();
        this.defaultDressingType = other.defaultDressingType == null ? null : other.defaultDressingType.copy();
        this.uniformModel = other.uniformModel == null ? null : other.uniformModel.copy();
        this.uniformTexture = other.uniformTexture == null ? null : other.uniformTexture.copy();
        this.uniformNoTexture = other.uniformNoTexture == null ? null : other.uniformNoTexture.copy();
        this.numberChest = other.numberChest == null ? null : other.numberChest.copy();
        this.numberBelly = other.numberBelly == null ? null : other.numberBelly.copy();
        this.numberBack = other.numberBack == null ? null : other.numberBack.copy();
        this.uniformNoPositionX = other.uniformNoPositionX == null ? null : other.uniformNoPositionX.copy();
        this.uniformNoPositionY = other.uniformNoPositionY == null ? null : other.uniformNoPositionY.copy();
    }

    @Override
    public MModelUniformUpCriteria copy() {
        return new MModelUniformUpCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getUniformUpId() {
        return uniformUpId;
    }

    public void setUniformUpId(IntegerFilter uniformUpId) {
        this.uniformUpId = uniformUpId;
    }

    public IntegerFilter getDefaultDressingType() {
        return defaultDressingType;
    }

    public void setDefaultDressingType(IntegerFilter defaultDressingType) {
        this.defaultDressingType = defaultDressingType;
    }

    public IntegerFilter getUniformModel() {
        return uniformModel;
    }

    public void setUniformModel(IntegerFilter uniformModel) {
        this.uniformModel = uniformModel;
    }

    public IntegerFilter getUniformTexture() {
        return uniformTexture;
    }

    public void setUniformTexture(IntegerFilter uniformTexture) {
        this.uniformTexture = uniformTexture;
    }

    public IntegerFilter getUniformNoTexture() {
        return uniformNoTexture;
    }

    public void setUniformNoTexture(IntegerFilter uniformNoTexture) {
        this.uniformNoTexture = uniformNoTexture;
    }

    public IntegerFilter getNumberChest() {
        return numberChest;
    }

    public void setNumberChest(IntegerFilter numberChest) {
        this.numberChest = numberChest;
    }

    public IntegerFilter getNumberBelly() {
        return numberBelly;
    }

    public void setNumberBelly(IntegerFilter numberBelly) {
        this.numberBelly = numberBelly;
    }

    public IntegerFilter getNumberBack() {
        return numberBack;
    }

    public void setNumberBack(IntegerFilter numberBack) {
        this.numberBack = numberBack;
    }

    public IntegerFilter getUniformNoPositionX() {
        return uniformNoPositionX;
    }

    public void setUniformNoPositionX(IntegerFilter uniformNoPositionX) {
        this.uniformNoPositionX = uniformNoPositionX;
    }

    public IntegerFilter getUniformNoPositionY() {
        return uniformNoPositionY;
    }

    public void setUniformNoPositionY(IntegerFilter uniformNoPositionY) {
        this.uniformNoPositionY = uniformNoPositionY;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MModelUniformUpCriteria that = (MModelUniformUpCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(uniformUpId, that.uniformUpId) &&
            Objects.equals(defaultDressingType, that.defaultDressingType) &&
            Objects.equals(uniformModel, that.uniformModel) &&
            Objects.equals(uniformTexture, that.uniformTexture) &&
            Objects.equals(uniformNoTexture, that.uniformNoTexture) &&
            Objects.equals(numberChest, that.numberChest) &&
            Objects.equals(numberBelly, that.numberBelly) &&
            Objects.equals(numberBack, that.numberBack) &&
            Objects.equals(uniformNoPositionX, that.uniformNoPositionX) &&
            Objects.equals(uniformNoPositionY, that.uniformNoPositionY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uniformUpId,
        defaultDressingType,
        uniformModel,
        uniformTexture,
        uniformNoTexture,
        numberChest,
        numberBelly,
        numberBack,
        uniformNoPositionX,
        uniformNoPositionY
        );
    }

    @Override
    public String toString() {
        return "MModelUniformUpCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uniformUpId != null ? "uniformUpId=" + uniformUpId + ", " : "") +
                (defaultDressingType != null ? "defaultDressingType=" + defaultDressingType + ", " : "") +
                (uniformModel != null ? "uniformModel=" + uniformModel + ", " : "") +
                (uniformTexture != null ? "uniformTexture=" + uniformTexture + ", " : "") +
                (uniformNoTexture != null ? "uniformNoTexture=" + uniformNoTexture + ", " : "") +
                (numberChest != null ? "numberChest=" + numberChest + ", " : "") +
                (numberBelly != null ? "numberBelly=" + numberBelly + ", " : "") +
                (numberBack != null ? "numberBack=" + numberBack + ", " : "") +
                (uniformNoPositionX != null ? "uniformNoPositionX=" + uniformNoPositionX + ", " : "") +
                (uniformNoPositionY != null ? "uniformNoPositionY=" + uniformNoPositionY + ", " : "") +
            "}";
    }

}
