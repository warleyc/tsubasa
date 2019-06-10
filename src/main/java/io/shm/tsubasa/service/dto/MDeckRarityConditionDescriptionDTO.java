package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MDeckRarityConditionDescription} entity.
 */
public class MDeckRarityConditionDescriptionDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer rarityGroupId;

    @NotNull
    private Integer countType;

    @NotNull
    private Integer isStarting;

    
    @Lob
    private String description;

    
    @Lob
    private String iconName;

    
    @Lob
    private String smallIconName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRarityGroupId() {
        return rarityGroupId;
    }

    public void setRarityGroupId(Integer rarityGroupId) {
        this.rarityGroupId = rarityGroupId;
    }

    public Integer getCountType() {
        return countType;
    }

    public void setCountType(Integer countType) {
        this.countType = countType;
    }

    public Integer getIsStarting() {
        return isStarting;
    }

    public void setIsStarting(Integer isStarting) {
        this.isStarting = isStarting;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getSmallIconName() {
        return smallIconName;
    }

    public void setSmallIconName(String smallIconName) {
        this.smallIconName = smallIconName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO = (MDeckRarityConditionDescriptionDTO) o;
        if (mDeckRarityConditionDescriptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mDeckRarityConditionDescriptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MDeckRarityConditionDescriptionDTO{" +
            "id=" + getId() +
            ", rarityGroupId=" + getRarityGroupId() +
            ", countType=" + getCountType() +
            ", isStarting=" + getIsStarting() +
            ", description='" + getDescription() + "'" +
            ", iconName='" + getIconName() + "'" +
            ", smallIconName='" + getSmallIconName() + "'" +
            "}";
    }
}
