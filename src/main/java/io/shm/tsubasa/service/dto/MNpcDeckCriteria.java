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
 * Criteria class for the {@link io.shm.tsubasa.domain.MNpcDeck} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MNpcDeckResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-npc-decks?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MNpcDeckCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter uniformBottomFp;

    private IntegerFilter uniformUpFp;

    private IntegerFilter uniformBottomGk;

    private IntegerFilter uniformUpGk;

    private IntegerFilter formationId;

    private IntegerFilter captainCardId;

    private IntegerFilter teamEffect1CardId;

    private IntegerFilter teamEffect2CardId;

    private IntegerFilter teamEffect3CardId;

    private IntegerFilter npcCardId1;

    private IntegerFilter npcCardId2;

    private IntegerFilter npcCardId3;

    private IntegerFilter npcCardId4;

    private IntegerFilter npcCardId5;

    private IntegerFilter npcCardId6;

    private IntegerFilter npcCardId7;

    private IntegerFilter npcCardId8;

    private IntegerFilter npcCardId9;

    private IntegerFilter npcCardId10;

    private IntegerFilter npcCardId11;

    private IntegerFilter tick;

    private LongFilter idId;

    private LongFilter mDummyOpponentId;

    public MNpcDeckCriteria(){
    }

    public MNpcDeckCriteria(MNpcDeckCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.uniformBottomFp = other.uniformBottomFp == null ? null : other.uniformBottomFp.copy();
        this.uniformUpFp = other.uniformUpFp == null ? null : other.uniformUpFp.copy();
        this.uniformBottomGk = other.uniformBottomGk == null ? null : other.uniformBottomGk.copy();
        this.uniformUpGk = other.uniformUpGk == null ? null : other.uniformUpGk.copy();
        this.formationId = other.formationId == null ? null : other.formationId.copy();
        this.captainCardId = other.captainCardId == null ? null : other.captainCardId.copy();
        this.teamEffect1CardId = other.teamEffect1CardId == null ? null : other.teamEffect1CardId.copy();
        this.teamEffect2CardId = other.teamEffect2CardId == null ? null : other.teamEffect2CardId.copy();
        this.teamEffect3CardId = other.teamEffect3CardId == null ? null : other.teamEffect3CardId.copy();
        this.npcCardId1 = other.npcCardId1 == null ? null : other.npcCardId1.copy();
        this.npcCardId2 = other.npcCardId2 == null ? null : other.npcCardId2.copy();
        this.npcCardId3 = other.npcCardId3 == null ? null : other.npcCardId3.copy();
        this.npcCardId4 = other.npcCardId4 == null ? null : other.npcCardId4.copy();
        this.npcCardId5 = other.npcCardId5 == null ? null : other.npcCardId5.copy();
        this.npcCardId6 = other.npcCardId6 == null ? null : other.npcCardId6.copy();
        this.npcCardId7 = other.npcCardId7 == null ? null : other.npcCardId7.copy();
        this.npcCardId8 = other.npcCardId8 == null ? null : other.npcCardId8.copy();
        this.npcCardId9 = other.npcCardId9 == null ? null : other.npcCardId9.copy();
        this.npcCardId10 = other.npcCardId10 == null ? null : other.npcCardId10.copy();
        this.npcCardId11 = other.npcCardId11 == null ? null : other.npcCardId11.copy();
        this.tick = other.tick == null ? null : other.tick.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
        this.mDummyOpponentId = other.mDummyOpponentId == null ? null : other.mDummyOpponentId.copy();
    }

    @Override
    public MNpcDeckCriteria copy() {
        return new MNpcDeckCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getUniformBottomFp() {
        return uniformBottomFp;
    }

    public void setUniformBottomFp(IntegerFilter uniformBottomFp) {
        this.uniformBottomFp = uniformBottomFp;
    }

    public IntegerFilter getUniformUpFp() {
        return uniformUpFp;
    }

    public void setUniformUpFp(IntegerFilter uniformUpFp) {
        this.uniformUpFp = uniformUpFp;
    }

    public IntegerFilter getUniformBottomGk() {
        return uniformBottomGk;
    }

    public void setUniformBottomGk(IntegerFilter uniformBottomGk) {
        this.uniformBottomGk = uniformBottomGk;
    }

    public IntegerFilter getUniformUpGk() {
        return uniformUpGk;
    }

    public void setUniformUpGk(IntegerFilter uniformUpGk) {
        this.uniformUpGk = uniformUpGk;
    }

    public IntegerFilter getFormationId() {
        return formationId;
    }

    public void setFormationId(IntegerFilter formationId) {
        this.formationId = formationId;
    }

    public IntegerFilter getCaptainCardId() {
        return captainCardId;
    }

    public void setCaptainCardId(IntegerFilter captainCardId) {
        this.captainCardId = captainCardId;
    }

    public IntegerFilter getTeamEffect1CardId() {
        return teamEffect1CardId;
    }

    public void setTeamEffect1CardId(IntegerFilter teamEffect1CardId) {
        this.teamEffect1CardId = teamEffect1CardId;
    }

    public IntegerFilter getTeamEffect2CardId() {
        return teamEffect2CardId;
    }

    public void setTeamEffect2CardId(IntegerFilter teamEffect2CardId) {
        this.teamEffect2CardId = teamEffect2CardId;
    }

    public IntegerFilter getTeamEffect3CardId() {
        return teamEffect3CardId;
    }

    public void setTeamEffect3CardId(IntegerFilter teamEffect3CardId) {
        this.teamEffect3CardId = teamEffect3CardId;
    }

    public IntegerFilter getNpcCardId1() {
        return npcCardId1;
    }

    public void setNpcCardId1(IntegerFilter npcCardId1) {
        this.npcCardId1 = npcCardId1;
    }

    public IntegerFilter getNpcCardId2() {
        return npcCardId2;
    }

    public void setNpcCardId2(IntegerFilter npcCardId2) {
        this.npcCardId2 = npcCardId2;
    }

    public IntegerFilter getNpcCardId3() {
        return npcCardId3;
    }

    public void setNpcCardId3(IntegerFilter npcCardId3) {
        this.npcCardId3 = npcCardId3;
    }

    public IntegerFilter getNpcCardId4() {
        return npcCardId4;
    }

    public void setNpcCardId4(IntegerFilter npcCardId4) {
        this.npcCardId4 = npcCardId4;
    }

    public IntegerFilter getNpcCardId5() {
        return npcCardId5;
    }

    public void setNpcCardId5(IntegerFilter npcCardId5) {
        this.npcCardId5 = npcCardId5;
    }

    public IntegerFilter getNpcCardId6() {
        return npcCardId6;
    }

    public void setNpcCardId6(IntegerFilter npcCardId6) {
        this.npcCardId6 = npcCardId6;
    }

    public IntegerFilter getNpcCardId7() {
        return npcCardId7;
    }

    public void setNpcCardId7(IntegerFilter npcCardId7) {
        this.npcCardId7 = npcCardId7;
    }

    public IntegerFilter getNpcCardId8() {
        return npcCardId8;
    }

    public void setNpcCardId8(IntegerFilter npcCardId8) {
        this.npcCardId8 = npcCardId8;
    }

    public IntegerFilter getNpcCardId9() {
        return npcCardId9;
    }

    public void setNpcCardId9(IntegerFilter npcCardId9) {
        this.npcCardId9 = npcCardId9;
    }

    public IntegerFilter getNpcCardId10() {
        return npcCardId10;
    }

    public void setNpcCardId10(IntegerFilter npcCardId10) {
        this.npcCardId10 = npcCardId10;
    }

    public IntegerFilter getNpcCardId11() {
        return npcCardId11;
    }

    public void setNpcCardId11(IntegerFilter npcCardId11) {
        this.npcCardId11 = npcCardId11;
    }

    public IntegerFilter getTick() {
        return tick;
    }

    public void setTick(IntegerFilter tick) {
        this.tick = tick;
    }

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }

    public LongFilter getMDummyOpponentId() {
        return mDummyOpponentId;
    }

    public void setMDummyOpponentId(LongFilter mDummyOpponentId) {
        this.mDummyOpponentId = mDummyOpponentId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MNpcDeckCriteria that = (MNpcDeckCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(uniformBottomFp, that.uniformBottomFp) &&
            Objects.equals(uniformUpFp, that.uniformUpFp) &&
            Objects.equals(uniformBottomGk, that.uniformBottomGk) &&
            Objects.equals(uniformUpGk, that.uniformUpGk) &&
            Objects.equals(formationId, that.formationId) &&
            Objects.equals(captainCardId, that.captainCardId) &&
            Objects.equals(teamEffect1CardId, that.teamEffect1CardId) &&
            Objects.equals(teamEffect2CardId, that.teamEffect2CardId) &&
            Objects.equals(teamEffect3CardId, that.teamEffect3CardId) &&
            Objects.equals(npcCardId1, that.npcCardId1) &&
            Objects.equals(npcCardId2, that.npcCardId2) &&
            Objects.equals(npcCardId3, that.npcCardId3) &&
            Objects.equals(npcCardId4, that.npcCardId4) &&
            Objects.equals(npcCardId5, that.npcCardId5) &&
            Objects.equals(npcCardId6, that.npcCardId6) &&
            Objects.equals(npcCardId7, that.npcCardId7) &&
            Objects.equals(npcCardId8, that.npcCardId8) &&
            Objects.equals(npcCardId9, that.npcCardId9) &&
            Objects.equals(npcCardId10, that.npcCardId10) &&
            Objects.equals(npcCardId11, that.npcCardId11) &&
            Objects.equals(tick, that.tick) &&
            Objects.equals(idId, that.idId) &&
            Objects.equals(mDummyOpponentId, that.mDummyOpponentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uniformBottomFp,
        uniformUpFp,
        uniformBottomGk,
        uniformUpGk,
        formationId,
        captainCardId,
        teamEffect1CardId,
        teamEffect2CardId,
        teamEffect3CardId,
        npcCardId1,
        npcCardId2,
        npcCardId3,
        npcCardId4,
        npcCardId5,
        npcCardId6,
        npcCardId7,
        npcCardId8,
        npcCardId9,
        npcCardId10,
        npcCardId11,
        tick,
        idId,
        mDummyOpponentId
        );
    }

    @Override
    public String toString() {
        return "MNpcDeckCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uniformBottomFp != null ? "uniformBottomFp=" + uniformBottomFp + ", " : "") +
                (uniformUpFp != null ? "uniformUpFp=" + uniformUpFp + ", " : "") +
                (uniformBottomGk != null ? "uniformBottomGk=" + uniformBottomGk + ", " : "") +
                (uniformUpGk != null ? "uniformUpGk=" + uniformUpGk + ", " : "") +
                (formationId != null ? "formationId=" + formationId + ", " : "") +
                (captainCardId != null ? "captainCardId=" + captainCardId + ", " : "") +
                (teamEffect1CardId != null ? "teamEffect1CardId=" + teamEffect1CardId + ", " : "") +
                (teamEffect2CardId != null ? "teamEffect2CardId=" + teamEffect2CardId + ", " : "") +
                (teamEffect3CardId != null ? "teamEffect3CardId=" + teamEffect3CardId + ", " : "") +
                (npcCardId1 != null ? "npcCardId1=" + npcCardId1 + ", " : "") +
                (npcCardId2 != null ? "npcCardId2=" + npcCardId2 + ", " : "") +
                (npcCardId3 != null ? "npcCardId3=" + npcCardId3 + ", " : "") +
                (npcCardId4 != null ? "npcCardId4=" + npcCardId4 + ", " : "") +
                (npcCardId5 != null ? "npcCardId5=" + npcCardId5 + ", " : "") +
                (npcCardId6 != null ? "npcCardId6=" + npcCardId6 + ", " : "") +
                (npcCardId7 != null ? "npcCardId7=" + npcCardId7 + ", " : "") +
                (npcCardId8 != null ? "npcCardId8=" + npcCardId8 + ", " : "") +
                (npcCardId9 != null ? "npcCardId9=" + npcCardId9 + ", " : "") +
                (npcCardId10 != null ? "npcCardId10=" + npcCardId10 + ", " : "") +
                (npcCardId11 != null ? "npcCardId11=" + npcCardId11 + ", " : "") +
                (tick != null ? "tick=" + tick + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
                (mDummyOpponentId != null ? "mDummyOpponentId=" + mDummyOpponentId + ", " : "") +
            "}";
    }

}
