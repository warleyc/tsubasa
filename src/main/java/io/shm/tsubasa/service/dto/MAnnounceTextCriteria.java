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
 * Criteria class for the {@link io.shm.tsubasa.domain.MAnnounceText} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MAnnounceTextResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-announce-texts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MAnnounceTextCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter groupId;

    private IntegerFilter seqNo;

    private IntegerFilter delayTime;

    private IntegerFilter continueTime;

    public MAnnounceTextCriteria(){
    }

    public MAnnounceTextCriteria(MAnnounceTextCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.groupId = other.groupId == null ? null : other.groupId.copy();
        this.seqNo = other.seqNo == null ? null : other.seqNo.copy();
        this.delayTime = other.delayTime == null ? null : other.delayTime.copy();
        this.continueTime = other.continueTime == null ? null : other.continueTime.copy();
    }

    @Override
    public MAnnounceTextCriteria copy() {
        return new MAnnounceTextCriteria(this);
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

    public IntegerFilter getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(IntegerFilter seqNo) {
        this.seqNo = seqNo;
    }

    public IntegerFilter getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(IntegerFilter delayTime) {
        this.delayTime = delayTime;
    }

    public IntegerFilter getContinueTime() {
        return continueTime;
    }

    public void setContinueTime(IntegerFilter continueTime) {
        this.continueTime = continueTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MAnnounceTextCriteria that = (MAnnounceTextCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(seqNo, that.seqNo) &&
            Objects.equals(delayTime, that.delayTime) &&
            Objects.equals(continueTime, that.continueTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        groupId,
        seqNo,
        delayTime,
        continueTime
        );
    }

    @Override
    public String toString() {
        return "MAnnounceTextCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (groupId != null ? "groupId=" + groupId + ", " : "") +
                (seqNo != null ? "seqNo=" + seqNo + ", " : "") +
                (delayTime != null ? "delayTime=" + delayTime + ", " : "") +
                (continueTime != null ? "continueTime=" + continueTime + ", " : "") +
            "}";
    }

}