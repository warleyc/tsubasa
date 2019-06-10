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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGachaRenditionFirstGimmick} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGachaRenditionFirstGimmickResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-gacha-rendition-first-gimmicks?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGachaRenditionFirstGimmickCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter isSsr;

    private IntegerFilter weight;

    private IntegerFilter birdNum;

    private IntegerFilter isTickerTape;

    public MGachaRenditionFirstGimmickCriteria(){
    }

    public MGachaRenditionFirstGimmickCriteria(MGachaRenditionFirstGimmickCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.isSsr = other.isSsr == null ? null : other.isSsr.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
        this.birdNum = other.birdNum == null ? null : other.birdNum.copy();
        this.isTickerTape = other.isTickerTape == null ? null : other.isTickerTape.copy();
    }

    @Override
    public MGachaRenditionFirstGimmickCriteria copy() {
        return new MGachaRenditionFirstGimmickCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public IntegerFilter getBirdNum() {
        return birdNum;
    }

    public void setBirdNum(IntegerFilter birdNum) {
        this.birdNum = birdNum;
    }

    public IntegerFilter getIsTickerTape() {
        return isTickerTape;
    }

    public void setIsTickerTape(IntegerFilter isTickerTape) {
        this.isTickerTape = isTickerTape;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGachaRenditionFirstGimmickCriteria that = (MGachaRenditionFirstGimmickCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(isSsr, that.isSsr) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(birdNum, that.birdNum) &&
            Objects.equals(isTickerTape, that.isTickerTape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        isSsr,
        weight,
        birdNum,
        isTickerTape
        );
    }

    @Override
    public String toString() {
        return "MGachaRenditionFirstGimmickCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (isSsr != null ? "isSsr=" + isSsr + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (birdNum != null ? "birdNum=" + birdNum + ", " : "") +
                (isTickerTape != null ? "isTickerTape=" + isTickerTape + ", " : "") +
            "}";
    }

}
