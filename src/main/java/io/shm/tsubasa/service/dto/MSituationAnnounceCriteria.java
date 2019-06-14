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
 * Criteria class for the {@link io.shm.tsubasa.domain.MSituationAnnounce} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MSituationAnnounceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-situation-announces?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MSituationAnnounceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter situationId;

    private IntegerFilter groupId;

    public MSituationAnnounceCriteria(){
    }

    public MSituationAnnounceCriteria(MSituationAnnounceCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.situationId = other.situationId == null ? null : other.situationId.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
    }

    @Override
    public MSituationAnnounceCriteria copy() {
        return new MSituationAnnounceCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSituationId() {
        return situationId;
    }

    public void setSituationId(IntegerFilter situationId) {
        this.situationId = situationId;
    }

    public IntegerFilter getGroupId() {
        return groupId;
    }

    public void setGroupId(IntegerFilter groupId) {
        this.groupId = groupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MSituationAnnounceCriteria that = (MSituationAnnounceCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(situationId, that.situationId) &&
            Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        situationId,
        groupId
        );
    }

    @Override
    public String toString() {
        return "MSituationAnnounceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (situationId != null ? "situationId=" + situationId + ", " : "") +
                (groupId != null ? "groupId=" + groupId + ", " : "") +
            "}";
    }

}
