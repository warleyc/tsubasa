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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMatchFormation} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMatchFormationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-match-formations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMatchFormationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter position1;

    private IntegerFilter position2;

    private IntegerFilter position3;

    private IntegerFilter position4;

    private IntegerFilter position5;

    private IntegerFilter position6;

    private IntegerFilter position7;

    private IntegerFilter position8;

    private IntegerFilter position9;

    private IntegerFilter position10;

    private IntegerFilter position11;

    public MMatchFormationCriteria(){
    }

    public MMatchFormationCriteria(MMatchFormationCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.position1 = other.position1 == null ? null : other.position1.copy();
        this.position2 = other.position2 == null ? null : other.position2.copy();
        this.position3 = other.position3 == null ? null : other.position3.copy();
        this.position4 = other.position4 == null ? null : other.position4.copy();
        this.position5 = other.position5 == null ? null : other.position5.copy();
        this.position6 = other.position6 == null ? null : other.position6.copy();
        this.position7 = other.position7 == null ? null : other.position7.copy();
        this.position8 = other.position8 == null ? null : other.position8.copy();
        this.position9 = other.position9 == null ? null : other.position9.copy();
        this.position10 = other.position10 == null ? null : other.position10.copy();
        this.position11 = other.position11 == null ? null : other.position11.copy();
    }

    @Override
    public MMatchFormationCriteria copy() {
        return new MMatchFormationCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getPosition1() {
        return position1;
    }

    public void setPosition1(IntegerFilter position1) {
        this.position1 = position1;
    }

    public IntegerFilter getPosition2() {
        return position2;
    }

    public void setPosition2(IntegerFilter position2) {
        this.position2 = position2;
    }

    public IntegerFilter getPosition3() {
        return position3;
    }

    public void setPosition3(IntegerFilter position3) {
        this.position3 = position3;
    }

    public IntegerFilter getPosition4() {
        return position4;
    }

    public void setPosition4(IntegerFilter position4) {
        this.position4 = position4;
    }

    public IntegerFilter getPosition5() {
        return position5;
    }

    public void setPosition5(IntegerFilter position5) {
        this.position5 = position5;
    }

    public IntegerFilter getPosition6() {
        return position6;
    }

    public void setPosition6(IntegerFilter position6) {
        this.position6 = position6;
    }

    public IntegerFilter getPosition7() {
        return position7;
    }

    public void setPosition7(IntegerFilter position7) {
        this.position7 = position7;
    }

    public IntegerFilter getPosition8() {
        return position8;
    }

    public void setPosition8(IntegerFilter position8) {
        this.position8 = position8;
    }

    public IntegerFilter getPosition9() {
        return position9;
    }

    public void setPosition9(IntegerFilter position9) {
        this.position9 = position9;
    }

    public IntegerFilter getPosition10() {
        return position10;
    }

    public void setPosition10(IntegerFilter position10) {
        this.position10 = position10;
    }

    public IntegerFilter getPosition11() {
        return position11;
    }

    public void setPosition11(IntegerFilter position11) {
        this.position11 = position11;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMatchFormationCriteria that = (MMatchFormationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(position1, that.position1) &&
            Objects.equals(position2, that.position2) &&
            Objects.equals(position3, that.position3) &&
            Objects.equals(position4, that.position4) &&
            Objects.equals(position5, that.position5) &&
            Objects.equals(position6, that.position6) &&
            Objects.equals(position7, that.position7) &&
            Objects.equals(position8, that.position8) &&
            Objects.equals(position9, that.position9) &&
            Objects.equals(position10, that.position10) &&
            Objects.equals(position11, that.position11);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        position1,
        position2,
        position3,
        position4,
        position5,
        position6,
        position7,
        position8,
        position9,
        position10,
        position11
        );
    }

    @Override
    public String toString() {
        return "MMatchFormationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (position1 != null ? "position1=" + position1 + ", " : "") +
                (position2 != null ? "position2=" + position2 + ", " : "") +
                (position3 != null ? "position3=" + position3 + ", " : "") +
                (position4 != null ? "position4=" + position4 + ", " : "") +
                (position5 != null ? "position5=" + position5 + ", " : "") +
                (position6 != null ? "position6=" + position6 + ", " : "") +
                (position7 != null ? "position7=" + position7 + ", " : "") +
                (position8 != null ? "position8=" + position8 + ", " : "") +
                (position9 != null ? "position9=" + position9 + ", " : "") +
                (position10 != null ? "position10=" + position10 + ", " : "") +
                (position11 != null ? "position11=" + position11 + ", " : "") +
            "}";
    }

}
