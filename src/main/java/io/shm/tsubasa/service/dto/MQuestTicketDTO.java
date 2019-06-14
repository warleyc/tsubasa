package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MQuestTicket} entity.
 */
public class MQuestTicketDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    
    @Lob
    private String shortName;

    
    @Lob
    private String description;

    
    @Lob
    private String thumbnailAsset;


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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailAsset() {
        return thumbnailAsset;
    }

    public void setThumbnailAsset(String thumbnailAsset) {
        this.thumbnailAsset = thumbnailAsset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MQuestTicketDTO mQuestTicketDTO = (MQuestTicketDTO) o;
        if (mQuestTicketDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mQuestTicketDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MQuestTicketDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", description='" + getDescription() + "'" +
            ", thumbnailAsset='" + getThumbnailAsset() + "'" +
            "}";
    }
}
