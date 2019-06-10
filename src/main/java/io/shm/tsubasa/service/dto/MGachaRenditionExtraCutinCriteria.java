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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGachaRenditionExtraCutin} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGachaRenditionExtraCutinResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-gacha-rendition-extra-cutins?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGachaRenditionExtraCutinCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter renditionId;

    public MGachaRenditionExtraCutinCriteria(){
    }

    public MGachaRenditionExtraCutinCriteria(MGachaRenditionExtraCutinCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.renditionId = other.renditionId == null ? null : other.renditionId.copy();
    }

    @Override
    public MGachaRenditionExtraCutinCriteria copy() {
        return new MGachaRenditionExtraCutinCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRenditionId() {
        return renditionId;
    }

    public void setRenditionId(IntegerFilter renditionId) {
        this.renditionId = renditionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGachaRenditionExtraCutinCriteria that = (MGachaRenditionExtraCutinCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(renditionId, that.renditionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        renditionId
        );
    }

    @Override
    public String toString() {
        return "MGachaRenditionExtraCutinCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (renditionId != null ? "renditionId=" + renditionId + ", " : "") +
            "}";
    }

}
