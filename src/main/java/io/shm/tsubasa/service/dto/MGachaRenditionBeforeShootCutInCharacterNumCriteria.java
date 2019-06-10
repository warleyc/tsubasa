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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInCharacterNum} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGachaRenditionBeforeShootCutInCharacterNumResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-gacha-rendition-before-shoot-cut-in-character-nums?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGachaRenditionBeforeShootCutInCharacterNumCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter isManySsr;

    private IntegerFilter isSsr;

    private IntegerFilter pattern;

    private IntegerFilter weight;

    private IntegerFilter num;

    public MGachaRenditionBeforeShootCutInCharacterNumCriteria(){
    }

    public MGachaRenditionBeforeShootCutInCharacterNumCriteria(MGachaRenditionBeforeShootCutInCharacterNumCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.isManySsr = other.isManySsr == null ? null : other.isManySsr.copy();
        this.isSsr = other.isSsr == null ? null : other.isSsr.copy();
        this.pattern = other.pattern == null ? null : other.pattern.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
        this.num = other.num == null ? null : other.num.copy();
    }

    @Override
    public MGachaRenditionBeforeShootCutInCharacterNumCriteria copy() {
        return new MGachaRenditionBeforeShootCutInCharacterNumCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getIsManySsr() {
        return isManySsr;
    }

    public void setIsManySsr(IntegerFilter isManySsr) {
        this.isManySsr = isManySsr;
    }

    public IntegerFilter getIsSsr() {
        return isSsr;
    }

    public void setIsSsr(IntegerFilter isSsr) {
        this.isSsr = isSsr;
    }

    public IntegerFilter getPattern() {
        return pattern;
    }

    public void setPattern(IntegerFilter pattern) {
        this.pattern = pattern;
    }

    public IntegerFilter getWeight() {
        return weight;
    }

    public void setWeight(IntegerFilter weight) {
        this.weight = weight;
    }

    public IntegerFilter getNum() {
        return num;
    }

    public void setNum(IntegerFilter num) {
        this.num = num;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGachaRenditionBeforeShootCutInCharacterNumCriteria that = (MGachaRenditionBeforeShootCutInCharacterNumCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(isManySsr, that.isManySsr) &&
            Objects.equals(isSsr, that.isSsr) &&
            Objects.equals(pattern, that.pattern) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(num, that.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        isManySsr,
        isSsr,
        pattern,
        weight,
        num
        );
    }

    @Override
    public String toString() {
        return "MGachaRenditionBeforeShootCutInCharacterNumCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (isManySsr != null ? "isManySsr=" + isManySsr + ", " : "") +
                (isSsr != null ? "isSsr=" + isSsr + ", " : "") +
                (pattern != null ? "pattern=" + pattern + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (num != null ? "num=" + num + ", " : "") +
            "}";
    }

}
