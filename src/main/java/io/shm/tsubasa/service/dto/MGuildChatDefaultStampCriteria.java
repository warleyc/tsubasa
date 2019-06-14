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
 * Criteria class for the {@link io.shm.tsubasa.domain.MGuildChatDefaultStamp} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGuildChatDefaultStampResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-guild-chat-default-stamps?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGuildChatDefaultStampCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter masterId;

    public MGuildChatDefaultStampCriteria(){
    }

    public MGuildChatDefaultStampCriteria(MGuildChatDefaultStampCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.masterId = other.masterId == null ? null : other.masterId.copy();
    }

    @Override
    public MGuildChatDefaultStampCriteria copy() {
        return new MGuildChatDefaultStampCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getMasterId() {
        return masterId;
    }

    public void setMasterId(IntegerFilter masterId) {
        this.masterId = masterId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGuildChatDefaultStampCriteria that = (MGuildChatDefaultStampCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(masterId, that.masterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        masterId
        );
    }

    @Override
    public String toString() {
        return "MGuildChatDefaultStampCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (masterId != null ? "masterId=" + masterId + ", " : "") +
            "}";
    }

}
