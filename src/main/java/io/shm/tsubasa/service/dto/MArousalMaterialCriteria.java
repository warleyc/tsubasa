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
 * Criteria class for the {@link io.shm.tsubasa.domain.MArousalMaterial} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MArousalMaterialResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-arousal-materials?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MArousalMaterialCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter groupId;

    private IntegerFilter contentType;

    private IntegerFilter contentId;

    private IntegerFilter contentAmount;

    private IntegerFilter mainActionLevel;

    public MArousalMaterialCriteria(){
    }

    public MArousalMaterialCriteria(MArousalMaterialCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.contentType = other.contentType == null ? null : other.contentType.copy();
        this.contentId = other.contentId == null ? null : other.contentId.copy();
        this.contentAmount = other.contentAmount == null ? null : other.contentAmount.copy();
        this.mainActionLevel = other.mainActionLevel == null ? null : other.mainActionLevel.copy();
    }

    @Override
    public MArousalMaterialCriteria copy() {
        return new MArousalMaterialCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getGroupId() {
        return groupId;
    }

    public void setGroupId(IntegerFilter groupId) {
        this.groupId = groupId;
    }

    public IntegerFilter getContentType() {
        return contentType;
    }

    public void setContentType(IntegerFilter contentType) {
        this.contentType = contentType;
    }

    public IntegerFilter getContentId() {
        return contentId;
    }

    public void setContentId(IntegerFilter contentId) {
        this.contentId = contentId;
    }

    public IntegerFilter getContentAmount() {
        return contentAmount;
    }

    public void setContentAmount(IntegerFilter contentAmount) {
        this.contentAmount = contentAmount;
    }

    public IntegerFilter getMainActionLevel() {
        return mainActionLevel;
    }

    public void setMainActionLevel(IntegerFilter mainActionLevel) {
        this.mainActionLevel = mainActionLevel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MArousalMaterialCriteria that = (MArousalMaterialCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(contentType, that.contentType) &&
            Objects.equals(contentId, that.contentId) &&
            Objects.equals(contentAmount, that.contentAmount) &&
            Objects.equals(mainActionLevel, that.mainActionLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        groupId,
        contentType,
        contentId,
        contentAmount,
        mainActionLevel
        );
    }

    @Override
    public String toString() {
        return "MArousalMaterialCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (groupId != null ? "groupId=" + groupId + ", " : "") +
                (contentType != null ? "contentType=" + contentType + ", " : "") +
                (contentId != null ? "contentId=" + contentId + ", " : "") +
                (contentAmount != null ? "contentAmount=" + contentAmount + ", " : "") +
                (mainActionLevel != null ? "mainActionLevel=" + mainActionLevel + ", " : "") +
            "}";
    }

}
