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
 * Criteria class for the {@link io.shm.tsubasa.domain.MNpcPersonality} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MNpcPersonalityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-npc-personalities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MNpcPersonalityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter passingTargetRate;

    private IntegerFilter actionSkillRate;

    private IntegerFilter dribbleMagnification;

    private IntegerFilter passingMagnification;

    private IntegerFilter onetwoMagnification;

    private IntegerFilter shootMagnification;

    private IntegerFilter volleyShootMagnification;

    private IntegerFilter headingShootMagnification;

    private IntegerFilter tackleMagnification;

    private IntegerFilter blockMagnification;

    private IntegerFilter passCutMagnification;

    private IntegerFilter clearMagnification;

    private IntegerFilter competeMagnification;

    private IntegerFilter trapMagnification;

    public MNpcPersonalityCriteria(){
    }

    public MNpcPersonalityCriteria(MNpcPersonalityCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.passingTargetRate = other.passingTargetRate == null ? null : other.passingTargetRate.copy();
        this.actionSkillRate = other.actionSkillRate == null ? null : other.actionSkillRate.copy();
        this.dribbleMagnification = other.dribbleMagnification == null ? null : other.dribbleMagnification.copy();
        this.passingMagnification = other.passingMagnification == null ? null : other.passingMagnification.copy();
        this.onetwoMagnification = other.onetwoMagnification == null ? null : other.onetwoMagnification.copy();
        this.shootMagnification = other.shootMagnification == null ? null : other.shootMagnification.copy();
        this.volleyShootMagnification = other.volleyShootMagnification == null ? null : other.volleyShootMagnification.copy();
        this.headingShootMagnification = other.headingShootMagnification == null ? null : other.headingShootMagnification.copy();
        this.tackleMagnification = other.tackleMagnification == null ? null : other.tackleMagnification.copy();
        this.blockMagnification = other.blockMagnification == null ? null : other.blockMagnification.copy();
        this.passCutMagnification = other.passCutMagnification == null ? null : other.passCutMagnification.copy();
        this.clearMagnification = other.clearMagnification == null ? null : other.clearMagnification.copy();
        this.competeMagnification = other.competeMagnification == null ? null : other.competeMagnification.copy();
        this.trapMagnification = other.trapMagnification == null ? null : other.trapMagnification.copy();
    }

    @Override
    public MNpcPersonalityCriteria copy() {
        return new MNpcPersonalityCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getPassingTargetRate() {
        return passingTargetRate;
    }

    public void setPassingTargetRate(IntegerFilter passingTargetRate) {
        this.passingTargetRate = passingTargetRate;
    }

    public IntegerFilter getActionSkillRate() {
        return actionSkillRate;
    }

    public void setActionSkillRate(IntegerFilter actionSkillRate) {
        this.actionSkillRate = actionSkillRate;
    }

    public IntegerFilter getDribbleMagnification() {
        return dribbleMagnification;
    }

    public void setDribbleMagnification(IntegerFilter dribbleMagnification) {
        this.dribbleMagnification = dribbleMagnification;
    }

    public IntegerFilter getPassingMagnification() {
        return passingMagnification;
    }

    public void setPassingMagnification(IntegerFilter passingMagnification) {
        this.passingMagnification = passingMagnification;
    }

    public IntegerFilter getOnetwoMagnification() {
        return onetwoMagnification;
    }

    public void setOnetwoMagnification(IntegerFilter onetwoMagnification) {
        this.onetwoMagnification = onetwoMagnification;
    }

    public IntegerFilter getShootMagnification() {
        return shootMagnification;
    }

    public void setShootMagnification(IntegerFilter shootMagnification) {
        this.shootMagnification = shootMagnification;
    }

    public IntegerFilter getVolleyShootMagnification() {
        return volleyShootMagnification;
    }

    public void setVolleyShootMagnification(IntegerFilter volleyShootMagnification) {
        this.volleyShootMagnification = volleyShootMagnification;
    }

    public IntegerFilter getHeadingShootMagnification() {
        return headingShootMagnification;
    }

    public void setHeadingShootMagnification(IntegerFilter headingShootMagnification) {
        this.headingShootMagnification = headingShootMagnification;
    }

    public IntegerFilter getTackleMagnification() {
        return tackleMagnification;
    }

    public void setTackleMagnification(IntegerFilter tackleMagnification) {
        this.tackleMagnification = tackleMagnification;
    }

    public IntegerFilter getBlockMagnification() {
        return blockMagnification;
    }

    public void setBlockMagnification(IntegerFilter blockMagnification) {
        this.blockMagnification = blockMagnification;
    }

    public IntegerFilter getPassCutMagnification() {
        return passCutMagnification;
    }

    public void setPassCutMagnification(IntegerFilter passCutMagnification) {
        this.passCutMagnification = passCutMagnification;
    }

    public IntegerFilter getClearMagnification() {
        return clearMagnification;
    }

    public void setClearMagnification(IntegerFilter clearMagnification) {
        this.clearMagnification = clearMagnification;
    }

    public IntegerFilter getCompeteMagnification() {
        return competeMagnification;
    }

    public void setCompeteMagnification(IntegerFilter competeMagnification) {
        this.competeMagnification = competeMagnification;
    }

    public IntegerFilter getTrapMagnification() {
        return trapMagnification;
    }

    public void setTrapMagnification(IntegerFilter trapMagnification) {
        this.trapMagnification = trapMagnification;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MNpcPersonalityCriteria that = (MNpcPersonalityCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(passingTargetRate, that.passingTargetRate) &&
            Objects.equals(actionSkillRate, that.actionSkillRate) &&
            Objects.equals(dribbleMagnification, that.dribbleMagnification) &&
            Objects.equals(passingMagnification, that.passingMagnification) &&
            Objects.equals(onetwoMagnification, that.onetwoMagnification) &&
            Objects.equals(shootMagnification, that.shootMagnification) &&
            Objects.equals(volleyShootMagnification, that.volleyShootMagnification) &&
            Objects.equals(headingShootMagnification, that.headingShootMagnification) &&
            Objects.equals(tackleMagnification, that.tackleMagnification) &&
            Objects.equals(blockMagnification, that.blockMagnification) &&
            Objects.equals(passCutMagnification, that.passCutMagnification) &&
            Objects.equals(clearMagnification, that.clearMagnification) &&
            Objects.equals(competeMagnification, that.competeMagnification) &&
            Objects.equals(trapMagnification, that.trapMagnification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        passingTargetRate,
        actionSkillRate,
        dribbleMagnification,
        passingMagnification,
        onetwoMagnification,
        shootMagnification,
        volleyShootMagnification,
        headingShootMagnification,
        tackleMagnification,
        blockMagnification,
        passCutMagnification,
        clearMagnification,
        competeMagnification,
        trapMagnification
        );
    }

    @Override
    public String toString() {
        return "MNpcPersonalityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (passingTargetRate != null ? "passingTargetRate=" + passingTargetRate + ", " : "") +
                (actionSkillRate != null ? "actionSkillRate=" + actionSkillRate + ", " : "") +
                (dribbleMagnification != null ? "dribbleMagnification=" + dribbleMagnification + ", " : "") +
                (passingMagnification != null ? "passingMagnification=" + passingMagnification + ", " : "") +
                (onetwoMagnification != null ? "onetwoMagnification=" + onetwoMagnification + ", " : "") +
                (shootMagnification != null ? "shootMagnification=" + shootMagnification + ", " : "") +
                (volleyShootMagnification != null ? "volleyShootMagnification=" + volleyShootMagnification + ", " : "") +
                (headingShootMagnification != null ? "headingShootMagnification=" + headingShootMagnification + ", " : "") +
                (tackleMagnification != null ? "tackleMagnification=" + tackleMagnification + ", " : "") +
                (blockMagnification != null ? "blockMagnification=" + blockMagnification + ", " : "") +
                (passCutMagnification != null ? "passCutMagnification=" + passCutMagnification + ", " : "") +
                (clearMagnification != null ? "clearMagnification=" + clearMagnification + ", " : "") +
                (competeMagnification != null ? "competeMagnification=" + competeMagnification + ", " : "") +
                (trapMagnification != null ? "trapMagnification=" + trapMagnification + ", " : "") +
            "}";
    }

}
