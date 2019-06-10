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
 * Criteria class for the {@link io.shm.tsubasa.domain.MCutShootOrbit} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MCutShootOrbitResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-cut-shoot-orbits?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MCutShootOrbitCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter shootOrbit;

    private IntegerFilter shootResult;

    public MCutShootOrbitCriteria(){
    }

    public MCutShootOrbitCriteria(MCutShootOrbitCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.shootOrbit = other.shootOrbit == null ? null : other.shootOrbit.copy();
        this.shootResult = other.shootResult == null ? null : other.shootResult.copy();
    }

    @Override
    public MCutShootOrbitCriteria copy() {
        return new MCutShootOrbitCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getShootOrbit() {
        return shootOrbit;
    }

    public void setShootOrbit(IntegerFilter shootOrbit) {
        this.shootOrbit = shootOrbit;
    }

    public IntegerFilter getShootResult() {
        return shootResult;
    }

    public void setShootResult(IntegerFilter shootResult) {
        this.shootResult = shootResult;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MCutShootOrbitCriteria that = (MCutShootOrbitCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(shootOrbit, that.shootOrbit) &&
            Objects.equals(shootResult, that.shootResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        shootOrbit,
        shootResult
        );
    }

    @Override
    public String toString() {
        return "MCutShootOrbitCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (shootOrbit != null ? "shootOrbit=" + shootOrbit + ", " : "") +
                (shootResult != null ? "shootResult=" + shootResult + ", " : "") +
            "}";
    }

}
