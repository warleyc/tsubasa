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
 * Criteria class for the {@link io.shm.tsubasa.domain.MColorPalette} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MColorPaletteResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-color-palettes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MColorPaletteCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter orderNum;

    public MColorPaletteCriteria(){
    }

    public MColorPaletteCriteria(MColorPaletteCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
    }

    @Override
    public MColorPaletteCriteria copy() {
        return new MColorPaletteCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(IntegerFilter orderNum) {
        this.orderNum = orderNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MColorPaletteCriteria that = (MColorPaletteCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(orderNum, that.orderNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        orderNum
        );
    }

    @Override
    public String toString() {
        return "MColorPaletteCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
            "}";
    }

}
