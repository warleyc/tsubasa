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
 * Criteria class for the {@link io.shm.tsubasa.domain.MChatSystemMessage} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MChatSystemMessageResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-chat-system-messages?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MChatSystemMessageCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter messageType;

    public MChatSystemMessageCriteria(){
    }

    public MChatSystemMessageCriteria(MChatSystemMessageCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.messageType = other.messageType == null ? null : other.messageType.copy();
    }

    @Override
    public MChatSystemMessageCriteria copy() {
        return new MChatSystemMessageCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getMessageType() {
        return messageType;
    }

    public void setMessageType(IntegerFilter messageType) {
        this.messageType = messageType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MChatSystemMessageCriteria that = (MChatSystemMessageCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(messageType, that.messageType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        messageType
        );
    }

    @Override
    public String toString() {
        return "MChatSystemMessageCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (messageType != null ? "messageType=" + messageType + ", " : "") +
            "}";
    }

}
