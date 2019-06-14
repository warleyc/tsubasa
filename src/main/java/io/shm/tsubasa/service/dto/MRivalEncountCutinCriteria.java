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
 * Criteria class for the {@link io.shm.tsubasa.domain.MRivalEncountCutin} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MRivalEncountCutinResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-rival-encount-cutins?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MRivalEncountCutinCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter offenseCharacterId;

    private IntegerFilter offenseTeamId;

    private IntegerFilter defenseCharacterId;

    private IntegerFilter defenseTeamId;

    private IntegerFilter cutinType;

    public MRivalEncountCutinCriteria(){
    }

    public MRivalEncountCutinCriteria(MRivalEncountCutinCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.offenseCharacterId = other.offenseCharacterId == null ? null : other.offenseCharacterId.copy();
        this.offenseTeamId = other.offenseTeamId == null ? null : other.offenseTeamId.copy();
        this.defenseCharacterId = other.defenseCharacterId == null ? null : other.defenseCharacterId.copy();
        this.defenseTeamId = other.defenseTeamId == null ? null : other.defenseTeamId.copy();
        this.cutinType = other.cutinType == null ? null : other.cutinType.copy();
    }

    @Override
    public MRivalEncountCutinCriteria copy() {
        return new MRivalEncountCutinCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getOffenseCharacterId() {
        return offenseCharacterId;
    }

    public void setOffenseCharacterId(IntegerFilter offenseCharacterId) {
        this.offenseCharacterId = offenseCharacterId;
    }

    public IntegerFilter getOffenseTeamId() {
        return offenseTeamId;
    }

    public void setOffenseTeamId(IntegerFilter offenseTeamId) {
        this.offenseTeamId = offenseTeamId;
    }

    public IntegerFilter getDefenseCharacterId() {
        return defenseCharacterId;
    }

    public void setDefenseCharacterId(IntegerFilter defenseCharacterId) {
        this.defenseCharacterId = defenseCharacterId;
    }

    public IntegerFilter getDefenseTeamId() {
        return defenseTeamId;
    }

    public void setDefenseTeamId(IntegerFilter defenseTeamId) {
        this.defenseTeamId = defenseTeamId;
    }

    public IntegerFilter getCutinType() {
        return cutinType;
    }

    public void setCutinType(IntegerFilter cutinType) {
        this.cutinType = cutinType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MRivalEncountCutinCriteria that = (MRivalEncountCutinCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(offenseCharacterId, that.offenseCharacterId) &&
            Objects.equals(offenseTeamId, that.offenseTeamId) &&
            Objects.equals(defenseCharacterId, that.defenseCharacterId) &&
            Objects.equals(defenseTeamId, that.defenseTeamId) &&
            Objects.equals(cutinType, that.cutinType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        offenseCharacterId,
        offenseTeamId,
        defenseCharacterId,
        defenseTeamId,
        cutinType
        );
    }

    @Override
    public String toString() {
        return "MRivalEncountCutinCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (offenseCharacterId != null ? "offenseCharacterId=" + offenseCharacterId + ", " : "") +
                (offenseTeamId != null ? "offenseTeamId=" + offenseTeamId + ", " : "") +
                (defenseCharacterId != null ? "defenseCharacterId=" + defenseCharacterId + ", " : "") +
                (defenseTeamId != null ? "defenseTeamId=" + defenseTeamId + ", " : "") +
                (cutinType != null ? "cutinType=" + cutinType + ", " : "") +
            "}";
    }

}
