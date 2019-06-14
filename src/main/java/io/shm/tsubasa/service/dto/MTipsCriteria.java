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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTips} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTipsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-tips?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTipsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter priority;

    private IntegerFilter colorType;

    private IntegerFilter isTips;

    private IntegerFilter startAt;

    private IntegerFilter endAt;

    public MTipsCriteria(){
    }

    public MTipsCriteria(MTipsCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.priority = other.priority == null ? null : other.priority.copy();
        this.colorType = other.colorType == null ? null : other.colorType.copy();
        this.isTips = other.isTips == null ? null : other.isTips.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.endAt = other.endAt == null ? null : other.endAt.copy();
    }

    @Override
    public MTipsCriteria copy() {
        return new MTipsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getPriority() {
        return priority;
    }

    public void setPriority(IntegerFilter priority) {
        this.priority = priority;
    }

    public IntegerFilter getColorType() {
        return colorType;
    }

    public void setColorType(IntegerFilter colorType) {
        this.colorType = colorType;
    }

    public IntegerFilter getIsTips() {
        return isTips;
    }

    public void setIsTips(IntegerFilter isTips) {
        this.isTips = isTips;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTipsCriteria that = (MTipsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(priority, that.priority) &&
            Objects.equals(colorType, that.colorType) &&
            Objects.equals(isTips, that.isTips) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        priority,
        colorType,
        isTips,
        startAt,
        endAt
        );
    }

    @Override
    public String toString() {
        return "MTipsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (priority != null ? "priority=" + priority + ", " : "") +
                (colorType != null ? "colorType=" + colorType + ", " : "") +
                (isTips != null ? "isTips=" + isTips + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (endAt != null ? "endAt=" + endAt + ", " : "") +
            "}";
    }

}
