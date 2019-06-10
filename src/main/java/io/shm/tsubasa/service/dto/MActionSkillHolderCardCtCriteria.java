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
 * Criteria class for the {@link io.shm.tsubasa.domain.MActionSkillHolderCardCt} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MActionSkillHolderCardCtResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-action-skill-holder-card-cts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MActionSkillHolderCardCtCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter targetCharaId;

    private IntegerFilter actionMasterId;

    private IntegerFilter actionSkillExp;

    private LongFilter idId;

    public MActionSkillHolderCardCtCriteria(){
    }

    public MActionSkillHolderCardCtCriteria(MActionSkillHolderCardCtCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.targetCharaId = other.targetCharaId == null ? null : other.targetCharaId.copy();
        this.actionMasterId = other.actionMasterId == null ? null : other.actionMasterId.copy();
        this.actionSkillExp = other.actionSkillExp == null ? null : other.actionSkillExp.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
    }

    @Override
    public MActionSkillHolderCardCtCriteria copy() {
        return new MActionSkillHolderCardCtCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getTargetCharaId() {
        return targetCharaId;
    }

    public void setTargetCharaId(IntegerFilter targetCharaId) {
        this.targetCharaId = targetCharaId;
    }

    public IntegerFilter getActionMasterId() {
        return actionMasterId;
    }

    public void setActionMasterId(IntegerFilter actionMasterId) {
        this.actionMasterId = actionMasterId;
    }

    public IntegerFilter getActionSkillExp() {
        return actionSkillExp;
    }

    public void setActionSkillExp(IntegerFilter actionSkillExp) {
        this.actionSkillExp = actionSkillExp;
    }

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MActionSkillHolderCardCtCriteria that = (MActionSkillHolderCardCtCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(targetCharaId, that.targetCharaId) &&
            Objects.equals(actionMasterId, that.actionMasterId) &&
            Objects.equals(actionSkillExp, that.actionSkillExp) &&
            Objects.equals(idId, that.idId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        targetCharaId,
        actionMasterId,
        actionSkillExp,
        idId
        );
    }

    @Override
    public String toString() {
        return "MActionSkillHolderCardCtCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (targetCharaId != null ? "targetCharaId=" + targetCharaId + ", " : "") +
                (actionMasterId != null ? "actionMasterId=" + actionMasterId + ", " : "") +
                (actionSkillExp != null ? "actionSkillExp=" + actionSkillExp + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
            "}";
    }

}
