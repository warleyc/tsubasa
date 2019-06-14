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
 * Criteria class for the {@link io.shm.tsubasa.domain.MPenaltyKickCut} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MPenaltyKickCutResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-penalty-kick-cuts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MPenaltyKickCutCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter kickerCourse;

    private IntegerFilter keeperCourse;

    private IntegerFilter keeperCommand;

    private IntegerFilter resultType;

    public MPenaltyKickCutCriteria(){
    }

    public MPenaltyKickCutCriteria(MPenaltyKickCutCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.kickerCourse = other.kickerCourse == null ? null : other.kickerCourse.copy();
        this.keeperCourse = other.keeperCourse == null ? null : other.keeperCourse.copy();
        this.keeperCommand = other.keeperCommand == null ? null : other.keeperCommand.copy();
        this.resultType = other.resultType == null ? null : other.resultType.copy();
    }

    @Override
    public MPenaltyKickCutCriteria copy() {
        return new MPenaltyKickCutCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getKickerCourse() {
        return kickerCourse;
    }

    public void setKickerCourse(IntegerFilter kickerCourse) {
        this.kickerCourse = kickerCourse;
    }

    public IntegerFilter getKeeperCourse() {
        return keeperCourse;
    }

    public void setKeeperCourse(IntegerFilter keeperCourse) {
        this.keeperCourse = keeperCourse;
    }

    public IntegerFilter getKeeperCommand() {
        return keeperCommand;
    }

    public void setKeeperCommand(IntegerFilter keeperCommand) {
        this.keeperCommand = keeperCommand;
    }

    public IntegerFilter getResultType() {
        return resultType;
    }

    public void setResultType(IntegerFilter resultType) {
        this.resultType = resultType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MPenaltyKickCutCriteria that = (MPenaltyKickCutCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(kickerCourse, that.kickerCourse) &&
            Objects.equals(keeperCourse, that.keeperCourse) &&
            Objects.equals(keeperCommand, that.keeperCommand) &&
            Objects.equals(resultType, that.resultType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        kickerCourse,
        keeperCourse,
        keeperCommand,
        resultType
        );
    }

    @Override
    public String toString() {
        return "MPenaltyKickCutCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (kickerCourse != null ? "kickerCourse=" + kickerCourse + ", " : "") +
                (keeperCourse != null ? "keeperCourse=" + keeperCourse + ", " : "") +
                (keeperCommand != null ? "keeperCommand=" + keeperCommand + ", " : "") +
                (resultType != null ? "resultType=" + resultType + ", " : "") +
            "}";
    }

}
