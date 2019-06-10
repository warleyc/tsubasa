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
 * Criteria class for the {@link io.shm.tsubasa.domain.MArousalItem} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MArousalItemResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-arousal-items?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MArousalItemCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter arousalItemType;

    public MArousalItemCriteria(){
    }

    public MArousalItemCriteria(MArousalItemCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.arousalItemType = other.arousalItemType == null ? null : other.arousalItemType.copy();
    }

    @Override
    public MArousalItemCriteria copy() {
        return new MArousalItemCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getArousalItemType() {
        return arousalItemType;
    }

    public void setArousalItemType(IntegerFilter arousalItemType) {
        this.arousalItemType = arousalItemType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MArousalItemCriteria that = (MArousalItemCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(arousalItemType, that.arousalItemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        arousalItemType
        );
    }

    @Override
    public String toString() {
        return "MArousalItemCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (arousalItemType != null ? "arousalItemType=" + arousalItemType + ", " : "") +
            "}";
    }

}
