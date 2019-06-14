package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMarathonBoostItem} entity.
 */
public class MMarathonBoostItemDTO implements Serializable {

    private Long id;

    private Integer eventId;

    @NotNull
    private Integer boostRatio;

    
    @Lob
    private String name;

    
    @Lob
    private String shortName;

    
    @Lob
    private String thumbnailAssetName;

    
    @Lob
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getBoostRatio() {
        return boostRatio;
    }

    public void setBoostRatio(Integer boostRatio) {
        this.boostRatio = boostRatio;
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

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
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

        MMarathonBoostItemDTO mMarathonBoostItemDTO = (MMarathonBoostItemDTO) o;
        if (mMarathonBoostItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMarathonBoostItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMarathonBoostItemDTO{" +
            "id=" + getId() +
            ", eventId=" + getEventId() +
            ", boostRatio=" + getBoostRatio() +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
