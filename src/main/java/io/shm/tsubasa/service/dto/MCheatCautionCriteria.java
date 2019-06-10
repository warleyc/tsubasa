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
 * Criteria class for the {@link io.shm.tsubasa.domain.MCheatCaution} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MCheatCautionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-cheat-cautions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MCheatCautionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter caution;

    public MCheatCautionCriteria(){
    }

    public MCheatCautionCriteria(MCheatCautionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.caution = other.caution == null ? null : other.caution.copy();
    }

    @Override
    public MCheatCautionCriteria copy() {
        return new MCheatCautionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getCaution() {
        return caution;
    }

    public void setCaution(IntegerFilter caution) {
        this.caution = caution;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MCheatCautionCriteria that = (MCheatCautionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(caution, that.caution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        caution
        );
    }

    @Override
    public String toString() {
        return "MCheatCautionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (caution != null ? "caution=" + caution + ", " : "") +
            "}";
    }

}
