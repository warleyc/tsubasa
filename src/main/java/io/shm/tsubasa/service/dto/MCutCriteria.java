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
 * Criteria class for the {@link io.shm.tsubasa.domain.MCut} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MCutResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-cuts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MCutCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter fpA;

    private IntegerFilter fpB;

    private IntegerFilter fpC;

    private IntegerFilter fpD;

    private IntegerFilter fpE;

    private IntegerFilter fpF;

    private IntegerFilter gkA;

    private IntegerFilter gkB;

    private IntegerFilter showEnvironmentalEffect;

    private IntegerFilter cutType;

    private IntegerFilter groupId;

    private IntegerFilter isCombiCut;

    public MCutCriteria(){
    }

    public MCutCriteria(MCutCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.fpA = other.fpA == null ? null : other.fpA.copy();
        this.fpB = other.fpB == null ? null : other.fpB.copy();
        this.fpC = other.fpC == null ? null : other.fpC.copy();
        this.fpD = other.fpD == null ? null : other.fpD.copy();
        this.fpE = other.fpE == null ? null : other.fpE.copy();
        this.fpF = other.fpF == null ? null : other.fpF.copy();
        this.gkA = other.gkA == null ? null : other.gkA.copy();
        this.gkB = other.gkB == null ? null : other.gkB.copy();
        this.showEnvironmentalEffect = other.showEnvironmentalEffect == null ? null : other.showEnvironmentalEffect.copy();
        this.cutType = other.cutType == null ? null : other.cutType.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.isCombiCut = other.isCombiCut == null ? null : other.isCombiCut.copy();
    }

    @Override
    public MCutCriteria copy() {
        return new MCutCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getFpA() {
        return fpA;
    }

    public void setFpA(IntegerFilter fpA) {
        this.fpA = fpA;
    }

    public IntegerFilter getFpB() {
        return fpB;
    }

    public void setFpB(IntegerFilter fpB) {
        this.fpB = fpB;
    }

    public IntegerFilter getFpC() {
        return fpC;
    }

    public void setFpC(IntegerFilter fpC) {
        this.fpC = fpC;
    }

    public IntegerFilter getFpD() {
        return fpD;
    }

    public void setFpD(IntegerFilter fpD) {
        this.fpD = fpD;
    }

    public IntegerFilter getFpE() {
        return fpE;
    }

    public void setFpE(IntegerFilter fpE) {
        this.fpE = fpE;
    }

    public IntegerFilter getFpF() {
        return fpF;
    }

    public void setFpF(IntegerFilter fpF) {
        this.fpF = fpF;
    }

    public IntegerFilter getGkA() {
        return gkA;
    }

    public void setGkA(IntegerFilter gkA) {
        this.gkA = gkA;
    }

    public IntegerFilter getGkB() {
        return gkB;
    }

    public void setGkB(IntegerFilter gkB) {
        this.gkB = gkB;
    }

    public IntegerFilter getShowEnvironmentalEffect() {
        return showEnvironmentalEffect;
    }

    public void setShowEnvironmentalEffect(IntegerFilter showEnvironmentalEffect) {
        this.showEnvironmentalEffect = showEnvironmentalEffect;
    }

    public IntegerFilter getCutType() {
        return cutType;
    }

    public void setCutType(IntegerFilter cutType) {
        this.cutType = cutType;
    }

    public IntegerFilter getGroupId() {
        return groupId;
    }

    public void setGroupId(IntegerFilter groupId) {
        this.groupId = groupId;
    }

    public IntegerFilter getIsCombiCut() {
        return isCombiCut;
    }

    public void setIsCombiCut(IntegerFilter isCombiCut) {
        this.isCombiCut = isCombiCut;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MCutCriteria that = (MCutCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fpA, that.fpA) &&
            Objects.equals(fpB, that.fpB) &&
            Objects.equals(fpC, that.fpC) &&
            Objects.equals(fpD, that.fpD) &&
            Objects.equals(fpE, that.fpE) &&
            Objects.equals(fpF, that.fpF) &&
            Objects.equals(gkA, that.gkA) &&
            Objects.equals(gkB, that.gkB) &&
            Objects.equals(showEnvironmentalEffect, that.showEnvironmentalEffect) &&
            Objects.equals(cutType, that.cutType) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(isCombiCut, that.isCombiCut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fpA,
        fpB,
        fpC,
        fpD,
        fpE,
        fpF,
        gkA,
        gkB,
        showEnvironmentalEffect,
        cutType,
        groupId,
        isCombiCut
        );
    }

    @Override
    public String toString() {
        return "MCutCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fpA != null ? "fpA=" + fpA + ", " : "") +
                (fpB != null ? "fpB=" + fpB + ", " : "") +
                (fpC != null ? "fpC=" + fpC + ", " : "") +
                (fpD != null ? "fpD=" + fpD + ", " : "") +
                (fpE != null ? "fpE=" + fpE + ", " : "") +
                (fpF != null ? "fpF=" + fpF + ", " : "") +
                (gkA != null ? "gkA=" + gkA + ", " : "") +
                (gkB != null ? "gkB=" + gkB + ", " : "") +
                (showEnvironmentalEffect != null ? "showEnvironmentalEffect=" + showEnvironmentalEffect + ", " : "") +
                (cutType != null ? "cutType=" + cutType + ", " : "") +
                (groupId != null ? "groupId=" + groupId + ", " : "") +
                (isCombiCut != null ? "isCombiCut=" + isCombiCut + ", " : "") +
            "}";
    }

}
