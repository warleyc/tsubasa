package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCharacter} entity.
 */
public class MCharacterDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    
    @Lob
    private String announceName;

    
    @Lob
    private String shortName;

    @NotNull
    private Integer characterBookPriority;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnounceName() {
        return announceName;
    }

    public void setAnnounceName(String announceName) {
        this.announceName = announceName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getCharacterBookPriority() {
        return characterBookPriority;
    }

    public void setCharacterBookPriority(Integer characterBookPriority) {
        this.characterBookPriority = characterBookPriority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCharacterDTO mCharacterDTO = (MCharacterDTO) o;
        if (mCharacterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCharacterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCharacterDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", announceName='" + getAnnounceName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", characterBookPriority=" + getCharacterBookPriority() +
            "}";
    }
}
