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
 * Criteria class for the {@link io.shm.tsubasa.domain.MEmblemParts} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MEmblemPartsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-emblem-parts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MEmblemPartsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter partsType;

    private LongFilter mDummyEmblemId;

    public MEmblemPartsCriteria(){
    }

    public MEmblemPartsCriteria(MEmblemPartsCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.partsType = other.partsType == null ? null : other.partsType.copy();
        this.mDummyEmblemId = other.mDummyEmblemId == null ? null : other.mDummyEmblemId.copy();
    }

    @Override
    public MEmblemPartsCriteria copy() {
        return new MEmblemPartsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getPartsType() {
        return partsType;
    }

    public void setPartsType(IntegerFilter partsType) {
        this.partsType = partsType;
    }

    public LongFilter getMDummyEmblemId() {
        return mDummyEmblemId;
    }

    public void setMDummyEmblemId(LongFilter mDummyEmblemId) {
        this.mDummyEmblemId = mDummyEmblemId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MEmblemPartsCriteria that = (MEmblemPartsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(partsType, that.partsType) &&
            Objects.equals(mDummyEmblemId, that.mDummyEmblemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        partsType,
        mDummyEmblemId
        );
    }

    @Override
    public String toString() {
        return "MEmblemPartsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (partsType != null ? "partsType=" + partsType + ", " : "") +
                (mDummyEmblemId != null ? "mDummyEmblemId=" + mDummyEmblemId + ", " : "") +
            "}";
    }

}
