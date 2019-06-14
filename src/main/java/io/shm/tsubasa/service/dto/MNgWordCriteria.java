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
 * Criteria class for the {@link io.shm.tsubasa.domain.MNgWord} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MNgWordResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-ng-words?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MNgWordCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter judgeType;

    public MNgWordCriteria(){
    }

    public MNgWordCriteria(MNgWordCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.judgeType = other.judgeType == null ? null : other.judgeType.copy();
    }

    @Override
    public MNgWordCriteria copy() {
        return new MNgWordCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(IntegerFilter judgeType) {
        this.judgeType = judgeType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MNgWordCriteria that = (MNgWordCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(judgeType, that.judgeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        judgeType
        );
    }

    @Override
    public String toString() {
        return "MNgWordCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (judgeType != null ? "judgeType=" + judgeType + ", " : "") +
            "}";
    }

}
