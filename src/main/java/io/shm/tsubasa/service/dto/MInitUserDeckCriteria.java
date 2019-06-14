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
 * Criteria class for the {@link io.shm.tsubasa.domain.MInitUserDeck} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MInitUserDeckResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-init-user-decks?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MInitUserDeckCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter deckId;

    private IntegerFilter formationId;

    private IntegerFilter captainCardId;

    private IntegerFilter gkCardId;

    private IntegerFilter fp1CardId;

    private IntegerFilter fp2CardId;

    private IntegerFilter fp3CardId;

    private IntegerFilter fp4CardId;

    private IntegerFilter fp5CardId;

    private IntegerFilter fp6CardId;

    private IntegerFilter fp7CardId;

    private IntegerFilter fp8CardId;

    private IntegerFilter fp9CardId;

    private IntegerFilter fp10CardId;

    private IntegerFilter sub1CardId;

    private IntegerFilter sub2CardId;

    private IntegerFilter sub3CardId;

    private IntegerFilter sub4CardId;

    private IntegerFilter sub5CardId;

    private IntegerFilter teamEffect1CardId;

    private IntegerFilter teamEffect2CardId;

    private IntegerFilter teamEffect3CardId;

    public MInitUserDeckCriteria(){
    }

    public MInitUserDeckCriteria(MInitUserDeckCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.deckId = other.deckId == null ? null : other.deckId.copy();
        this.formationId = other.formationId == null ? null : other.formationId.copy();
        this.captainCardId = other.captainCardId == null ? null : other.captainCardId.copy();
        this.gkCardId = other.gkCardId == null ? null : other.gkCardId.copy();
        this.fp1CardId = other.fp1CardId == null ? null : other.fp1CardId.copy();
        this.fp2CardId = other.fp2CardId == null ? null : other.fp2CardId.copy();
        this.fp3CardId = other.fp3CardId == null ? null : other.fp3CardId.copy();
        this.fp4CardId = other.fp4CardId == null ? null : other.fp4CardId.copy();
        this.fp5CardId = other.fp5CardId == null ? null : other.fp5CardId.copy();
        this.fp6CardId = other.fp6CardId == null ? null : other.fp6CardId.copy();
        this.fp7CardId = other.fp7CardId == null ? null : other.fp7CardId.copy();
        this.fp8CardId = other.fp8CardId == null ? null : other.fp8CardId.copy();
        this.fp9CardId = other.fp9CardId == null ? null : other.fp9CardId.copy();
        this.fp10CardId = other.fp10CardId == null ? null : other.fp10CardId.copy();
        this.sub1CardId = other.sub1CardId == null ? null : other.sub1CardId.copy();
        this.sub2CardId = other.sub2CardId == null ? null : other.sub2CardId.copy();
        this.sub3CardId = other.sub3CardId == null ? null : other.sub3CardId.copy();
        this.sub4CardId = other.sub4CardId == null ? null : other.sub4CardId.copy();
        this.sub5CardId = other.sub5CardId == null ? null : other.sub5CardId.copy();
        this.teamEffect1CardId = other.teamEffect1CardId == null ? null : other.teamEffect1CardId.copy();
        this.teamEffect2CardId = other.teamEffect2CardId == null ? null : other.teamEffect2CardId.copy();
        this.teamEffect3CardId = other.teamEffect3CardId == null ? null : other.teamEffect3CardId.copy();
    }

    @Override
    public MInitUserDeckCriteria copy() {
        return new MInitUserDeckCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getDeckId() {
        return deckId;
    }

    public void setDeckId(IntegerFilter deckId) {
        this.deckId = deckId;
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

    public IntegerFilter getGkCardId() {
        return gkCardId;
    }

    public void setGkCardId(IntegerFilter gkCardId) {
        this.gkCardId = gkCardId;
    }

    public IntegerFilter getFp1CardId() {
        return fp1CardId;
    }

    public void setFp1CardId(IntegerFilter fp1CardId) {
        this.fp1CardId = fp1CardId;
    }

    public IntegerFilter getFp2CardId() {
        return fp2CardId;
    }

    public void setFp2CardId(IntegerFilter fp2CardId) {
        this.fp2CardId = fp2CardId;
    }

    public IntegerFilter getFp3CardId() {
        return fp3CardId;
    }

    public void setFp3CardId(IntegerFilter fp3CardId) {
        this.fp3CardId = fp3CardId;
    }

    public IntegerFilter getFp4CardId() {
        return fp4CardId;
    }

    public void setFp4CardId(IntegerFilter fp4CardId) {
        this.fp4CardId = fp4CardId;
    }

    public IntegerFilter getFp5CardId() {
        return fp5CardId;
    }

    public void setFp5CardId(IntegerFilter fp5CardId) {
        this.fp5CardId = fp5CardId;
    }

    public IntegerFilter getFp6CardId() {
        return fp6CardId;
    }

    public void setFp6CardId(IntegerFilter fp6CardId) {
        this.fp6CardId = fp6CardId;
    }

    public IntegerFilter getFp7CardId() {
        return fp7CardId;
    }

    public void setFp7CardId(IntegerFilter fp7CardId) {
        this.fp7CardId = fp7CardId;
    }

    public IntegerFilter getFp8CardId() {
        return fp8CardId;
    }

    public void setFp8CardId(IntegerFilter fp8CardId) {
        this.fp8CardId = fp8CardId;
    }

    public IntegerFilter getFp9CardId() {
        return fp9CardId;
    }

    public void setFp9CardId(IntegerFilter fp9CardId) {
        this.fp9CardId = fp9CardId;
    }

    public IntegerFilter getFp10CardId() {
        return fp10CardId;
    }

    public void setFp10CardId(IntegerFilter fp10CardId) {
        this.fp10CardId = fp10CardId;
    }

    public IntegerFilter getSub1CardId() {
        return sub1CardId;
    }

    public void setSub1CardId(IntegerFilter sub1CardId) {
        this.sub1CardId = sub1CardId;
    }

    public IntegerFilter getSub2CardId() {
        return sub2CardId;
    }

    public void setSub2CardId(IntegerFilter sub2CardId) {
        this.sub2CardId = sub2CardId;
    }

    public IntegerFilter getSub3CardId() {
        return sub3CardId;
    }

    public void setSub3CardId(IntegerFilter sub3CardId) {
        this.sub3CardId = sub3CardId;
    }

    public IntegerFilter getSub4CardId() {
        return sub4CardId;
    }

    public void setSub4CardId(IntegerFilter sub4CardId) {
        this.sub4CardId = sub4CardId;
    }

    public IntegerFilter getSub5CardId() {
        return sub5CardId;
    }

    public void setSub5CardId(IntegerFilter sub5CardId) {
        this.sub5CardId = sub5CardId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MInitUserDeckCriteria that = (MInitUserDeckCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(deckId, that.deckId) &&
            Objects.equals(formationId, that.formationId) &&
            Objects.equals(captainCardId, that.captainCardId) &&
            Objects.equals(gkCardId, that.gkCardId) &&
            Objects.equals(fp1CardId, that.fp1CardId) &&
            Objects.equals(fp2CardId, that.fp2CardId) &&
            Objects.equals(fp3CardId, that.fp3CardId) &&
            Objects.equals(fp4CardId, that.fp4CardId) &&
            Objects.equals(fp5CardId, that.fp5CardId) &&
            Objects.equals(fp6CardId, that.fp6CardId) &&
            Objects.equals(fp7CardId, that.fp7CardId) &&
            Objects.equals(fp8CardId, that.fp8CardId) &&
            Objects.equals(fp9CardId, that.fp9CardId) &&
            Objects.equals(fp10CardId, that.fp10CardId) &&
            Objects.equals(sub1CardId, that.sub1CardId) &&
            Objects.equals(sub2CardId, that.sub2CardId) &&
            Objects.equals(sub3CardId, that.sub3CardId) &&
            Objects.equals(sub4CardId, that.sub4CardId) &&
            Objects.equals(sub5CardId, that.sub5CardId) &&
            Objects.equals(teamEffect1CardId, that.teamEffect1CardId) &&
            Objects.equals(teamEffect2CardId, that.teamEffect2CardId) &&
            Objects.equals(teamEffect3CardId, that.teamEffect3CardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        deckId,
        formationId,
        captainCardId,
        gkCardId,
        fp1CardId,
        fp2CardId,
        fp3CardId,
        fp4CardId,
        fp5CardId,
        fp6CardId,
        fp7CardId,
        fp8CardId,
        fp9CardId,
        fp10CardId,
        sub1CardId,
        sub2CardId,
        sub3CardId,
        sub4CardId,
        sub5CardId,
        teamEffect1CardId,
        teamEffect2CardId,
        teamEffect3CardId
        );
    }

    @Override
    public String toString() {
        return "MInitUserDeckCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (deckId != null ? "deckId=" + deckId + ", " : "") +
                (formationId != null ? "formationId=" + formationId + ", " : "") +
                (captainCardId != null ? "captainCardId=" + captainCardId + ", " : "") +
                (gkCardId != null ? "gkCardId=" + gkCardId + ", " : "") +
                (fp1CardId != null ? "fp1CardId=" + fp1CardId + ", " : "") +
                (fp2CardId != null ? "fp2CardId=" + fp2CardId + ", " : "") +
                (fp3CardId != null ? "fp3CardId=" + fp3CardId + ", " : "") +
                (fp4CardId != null ? "fp4CardId=" + fp4CardId + ", " : "") +
                (fp5CardId != null ? "fp5CardId=" + fp5CardId + ", " : "") +
                (fp6CardId != null ? "fp6CardId=" + fp6CardId + ", " : "") +
                (fp7CardId != null ? "fp7CardId=" + fp7CardId + ", " : "") +
                (fp8CardId != null ? "fp8CardId=" + fp8CardId + ", " : "") +
                (fp9CardId != null ? "fp9CardId=" + fp9CardId + ", " : "") +
                (fp10CardId != null ? "fp10CardId=" + fp10CardId + ", " : "") +
                (sub1CardId != null ? "sub1CardId=" + sub1CardId + ", " : "") +
                (sub2CardId != null ? "sub2CardId=" + sub2CardId + ", " : "") +
                (sub3CardId != null ? "sub3CardId=" + sub3CardId + ", " : "") +
                (sub4CardId != null ? "sub4CardId=" + sub4CardId + ", " : "") +
                (sub5CardId != null ? "sub5CardId=" + sub5CardId + ", " : "") +
                (teamEffect1CardId != null ? "teamEffect1CardId=" + teamEffect1CardId + ", " : "") +
                (teamEffect2CardId != null ? "teamEffect2CardId=" + teamEffect2CardId + ", " : "") +
                (teamEffect3CardId != null ? "teamEffect3CardId=" + teamEffect3CardId + ", " : "") +
            "}";
    }

}
