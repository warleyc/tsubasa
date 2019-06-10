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
 * Criteria class for the {@link io.shm.tsubasa.domain.MAssetTagMapping} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MAssetTagMappingResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-asset-tag-mappings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MAssetTagMappingCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter tag;

    public MAssetTagMappingCriteria(){
    }

    public MAssetTagMappingCriteria(MAssetTagMappingCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.tag = other.tag == null ? null : other.tag.copy();
    }

    @Override
    public MAssetTagMappingCriteria copy() {
        return new MAssetTagMappingCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getTag() {
        return tag;
    }

    public void setTag(IntegerFilter tag) {
        this.tag = tag;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MAssetTagMappingCriteria that = (MAssetTagMappingCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        tag
        );
    }

    @Override
    public String toString() {
        return "MAssetTagMappingCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (tag != null ? "tag=" + tag + ", " : "") +
            "}";
    }

}
