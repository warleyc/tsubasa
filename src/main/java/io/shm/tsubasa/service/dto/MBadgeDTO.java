package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MBadge} entity.
 */
public class MBadgeDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    @NotNull
    private Integer type;

    
    @Lob
    private String description;

    
    @Lob
    private String assetName;


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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MBadgeDTO mBadgeDTO = (MBadgeDTO) o;
        if (mBadgeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mBadgeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MBadgeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type=" + getType() +
            ", description='" + getDescription() + "'" +
            ", assetName='" + getAssetName() + "'" +
            "}";
    }
}
