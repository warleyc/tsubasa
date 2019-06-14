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
 * Criteria class for the {@link io.shm.tsubasa.domain.MModelCard} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MModelCardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-model-cards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MModelCardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter hairModel;

    private IntegerFilter hairTexture;

    private IntegerFilter headModel;

    private IntegerFilter headTexture;

    private IntegerFilter skinTexture;

    private IntegerFilter shoesModel;

    private IntegerFilter shoesTexture;

    private IntegerFilter globeTexture;

    private IntegerFilter uniqueModel;

    private IntegerFilter uniqueTexture;

    private IntegerFilter dressingTypeUp;

    private IntegerFilter dressingTypeBottom;

    private IntegerFilter height;

    private IntegerFilter width;

    private LongFilter mPlayableCardId;

    public MModelCardCriteria(){
    }

    public MModelCardCriteria(MModelCardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.hairModel = other.hairModel == null ? null : other.hairModel.copy();
        this.hairTexture = other.hairTexture == null ? null : other.hairTexture.copy();
        this.headModel = other.headModel == null ? null : other.headModel.copy();
        this.headTexture = other.headTexture == null ? null : other.headTexture.copy();
        this.skinTexture = other.skinTexture == null ? null : other.skinTexture.copy();
        this.shoesModel = other.shoesModel == null ? null : other.shoesModel.copy();
        this.shoesTexture = other.shoesTexture == null ? null : other.shoesTexture.copy();
        this.globeTexture = other.globeTexture == null ? null : other.globeTexture.copy();
        this.uniqueModel = other.uniqueModel == null ? null : other.uniqueModel.copy();
        this.uniqueTexture = other.uniqueTexture == null ? null : other.uniqueTexture.copy();
        this.dressingTypeUp = other.dressingTypeUp == null ? null : other.dressingTypeUp.copy();
        this.dressingTypeBottom = other.dressingTypeBottom == null ? null : other.dressingTypeBottom.copy();
        this.height = other.height == null ? null : other.height.copy();
        this.width = other.width == null ? null : other.width.copy();
        this.mPlayableCardId = other.mPlayableCardId == null ? null : other.mPlayableCardId.copy();
    }

    @Override
    public MModelCardCriteria copy() {
        return new MModelCardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getHairModel() {
        return hairModel;
    }

    public void setHairModel(IntegerFilter hairModel) {
        this.hairModel = hairModel;
    }

    public IntegerFilter getHairTexture() {
        return hairTexture;
    }

    public void setHairTexture(IntegerFilter hairTexture) {
        this.hairTexture = hairTexture;
    }

    public IntegerFilter getHeadModel() {
        return headModel;
    }

    public void setHeadModel(IntegerFilter headModel) {
        this.headModel = headModel;
    }

    public IntegerFilter getHeadTexture() {
        return headTexture;
    }

    public void setHeadTexture(IntegerFilter headTexture) {
        this.headTexture = headTexture;
    }

    public IntegerFilter getSkinTexture() {
        return skinTexture;
    }

    public void setSkinTexture(IntegerFilter skinTexture) {
        this.skinTexture = skinTexture;
    }

    public IntegerFilter getShoesModel() {
        return shoesModel;
    }

    public void setShoesModel(IntegerFilter shoesModel) {
        this.shoesModel = shoesModel;
    }

    public IntegerFilter getShoesTexture() {
        return shoesTexture;
    }

    public void setShoesTexture(IntegerFilter shoesTexture) {
        this.shoesTexture = shoesTexture;
    }

    public IntegerFilter getGlobeTexture() {
        return globeTexture;
    }

    public void setGlobeTexture(IntegerFilter globeTexture) {
        this.globeTexture = globeTexture;
    }

    public IntegerFilter getUniqueModel() {
        return uniqueModel;
    }

    public void setUniqueModel(IntegerFilter uniqueModel) {
        this.uniqueModel = uniqueModel;
    }

    public IntegerFilter getUniqueTexture() {
        return uniqueTexture;
    }

    public void setUniqueTexture(IntegerFilter uniqueTexture) {
        this.uniqueTexture = uniqueTexture;
    }

    public IntegerFilter getDressingTypeUp() {
        return dressingTypeUp;
    }

    public void setDressingTypeUp(IntegerFilter dressingTypeUp) {
        this.dressingTypeUp = dressingTypeUp;
    }

    public IntegerFilter getDressingTypeBottom() {
        return dressingTypeBottom;
    }

    public void setDressingTypeBottom(IntegerFilter dressingTypeBottom) {
        this.dressingTypeBottom = dressingTypeBottom;
    }

    public IntegerFilter getHeight() {
        return height;
    }

    public void setHeight(IntegerFilter height) {
        this.height = height;
    }

    public IntegerFilter getWidth() {
        return width;
    }

    public void setWidth(IntegerFilter width) {
        this.width = width;
    }

    public LongFilter getMPlayableCardId() {
        return mPlayableCardId;
    }

    public void setMPlayableCardId(LongFilter mPlayableCardId) {
        this.mPlayableCardId = mPlayableCardId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MModelCardCriteria that = (MModelCardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(hairModel, that.hairModel) &&
            Objects.equals(hairTexture, that.hairTexture) &&
            Objects.equals(headModel, that.headModel) &&
            Objects.equals(headTexture, that.headTexture) &&
            Objects.equals(skinTexture, that.skinTexture) &&
            Objects.equals(shoesModel, that.shoesModel) &&
            Objects.equals(shoesTexture, that.shoesTexture) &&
            Objects.equals(globeTexture, that.globeTexture) &&
            Objects.equals(uniqueModel, that.uniqueModel) &&
            Objects.equals(uniqueTexture, that.uniqueTexture) &&
            Objects.equals(dressingTypeUp, that.dressingTypeUp) &&
            Objects.equals(dressingTypeBottom, that.dressingTypeBottom) &&
            Objects.equals(height, that.height) &&
            Objects.equals(width, that.width) &&
            Objects.equals(mPlayableCardId, that.mPlayableCardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        hairModel,
        hairTexture,
        headModel,
        headTexture,
        skinTexture,
        shoesModel,
        shoesTexture,
        globeTexture,
        uniqueModel,
        uniqueTexture,
        dressingTypeUp,
        dressingTypeBottom,
        height,
        width,
        mPlayableCardId
        );
    }

    @Override
    public String toString() {
        return "MModelCardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (hairModel != null ? "hairModel=" + hairModel + ", " : "") +
                (hairTexture != null ? "hairTexture=" + hairTexture + ", " : "") +
                (headModel != null ? "headModel=" + headModel + ", " : "") +
                (headTexture != null ? "headTexture=" + headTexture + ", " : "") +
                (skinTexture != null ? "skinTexture=" + skinTexture + ", " : "") +
                (shoesModel != null ? "shoesModel=" + shoesModel + ", " : "") +
                (shoesTexture != null ? "shoesTexture=" + shoesTexture + ", " : "") +
                (globeTexture != null ? "globeTexture=" + globeTexture + ", " : "") +
                (uniqueModel != null ? "uniqueModel=" + uniqueModel + ", " : "") +
                (uniqueTexture != null ? "uniqueTexture=" + uniqueTexture + ", " : "") +
                (dressingTypeUp != null ? "dressingTypeUp=" + dressingTypeUp + ", " : "") +
                (dressingTypeBottom != null ? "dressingTypeBottom=" + dressingTypeBottom + ", " : "") +
                (height != null ? "height=" + height + ", " : "") +
                (width != null ? "width=" + width + ", " : "") +
                (mPlayableCardId != null ? "mPlayableCardId=" + mPlayableCardId + ", " : "") +
            "}";
    }

}
