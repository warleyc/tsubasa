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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTargetTriggerEffectGroup} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTargetTriggerEffectGroupResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-target-trigger-effect-groups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTargetTriggerEffectGroupCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter groupId;

    private IntegerFilter triggerEffectId;

    private LongFilter mtriggereffectbaseId;

    public MTargetTriggerEffectGroupCriteria(){
    }

    public MTargetTriggerEffectGroupCriteria(MTargetTriggerEffectGroupCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.triggerEffectId = other.triggerEffectId == null ? null : other.triggerEffectId.copy();
        this.mtriggereffectbaseId = other.mtriggereffectbaseId == null ? null : other.mtriggereffectbaseId.copy();
    }

    @Override
    public MTargetTriggerEffectGroupCriteria copy() {
        return new MTargetTriggerEffectGroupCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getGroupId() {
        return groupId;
    }

    public void setGroupId(IntegerFilter groupId) {
        this.groupId = groupId;
    }

    public IntegerFilter getTriggerEffectId() {
        return triggerEffectId;
    }

    public void setTriggerEffectId(IntegerFilter triggerEffectId) {
        this.triggerEffectId = triggerEffectId;
    }

    public LongFilter getMtriggereffectbaseId() {
        return mtriggereffectbaseId;
    }

    public void setMtriggereffectbaseId(LongFilter mtriggereffectbaseId) {
        this.mtriggereffectbaseId = mtriggereffectbaseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTargetTriggerEffectGroupCriteria that = (MTargetTriggerEffectGroupCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(triggerEffectId, that.triggerEffectId) &&
            Objects.equals(mtriggereffectbaseId, that.mtriggereffectbaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        groupId,
        triggerEffectId,
        mtriggereffectbaseId
        );
    }

    @Override
    public String toString() {
        return "MTargetTriggerEffectGroupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (groupId != null ? "groupId=" + groupId + ", " : "") +
                (triggerEffectId != null ? "triggerEffectId=" + triggerEffectId + ", " : "") +
                (mtriggereffectbaseId != null ? "mtriggereffectbaseId=" + mtriggereffectbaseId + ", " : "") +
            "}";
    }

}
