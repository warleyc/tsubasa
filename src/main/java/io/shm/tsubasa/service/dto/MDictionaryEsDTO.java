package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MDictionaryEs} entity.
 */
public class MDictionaryEsDTO implements Serializable {

    private Long id;

    
    @Lob
    private String key;

    
    @Lob
    private String message;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MDictionaryEsDTO mDictionaryEsDTO = (MDictionaryEsDTO) o;
        if (mDictionaryEsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mDictionaryEsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MDictionaryEsDTO{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
