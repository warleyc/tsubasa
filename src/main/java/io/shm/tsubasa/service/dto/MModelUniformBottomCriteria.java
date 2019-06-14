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
 * Criteria class for the {@link io.shm.tsubasa.domain.MModelUniformBottom} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MModelUniformBottomResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-model-uniform-bottoms?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MModelUniformBottomCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter uniformBottomId;

    private IntegerFilter defaultDressingType;

    private IntegerFilter uniformModel;

    private IntegerFilter uniformTexture;

    private IntegerFilter uniformNoTexture;

    private IntegerFilter numberRightLeg;

    private IntegerFilter numberLeftLeg;

    private IntegerFilter uniformNoPositionX;

    private IntegerFilter uniformNoPositionY;

    public MModelUniformBottomCriteria(){
    }

    public MModelUniformBottomCriteria(MModelUniformBottomCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.uniformBottomId = other.uniformBottomId == null ? null : other.uniformBottomId.copy();
        this.defaultDressingType = other.defaultDressingType == null ? null : other.defaultDressingType.copy();
        this.uniformModel = other.uniformModel == null ? null : other.uniformModel.copy();
        this.uniformTexture = other.uniformTexture == null ? null : other.uniformTexture.copy();
        this.uniformNoTexture = other.uniformNoTexture == null ? null : other.uniformNoTexture.copy();
        this.numberRightLeg = other.numberRightLeg == null ? null : other.numberRightLeg.copy();
        this.numberLeftLeg = other.numberLeftLeg == null ? null : other.numberLeftLeg.copy();
        this.uniformNoPositionX = other.uniformNoPositionX == null ? null : other.uniformNoPositionX.copy();
        this.uniformNoPositionY = other.uniformNoPositionY == null ? null : other.uniformNoPositionY.copy();
    }

    @Override
    public MModelUniformBottomCriteria copy() {
        return new MModelUniformBottomCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getUniformBottomId() {
        return uniformBottomId;
    }

    public void setUniformBottomId(IntegerFilter uniformBottomId) {
        this.uniformBottomId = uniformBottomId;
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

    public IntegerFilter getNumberRightLeg() {
        return numberRightLeg;
    }

    public void setNumberRightLeg(IntegerFilter numberRightLeg) {
        this.numberRightLeg = numberRightLeg;
    }

    public IntegerFilter getNumberLeftLeg() {
        return numberLeftLeg;
    }

    public void setNumberLeftLeg(IntegerFilter numberLeftLeg) {
        this.numberLeftLeg = numberLeftLeg;
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
        final MModelUniformBottomCriteria that = (MModelUniformBottomCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(uniformBottomId, that.uniformBottomId) &&
            Objects.equals(defaultDressingType, that.defaultDressingType) &&
            Objects.equals(uniformModel, that.uniformModel) &&
            Objects.equals(uniformTexture, that.uniformTexture) &&
            Objects.equals(uniformNoTexture, that.uniformNoTexture) &&
            Objects.equals(numberRightLeg, that.numberRightLeg) &&
            Objects.equals(numberLeftLeg, that.numberLeftLeg) &&
            Objects.equals(uniformNoPositionX, that.uniformNoPositionX) &&
            Objects.equals(uniformNoPositionY, that.uniformNoPositionY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uniformBottomId,
        defaultDressingType,
        uniformModel,
        uniformTexture,
        uniformNoTexture,
        numberRightLeg,
        numberLeftLeg,
        uniformNoPositionX,
        uniformNoPositionY
        );
    }

    @Override
    public String toString() {
        return "MModelUniformBottomCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uniformBottomId != null ? "uniformBottomId=" + uniformBottomId + ", " : "") +
                (defaultDressingType != null ? "defaultDressingType=" + defaultDressingType + ", " : "") +
                (uniformModel != null ? "uniformModel=" + uniformModel + ", " : "") +
                (uniformTexture != null ? "uniformTexture=" + uniformTexture + ", " : "") +
                (uniformNoTexture != null ? "uniformNoTexture=" + uniformNoTexture + ", " : "") +
                (numberRightLeg != null ? "numberRightLeg=" + numberRightLeg + ", " : "") +
                (numberLeftLeg != null ? "numberLeftLeg=" + numberLeftLeg + ", " : "") +
                (uniformNoPositionX != null ? "uniformNoPositionX=" + uniformNoPositionX + ", " : "") +
                (uniformNoPositionY != null ? "uniformNoPositionY=" + uniformNoPositionY + ", " : "") +
            "}";
    }

}
