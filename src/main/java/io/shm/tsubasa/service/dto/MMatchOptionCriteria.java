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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMatchOption} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMatchOptionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-match-options?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMatchOptionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter passiveEffectId;

    private IntegerFilter passiveEffectValue;

    private IntegerFilter activateFullPowerType;

    private IntegerFilter attributeMagnification;

    private IntegerFilter useStaminaMagnification;

    private IntegerFilter isIgnoreTeamSkill;

    private IntegerFilter staminaInfinityType;

    private LongFilter mpassiveeffectrangeId;

    private LongFilter mLeagueRegulationId;

    private LongFilter mPvpRegulationId;

    public MMatchOptionCriteria(){
    }

    public MMatchOptionCriteria(MMatchOptionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.passiveEffectId = other.passiveEffectId == null ? null : other.passiveEffectId.copy();
        this.passiveEffectValue = other.passiveEffectValue == null ? null : other.passiveEffectValue.copy();
        this.activateFullPowerType = other.activateFullPowerType == null ? null : other.activateFullPowerType.copy();
        this.attributeMagnification = other.attributeMagnification == null ? null : other.attributeMagnification.copy();
        this.useStaminaMagnification = other.useStaminaMagnification == null ? null : other.useStaminaMagnification.copy();
        this.isIgnoreTeamSkill = other.isIgnoreTeamSkill == null ? null : other.isIgnoreTeamSkill.copy();
        this.staminaInfinityType = other.staminaInfinityType == null ? null : other.staminaInfinityType.copy();
        this.mpassiveeffectrangeId = other.mpassiveeffectrangeId == null ? null : other.mpassiveeffectrangeId.copy();
        this.mLeagueRegulationId = other.mLeagueRegulationId == null ? null : other.mLeagueRegulationId.copy();
        this.mPvpRegulationId = other.mPvpRegulationId == null ? null : other.mPvpRegulationId.copy();
    }

    @Override
    public MMatchOptionCriteria copy() {
        return new MMatchOptionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getPassiveEffectId() {
        return passiveEffectId;
    }

    public void setPassiveEffectId(IntegerFilter passiveEffectId) {
        this.passiveEffectId = passiveEffectId;
    }

    public IntegerFilter getPassiveEffectValue() {
        return passiveEffectValue;
    }

    public void setPassiveEffectValue(IntegerFilter passiveEffectValue) {
        this.passiveEffectValue = passiveEffectValue;
    }

    public IntegerFilter getActivateFullPowerType() {
        return activateFullPowerType;
    }

    public void setActivateFullPowerType(IntegerFilter activateFullPowerType) {
        this.activateFullPowerType = activateFullPowerType;
    }

    public IntegerFilter getAttributeMagnification() {
        return attributeMagnification;
    }

    public void setAttributeMagnification(IntegerFilter attributeMagnification) {
        this.attributeMagnification = attributeMagnification;
    }

    public IntegerFilter getUseStaminaMagnification() {
        return useStaminaMagnification;
    }

    public void setUseStaminaMagnification(IntegerFilter useStaminaMagnification) {
        this.useStaminaMagnification = useStaminaMagnification;
    }

    public IntegerFilter getIsIgnoreTeamSkill() {
        return isIgnoreTeamSkill;
    }

    public void setIsIgnoreTeamSkill(IntegerFilter isIgnoreTeamSkill) {
        this.isIgnoreTeamSkill = isIgnoreTeamSkill;
    }

    public IntegerFilter getStaminaInfinityType() {
        return staminaInfinityType;
    }

    public void setStaminaInfinityType(IntegerFilter staminaInfinityType) {
        this.staminaInfinityType = staminaInfinityType;
    }

    public LongFilter getMpassiveeffectrangeId() {
        return mpassiveeffectrangeId;
    }

    public void setMpassiveeffectrangeId(LongFilter mpassiveeffectrangeId) {
        this.mpassiveeffectrangeId = mpassiveeffectrangeId;
    }

    public LongFilter getMLeagueRegulationId() {
        return mLeagueRegulationId;
    }

    public void setMLeagueRegulationId(LongFilter mLeagueRegulationId) {
        this.mLeagueRegulationId = mLeagueRegulationId;
    }

    public LongFilter getMPvpRegulationId() {
        return mPvpRegulationId;
    }

    public void setMPvpRegulationId(LongFilter mPvpRegulationId) {
        this.mPvpRegulationId = mPvpRegulationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMatchOptionCriteria that = (MMatchOptionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(passiveEffectId, that.passiveEffectId) &&
            Objects.equals(passiveEffectValue, that.passiveEffectValue) &&
            Objects.equals(activateFullPowerType, that.activateFullPowerType) &&
            Objects.equals(attributeMagnification, that.attributeMagnification) &&
            Objects.equals(useStaminaMagnification, that.useStaminaMagnification) &&
            Objects.equals(isIgnoreTeamSkill, that.isIgnoreTeamSkill) &&
            Objects.equals(staminaInfinityType, that.staminaInfinityType) &&
            Objects.equals(mpassiveeffectrangeId, that.mpassiveeffectrangeId) &&
            Objects.equals(mLeagueRegulationId, that.mLeagueRegulationId) &&
            Objects.equals(mPvpRegulationId, that.mPvpRegulationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        passiveEffectId,
        passiveEffectValue,
        activateFullPowerType,
        attributeMagnification,
        useStaminaMagnification,
        isIgnoreTeamSkill,
        staminaInfinityType,
        mpassiveeffectrangeId,
        mLeagueRegulationId,
        mPvpRegulationId
        );
    }

    @Override
    public String toString() {
        return "MMatchOptionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (passiveEffectId != null ? "passiveEffectId=" + passiveEffectId + ", " : "") +
                (passiveEffectValue != null ? "passiveEffectValue=" + passiveEffectValue + ", " : "") +
                (activateFullPowerType != null ? "activateFullPowerType=" + activateFullPowerType + ", " : "") +
                (attributeMagnification != null ? "attributeMagnification=" + attributeMagnification + ", " : "") +
                (useStaminaMagnification != null ? "useStaminaMagnification=" + useStaminaMagnification + ", " : "") +
                (isIgnoreTeamSkill != null ? "isIgnoreTeamSkill=" + isIgnoreTeamSkill + ", " : "") +
                (staminaInfinityType != null ? "staminaInfinityType=" + staminaInfinityType + ", " : "") +
                (mpassiveeffectrangeId != null ? "mpassiveeffectrangeId=" + mpassiveeffectrangeId + ", " : "") +
                (mLeagueRegulationId != null ? "mLeagueRegulationId=" + mLeagueRegulationId + ", " : "") +
                (mPvpRegulationId != null ? "mPvpRegulationId=" + mPvpRegulationId + ", " : "") +
            "}";
    }

}
