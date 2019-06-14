package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MUniformOriginalSet} entity.
 */
public class MUniformOriginalSetDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    
    @Lob
    private String shortName;

    
    @Lob
    private String menuName;

    @NotNull
    private Integer upModelId;

    @NotNull
    private Integer bottomModelId;

    
    @Lob
    private String thumbnailAssetName;

    @NotNull
    private Integer uniformType;

    @NotNull
    private Integer isDefault;

    @NotNull
    private Integer orderNum;

    
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getUpModelId() {
        return upModelId;
    }

    public void setUpModelId(Integer upModelId) {
        this.upModelId = upModelId;
    }

    public Integer getBottomModelId() {
        return bottomModelId;
    }

    public void setBottomModelId(Integer bottomModelId) {
        this.bottomModelId = bottomModelId;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
    }

    public Integer getUniformType() {
        return uniformType;
    }

    public void setUniformType(Integer uniformType) {
        this.uniformType = uniformType;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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

        MUniformOriginalSetDTO mUniformOriginalSetDTO = (MUniformOriginalSetDTO) o;
        if (mUniformOriginalSetDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mUniformOriginalSetDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MUniformOriginalSetDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", menuName='" + getMenuName() + "'" +
            ", upModelId=" + getUpModelId() +
            ", bottomModelId=" + getBottomModelId() +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            ", uniformType=" + getUniformType() +
            ", isDefault=" + getIsDefault() +
            ", orderNum=" + getOrderNum() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
