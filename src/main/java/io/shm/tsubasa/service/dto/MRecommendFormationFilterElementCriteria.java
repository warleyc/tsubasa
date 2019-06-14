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
 * Criteria class for the {@link io.shm.tsubasa.domain.MRecommendFormationFilterElement} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MRecommendFormationFilterElementResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-recommend-formation-filter-elements?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MRecommendFormationFilterElementCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter filterType;

    private IntegerFilter elementId;

    public MRecommendFormationFilterElementCriteria(){
    }

    public MRecommendFormationFilterElementCriteria(MRecommendFormationFilterElementCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.filterType = other.filterType == null ? null : other.filterType.copy();
        this.elementId = other.elementId == null ? null : other.elementId.copy();
    }

    @Override
    public MRecommendFormationFilterElementCriteria copy() {
        return new MRecommendFormationFilterElementCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getFilterType() {
        return filterType;
    }

    public void setFilterType(IntegerFilter filterType) {
        this.filterType = filterType;
    }

    public IntegerFilter getElementId() {
        return elementId;
    }

    public void setElementId(IntegerFilter elementId) {
        this.elementId = elementId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MRecommendFormationFilterElementCriteria that = (MRecommendFormationFilterElementCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(filterType, that.filterType) &&
            Objects.equals(elementId, that.elementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        filterType,
        elementId
        );
    }

    @Override
    public String toString() {
        return "MRecommendFormationFilterElementCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (filterType != null ? "filterType=" + filterType + ", " : "") +
                (elementId != null ? "elementId=" + elementId + ", " : "") +
            "}";
    }

}
