package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MSoundBankEvent} entity.
 */
public class MSoundBankEventDTO implements Serializable {

    private Long id;

    
    @Lob
    private String path;

    
    @Lob
    private String name;

    
    @Lob
    private String eventId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MSoundBankEventDTO mSoundBankEventDTO = (MSoundBankEventDTO) o;
        if (mSoundBankEventDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mSoundBankEventDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MSoundBankEventDTO{" +
            "id=" + getId() +
            ", path='" + getPath() + "'" +
            ", name='" + getName() + "'" +
            ", eventId='" + getEventId() + "'" +
            "}";
    }
}
