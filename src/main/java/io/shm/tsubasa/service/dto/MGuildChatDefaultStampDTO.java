package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGuildChatDefaultStamp} entity.
 */
public class MGuildChatDefaultStampDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer masterId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
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

        MGuildChatDefaultStampDTO mGuildChatDefaultStampDTO = (MGuildChatDefaultStampDTO) o;
        if (mGuildChatDefaultStampDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGuildChatDefaultStampDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGuildChatDefaultStampDTO{" +
            "id=" + getId() +
            ", masterId=" + getMasterId() +
            "}";
    }
}
