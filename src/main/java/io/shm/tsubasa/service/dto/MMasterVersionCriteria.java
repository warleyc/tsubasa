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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMasterVersion} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMasterVersionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-master-versions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMasterVersionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter version;

    private IntegerFilter size;

    public MMasterVersionCriteria(){
    }

    public MMasterVersionCriteria(MMasterVersionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.version = other.version == null ? null : other.version.copy();
        this.size = other.size == null ? null : other.size.copy();
    }

    @Override
    public MMasterVersionCriteria copy() {
        return new MMasterVersionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getVersion() {
        return version;
    }

    public void setVersion(IntegerFilter version) {
        this.version = version;
    }

    public IntegerFilter getSize() {
        return size;
    }

    public void setSize(IntegerFilter size) {
        this.size = size;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMasterVersionCriteria that = (MMasterVersionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(version, that.version) &&
            Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        version,
        size
        );
    }

    @Override
    public String toString() {
        return "MMasterVersionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (version != null ? "version=" + version + ", " : "") +
                (size != null ? "size=" + size + ", " : "") +
            "}";
    }

}
