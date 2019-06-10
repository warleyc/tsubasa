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
 * Criteria class for the {@link io.shm.tsubasa.domain.MDeckCondition} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MDeckConditionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-deck-conditions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MDeckConditionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter targetFormationGroupId;

    private IntegerFilter targetCharacterGroupMinId;

    private IntegerFilter targetCharacterGroupMaxId;

    private IntegerFilter targetPlayableCardGroupMinId;

    private IntegerFilter targetPlayableCardGroupMaxId;

    private IntegerFilter targetRarityGroupId;

    private IntegerFilter targetAttribute;

    private IntegerFilter targetNationalityGroupMinId;

    private IntegerFilter targetNationalityGroupMaxId;

    private IntegerFilter targetTeamGroupMinId;

    private IntegerFilter targetTeamGroupMaxId;

    private IntegerFilter targetActionGroupMinId;

    private IntegerFilter targetActionGroupMaxId;

    private IntegerFilter targetTriggerEffectGroupMinId;

    private IntegerFilter targetTriggerEffectGroupMaxId;

    private IntegerFilter targetCharacterMinCount;

    private IntegerFilter targetCharacterMaxCount;

    private IntegerFilter targetPlayableCardMinCount;

    private IntegerFilter targetPlayableCardMaxCount;

    private IntegerFilter targetRarityMaxCount;

    private IntegerFilter targetAttributeMinCount;

    private IntegerFilter targetNationalityMinCount;

    private IntegerFilter targetNationalityMaxCount;

    private IntegerFilter targetTeamMinCount;

    private IntegerFilter targetTeamMaxCount;

    private IntegerFilter targetActionMinCount;

    private IntegerFilter targetActionMaxCount;

    private IntegerFilter targetTriggerEffectMinCount;

    private IntegerFilter targetTriggerEffectMaxCount;

    private IntegerFilter targetCharacterIsStartingMin;

    private IntegerFilter targetCharacterIsStartingMax;

    private IntegerFilter targetPlayableCardIsStartingMin;

    private IntegerFilter targetPlayableCardIsStartingMax;

    private IntegerFilter targetRarityIsStarting;

    private IntegerFilter targetAttributeIsStarting;

    private IntegerFilter targetNationalityIsStartingMin;

    private IntegerFilter targetNationalityIsStartingMax;

    private IntegerFilter targetTeamIsStartingMin;

    private IntegerFilter targetTeamIsStartingMax;

    private IntegerFilter targetActionIsStartingMin;

    private IntegerFilter targetActionIsStartingMax;

    private IntegerFilter targetTriggerEffectIsStartingMin;

    private IntegerFilter targetTriggerEffectIsStartingMax;

    public MDeckConditionCriteria(){
    }

    public MDeckConditionCriteria(MDeckConditionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.targetFormationGroupId = other.targetFormationGroupId == null ? null : other.targetFormationGroupId.copy();
        this.targetCharacterGroupMinId = other.targetCharacterGroupMinId == null ? null : other.targetCharacterGroupMinId.copy();
        this.targetCharacterGroupMaxId = other.targetCharacterGroupMaxId == null ? null : other.targetCharacterGroupMaxId.copy();
        this.targetPlayableCardGroupMinId = other.targetPlayableCardGroupMinId == null ? null : other.targetPlayableCardGroupMinId.copy();
        this.targetPlayableCardGroupMaxId = other.targetPlayableCardGroupMaxId == null ? null : other.targetPlayableCardGroupMaxId.copy();
        this.targetRarityGroupId = other.targetRarityGroupId == null ? null : other.targetRarityGroupId.copy();
        this.targetAttribute = other.targetAttribute == null ? null : other.targetAttribute.copy();
        this.targetNationalityGroupMinId = other.targetNationalityGroupMinId == null ? null : other.targetNationalityGroupMinId.copy();
        this.targetNationalityGroupMaxId = other.targetNationalityGroupMaxId == null ? null : other.targetNationalityGroupMaxId.copy();
        this.targetTeamGroupMinId = other.targetTeamGroupMinId == null ? null : other.targetTeamGroupMinId.copy();
        this.targetTeamGroupMaxId = other.targetTeamGroupMaxId == null ? null : other.targetTeamGroupMaxId.copy();
        this.targetActionGroupMinId = other.targetActionGroupMinId == null ? null : other.targetActionGroupMinId.copy();
        this.targetActionGroupMaxId = other.targetActionGroupMaxId == null ? null : other.targetActionGroupMaxId.copy();
        this.targetTriggerEffectGroupMinId = other.targetTriggerEffectGroupMinId == null ? null : other.targetTriggerEffectGroupMinId.copy();
        this.targetTriggerEffectGroupMaxId = other.targetTriggerEffectGroupMaxId == null ? null : other.targetTriggerEffectGroupMaxId.copy();
        this.targetCharacterMinCount = other.targetCharacterMinCount == null ? null : other.targetCharacterMinCount.copy();
        this.targetCharacterMaxCount = other.targetCharacterMaxCount == null ? null : other.targetCharacterMaxCount.copy();
        this.targetPlayableCardMinCount = other.targetPlayableCardMinCount == null ? null : other.targetPlayableCardMinCount.copy();
        this.targetPlayableCardMaxCount = other.targetPlayableCardMaxCount == null ? null : other.targetPlayableCardMaxCount.copy();
        this.targetRarityMaxCount = other.targetRarityMaxCount == null ? null : other.targetRarityMaxCount.copy();
        this.targetAttributeMinCount = other.targetAttributeMinCount == null ? null : other.targetAttributeMinCount.copy();
        this.targetNationalityMinCount = other.targetNationalityMinCount == null ? null : other.targetNationalityMinCount.copy();
        this.targetNationalityMaxCount = other.targetNationalityMaxCount == null ? null : other.targetNationalityMaxCount.copy();
        this.targetTeamMinCount = other.targetTeamMinCount == null ? null : other.targetTeamMinCount.copy();
        this.targetTeamMaxCount = other.targetTeamMaxCount == null ? null : other.targetTeamMaxCount.copy();
        this.targetActionMinCount = other.targetActionMinCount == null ? null : other.targetActionMinCount.copy();
        this.targetActionMaxCount = other.targetActionMaxCount == null ? null : other.targetActionMaxCount.copy();
        this.targetTriggerEffectMinCount = other.targetTriggerEffectMinCount == null ? null : other.targetTriggerEffectMinCount.copy();
        this.targetTriggerEffectMaxCount = other.targetTriggerEffectMaxCount == null ? null : other.targetTriggerEffectMaxCount.copy();
        this.targetCharacterIsStartingMin = other.targetCharacterIsStartingMin == null ? null : other.targetCharacterIsStartingMin.copy();
        this.targetCharacterIsStartingMax = other.targetCharacterIsStartingMax == null ? null : other.targetCharacterIsStartingMax.copy();
        this.targetPlayableCardIsStartingMin = other.targetPlayableCardIsStartingMin == null ? null : other.targetPlayableCardIsStartingMin.copy();
        this.targetPlayableCardIsStartingMax = other.targetPlayableCardIsStartingMax == null ? null : other.targetPlayableCardIsStartingMax.copy();
        this.targetRarityIsStarting = other.targetRarityIsStarting == null ? null : other.targetRarityIsStarting.copy();
        this.targetAttributeIsStarting = other.targetAttributeIsStarting == null ? null : other.targetAttributeIsStarting.copy();
        this.targetNationalityIsStartingMin = other.targetNationalityIsStartingMin == null ? null : other.targetNationalityIsStartingMin.copy();
        this.targetNationalityIsStartingMax = other.targetNationalityIsStartingMax == null ? null : other.targetNationalityIsStartingMax.copy();
        this.targetTeamIsStartingMin = other.targetTeamIsStartingMin == null ? null : other.targetTeamIsStartingMin.copy();
        this.targetTeamIsStartingMax = other.targetTeamIsStartingMax == null ? null : other.targetTeamIsStartingMax.copy();
        this.targetActionIsStartingMin = other.targetActionIsStartingMin == null ? null : other.targetActionIsStartingMin.copy();
        this.targetActionIsStartingMax = other.targetActionIsStartingMax == null ? null : other.targetActionIsStartingMax.copy();
        this.targetTriggerEffectIsStartingMin = other.targetTriggerEffectIsStartingMin == null ? null : other.targetTriggerEffectIsStartingMin.copy();
        this.targetTriggerEffectIsStartingMax = other.targetTriggerEffectIsStartingMax == null ? null : other.targetTriggerEffectIsStartingMax.copy();
    }

    @Override
    public MDeckConditionCriteria copy() {
        return new MDeckConditionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getTargetFormationGroupId() {
        return targetFormationGroupId;
    }

    public void setTargetFormationGroupId(IntegerFilter targetFormationGroupId) {
        this.targetFormationGroupId = targetFormationGroupId;
    }

    public IntegerFilter getTargetCharacterGroupMinId() {
        return targetCharacterGroupMinId;
    }

    public void setTargetCharacterGroupMinId(IntegerFilter targetCharacterGroupMinId) {
        this.targetCharacterGroupMinId = targetCharacterGroupMinId;
    }

    public IntegerFilter getTargetCharacterGroupMaxId() {
        return targetCharacterGroupMaxId;
    }

    public void setTargetCharacterGroupMaxId(IntegerFilter targetCharacterGroupMaxId) {
        this.targetCharacterGroupMaxId = targetCharacterGroupMaxId;
    }

    public IntegerFilter getTargetPlayableCardGroupMinId() {
        return targetPlayableCardGroupMinId;
    }

    public void setTargetPlayableCardGroupMinId(IntegerFilter targetPlayableCardGroupMinId) {
        this.targetPlayableCardGroupMinId = targetPlayableCardGroupMinId;
    }

    public IntegerFilter getTargetPlayableCardGroupMaxId() {
        return targetPlayableCardGroupMaxId;
    }

    public void setTargetPlayableCardGroupMaxId(IntegerFilter targetPlayableCardGroupMaxId) {
        this.targetPlayableCardGroupMaxId = targetPlayableCardGroupMaxId;
    }

    public IntegerFilter getTargetRarityGroupId() {
        return targetRarityGroupId;
    }

    public void setTargetRarityGroupId(IntegerFilter targetRarityGroupId) {
        this.targetRarityGroupId = targetRarityGroupId;
    }

    public IntegerFilter getTargetAttribute() {
        return targetAttribute;
    }

    public void setTargetAttribute(IntegerFilter targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public IntegerFilter getTargetNationalityGroupMinId() {
        return targetNationalityGroupMinId;
    }

    public void setTargetNationalityGroupMinId(IntegerFilter targetNationalityGroupMinId) {
        this.targetNationalityGroupMinId = targetNationalityGroupMinId;
    }

    public IntegerFilter getTargetNationalityGroupMaxId() {
        return targetNationalityGroupMaxId;
    }

    public void setTargetNationalityGroupMaxId(IntegerFilter targetNationalityGroupMaxId) {
        this.targetNationalityGroupMaxId = targetNationalityGroupMaxId;
    }

    public IntegerFilter getTargetTeamGroupMinId() {
        return targetTeamGroupMinId;
    }

    public void setTargetTeamGroupMinId(IntegerFilter targetTeamGroupMinId) {
        this.targetTeamGroupMinId = targetTeamGroupMinId;
    }

    public IntegerFilter getTargetTeamGroupMaxId() {
        return targetTeamGroupMaxId;
    }

    public void setTargetTeamGroupMaxId(IntegerFilter targetTeamGroupMaxId) {
        this.targetTeamGroupMaxId = targetTeamGroupMaxId;
    }

    public IntegerFilter getTargetActionGroupMinId() {
        return targetActionGroupMinId;
    }

    public void setTargetActionGroupMinId(IntegerFilter targetActionGroupMinId) {
        this.targetActionGroupMinId = targetActionGroupMinId;
    }

    public IntegerFilter getTargetActionGroupMaxId() {
        return targetActionGroupMaxId;
    }

    public void setTargetActionGroupMaxId(IntegerFilter targetActionGroupMaxId) {
        this.targetActionGroupMaxId = targetActionGroupMaxId;
    }

    public IntegerFilter getTargetTriggerEffectGroupMinId() {
        return targetTriggerEffectGroupMinId;
    }

    public void setTargetTriggerEffectGroupMinId(IntegerFilter targetTriggerEffectGroupMinId) {
        this.targetTriggerEffectGroupMinId = targetTriggerEffectGroupMinId;
    }

    public IntegerFilter getTargetTriggerEffectGroupMaxId() {
        return targetTriggerEffectGroupMaxId;
    }

    public void setTargetTriggerEffectGroupMaxId(IntegerFilter targetTriggerEffectGroupMaxId) {
        this.targetTriggerEffectGroupMaxId = targetTriggerEffectGroupMaxId;
    }

    public IntegerFilter getTargetCharacterMinCount() {
        return targetCharacterMinCount;
    }

    public void setTargetCharacterMinCount(IntegerFilter targetCharacterMinCount) {
        this.targetCharacterMinCount = targetCharacterMinCount;
    }

    public IntegerFilter getTargetCharacterMaxCount() {
        return targetCharacterMaxCount;
    }

    public void setTargetCharacterMaxCount(IntegerFilter targetCharacterMaxCount) {
        this.targetCharacterMaxCount = targetCharacterMaxCount;
    }

    public IntegerFilter getTargetPlayableCardMinCount() {
        return targetPlayableCardMinCount;
    }

    public void setTargetPlayableCardMinCount(IntegerFilter targetPlayableCardMinCount) {
        this.targetPlayableCardMinCount = targetPlayableCardMinCount;
    }

    public IntegerFilter getTargetPlayableCardMaxCount() {
        return targetPlayableCardMaxCount;
    }

    public void setTargetPlayableCardMaxCount(IntegerFilter targetPlayableCardMaxCount) {
        this.targetPlayableCardMaxCount = targetPlayableCardMaxCount;
    }

    public IntegerFilter getTargetRarityMaxCount() {
        return targetRarityMaxCount;
    }

    public void setTargetRarityMaxCount(IntegerFilter targetRarityMaxCount) {
        this.targetRarityMaxCount = targetRarityMaxCount;
    }

    public IntegerFilter getTargetAttributeMinCount() {
        return targetAttributeMinCount;
    }

    public void setTargetAttributeMinCount(IntegerFilter targetAttributeMinCount) {
        this.targetAttributeMinCount = targetAttributeMinCount;
    }

    public IntegerFilter getTargetNationalityMinCount() {
        return targetNationalityMinCount;
    }

    public void setTargetNationalityMinCount(IntegerFilter targetNationalityMinCount) {
        this.targetNationalityMinCount = targetNationalityMinCount;
    }

    public IntegerFilter getTargetNationalityMaxCount() {
        return targetNationalityMaxCount;
    }

    public void setTargetNationalityMaxCount(IntegerFilter targetNationalityMaxCount) {
        this.targetNationalityMaxCount = targetNationalityMaxCount;
    }

    public IntegerFilter getTargetTeamMinCount() {
        return targetTeamMinCount;
    }

    public void setTargetTeamMinCount(IntegerFilter targetTeamMinCount) {
        this.targetTeamMinCount = targetTeamMinCount;
    }

    public IntegerFilter getTargetTeamMaxCount() {
        return targetTeamMaxCount;
    }

    public void setTargetTeamMaxCount(IntegerFilter targetTeamMaxCount) {
        this.targetTeamMaxCount = targetTeamMaxCount;
    }

    public IntegerFilter getTargetActionMinCount() {
        return targetActionMinCount;
    }

    public void setTargetActionMinCount(IntegerFilter targetActionMinCount) {
        this.targetActionMinCount = targetActionMinCount;
    }

    public IntegerFilter getTargetActionMaxCount() {
        return targetActionMaxCount;
    }

    public void setTargetActionMaxCount(IntegerFilter targetActionMaxCount) {
        this.targetActionMaxCount = targetActionMaxCount;
    }

    public IntegerFilter getTargetTriggerEffectMinCount() {
        return targetTriggerEffectMinCount;
    }

    public void setTargetTriggerEffectMinCount(IntegerFilter targetTriggerEffectMinCount) {
        this.targetTriggerEffectMinCount = targetTriggerEffectMinCount;
    }

    public IntegerFilter getTargetTriggerEffectMaxCount() {
        return targetTriggerEffectMaxCount;
    }

    public void setTargetTriggerEffectMaxCount(IntegerFilter targetTriggerEffectMaxCount) {
        this.targetTriggerEffectMaxCount = targetTriggerEffectMaxCount;
    }

    public IntegerFilter getTargetCharacterIsStartingMin() {
        return targetCharacterIsStartingMin;
    }

    public void setTargetCharacterIsStartingMin(IntegerFilter targetCharacterIsStartingMin) {
        this.targetCharacterIsStartingMin = targetCharacterIsStartingMin;
    }

    public IntegerFilter getTargetCharacterIsStartingMax() {
        return targetCharacterIsStartingMax;
    }

    public void setTargetCharacterIsStartingMax(IntegerFilter targetCharacterIsStartingMax) {
        this.targetCharacterIsStartingMax = targetCharacterIsStartingMax;
    }

    public IntegerFilter getTargetPlayableCardIsStartingMin() {
        return targetPlayableCardIsStartingMin;
    }

    public void setTargetPlayableCardIsStartingMin(IntegerFilter targetPlayableCardIsStartingMin) {
        this.targetPlayableCardIsStartingMin = targetPlayableCardIsStartingMin;
    }

    public IntegerFilter getTargetPlayableCardIsStartingMax() {
        return targetPlayableCardIsStartingMax;
    }

    public void setTargetPlayableCardIsStartingMax(IntegerFilter targetPlayableCardIsStartingMax) {
        this.targetPlayableCardIsStartingMax = targetPlayableCardIsStartingMax;
    }

    public IntegerFilter getTargetRarityIsStarting() {
        return targetRarityIsStarting;
    }

    public void setTargetRarityIsStarting(IntegerFilter targetRarityIsStarting) {
        this.targetRarityIsStarting = targetRarityIsStarting;
    }

    public IntegerFilter getTargetAttributeIsStarting() {
        return targetAttributeIsStarting;
    }

    public void setTargetAttributeIsStarting(IntegerFilter targetAttributeIsStarting) {
        this.targetAttributeIsStarting = targetAttributeIsStarting;
    }

    public IntegerFilter getTargetNationalityIsStartingMin() {
        return targetNationalityIsStartingMin;
    }

    public void setTargetNationalityIsStartingMin(IntegerFilter targetNationalityIsStartingMin) {
        this.targetNationalityIsStartingMin = targetNationalityIsStartingMin;
    }

    public IntegerFilter getTargetNationalityIsStartingMax() {
        return targetNationalityIsStartingMax;
    }

    public void setTargetNationalityIsStartingMax(IntegerFilter targetNationalityIsStartingMax) {
        this.targetNationalityIsStartingMax = targetNationalityIsStartingMax;
    }

    public IntegerFilter getTargetTeamIsStartingMin() {
        return targetTeamIsStartingMin;
    }

    public void setTargetTeamIsStartingMin(IntegerFilter targetTeamIsStartingMin) {
        this.targetTeamIsStartingMin = targetTeamIsStartingMin;
    }

    public IntegerFilter getTargetTeamIsStartingMax() {
        return targetTeamIsStartingMax;
    }

    public void setTargetTeamIsStartingMax(IntegerFilter targetTeamIsStartingMax) {
        this.targetTeamIsStartingMax = targetTeamIsStartingMax;
    }

    public IntegerFilter getTargetActionIsStartingMin() {
        return targetActionIsStartingMin;
    }

    public void setTargetActionIsStartingMin(IntegerFilter targetActionIsStartingMin) {
        this.targetActionIsStartingMin = targetActionIsStartingMin;
    }

    public IntegerFilter getTargetActionIsStartingMax() {
        return targetActionIsStartingMax;
    }

    public void setTargetActionIsStartingMax(IntegerFilter targetActionIsStartingMax) {
        this.targetActionIsStartingMax = targetActionIsStartingMax;
    }

    public IntegerFilter getTargetTriggerEffectIsStartingMin() {
        return targetTriggerEffectIsStartingMin;
    }

    public void setTargetTriggerEffectIsStartingMin(IntegerFilter targetTriggerEffectIsStartingMin) {
        this.targetTriggerEffectIsStartingMin = targetTriggerEffectIsStartingMin;
    }

    public IntegerFilter getTargetTriggerEffectIsStartingMax() {
        return targetTriggerEffectIsStartingMax;
    }

    public void setTargetTriggerEffectIsStartingMax(IntegerFilter targetTriggerEffectIsStartingMax) {
        this.targetTriggerEffectIsStartingMax = targetTriggerEffectIsStartingMax;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MDeckConditionCriteria that = (MDeckConditionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(targetFormationGroupId, that.targetFormationGroupId) &&
            Objects.equals(targetCharacterGroupMinId, that.targetCharacterGroupMinId) &&
            Objects.equals(targetCharacterGroupMaxId, that.targetCharacterGroupMaxId) &&
            Objects.equals(targetPlayableCardGroupMinId, that.targetPlayableCardGroupMinId) &&
            Objects.equals(targetPlayableCardGroupMaxId, that.targetPlayableCardGroupMaxId) &&
            Objects.equals(targetRarityGroupId, that.targetRarityGroupId) &&
            Objects.equals(targetAttribute, that.targetAttribute) &&
            Objects.equals(targetNationalityGroupMinId, that.targetNationalityGroupMinId) &&
            Objects.equals(targetNationalityGroupMaxId, that.targetNationalityGroupMaxId) &&
            Objects.equals(targetTeamGroupMinId, that.targetTeamGroupMinId) &&
            Objects.equals(targetTeamGroupMaxId, that.targetTeamGroupMaxId) &&
            Objects.equals(targetActionGroupMinId, that.targetActionGroupMinId) &&
            Objects.equals(targetActionGroupMaxId, that.targetActionGroupMaxId) &&
            Objects.equals(targetTriggerEffectGroupMinId, that.targetTriggerEffectGroupMinId) &&
            Objects.equals(targetTriggerEffectGroupMaxId, that.targetTriggerEffectGroupMaxId) &&
            Objects.equals(targetCharacterMinCount, that.targetCharacterMinCount) &&
            Objects.equals(targetCharacterMaxCount, that.targetCharacterMaxCount) &&
            Objects.equals(targetPlayableCardMinCount, that.targetPlayableCardMinCount) &&
            Objects.equals(targetPlayableCardMaxCount, that.targetPlayableCardMaxCount) &&
            Objects.equals(targetRarityMaxCount, that.targetRarityMaxCount) &&
            Objects.equals(targetAttributeMinCount, that.targetAttributeMinCount) &&
            Objects.equals(targetNationalityMinCount, that.targetNationalityMinCount) &&
            Objects.equals(targetNationalityMaxCount, that.targetNationalityMaxCount) &&
            Objects.equals(targetTeamMinCount, that.targetTeamMinCount) &&
            Objects.equals(targetTeamMaxCount, that.targetTeamMaxCount) &&
            Objects.equals(targetActionMinCount, that.targetActionMinCount) &&
            Objects.equals(targetActionMaxCount, that.targetActionMaxCount) &&
            Objects.equals(targetTriggerEffectMinCount, that.targetTriggerEffectMinCount) &&
            Objects.equals(targetTriggerEffectMaxCount, that.targetTriggerEffectMaxCount) &&
            Objects.equals(targetCharacterIsStartingMin, that.targetCharacterIsStartingMin) &&
            Objects.equals(targetCharacterIsStartingMax, that.targetCharacterIsStartingMax) &&
            Objects.equals(targetPlayableCardIsStartingMin, that.targetPlayableCardIsStartingMin) &&
            Objects.equals(targetPlayableCardIsStartingMax, that.targetPlayableCardIsStartingMax) &&
            Objects.equals(targetRarityIsStarting, that.targetRarityIsStarting) &&
            Objects.equals(targetAttributeIsStarting, that.targetAttributeIsStarting) &&
            Objects.equals(targetNationalityIsStartingMin, that.targetNationalityIsStartingMin) &&
            Objects.equals(targetNationalityIsStartingMax, that.targetNationalityIsStartingMax) &&
            Objects.equals(targetTeamIsStartingMin, that.targetTeamIsStartingMin) &&
            Objects.equals(targetTeamIsStartingMax, that.targetTeamIsStartingMax) &&
            Objects.equals(targetActionIsStartingMin, that.targetActionIsStartingMin) &&
            Objects.equals(targetActionIsStartingMax, that.targetActionIsStartingMax) &&
            Objects.equals(targetTriggerEffectIsStartingMin, that.targetTriggerEffectIsStartingMin) &&
            Objects.equals(targetTriggerEffectIsStartingMax, that.targetTriggerEffectIsStartingMax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        targetFormationGroupId,
        targetCharacterGroupMinId,
        targetCharacterGroupMaxId,
        targetPlayableCardGroupMinId,
        targetPlayableCardGroupMaxId,
        targetRarityGroupId,
        targetAttribute,
        targetNationalityGroupMinId,
        targetNationalityGroupMaxId,
        targetTeamGroupMinId,
        targetTeamGroupMaxId,
        targetActionGroupMinId,
        targetActionGroupMaxId,
        targetTriggerEffectGroupMinId,
        targetTriggerEffectGroupMaxId,
        targetCharacterMinCount,
        targetCharacterMaxCount,
        targetPlayableCardMinCount,
        targetPlayableCardMaxCount,
        targetRarityMaxCount,
        targetAttributeMinCount,
        targetNationalityMinCount,
        targetNationalityMaxCount,
        targetTeamMinCount,
        targetTeamMaxCount,
        targetActionMinCount,
        targetActionMaxCount,
        targetTriggerEffectMinCount,
        targetTriggerEffectMaxCount,
        targetCharacterIsStartingMin,
        targetCharacterIsStartingMax,
        targetPlayableCardIsStartingMin,
        targetPlayableCardIsStartingMax,
        targetRarityIsStarting,
        targetAttributeIsStarting,
        targetNationalityIsStartingMin,
        targetNationalityIsStartingMax,
        targetTeamIsStartingMin,
        targetTeamIsStartingMax,
        targetActionIsStartingMin,
        targetActionIsStartingMax,
        targetTriggerEffectIsStartingMin,
        targetTriggerEffectIsStartingMax
        );
    }

    @Override
    public String toString() {
        return "MDeckConditionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (targetFormationGroupId != null ? "targetFormationGroupId=" + targetFormationGroupId + ", " : "") +
                (targetCharacterGroupMinId != null ? "targetCharacterGroupMinId=" + targetCharacterGroupMinId + ", " : "") +
                (targetCharacterGroupMaxId != null ? "targetCharacterGroupMaxId=" + targetCharacterGroupMaxId + ", " : "") +
                (targetPlayableCardGroupMinId != null ? "targetPlayableCardGroupMinId=" + targetPlayableCardGroupMinId + ", " : "") +
                (targetPlayableCardGroupMaxId != null ? "targetPlayableCardGroupMaxId=" + targetPlayableCardGroupMaxId + ", " : "") +
                (targetRarityGroupId != null ? "targetRarityGroupId=" + targetRarityGroupId + ", " : "") +
                (targetAttribute != null ? "targetAttribute=" + targetAttribute + ", " : "") +
                (targetNationalityGroupMinId != null ? "targetNationalityGroupMinId=" + targetNationalityGroupMinId + ", " : "") +
                (targetNationalityGroupMaxId != null ? "targetNationalityGroupMaxId=" + targetNationalityGroupMaxId + ", " : "") +
                (targetTeamGroupMinId != null ? "targetTeamGroupMinId=" + targetTeamGroupMinId + ", " : "") +
                (targetTeamGroupMaxId != null ? "targetTeamGroupMaxId=" + targetTeamGroupMaxId + ", " : "") +
                (targetActionGroupMinId != null ? "targetActionGroupMinId=" + targetActionGroupMinId + ", " : "") +
                (targetActionGroupMaxId != null ? "targetActionGroupMaxId=" + targetActionGroupMaxId + ", " : "") +
                (targetTriggerEffectGroupMinId != null ? "targetTriggerEffectGroupMinId=" + targetTriggerEffectGroupMinId + ", " : "") +
                (targetTriggerEffectGroupMaxId != null ? "targetTriggerEffectGroupMaxId=" + targetTriggerEffectGroupMaxId + ", " : "") +
                (targetCharacterMinCount != null ? "targetCharacterMinCount=" + targetCharacterMinCount + ", " : "") +
                (targetCharacterMaxCount != null ? "targetCharacterMaxCount=" + targetCharacterMaxCount + ", " : "") +
                (targetPlayableCardMinCount != null ? "targetPlayableCardMinCount=" + targetPlayableCardMinCount + ", " : "") +
                (targetPlayableCardMaxCount != null ? "targetPlayableCardMaxCount=" + targetPlayableCardMaxCount + ", " : "") +
                (targetRarityMaxCount != null ? "targetRarityMaxCount=" + targetRarityMaxCount + ", " : "") +
                (targetAttributeMinCount != null ? "targetAttributeMinCount=" + targetAttributeMinCount + ", " : "") +
                (targetNationalityMinCount != null ? "targetNationalityMinCount=" + targetNationalityMinCount + ", " : "") +
                (targetNationalityMaxCount != null ? "targetNationalityMaxCount=" + targetNationalityMaxCount + ", " : "") +
                (targetTeamMinCount != null ? "targetTeamMinCount=" + targetTeamMinCount + ", " : "") +
                (targetTeamMaxCount != null ? "targetTeamMaxCount=" + targetTeamMaxCount + ", " : "") +
                (targetActionMinCount != null ? "targetActionMinCount=" + targetActionMinCount + ", " : "") +
                (targetActionMaxCount != null ? "targetActionMaxCount=" + targetActionMaxCount + ", " : "") +
                (targetTriggerEffectMinCount != null ? "targetTriggerEffectMinCount=" + targetTriggerEffectMinCount + ", " : "") +
                (targetTriggerEffectMaxCount != null ? "targetTriggerEffectMaxCount=" + targetTriggerEffectMaxCount + ", " : "") +
                (targetCharacterIsStartingMin != null ? "targetCharacterIsStartingMin=" + targetCharacterIsStartingMin + ", " : "") +
                (targetCharacterIsStartingMax != null ? "targetCharacterIsStartingMax=" + targetCharacterIsStartingMax + ", " : "") +
                (targetPlayableCardIsStartingMin != null ? "targetPlayableCardIsStartingMin=" + targetPlayableCardIsStartingMin + ", " : "") +
                (targetPlayableCardIsStartingMax != null ? "targetPlayableCardIsStartingMax=" + targetPlayableCardIsStartingMax + ", " : "") +
                (targetRarityIsStarting != null ? "targetRarityIsStarting=" + targetRarityIsStarting + ", " : "") +
                (targetAttributeIsStarting != null ? "targetAttributeIsStarting=" + targetAttributeIsStarting + ", " : "") +
                (targetNationalityIsStartingMin != null ? "targetNationalityIsStartingMin=" + targetNationalityIsStartingMin + ", " : "") +
                (targetNationalityIsStartingMax != null ? "targetNationalityIsStartingMax=" + targetNationalityIsStartingMax + ", " : "") +
                (targetTeamIsStartingMin != null ? "targetTeamIsStartingMin=" + targetTeamIsStartingMin + ", " : "") +
                (targetTeamIsStartingMax != null ? "targetTeamIsStartingMax=" + targetTeamIsStartingMax + ", " : "") +
                (targetActionIsStartingMin != null ? "targetActionIsStartingMin=" + targetActionIsStartingMin + ", " : "") +
                (targetActionIsStartingMax != null ? "targetActionIsStartingMax=" + targetActionIsStartingMax + ", " : "") +
                (targetTriggerEffectIsStartingMin != null ? "targetTriggerEffectIsStartingMin=" + targetTriggerEffectIsStartingMin + ", " : "") +
                (targetTriggerEffectIsStartingMax != null ? "targetTriggerEffectIsStartingMax=" + targetTriggerEffectIsStartingMax + ", " : "") +
            "}";
    }

}
