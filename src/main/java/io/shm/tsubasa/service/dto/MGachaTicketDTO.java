package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaTicket} entity.
 */
public class MGachaTicketDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    
    @Lob
    private String shortName;

    
    @Lob
    private String description;

    @NotNull
    private Integer maxAmount;

    
    @Lob
    private String thumbnailAssetName;

    @NotNull
    private Integer endAt;


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

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
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

        MGachaTicketDTO mGachaTicketDTO = (MGachaTicketDTO) o;
        if (mGachaTicketDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaTicketDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaTicketDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", description='" + getDescription() + "'" +
            ", maxAmount=" + getMaxAmount() +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            ", endAt=" + getEndAt() +
            "}";
    }
}
