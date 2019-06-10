package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCheatCaution} entity.
 */
public class MCheatCautionDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer caution;

    
    @Lob
    private String description;

    
    @Lob
    private String imageAssetName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCaution() {
        return caution;
    }

    public void setCaution(Integer caution) {
        this.caution = caution;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCheatCautionDTO mCheatCautionDTO = (MCheatCautionDTO) o;
        if (mCheatCautionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCheatCautionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCheatCautionDTO{" +
            "id=" + getId() +
            ", caution=" + getCaution() +
            ", description='" + getDescription() + "'" +
            ", imageAssetName='" + getImageAssetName() + "'" +
            "}";
    }
}
