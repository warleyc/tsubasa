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
 * Criteria class for the {@link io.shm.tsubasa.domain.MAction} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MActionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-actions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MActionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter initialCommand;

    private IntegerFilter rarity;

    private IntegerFilter commandType;

    private IntegerFilter ballConditionGround;

    private IntegerFilter ballConditionLow;

    private IntegerFilter ballConditionHigh;

    private IntegerFilter staminaLvMin;

    private IntegerFilter staminaLvMax;

    private IntegerFilter powerLvMin;

    private IntegerFilter powerLvMax;

    private IntegerFilter blowOffCount;

    private IntegerFilter mShootId;

    private IntegerFilter foulRate;

    private IntegerFilter distanceDecayType;

    private IntegerFilter activateCharacterCount;

    private IntegerFilter actionCutId;

    private IntegerFilter powerupGroup;

    private LongFilter mTargetActionGroupId;

    public MActionCriteria(){
    }

    public MActionCriteria(MActionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.initialCommand = other.initialCommand == null ? null : other.initialCommand.copy();
        this.rarity = other.rarity == null ? null : other.rarity.copy();
        this.commandType = other.commandType == null ? null : other.commandType.copy();
        this.ballConditionGround = other.ballConditionGround == null ? null : other.ballConditionGround.copy();
        this.ballConditionLow = other.ballConditionLow == null ? null : other.ballConditionLow.copy();
        this.ballConditionHigh = other.ballConditionHigh == null ? null : other.ballConditionHigh.copy();
        this.staminaLvMin = other.staminaLvMin == null ? null : other.staminaLvMin.copy();
        this.staminaLvMax = other.staminaLvMax == null ? null : other.staminaLvMax.copy();
        this.powerLvMin = other.powerLvMin == null ? null : other.powerLvMin.copy();
        this.powerLvMax = other.powerLvMax == null ? null : other.powerLvMax.copy();
        this.blowOffCount = other.blowOffCount == null ? null : other.blowOffCount.copy();
        this.mShootId = other.mShootId == null ? null : other.mShootId.copy();
        this.foulRate = other.foulRate == null ? null : other.foulRate.copy();
        this.distanceDecayType = other.distanceDecayType == null ? null : other.distanceDecayType.copy();
        this.activateCharacterCount = other.activateCharacterCount == null ? null : other.activateCharacterCount.copy();
        this.actionCutId = other.actionCutId == null ? null : other.actionCutId.copy();
        this.powerupGroup = other.powerupGroup == null ? null : other.powerupGroup.copy();
        this.mTargetActionGroupId = other.mTargetActionGroupId == null ? null : other.mTargetActionGroupId.copy();
    }

    @Override
    public MActionCriteria copy() {
        return new MActionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getInitialCommand() {
        return initialCommand;
    }

    public void setInitialCommand(IntegerFilter initialCommand) {
        this.initialCommand = initialCommand;
    }

    public IntegerFilter getRarity() {
        return rarity;
    }

    public void setRarity(IntegerFilter rarity) {
        this.rarity = rarity;
    }

    public IntegerFilter getCommandType() {
        return commandType;
    }

    public void setCommandType(IntegerFilter commandType) {
        this.commandType = commandType;
    }

    public IntegerFilter getBallConditionGround() {
        return ballConditionGround;
    }

    public void setBallConditionGround(IntegerFilter ballConditionGround) {
        this.ballConditionGround = ballConditionGround;
    }

    public IntegerFilter getBallConditionLow() {
        return ballConditionLow;
    }

    public void setBallConditionLow(IntegerFilter ballConditionLow) {
        this.ballConditionLow = ballConditionLow;
    }

    public IntegerFilter getBallConditionHigh() {
        return ballConditionHigh;
    }

    public void setBallConditionHigh(IntegerFilter ballConditionHigh) {
        this.ballConditionHigh = ballConditionHigh;
    }

    public IntegerFilter getStaminaLvMin() {
        return staminaLvMin;
    }

    public void setStaminaLvMin(IntegerFilter staminaLvMin) {
        this.staminaLvMin = staminaLvMin;
    }

    public IntegerFilter getStaminaLvMax() {
        return staminaLvMax;
    }

    public void setStaminaLvMax(IntegerFilter staminaLvMax) {
        this.staminaLvMax = staminaLvMax;
    }

    public IntegerFilter getPowerLvMin() {
        return powerLvMin;
    }

    public void setPowerLvMin(IntegerFilter powerLvMin) {
        this.powerLvMin = powerLvMin;
    }

    public IntegerFilter getPowerLvMax() {
        return powerLvMax;
    }

    public void setPowerLvMax(IntegerFilter powerLvMax) {
        this.powerLvMax = powerLvMax;
    }

    public IntegerFilter getBlowOffCount() {
        return blowOffCount;
    }

    public void setBlowOffCount(IntegerFilter blowOffCount) {
        this.blowOffCount = blowOffCount;
    }

    public IntegerFilter getmShootId() {
        return mShootId;
    }

    public void setmShootId(IntegerFilter mShootId) {
        this.mShootId = mShootId;
    }

    public IntegerFilter getFoulRate() {
        return foulRate;
    }

    public void setFoulRate(IntegerFilter foulRate) {
        this.foulRate = foulRate;
    }

    public IntegerFilter getDistanceDecayType() {
        return distanceDecayType;
    }

    public void setDistanceDecayType(IntegerFilter distanceDecayType) {
        this.distanceDecayType = distanceDecayType;
    }

    public IntegerFilter getActivateCharacterCount() {
        return activateCharacterCount;
    }

    public void setActivateCharacterCount(IntegerFilter activateCharacterCount) {
        this.activateCharacterCount = activateCharacterCount;
    }

    public IntegerFilter getActionCutId() {
        return actionCutId;
    }

    public void setActionCutId(IntegerFilter actionCutId) {
        this.actionCutId = actionCutId;
    }

    public IntegerFilter getPowerupGroup() {
        return powerupGroup;
    }

    public void setPowerupGroup(IntegerFilter powerupGroup) {
        this.powerupGroup = powerupGroup;
    }

    public LongFilter getMTargetActionGroupId() {
        return mTargetActionGroupId;
    }

    public void setMTargetActionGroupId(LongFilter mTargetActionGroupId) {
        this.mTargetActionGroupId = mTargetActionGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MActionCriteria that = (MActionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(initialCommand, that.initialCommand) &&
            Objects.equals(rarity, that.rarity) &&
            Objects.equals(commandType, that.commandType) &&
            Objects.equals(ballConditionGround, that.ballConditionGround) &&
            Objects.equals(ballConditionLow, that.ballConditionLow) &&
            Objects.equals(ballConditionHigh, that.ballConditionHigh) &&
            Objects.equals(staminaLvMin, that.staminaLvMin) &&
            Objects.equals(staminaLvMax, that.staminaLvMax) &&
            Objects.equals(powerLvMin, that.powerLvMin) &&
            Objects.equals(powerLvMax, that.powerLvMax) &&
            Objects.equals(blowOffCount, that.blowOffCount) &&
            Objects.equals(mShootId, that.mShootId) &&
            Objects.equals(foulRate, that.foulRate) &&
            Objects.equals(distanceDecayType, that.distanceDecayType) &&
            Objects.equals(activateCharacterCount, that.activateCharacterCount) &&
            Objects.equals(actionCutId, that.actionCutId) &&
            Objects.equals(powerupGroup, that.powerupGroup) &&
            Objects.equals(mTargetActionGroupId, that.mTargetActionGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        initialCommand,
        rarity,
        commandType,
        ballConditionGround,
        ballConditionLow,
        ballConditionHigh,
        staminaLvMin,
        staminaLvMax,
        powerLvMin,
        powerLvMax,
        blowOffCount,
        mShootId,
        foulRate,
        distanceDecayType,
        activateCharacterCount,
        actionCutId,
        powerupGroup,
        mTargetActionGroupId
        );
    }

    @Override
    public String toString() {
        return "MActionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (initialCommand != null ? "initialCommand=" + initialCommand + ", " : "") +
                (rarity != null ? "rarity=" + rarity + ", " : "") +
                (commandType != null ? "commandType=" + commandType + ", " : "") +
                (ballConditionGround != null ? "ballConditionGround=" + ballConditionGround + ", " : "") +
                (ballConditionLow != null ? "ballConditionLow=" + ballConditionLow + ", " : "") +
                (ballConditionHigh != null ? "ballConditionHigh=" + ballConditionHigh + ", " : "") +
                (staminaLvMin != null ? "staminaLvMin=" + staminaLvMin + ", " : "") +
                (staminaLvMax != null ? "staminaLvMax=" + staminaLvMax + ", " : "") +
                (powerLvMin != null ? "powerLvMin=" + powerLvMin + ", " : "") +
                (powerLvMax != null ? "powerLvMax=" + powerLvMax + ", " : "") +
                (blowOffCount != null ? "blowOffCount=" + blowOffCount + ", " : "") +
                (mShootId != null ? "mShootId=" + mShootId + ", " : "") +
                (foulRate != null ? "foulRate=" + foulRate + ", " : "") +
                (distanceDecayType != null ? "distanceDecayType=" + distanceDecayType + ", " : "") +
                (activateCharacterCount != null ? "activateCharacterCount=" + activateCharacterCount + ", " : "") +
                (actionCutId != null ? "actionCutId=" + actionCutId + ", " : "") +
                (powerupGroup != null ? "powerupGroup=" + powerupGroup + ", " : "") +
                (mTargetActionGroupId != null ? "mTargetActionGroupId=" + mTargetActionGroupId + ", " : "") +
            "}";
    }

}
