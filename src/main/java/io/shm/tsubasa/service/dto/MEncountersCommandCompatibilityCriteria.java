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
 * Criteria class for the {@link io.shm.tsubasa.domain.MEncountersCommandCompatibility} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MEncountersCommandCompatibilityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-encounters-command-compatibilities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MEncountersCommandCompatibilityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter encountersType;

    private IntegerFilter offenseCommandType;

    private IntegerFilter defenseCommandType;

    private IntegerFilter offenseMagnification;

    private IntegerFilter defenseMagnification;

    public MEncountersCommandCompatibilityCriteria(){
    }

    public MEncountersCommandCompatibilityCriteria(MEncountersCommandCompatibilityCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.encountersType = other.encountersType == null ? null : other.encountersType.copy();
        this.offenseCommandType = other.offenseCommandType == null ? null : other.offenseCommandType.copy();
        this.defenseCommandType = other.defenseCommandType == null ? null : other.defenseCommandType.copy();
        this.offenseMagnification = other.offenseMagnification == null ? null : other.offenseMagnification.copy();
        this.defenseMagnification = other.defenseMagnification == null ? null : other.defenseMagnification.copy();
    }

    @Override
    public MEncountersCommandCompatibilityCriteria copy() {
        return new MEncountersCommandCompatibilityCriteria(this);
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

    public IntegerFilter getOffenseCommandType() {
        return offenseCommandType;
    }

    public void setOffenseCommandType(IntegerFilter offenseCommandType) {
        this.offenseCommandType = offenseCommandType;
    }

    public IntegerFilter getDefenseCommandType() {
        return defenseCommandType;
    }

    public void setDefenseCommandType(IntegerFilter defenseCommandType) {
        this.defenseCommandType = defenseCommandType;
    }

    public IntegerFilter getOffenseMagnification() {
        return offenseMagnification;
    }

    public void setOffenseMagnification(IntegerFilter offenseMagnification) {
        this.offenseMagnification = offenseMagnification;
    }

    public IntegerFilter getDefenseMagnification() {
        return defenseMagnification;
    }

    public void setDefenseMagnification(IntegerFilter defenseMagnification) {
        this.defenseMagnification = defenseMagnification;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MEncountersCommandCompatibilityCriteria that = (MEncountersCommandCompatibilityCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(encountersType, that.encountersType) &&
            Objects.equals(offenseCommandType, that.offenseCommandType) &&
            Objects.equals(defenseCommandType, that.defenseCommandType) &&
            Objects.equals(offenseMagnification, that.offenseMagnification) &&
            Objects.equals(defenseMagnification, that.defenseMagnification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        encountersType,
        offenseCommandType,
        defenseCommandType,
        offenseMagnification,
        defenseMagnification
        );
    }

    @Override
    public String toString() {
        return "MEncountersCommandCompatibilityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (encountersType != null ? "encountersType=" + encountersType + ", " : "") +
                (offenseCommandType != null ? "offenseCommandType=" + offenseCommandType + ", " : "") +
                (defenseCommandType != null ? "defenseCommandType=" + defenseCommandType + ", " : "") +
                (offenseMagnification != null ? "offenseMagnification=" + offenseMagnification + ", " : "") +
                (defenseMagnification != null ? "defenseMagnification=" + defenseMagnification + ", " : "") +
            "}";
    }

}
