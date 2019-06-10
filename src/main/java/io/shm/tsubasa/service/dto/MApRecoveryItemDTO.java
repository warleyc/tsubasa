package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MApRecoveryItem} entity.
 */
public class MApRecoveryItemDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer apRecoveryItemType;

    
    @Lob
    private String name;

    
    @Lob
    private String description;

    
    @Lob
    private String thumbnailAssetName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getApRecoveryItemType() {
        return apRecoveryItemType;
    }

    public void setApRecoveryItemType(Integer apRecoveryItemType) {
        this.apRecoveryItemType = apRecoveryItemType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MApRecoveryItemDTO mApRecoveryItemDTO = (MApRecoveryItemDTO) o;
        if (mApRecoveryItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mApRecoveryItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MApRecoveryItemDTO{" +
            "id=" + getId() +
            ", apRecoveryItemType=" + getApRecoveryItemType() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            "}";
    }
}
