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
 * Criteria class for the {@link io.shm.tsubasa.domain.MStoreReviewUrl} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MStoreReviewUrlResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-store-review-urls?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MStoreReviewUrlCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter platform;

    public MStoreReviewUrlCriteria(){
    }

    public MStoreReviewUrlCriteria(MStoreReviewUrlCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.platform = other.platform == null ? null : other.platform.copy();
    }

    @Override
    public MStoreReviewUrlCriteria copy() {
        return new MStoreReviewUrlCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getPlatform() {
        return platform;
    }

    public void setPlatform(IntegerFilter platform) {
        this.platform = platform;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MStoreReviewUrlCriteria that = (MStoreReviewUrlCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(platform, that.platform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        platform
        );
    }

    @Override
    public String toString() {
        return "MStoreReviewUrlCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (platform != null ? "platform=" + platform + ", " : "") +
            "}";
    }

}
