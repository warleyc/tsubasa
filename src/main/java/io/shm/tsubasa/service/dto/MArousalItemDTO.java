package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MArousalItem} entity.
 */
public class MArousalItemDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    
    @Lob
    private String description;

    
    @Lob
    private String thumbnailAssetName;

    
    @Lob
    private String thumbnailBgAssetName;

    
    @Lob
    private String thumbnailFrameAssetName;

    @NotNull
    private Integer arousalItemType;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
    }

    public String getThumbnailBgAssetName() {
        return thumbnailBgAssetName;
    }

    public void setThumbnailBgAssetName(String thumbnailBgAssetName) {
        this.thumbnailBgAssetName = thumbnailBgAssetName;
    }

    public String getThumbnailFrameAssetName() {
        return thumbnailFrameAssetName;
    }

    public void setThumbnailFrameAssetName(String thumbnailFrameAssetName) {
        this.thumbnailFrameAssetName = thumbnailFrameAssetName;
    }

    public Integer getArousalItemType() {
        return arousalItemType;
    }

    public void setArousalItemType(Integer arousalItemType) {
        this.arousalItemType = arousalItemType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MArousalItemDTO mArousalItemDTO = (MArousalItemDTO) o;
        if (mArousalItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mArousalItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MArousalItemDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            ", thumbnailBgAssetName='" + getThumbnailBgAssetName() + "'" +
            ", thumbnailFrameAssetName='" + getThumbnailFrameAssetName() + "'" +
            ", arousalItemType=" + getArousalItemType() +
            "}";
    }
}
