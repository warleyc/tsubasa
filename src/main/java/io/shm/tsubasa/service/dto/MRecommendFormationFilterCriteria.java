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
 * Criteria class for the {@link io.shm.tsubasa.domain.MRecommendFormationFilter} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MRecommendFormationFilterResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-recommend-formation-filters?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MRecommendFormationFilterCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter filterType;

    public MRecommendFormationFilterCriteria(){
    }

    public MRecommendFormationFilterCriteria(MRecommendFormationFilterCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.filterType = other.filterType == null ? null : other.filterType.copy();
    }

    @Override
    public MRecommendFormationFilterCriteria copy() {
        return new MRecommendFormationFilterCriteria(this);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MRecommendFormationFilterCriteria that = (MRecommendFormationFilterCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(filterType, that.filterType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        filterType
        );
    }

    @Override
    public String toString() {
        return "MRecommendFormationFilterCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (filterType != null ? "filterType=" + filterType + ", " : "") +
            "}";
    }

}
