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
 * Criteria class for the {@link io.shm.tsubasa.domain.MAreaActionWeight} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MAreaActionWeightResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-area-action-weights?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MAreaActionWeightCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter areaType;

    private IntegerFilter dribbleRate;

    private IntegerFilter passingRate;

    private IntegerFilter onetwoRate;

    private IntegerFilter shootRate;

    private IntegerFilter volleyShootRate;

    private IntegerFilter headingShootRate;

    private IntegerFilter tackleRate;

    private IntegerFilter blockRate;

    private IntegerFilter passCutRate;

    private IntegerFilter clearRate;

    private IntegerFilter competeRate;

    private IntegerFilter trapRate;

    public MAreaActionWeightCriteria(){
    }

    public MAreaActionWeightCriteria(MAreaActionWeightCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.areaType = other.areaType == null ? null : other.areaType.copy();
        this.dribbleRate = other.dribbleRate == null ? null : other.dribbleRate.copy();
        this.passingRate = other.passingRate == null ? null : other.passingRate.copy();
        this.onetwoRate = other.onetwoRate == null ? null : other.onetwoRate.copy();
        this.shootRate = other.shootRate == null ? null : other.shootRate.copy();
        this.volleyShootRate = other.volleyShootRate == null ? null : other.volleyShootRate.copy();
        this.headingShootRate = other.headingShootRate == null ? null : other.headingShootRate.copy();
        this.tackleRate = other.tackleRate == null ? null : other.tackleRate.copy();
        this.blockRate = other.blockRate == null ? null : other.blockRate.copy();
        this.passCutRate = other.passCutRate == null ? null : other.passCutRate.copy();
        this.clearRate = other.clearRate == null ? null : other.clearRate.copy();
        this.competeRate = other.competeRate == null ? null : other.competeRate.copy();
        this.trapRate = other.trapRate == null ? null : other.trapRate.copy();
    }

    @Override
    public MAreaActionWeightCriteria copy() {
        return new MAreaActionWeightCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getAreaType() {
        return areaType;
    }

    public void setAreaType(IntegerFilter areaType) {
        this.areaType = areaType;
    }

    public IntegerFilter getDribbleRate() {
        return dribbleRate;
    }

    public void setDribbleRate(IntegerFilter dribbleRate) {
        this.dribbleRate = dribbleRate;
    }

    public IntegerFilter getPassingRate() {
        return passingRate;
    }

    public void setPassingRate(IntegerFilter passingRate) {
        this.passingRate = passingRate;
    }

    public IntegerFilter getOnetwoRate() {
        return onetwoRate;
    }

    public void setOnetwoRate(IntegerFilter onetwoRate) {
        this.onetwoRate = onetwoRate;
    }

    public IntegerFilter getShootRate() {
        return shootRate;
    }

    public void setShootRate(IntegerFilter shootRate) {
        this.shootRate = shootRate;
    }

    public IntegerFilter getVolleyShootRate() {
        return volleyShootRate;
    }

    public void setVolleyShootRate(IntegerFilter volleyShootRate) {
        this.volleyShootRate = volleyShootRate;
    }

    public IntegerFilter getHeadingShootRate() {
        return headingShootRate;
    }

    public void setHeadingShootRate(IntegerFilter headingShootRate) {
        this.headingShootRate = headingShootRate;
    }

    public IntegerFilter getTackleRate() {
        return tackleRate;
    }

    public void setTackleRate(IntegerFilter tackleRate) {
        this.tackleRate = tackleRate;
    }

    public IntegerFilter getBlockRate() {
        return blockRate;
    }

    public void setBlockRate(IntegerFilter blockRate) {
        this.blockRate = blockRate;
    }

    public IntegerFilter getPassCutRate() {
        return passCutRate;
    }

    public void setPassCutRate(IntegerFilter passCutRate) {
        this.passCutRate = passCutRate;
    }

    public IntegerFilter getClearRate() {
        return clearRate;
    }

    public void setClearRate(IntegerFilter clearRate) {
        this.clearRate = clearRate;
    }

    public IntegerFilter getCompeteRate() {
        return competeRate;
    }

    public void setCompeteRate(IntegerFilter competeRate) {
        this.competeRate = competeRate;
    }

    public IntegerFilter getTrapRate() {
        return trapRate;
    }

    public void setTrapRate(IntegerFilter trapRate) {
        this.trapRate = trapRate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MAreaActionWeightCriteria that = (MAreaActionWeightCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(areaType, that.areaType) &&
            Objects.equals(dribbleRate, that.dribbleRate) &&
            Objects.equals(passingRate, that.passingRate) &&
            Objects.equals(onetwoRate, that.onetwoRate) &&
            Objects.equals(shootRate, that.shootRate) &&
            Objects.equals(volleyShootRate, that.volleyShootRate) &&
            Objects.equals(headingShootRate, that.headingShootRate) &&
            Objects.equals(tackleRate, that.tackleRate) &&
            Objects.equals(blockRate, that.blockRate) &&
            Objects.equals(passCutRate, that.passCutRate) &&
            Objects.equals(clearRate, that.clearRate) &&
            Objects.equals(competeRate, that.competeRate) &&
            Objects.equals(trapRate, that.trapRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        areaType,
        dribbleRate,
        passingRate,
        onetwoRate,
        shootRate,
        volleyShootRate,
        headingShootRate,
        tackleRate,
        blockRate,
        passCutRate,
        clearRate,
        competeRate,
        trapRate
        );
    }

    @Override
    public String toString() {
        return "MAreaActionWeightCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (areaType != null ? "areaType=" + areaType + ", " : "") +
                (dribbleRate != null ? "dribbleRate=" + dribbleRate + ", " : "") +
                (passingRate != null ? "passingRate=" + passingRate + ", " : "") +
                (onetwoRate != null ? "onetwoRate=" + onetwoRate + ", " : "") +
                (shootRate != null ? "shootRate=" + shootRate + ", " : "") +
                (volleyShootRate != null ? "volleyShootRate=" + volleyShootRate + ", " : "") +
                (headingShootRate != null ? "headingShootRate=" + headingShootRate + ", " : "") +
                (tackleRate != null ? "tackleRate=" + tackleRate + ", " : "") +
                (blockRate != null ? "blockRate=" + blockRate + ", " : "") +
                (passCutRate != null ? "passCutRate=" + passCutRate + ", " : "") +
                (clearRate != null ? "clearRate=" + clearRate + ", " : "") +
                (competeRate != null ? "competeRate=" + competeRate + ", " : "") +
                (trapRate != null ? "trapRate=" + trapRate + ", " : "") +
            "}";
    }

}
