package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCardThumbnailAssets} entity.
 */
public class MCardThumbnailAssetsDTO implements Serializable {

    private Long id;

    
    @Lob
    private String thumbnailAssetName;

    
    @Lob
    private String thumbnailFrameName;

    @Lob
    private String thumbnailBackgoundAssetName;

    @Lob
    private String thumbnailEffectAssetName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
    }

    public String getThumbnailFrameName() {
        return thumbnailFrameName;
    }

    public void setThumbnailFrameName(String thumbnailFrameName) {
        this.thumbnailFrameName = thumbnailFrameName;
    }

    public String getThumbnailBackgoundAssetName() {
        return thumbnailBackgoundAssetName;
    }

    public void setThumbnailBackgoundAssetName(String thumbnailBackgoundAssetName) {
        this.thumbnailBackgoundAssetName = thumbnailBackgoundAssetName;
    }

    public String getThumbnailEffectAssetName() {
        return thumbnailEffectAssetName;
    }

    public void setThumbnailEffectAssetName(String thumbnailEffectAssetName) {
        this.thumbnailEffectAssetName = thumbnailEffectAssetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCardThumbnailAssetsDTO mCardThumbnailAssetsDTO = (MCardThumbnailAssetsDTO) o;
        if (mCardThumbnailAssetsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCardThumbnailAssetsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCardThumbnailAssetsDTO{" +
            "id=" + getId() +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            ", thumbnailFrameName='" + getThumbnailFrameName() + "'" +
            ", thumbnailBackgoundAssetName='" + getThumbnailBackgoundAssetName() + "'" +
            ", thumbnailEffectAssetName='" + getThumbnailEffectAssetName() + "'" +
            "}";
    }
}
