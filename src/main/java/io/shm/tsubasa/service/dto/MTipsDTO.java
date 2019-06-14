package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTips} entity.
 */
public class MTipsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer priority;

    
    @Lob
    private String title;

    
    @Lob
    private String description;

    
    @Lob
    private String imageAssetName;

    @NotNull
    private Integer colorType;

    @NotNull
    private Integer isTips;

    @NotNull
    private Integer startAt;

    @NotNull
    private Integer endAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageAssetName() {
        return imageAssetName;
    }

    public void setImageAssetName(String imageAssetName) {
        this.imageAssetName = imageAssetName;
    }

    public Integer getColorType() {
        return colorType;
    }

    public void setColorType(Integer colorType) {
        this.colorType = colorType;
    }

    public Integer getIsTips() {
        return isTips;
    }

    public void setIsTips(Integer isTips) {
        this.isTips = isTips;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTipsDTO mTipsDTO = (MTipsDTO) o;
        if (mTipsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTipsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTipsDTO{" +
            "id=" + getId() +
            ", priority=" + getPriority() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageAssetName='" + getImageAssetName() + "'" +
            ", colorType=" + getColorType() +
            ", isTips=" + getIsTips() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            "}";
    }
}
