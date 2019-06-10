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
 * Criteria class for the {@link io.shm.tsubasa.domain.MCharacterBook} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MCharacterBookResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-character-books?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MCharacterBookCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter series;

    private IntegerFilter height;

    private IntegerFilter weight;

    public MCharacterBookCriteria(){
    }

    public MCharacterBookCriteria(MCharacterBookCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.series = other.series == null ? null : other.series.copy();
        this.height = other.height == null ? null : other.height.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
    }

    @Override
    public MCharacterBookCriteria copy() {
        return new MCharacterBookCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSeries() {
        return series;
    }

    public void setSeries(IntegerFilter series) {
        this.series = series;
    }

    public IntegerFilter getHeight() {
        return height;
    }

    public void setHeight(IntegerFilter height) {
        this.height = height;
    }

    public IntegerFilter getWeight() {
        return weight;
    }

    public void setWeight(IntegerFilter weight) {
        this.weight = weight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MCharacterBookCriteria that = (MCharacterBookCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(series, that.series) &&
            Objects.equals(height, that.height) &&
            Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        series,
        height,
        weight
        );
    }

    @Override
    public String toString() {
        return "MCharacterBookCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (series != null ? "series=" + series + ", " : "") +
                (height != null ? "height=" + height + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
            "}";
    }

}
