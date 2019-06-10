package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MDistributeParamPoint} entity.
 */
public class MDistributeParamPointDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer targetAttribute;

    @NotNull
    private Integer targetNationalityGroupId;

    
    @Lob
    private String name;

    
    @Lob
    private String description;

    
    @Lob
    private String thumbnailAssetName;

    
    @Lob
    private String iconAssetName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTargetAttribute() {
        return targetAttribute;
    }

    public void setTargetAttribute(Integer targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public Integer getTargetNationalityGroupId() {
        return targetNationalityGroupId;
    }

    public void setTargetNationalityGroupId(Integer targetNationalityGroupId) {
        this.targetNationalityGroupId = targetNationalityGroupId;
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

    public String getIconAssetName() {
        return iconAssetName;
    }

    public void setIconAssetName(String iconAssetName) {
        this.iconAssetName = iconAssetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MDistributeParamPointDTO mDistributeParamPointDTO = (MDistributeParamPointDTO) o;
        if (mDistributeParamPointDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mDistributeParamPointDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MDistributeParamPointDTO{" +
            "id=" + getId() +
            ", targetAttribute=" + getTargetAttribute() +
            ", targetNationalityGroupId=" + getTargetNationalityGroupId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            ", iconAssetName='" + getIconAssetName() + "'" +
            "}";
    }
}
