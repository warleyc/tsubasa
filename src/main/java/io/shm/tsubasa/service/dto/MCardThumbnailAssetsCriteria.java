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
 * Criteria class for the {@link io.shm.tsubasa.domain.MCardThumbnailAssets} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MCardThumbnailAssetsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-card-thumbnail-assets?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MCardThumbnailAssetsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter mCardPowerupActionSkillId;

    private LongFilter mTrainingCardId;

    public MCardThumbnailAssetsCriteria(){
    }

    public MCardThumbnailAssetsCriteria(MCardThumbnailAssetsCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.mCardPowerupActionSkillId = other.mCardPowerupActionSkillId == null ? null : other.mCardPowerupActionSkillId.copy();
        this.mTrainingCardId = other.mTrainingCardId == null ? null : other.mTrainingCardId.copy();
    }

    @Override
    public MCardThumbnailAssetsCriteria copy() {
        return new MCardThumbnailAssetsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getMCardPowerupActionSkillId() {
        return mCardPowerupActionSkillId;
    }

    public void setMCardPowerupActionSkillId(LongFilter mCardPowerupActionSkillId) {
        this.mCardPowerupActionSkillId = mCardPowerupActionSkillId;
    }

    public LongFilter getMTrainingCardId() {
        return mTrainingCardId;
    }

    public void setMTrainingCardId(LongFilter mTrainingCardId) {
        this.mTrainingCardId = mTrainingCardId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MCardThumbnailAssetsCriteria that = (MCardThumbnailAssetsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(mCardPowerupActionSkillId, that.mCardPowerupActionSkillId) &&
            Objects.equals(mTrainingCardId, that.mTrainingCardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        mCardPowerupActionSkillId,
        mTrainingCardId
        );
    }

    @Override
    public String toString() {
        return "MCardThumbnailAssetsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (mCardPowerupActionSkillId != null ? "mCardPowerupActionSkillId=" + mCardPowerupActionSkillId + ", " : "") +
                (mTrainingCardId != null ? "mTrainingCardId=" + mTrainingCardId + ", " : "") +
            "}";
    }

}
