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
 * Criteria class for the {@link io.shm.tsubasa.domain.MEncountersCut} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MEncountersCutResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-encounters-cuts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MEncountersCutCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter condition;

    private IntegerFilter ballFloatCondition;

    private IntegerFilter command1Type;

    private IntegerFilter command1IsSkill;

    private IntegerFilter command2Type;

    private IntegerFilter command2IsSkill;

    private IntegerFilter result;

    private IntegerFilter shootResult;

    private IntegerFilter isThrown;

    public MEncountersCutCriteria(){
    }

    public MEncountersCutCriteria(MEncountersCutCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.condition = other.condition == null ? null : other.condition.copy();
        this.ballFloatCondition = other.ballFloatCondition == null ? null : other.ballFloatCondition.copy();
        this.command1Type = other.command1Type == null ? null : other.command1Type.copy();
        this.command1IsSkill = other.command1IsSkill == null ? null : other.command1IsSkill.copy();
        this.command2Type = other.command2Type == null ? null : other.command2Type.copy();
        this.command2IsSkill = other.command2IsSkill == null ? null : other.command2IsSkill.copy();
        this.result = other.result == null ? null : other.result.copy();
        this.shootResult = other.shootResult == null ? null : other.shootResult.copy();
        this.isThrown = other.isThrown == null ? null : other.isThrown.copy();
    }

    @Override
    public MEncountersCutCriteria copy() {
        return new MEncountersCutCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getCondition() {
        return condition;
    }

    public void setCondition(IntegerFilter condition) {
        this.condition = condition;
    }

    public IntegerFilter getBallFloatCondition() {
        return ballFloatCondition;
    }

    public void setBallFloatCondition(IntegerFilter ballFloatCondition) {
        this.ballFloatCondition = ballFloatCondition;
    }

    public IntegerFilter getCommand1Type() {
        return command1Type;
    }

    public void setCommand1Type(IntegerFilter command1Type) {
        this.command1Type = command1Type;
    }

    public IntegerFilter getCommand1IsSkill() {
        return command1IsSkill;
    }

    public void setCommand1IsSkill(IntegerFilter command1IsSkill) {
        this.command1IsSkill = command1IsSkill;
    }

    public IntegerFilter getCommand2Type() {
        return command2Type;
    }

    public void setCommand2Type(IntegerFilter command2Type) {
        this.command2Type = command2Type;
    }

    public IntegerFilter getCommand2IsSkill() {
        return command2IsSkill;
    }

    public void setCommand2IsSkill(IntegerFilter command2IsSkill) {
        this.command2IsSkill = command2IsSkill;
    }

    public IntegerFilter getResult() {
        return result;
    }

    public void setResult(IntegerFilter result) {
        this.result = result;
    }

    public IntegerFilter getShootResult() {
        return shootResult;
    }

    public void setShootResult(IntegerFilter shootResult) {
        this.shootResult = shootResult;
    }

    public IntegerFilter getIsThrown() {
        return isThrown;
    }

    public void setIsThrown(IntegerFilter isThrown) {
        this.isThrown = isThrown;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MEncountersCutCriteria that = (MEncountersCutCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(condition, that.condition) &&
            Objects.equals(ballFloatCondition, that.ballFloatCondition) &&
            Objects.equals(command1Type, that.command1Type) &&
            Objects.equals(command1IsSkill, that.command1IsSkill) &&
            Objects.equals(command2Type, that.command2Type) &&
            Objects.equals(command2IsSkill, that.command2IsSkill) &&
            Objects.equals(result, that.result) &&
            Objects.equals(shootResult, that.shootResult) &&
            Objects.equals(isThrown, that.isThrown);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        condition,
        ballFloatCondition,
        command1Type,
        command1IsSkill,
        command2Type,
        command2IsSkill,
        result,
        shootResult,
        isThrown
        );
    }

    @Override
    public String toString() {
        return "MEncountersCutCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (condition != null ? "condition=" + condition + ", " : "") +
                (ballFloatCondition != null ? "ballFloatCondition=" + ballFloatCondition + ", " : "") +
                (command1Type != null ? "command1Type=" + command1Type + ", " : "") +
                (command1IsSkill != null ? "command1IsSkill=" + command1IsSkill + ", " : "") +
                (command2Type != null ? "command2Type=" + command2Type + ", " : "") +
                (command2IsSkill != null ? "command2IsSkill=" + command2IsSkill + ", " : "") +
                (result != null ? "result=" + result + ", " : "") +
                (shootResult != null ? "shootResult=" + shootResult + ", " : "") +
                (isThrown != null ? "isThrown=" + isThrown + ", " : "") +
            "}";
    }

}
