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
 * Criteria class for the {@link io.shm.tsubasa.domain.MDeckRarityConditionDescription} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MDeckRarityConditionDescriptionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-deck-rarity-condition-descriptions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MDeckRarityConditionDescriptionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter rarityGroupId;

    private IntegerFilter countType;

    private IntegerFilter isStarting;

    public MDeckRarityConditionDescriptionCriteria(){
    }

    public MDeckRarityConditionDescriptionCriteria(MDeckRarityConditionDescriptionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.rarityGroupId = other.rarityGroupId == null ? null : other.rarityGroupId.copy();
        this.countType = other.countType == null ? null : other.countType.copy();
        this.isStarting = other.isStarting == null ? null : other.isStarting.copy();
    }

    @Override
    public MDeckRarityConditionDescriptionCriteria copy() {
        return new MDeckRarityConditionDescriptionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRarityGroupId() {
        return rarityGroupId;
    }

    public void setRarityGroupId(IntegerFilter rarityGroupId) {
        this.rarityGroupId = rarityGroupId;
    }

    public IntegerFilter getCountType() {
        return countType;
    }

    public void setCountType(IntegerFilter countType) {
        this.countType = countType;
    }

    public IntegerFilter getIsStarting() {
        return isStarting;
    }

    public void setIsStarting(IntegerFilter isStarting) {
        this.isStarting = isStarting;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MDeckRarityConditionDescriptionCriteria that = (MDeckRarityConditionDescriptionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(rarityGroupId, that.rarityGroupId) &&
            Objects.equals(countType, that.countType) &&
            Objects.equals(isStarting, that.isStarting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        rarityGroupId,
        countType,
        isStarting
        );
    }

    @Override
    public String toString() {
        return "MDeckRarityConditionDescriptionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (rarityGroupId != null ? "rarityGroupId=" + rarityGroupId + ", " : "") +
                (countType != null ? "countType=" + countType + ", " : "") +
                (isStarting != null ? "isStarting=" + isStarting + ", " : "") +
            "}";
    }

}
