package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MChatSystemMessage} entity.
 */
public class MChatSystemMessageDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer messageType;

    
    @Lob
    private String messageKey;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MChatSystemMessageDTO mChatSystemMessageDTO = (MChatSystemMessageDTO) o;
        if (mChatSystemMessageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mChatSystemMessageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MChatSystemMessageDTO{" +
            "id=" + getId() +
            ", messageType=" + getMessageType() +
            ", messageKey='" + getMessageKey() + "'" +
            "}";
    }
}
