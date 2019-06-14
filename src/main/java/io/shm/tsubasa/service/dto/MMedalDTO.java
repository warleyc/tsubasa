package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMedal} entity.
 */
public class MMedalDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer medalType;

    
    @Lob
    private String name;

    
    @Lob
    private String description;

    @NotNull
    private Integer maxAmount;

    
    @Lob
    private String iconAssetName;

    
    @Lob
    private String thumbnailAssetName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMedalType() {
        return medalType;
    }

    public void setMedalType(Integer medalType) {
        this.medalType = medalType;
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

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getIconAssetName() {
        return iconAssetName;
    }

    public void setIconAssetName(String iconAssetName) {
        this.iconAssetName = iconAssetName;
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

        MMedalDTO mMedalDTO = (MMedalDTO) o;
        if (mMedalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMedalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMedalDTO{" +
            "id=" + getId() +
            ", medalType=" + getMedalType() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", maxAmount=" + getMaxAmount() +
            ", iconAssetName='" + getIconAssetName() + "'" +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            "}";
    }
}
