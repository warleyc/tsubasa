package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGuildNegativeWord} entity.
 */
public class MGuildNegativeWordDTO implements Serializable {

    private Long id;

    
    @Lob
    private String word;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGuildNegativeWordDTO mGuildNegativeWordDTO = (MGuildNegativeWordDTO) o;
        if (mGuildNegativeWordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGuildNegativeWordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGuildNegativeWordDTO{" +
            "id=" + getId() +
            ", word='" + getWord() + "'" +
            "}";
    }
}
