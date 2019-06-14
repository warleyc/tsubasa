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
 * Criteria class for the {@link io.shm.tsubasa.domain.MShoot} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MShootResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-shoots?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MShootCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter angleDecayType;

    private IntegerFilter shootOrbit;

    private IntegerFilter judgementId;

    public MShootCriteria(){
    }

    public MShootCriteria(MShootCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.angleDecayType = other.angleDecayType == null ? null : other.angleDecayType.copy();
        this.shootOrbit = other.shootOrbit == null ? null : other.shootOrbit.copy();
        this.judgementId = other.judgementId == null ? null : other.judgementId.copy();
    }

    @Override
    public MShootCriteria copy() {
        return new MShootCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getAngleDecayType() {
        return angleDecayType;
    }

    public void setAngleDecayType(IntegerFilter angleDecayType) {
        this.angleDecayType = angleDecayType;
    }

    public IntegerFilter getShootOrbit() {
        return shootOrbit;
    }

    public void setShootOrbit(IntegerFilter shootOrbit) {
        this.shootOrbit = shootOrbit;
    }

    public IntegerFilter getJudgementId() {
        return judgementId;
    }

    public void setJudgementId(IntegerFilter judgementId) {
        this.judgementId = judgementId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MShootCriteria that = (MShootCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(angleDecayType, that.angleDecayType) &&
            Objects.equals(shootOrbit, that.shootOrbit) &&
            Objects.equals(judgementId, that.judgementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        angleDecayType,
        shootOrbit,
        judgementId
        );
    }

    @Override
    public String toString() {
        return "MShootCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (angleDecayType != null ? "angleDecayType=" + angleDecayType + ", " : "") +
                (shootOrbit != null ? "shootOrbit=" + shootOrbit + ", " : "") +
                (judgementId != null ? "judgementId=" + judgementId + ", " : "") +
            "}";
    }

}
