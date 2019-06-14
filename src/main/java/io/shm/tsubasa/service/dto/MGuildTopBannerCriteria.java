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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGuildTopBanner} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGuildTopBannerResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-guild-top-banners?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGuildTopBannerCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter guildBannerType;

    private IntegerFilter startAt;

    private IntegerFilter endAt;

    public MGuildTopBannerCriteria(){
    }

    public MGuildTopBannerCriteria(MGuildTopBannerCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.guildBannerType = other.guildBannerType == null ? null : other.guildBannerType.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.endAt = other.endAt == null ? null : other.endAt.copy();
    }

    @Override
    public MGuildTopBannerCriteria copy() {
        return new MGuildTopBannerCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getGuildBannerType() {
        return guildBannerType;
    }

    public void setGuildBannerType(IntegerFilter guildBannerType) {
        this.guildBannerType = guildBannerType;
    }

    public IntegerFilter getStartAt() {
        return startAt;
    }

    public void setStartAt(IntegerFilter startAt) {
        this.startAt = startAt;
    }

    public IntegerFilter getEndAt() {
        return endAt;
    }

    public void setEndAt(IntegerFilter endAt) {
        this.endAt = endAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGuildTopBannerCriteria that = (MGuildTopBannerCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(guildBannerType, that.guildBannerType) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        guildBannerType,
        startAt,
        endAt
        );
    }

    @Override
    public String toString() {
        return "MGuildTopBannerCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (guildBannerType != null ? "guildBannerType=" + guildBannerType + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (endAt != null ? "endAt=" + endAt + ", " : "") +
            "}";
    }

}
