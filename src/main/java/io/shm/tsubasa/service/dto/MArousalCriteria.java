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
 * Criteria class for the {@link io.shm.tsubasa.domain.MArousal} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MArousalResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-arousals?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MArousalCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter beforeId;

    private IntegerFilter afterId;

    private IntegerFilter cost;

    private IntegerFilter materialGroupId;

    private LongFilter idId;

    public MArousalCriteria(){
    }

    public MArousalCriteria(MArousalCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.beforeId = other.beforeId == null ? null : other.beforeId.copy();
        this.afterId = other.afterId == null ? null : other.afterId.copy();
        this.cost = other.cost == null ? null : other.cost.copy();
        this.materialGroupId = other.materialGroupId == null ? null : other.materialGroupId.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
    }

    @Override
    public MArousalCriteria copy() {
        return new MArousalCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getBeforeId() {
        return beforeId;
    }

    public void setBeforeId(IntegerFilter beforeId) {
        this.beforeId = beforeId;
    }

    public IntegerFilter getAfterId() {
        return afterId;
    }

    public void setAfterId(IntegerFilter afterId) {
        this.afterId = afterId;
    }

    public IntegerFilter getCost() {
        return cost;
    }

    public void setCost(IntegerFilter cost) {
        this.cost = cost;
    }

    public IntegerFilter getMaterialGroupId() {
        return materialGroupId;
    }

    public void setMaterialGroupId(IntegerFilter materialGroupId) {
        this.materialGroupId = materialGroupId;
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
        final MArousalCriteria that = (MArousalCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(beforeId, that.beforeId) &&
            Objects.equals(afterId, that.afterId) &&
            Objects.equals(cost, that.cost) &&
            Objects.equals(materialGroupId, that.materialGroupId) &&
            Objects.equals(idId, that.idId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        beforeId,
        afterId,
        cost,
        materialGroupId,
        idId
        );
    }

    @Override
    public String toString() {
        return "MArousalCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (beforeId != null ? "beforeId=" + beforeId + ", " : "") +
                (afterId != null ? "afterId=" + afterId + ", " : "") +
                (cost != null ? "cost=" + cost + ", " : "") +
                (materialGroupId != null ? "materialGroupId=" + materialGroupId + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
            "}";
    }

}
