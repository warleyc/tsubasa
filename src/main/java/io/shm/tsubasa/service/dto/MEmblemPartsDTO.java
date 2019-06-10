package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MEmblemParts} entity.
 */
public class MEmblemPartsDTO implements Serializable {

    private Long id;

    
    @Lob
    private String assetName;

    @NotNull
    private Integer partsType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Integer getPartsType() {
        return partsType;
    }

    public void setPartsType(Integer partsType) {
        this.partsType = partsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MEmblemPartsDTO mEmblemPartsDTO = (MEmblemPartsDTO) o;
        if (mEmblemPartsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mEmblemPartsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MEmblemPartsDTO{" +
            "id=" + getId() +
            ", assetName='" + getAssetName() + "'" +
            ", partsType=" + getPartsType() +
            "}";
    }
}
