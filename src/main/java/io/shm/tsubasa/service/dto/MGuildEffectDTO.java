package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGuildEffect} entity.
 */
public class MGuildEffectDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer effectType;

    
    @Lob
    private String name;

    private Integer effectId;

    
    @Lob
    private String thumbnailPath;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEffectType() {
        return effectType;
    }

    public void setEffectType(Integer effectType) {
        this.effectType = effectType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEffectId() {
        return effectId;
    }

    public void setEffectId(Integer effectId) {
        this.effectId = effectId;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGuildEffectDTO mGuildEffectDTO = (MGuildEffectDTO) o;
        if (mGuildEffectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGuildEffectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGuildEffectDTO{" +
            "id=" + getId() +
            ", effectType=" + getEffectType() +
            ", name='" + getName() + "'" +
            ", effectId=" + getEffectId() +
            ", thumbnailPath='" + getThumbnailPath() + "'" +
            "}";
    }
}
