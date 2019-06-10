package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MEmblemSet} entity.
 */
public class MEmblemSetDTO implements Serializable {

    private Long id;

    
    @Lob
    private String assetName;

    
    @Lob
    private String name;

    
    @Lob
    private String description;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MEmblemSetDTO mEmblemSetDTO = (MEmblemSetDTO) o;
        if (mEmblemSetDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mEmblemSetDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MEmblemSetDTO{" +
            "id=" + getId() +
            ", assetName='" + getAssetName() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
