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
 * Criteria class for the {@link io.shm.tsubasa.domain.MMovieAsset} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MMovieAssetResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-movie-assets?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MMovieAssetCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter lang;

    private IntegerFilter size;

    private IntegerFilter version;

    private IntegerFilter type;

    public MMovieAssetCriteria(){
    }

    public MMovieAssetCriteria(MMovieAssetCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.lang = other.lang == null ? null : other.lang.copy();
        this.size = other.size == null ? null : other.size.copy();
        this.version = other.version == null ? null : other.version.copy();
        this.type = other.type == null ? null : other.type.copy();
    }

    @Override
    public MMovieAssetCriteria copy() {
        return new MMovieAssetCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getLang() {
        return lang;
    }

    public void setLang(IntegerFilter lang) {
        this.lang = lang;
    }

    public IntegerFilter getSize() {
        return size;
    }

    public void setSize(IntegerFilter size) {
        this.size = size;
    }

    public IntegerFilter getVersion() {
        return version;
    }

    public void setVersion(IntegerFilter version) {
        this.version = version;
    }

    public IntegerFilter getType() {
        return type;
    }

    public void setType(IntegerFilter type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MMovieAssetCriteria that = (MMovieAssetCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(lang, that.lang) &&
            Objects.equals(size, that.size) &&
            Objects.equals(version, that.version) &&
            Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        lang,
        size,
        version,
        type
        );
    }

    @Override
    public String toString() {
        return "MMovieAssetCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (lang != null ? "lang=" + lang + ", " : "") +
                (size != null ? "size=" + size + ", " : "") +
                (version != null ? "version=" + version + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
            "}";
    }

}
