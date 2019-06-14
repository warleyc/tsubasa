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
 * Criteria class for the {@link io.shm.tsubasa.domain.MLoginBonusSerif} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MLoginBonusSerifResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-login-bonus-serifs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MLoginBonusSerifCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter serifId;

    public MLoginBonusSerifCriteria(){
    }

    public MLoginBonusSerifCriteria(MLoginBonusSerifCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.serifId = other.serifId == null ? null : other.serifId.copy();
    }

    @Override
    public MLoginBonusSerifCriteria copy() {
        return new MLoginBonusSerifCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSerifId() {
        return serifId;
    }

    public void setSerifId(IntegerFilter serifId) {
        this.serifId = serifId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MLoginBonusSerifCriteria that = (MLoginBonusSerifCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(serifId, that.serifId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        serifId
        );
    }

    @Override
    public String toString() {
        return "MLoginBonusSerifCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (serifId != null ? "serifId=" + serifId + ", " : "") +
            "}";
    }

}
