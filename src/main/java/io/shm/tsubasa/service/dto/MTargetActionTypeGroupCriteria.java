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
 * Criteria class for the {@link io.shm.tsubasa.domain.MTargetActionTypeGroup} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTargetActionTypeGroupResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-target-action-type-groups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTargetActionTypeGroupCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter groupId;

    private IntegerFilter commandType;

    public MTargetActionTypeGroupCriteria(){
    }

    public MTargetActionTypeGroupCriteria(MTargetActionTypeGroupCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.commandType = other.commandType == null ? null : other.commandType.copy();
    }

    @Override
    public MTargetActionTypeGroupCriteria copy() {
        return new MTargetActionTypeGroupCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getGroupId() {
        return groupId;
    }

    public void setGroupId(IntegerFilter groupId) {
        this.groupId = groupId;
    }

    public IntegerFilter getCommandType() {
        return commandType;
    }

    public void setCommandType(IntegerFilter commandType) {
        this.commandType = commandType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTargetActionTypeGroupCriteria that = (MTargetActionTypeGroupCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(commandType, that.commandType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        groupId,
        commandType
        );
    }

    @Override
    public String toString() {
        return "MTargetActionTypeGroupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (groupId != null ? "groupId=" + groupId + ", " : "") +
                (commandType != null ? "commandType=" + commandType + ", " : "") +
            "}";
    }

}
