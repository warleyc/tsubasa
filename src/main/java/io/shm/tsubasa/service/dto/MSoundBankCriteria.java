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
 * Criteria class for the {@link io.shm.tsubasa.domain.MSoundBank} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MSoundBankResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-sound-banks?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MSoundBankCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter version;

    private IntegerFilter fileSize;

    public MSoundBankCriteria(){
    }

    public MSoundBankCriteria(MSoundBankCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.version = other.version == null ? null : other.version.copy();
        this.fileSize = other.fileSize == null ? null : other.fileSize.copy();
    }

    @Override
    public MSoundBankCriteria copy() {
        return new MSoundBankCriteria(this);
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

    public IntegerFilter getFileSize() {
        return fileSize;
    }

    public void setFileSize(IntegerFilter fileSize) {
        this.fileSize = fileSize;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MSoundBankCriteria that = (MSoundBankCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(version, that.version) &&
            Objects.equals(fileSize, that.fileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        version,
        fileSize
        );
    }

    @Override
    public String toString() {
        return "MSoundBankCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (version != null ? "version=" + version + ", " : "") +
                (fileSize != null ? "fileSize=" + fileSize + ", " : "") +
            "}";
    }

}
