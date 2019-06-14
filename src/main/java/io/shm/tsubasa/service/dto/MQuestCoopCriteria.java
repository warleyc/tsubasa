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
 * Criteria class for the {@link io.shm.tsubasa.domain.MQuestCoop} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MQuestCoopResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-quest-coops?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MQuestCoopCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter groupId;

    private IntegerFilter effectRarity;

    private IntegerFilter effectLevelFrom;

    private IntegerFilter effectLevelTo;

    private IntegerFilter choose1Weight;

    private IntegerFilter choose2Weight;

    public MQuestCoopCriteria(){
    }

    public MQuestCoopCriteria(MQuestCoopCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.effectRarity = other.effectRarity == null ? null : other.effectRarity.copy();
        this.effectLevelFrom = other.effectLevelFrom == null ? null : other.effectLevelFrom.copy();
        this.effectLevelTo = other.effectLevelTo == null ? null : other.effectLevelTo.copy();
        this.choose1Weight = other.choose1Weight == null ? null : other.choose1Weight.copy();
        this.choose2Weight = other.choose2Weight == null ? null : other.choose2Weight.copy();
    }

    @Override
    public MQuestCoopCriteria copy() {
        return new MQuestCoopCriteria(this);
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

    public IntegerFilter getEffectRarity() {
        return effectRarity;
    }

    public void setEffectRarity(IntegerFilter effectRarity) {
        this.effectRarity = effectRarity;
    }

    public IntegerFilter getEffectLevelFrom() {
        return effectLevelFrom;
    }

    public void setEffectLevelFrom(IntegerFilter effectLevelFrom) {
        this.effectLevelFrom = effectLevelFrom;
    }

    public IntegerFilter getEffectLevelTo() {
        return effectLevelTo;
    }

    public void setEffectLevelTo(IntegerFilter effectLevelTo) {
        this.effectLevelTo = effectLevelTo;
    }

    public IntegerFilter getChoose1Weight() {
        return choose1Weight;
    }

    public void setChoose1Weight(IntegerFilter choose1Weight) {
        this.choose1Weight = choose1Weight;
    }

    public IntegerFilter getChoose2Weight() {
        return choose2Weight;
    }

    public void setChoose2Weight(IntegerFilter choose2Weight) {
        this.choose2Weight = choose2Weight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MQuestCoopCriteria that = (MQuestCoopCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(effectRarity, that.effectRarity) &&
            Objects.equals(effectLevelFrom, that.effectLevelFrom) &&
            Objects.equals(effectLevelTo, that.effectLevelTo) &&
            Objects.equals(choose1Weight, that.choose1Weight) &&
            Objects.equals(choose2Weight, that.choose2Weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        groupId,
        effectRarity,
        effectLevelFrom,
        effectLevelTo,
        choose1Weight,
        choose2Weight
        );
    }

    @Override
    public String toString() {
        return "MQuestCoopCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (groupId != null ? "groupId=" + groupId + ", " : "") +
                (effectRarity != null ? "effectRarity=" + effectRarity + ", " : "") +
                (effectLevelFrom != null ? "effectLevelFrom=" + effectLevelFrom + ", " : "") +
                (effectLevelTo != null ? "effectLevelTo=" + effectLevelTo + ", " : "") +
                (choose1Weight != null ? "choose1Weight=" + choose1Weight + ", " : "") +
                (choose2Weight != null ? "choose2Weight=" + choose2Weight + ", " : "") +
            "}";
    }

}
