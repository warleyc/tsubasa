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
 * Criteria class for the {@link io.shm.tsubasa.domain.MKeeperCutAction} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MKeeperCutActionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-keeper-cut-actions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MKeeperCutActionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter actionCutId;

    private IntegerFilter keeperCutActionType;

    public MKeeperCutActionCriteria(){
    }

    public MKeeperCutActionCriteria(MKeeperCutActionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.actionCutId = other.actionCutId == null ? null : other.actionCutId.copy();
        this.keeperCutActionType = other.keeperCutActionType == null ? null : other.keeperCutActionType.copy();
    }

    @Override
    public MKeeperCutActionCriteria copy() {
        return new MKeeperCutActionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getActionCutId() {
        return actionCutId;
    }

    public void setActionCutId(IntegerFilter actionCutId) {
        this.actionCutId = actionCutId;
    }

    public IntegerFilter getKeeperCutActionType() {
        return keeperCutActionType;
    }

    public void setKeeperCutActionType(IntegerFilter keeperCutActionType) {
        this.keeperCutActionType = keeperCutActionType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MKeeperCutActionCriteria that = (MKeeperCutActionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(actionCutId, that.actionCutId) &&
            Objects.equals(keeperCutActionType, that.keeperCutActionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        actionCutId,
        keeperCutActionType
        );
    }

    @Override
    public String toString() {
        return "MKeeperCutActionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (actionCutId != null ? "actionCutId=" + actionCutId + ", " : "") +
                (keeperCutActionType != null ? "keeperCutActionType=" + keeperCutActionType + ", " : "") +
            "}";
    }

}
