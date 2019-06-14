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
 * Criteria class for the {@link io.shm.tsubasa.domain.MDummyOpponent} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MDummyOpponentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-dummy-opponents?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MDummyOpponentCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter npcDeckId;

    private IntegerFilter totalParameter;

    private IntegerFilter rank;

    private IntegerFilter emblemId;

    private IntegerFilter fpUniformUpId;

    private IntegerFilter fpUniformBottomId;

    private IntegerFilter gkUniformUpId;

    private IntegerFilter gkUniformBottomId;

    private LongFilter mnpcdeckId;

    public MDummyOpponentCriteria(){
    }

    public MDummyOpponentCriteria(MDummyOpponentCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.npcDeckId = other.npcDeckId == null ? null : other.npcDeckId.copy();
        this.totalParameter = other.totalParameter == null ? null : other.totalParameter.copy();
        this.rank = other.rank == null ? null : other.rank.copy();
        this.emblemId = other.emblemId == null ? null : other.emblemId.copy();
        this.fpUniformUpId = other.fpUniformUpId == null ? null : other.fpUniformUpId.copy();
        this.fpUniformBottomId = other.fpUniformBottomId == null ? null : other.fpUniformBottomId.copy();
        this.gkUniformUpId = other.gkUniformUpId == null ? null : other.gkUniformUpId.copy();
        this.gkUniformBottomId = other.gkUniformBottomId == null ? null : other.gkUniformBottomId.copy();
        this.mnpcdeckId = other.mnpcdeckId == null ? null : other.mnpcdeckId.copy();
    }

    @Override
    public MDummyOpponentCriteria copy() {
        return new MDummyOpponentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getNpcDeckId() {
        return npcDeckId;
    }

    public void setNpcDeckId(IntegerFilter npcDeckId) {
        this.npcDeckId = npcDeckId;
    }

    public IntegerFilter getTotalParameter() {
        return totalParameter;
    }

    public void setTotalParameter(IntegerFilter totalParameter) {
        this.totalParameter = totalParameter;
    }

    public IntegerFilter getRank() {
        return rank;
    }

    public void setRank(IntegerFilter rank) {
        this.rank = rank;
    }

    public IntegerFilter getEmblemId() {
        return emblemId;
    }

    public void setEmblemId(IntegerFilter emblemId) {
        this.emblemId = emblemId;
    }

    public IntegerFilter getFpUniformUpId() {
        return fpUniformUpId;
    }

    public void setFpUniformUpId(IntegerFilter fpUniformUpId) {
        this.fpUniformUpId = fpUniformUpId;
    }

    public IntegerFilter getFpUniformBottomId() {
        return fpUniformBottomId;
    }

    public void setFpUniformBottomId(IntegerFilter fpUniformBottomId) {
        this.fpUniformBottomId = fpUniformBottomId;
    }

    public IntegerFilter getGkUniformUpId() {
        return gkUniformUpId;
    }

    public void setGkUniformUpId(IntegerFilter gkUniformUpId) {
        this.gkUniformUpId = gkUniformUpId;
    }

    public IntegerFilter getGkUniformBottomId() {
        return gkUniformBottomId;
    }

    public void setGkUniformBottomId(IntegerFilter gkUniformBottomId) {
        this.gkUniformBottomId = gkUniformBottomId;
    }

    public LongFilter getMnpcdeckId() {
        return mnpcdeckId;
    }

    public void setMnpcdeckId(LongFilter mnpcdeckId) {
        this.mnpcdeckId = mnpcdeckId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MDummyOpponentCriteria that = (MDummyOpponentCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(npcDeckId, that.npcDeckId) &&
            Objects.equals(totalParameter, that.totalParameter) &&
            Objects.equals(rank, that.rank) &&
            Objects.equals(emblemId, that.emblemId) &&
            Objects.equals(fpUniformUpId, that.fpUniformUpId) &&
            Objects.equals(fpUniformBottomId, that.fpUniformBottomId) &&
            Objects.equals(gkUniformUpId, that.gkUniformUpId) &&
            Objects.equals(gkUniformBottomId, that.gkUniformBottomId) &&
            Objects.equals(mnpcdeckId, that.mnpcdeckId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        npcDeckId,
        totalParameter,
        rank,
        emblemId,
        fpUniformUpId,
        fpUniformBottomId,
        gkUniformUpId,
        gkUniformBottomId,
        mnpcdeckId
        );
    }

    @Override
    public String toString() {
        return "MDummyOpponentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (npcDeckId != null ? "npcDeckId=" + npcDeckId + ", " : "") +
                (totalParameter != null ? "totalParameter=" + totalParameter + ", " : "") +
                (rank != null ? "rank=" + rank + ", " : "") +
                (emblemId != null ? "emblemId=" + emblemId + ", " : "") +
                (fpUniformUpId != null ? "fpUniformUpId=" + fpUniformUpId + ", " : "") +
                (fpUniformBottomId != null ? "fpUniformBottomId=" + fpUniformBottomId + ", " : "") +
                (gkUniformUpId != null ? "gkUniformUpId=" + gkUniformUpId + ", " : "") +
                (gkUniformBottomId != null ? "gkUniformBottomId=" + gkUniformBottomId + ", " : "") +
                (mnpcdeckId != null ? "mnpcdeckId=" + mnpcdeckId + ", " : "") +
            "}";
    }

}
