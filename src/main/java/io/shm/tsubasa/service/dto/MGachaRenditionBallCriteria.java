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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGachaRenditionBall} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGachaRenditionBallResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-gacha-rendition-balls?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGachaRenditionBallCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter renditionId;

    private IntegerFilter isSsr;

    private IntegerFilter weight;

    public MGachaRenditionBallCriteria(){
    }

    public MGachaRenditionBallCriteria(MGachaRenditionBallCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.renditionId = other.renditionId == null ? null : other.renditionId.copy();
        this.isSsr = other.isSsr == null ? null : other.isSsr.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
    }

    @Override
    public MGachaRenditionBallCriteria copy() {
        return new MGachaRenditionBallCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRenditionId() {
        return renditionId;
    }

    public void setRenditionId(IntegerFilter renditionId) {
        this.renditionId = renditionId;
    }

    public IntegerFilter getIsSsr() {
        return isSsr;
    }

    public void setIsSsr(IntegerFilter isSsr) {
        this.isSsr = isSsr;
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
        final MGachaRenditionBallCriteria that = (MGachaRenditionBallCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(renditionId, that.renditionId) &&
            Objects.equals(isSsr, that.isSsr) &&
            Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        renditionId,
        isSsr,
        weight
        );
    }

    @Override
    public String toString() {
        return "MGachaRenditionBallCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (renditionId != null ? "renditionId=" + renditionId + ", " : "") +
                (isSsr != null ? "isSsr=" + isSsr + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
            "}";
    }

}
