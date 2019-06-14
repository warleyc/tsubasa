package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MStamp} entity.
 */
public class MStampDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    
    @Lob
    private String thumbnailAsset;

    @Lob
    private String soundEvent;

    
    @Lob
    private String description;


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

    public String getThumbnailAsset() {
        return thumbnailAsset;
    }

    public void setThumbnailAsset(String thumbnailAsset) {
        this.thumbnailAsset = thumbnailAsset;
    }

    public String getSoundEvent() {
        return soundEvent;
    }

    public void setSoundEvent(String soundEvent) {
        this.soundEvent = soundEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MStampDTO mStampDTO = (MStampDTO) o;
        if (mStampDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mStampDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MStampDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", thumbnailAsset='" + getThumbnailAsset() + "'" +
            ", soundEvent='" + getSoundEvent() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
