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
 * Criteria class for the {@link io.shm.tsubasa.domain.MEncountersBonus} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MEncountersBonusResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-encounters-bonuses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MEncountersBonusCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter encountersType;

    private IntegerFilter isSkillSuccess;

    private IntegerFilter threshold;

    private IntegerFilter probabilityBonusValue;

    public MEncountersBonusCriteria(){
    }

    public MEncountersBonusCriteria(MEncountersBonusCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.encountersType = other.encountersType == null ? null : other.encountersType.copy();
        this.isSkillSuccess = other.isSkillSuccess == null ? null : other.isSkillSuccess.copy();
        this.threshold = other.threshold == null ? null : other.threshold.copy();
        this.probabilityBonusValue = other.probabilityBonusValue == null ? null : other.probabilityBonusValue.copy();
    }

    @Override
    public MEncountersBonusCriteria copy() {
        return new MEncountersBonusCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getEncountersType() {
        return encountersType;
    }

    public void setEncountersType(IntegerFilter encountersType) {
        this.encountersType = encountersType;
    }

    public IntegerFilter getIsSkillSuccess() {
        return isSkillSuccess;
    }

    public void setIsSkillSuccess(IntegerFilter isSkillSuccess) {
        this.isSkillSuccess = isSkillSuccess;
    }

    public IntegerFilter getThreshold() {
        return threshold;
    }

    public void setThreshold(IntegerFilter threshold) {
        this.threshold = threshold;
    }

    public IntegerFilter getProbabilityBonusValue() {
        return probabilityBonusValue;
    }

    public void setProbabilityBonusValue(IntegerFilter probabilityBonusValue) {
        this.probabilityBonusValue = probabilityBonusValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MEncountersBonusCriteria that = (MEncountersBonusCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(encountersType, that.encountersType) &&
            Objects.equals(isSkillSuccess, that.isSkillSuccess) &&
            Objects.equals(threshold, that.threshold) &&
            Objects.equals(probabilityBonusValue, that.probabilityBonusValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        encountersType,
        isSkillSuccess,
        threshold,
        probabilityBonusValue
        );
    }

    @Override
    public String toString() {
        return "MEncountersBonusCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (encountersType != null ? "encountersType=" + encountersType + ", " : "") +
                (isSkillSuccess != null ? "isSkillSuccess=" + isSkillSuccess + ", " : "") +
                (threshold != null ? "threshold=" + threshold + ", " : "") +
                (probabilityBonusValue != null ? "probabilityBonusValue=" + probabilityBonusValue + ", " : "") +
            "}";
    }

}
