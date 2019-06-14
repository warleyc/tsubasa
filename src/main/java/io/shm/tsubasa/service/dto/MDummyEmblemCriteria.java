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
 * Criteria class for the {@link io.shm.tsubasa.domain.MDummyEmblem} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MDummyEmblemResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-dummy-emblems?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MDummyEmblemCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter backgroundId;

    private IntegerFilter upperId;

    private IntegerFilter middleId;

    private IntegerFilter lowerId;

    private LongFilter memblempartsId;

    public MDummyEmblemCriteria(){
    }

    public MDummyEmblemCriteria(MDummyEmblemCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.backgroundId = other.backgroundId == null ? null : other.backgroundId.copy();
        this.upperId = other.upperId == null ? null : other.upperId.copy();
        this.middleId = other.middleId == null ? null : other.middleId.copy();
        this.lowerId = other.lowerId == null ? null : other.lowerId.copy();
        this.memblempartsId = other.memblempartsId == null ? null : other.memblempartsId.copy();
    }

    @Override
    public MDummyEmblemCriteria copy() {
        return new MDummyEmblemCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(IntegerFilter backgroundId) {
        this.backgroundId = backgroundId;
    }

    public IntegerFilter getUpperId() {
        return upperId;
    }

    public void setUpperId(IntegerFilter upperId) {
        this.upperId = upperId;
    }

    public IntegerFilter getMiddleId() {
        return middleId;
    }

    public void setMiddleId(IntegerFilter middleId) {
        this.middleId = middleId;
    }

    public IntegerFilter getLowerId() {
        return lowerId;
    }

    public void setLowerId(IntegerFilter lowerId) {
        this.lowerId = lowerId;
    }

    public LongFilter getMemblempartsId() {
        return memblempartsId;
    }

    public void setMemblempartsId(LongFilter memblempartsId) {
        this.memblempartsId = memblempartsId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MDummyEmblemCriteria that = (MDummyEmblemCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(backgroundId, that.backgroundId) &&
            Objects.equals(upperId, that.upperId) &&
            Objects.equals(middleId, that.middleId) &&
            Objects.equals(lowerId, that.lowerId) &&
            Objects.equals(memblempartsId, that.memblempartsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        backgroundId,
        upperId,
        middleId,
        lowerId,
        memblempartsId
        );
    }

    @Override
    public String toString() {
        return "MDummyEmblemCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (backgroundId != null ? "backgroundId=" + backgroundId + ", " : "") +
                (upperId != null ? "upperId=" + upperId + ", " : "") +
                (middleId != null ? "middleId=" + middleId + ", " : "") +
                (lowerId != null ? "lowerId=" + lowerId + ", " : "") +
                (memblempartsId != null ? "memblempartsId=" + memblempartsId + ", " : "") +
            "}";
    }

}
