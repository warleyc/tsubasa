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
 * Criteria class for the {@link io.shm.tsubasa.domain.MExtensionSale} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MExtensionSaleResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-extension-sales?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MExtensionSaleCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter startAt;

    private IntegerFilter endAt;

    private IntegerFilter type;

    private IntegerFilter addExtension;

    public MExtensionSaleCriteria(){
    }

    public MExtensionSaleCriteria(MExtensionSaleCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.endAt = other.endAt == null ? null : other.endAt.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.addExtension = other.addExtension == null ? null : other.addExtension.copy();
    }

    @Override
    public MExtensionSaleCriteria copy() {
        return new MExtensionSaleCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getStartAt() {
        return startAt;
    }

    public void setStartAt(IntegerFilter startAt) {
        this.startAt = startAt;
    }

    public IntegerFilter getEndAt() {
        return endAt;
    }

    public void setEndAt(IntegerFilter endAt) {
        this.endAt = endAt;
    }

    public IntegerFilter getType() {
        return type;
    }

    public void setType(IntegerFilter type) {
        this.type = type;
    }

    public IntegerFilter getAddExtension() {
        return addExtension;
    }

    public void setAddExtension(IntegerFilter addExtension) {
        this.addExtension = addExtension;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MExtensionSaleCriteria that = (MExtensionSaleCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(endAt, that.endAt) &&
            Objects.equals(type, that.type) &&
            Objects.equals(addExtension, that.addExtension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        startAt,
        endAt,
        type,
        addExtension
        );
    }

    @Override
    public String toString() {
        return "MExtensionSaleCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (endAt != null ? "endAt=" + endAt + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (addExtension != null ? "addExtension=" + addExtension + ", " : "") +
            "}";
    }

}
