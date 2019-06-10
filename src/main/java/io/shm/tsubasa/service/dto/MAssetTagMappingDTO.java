package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MAssetTagMapping} entity.
 */
public class MAssetTagMappingDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer tag;

    
    @Lob
    private String ids;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MAssetTagMappingDTO mAssetTagMappingDTO = (MAssetTagMappingDTO) o;
        if (mAssetTagMappingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mAssetTagMappingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MAssetTagMappingDTO{" +
            "id=" + getId() +
            ", tag=" + getTag() +
            ", ids='" + getIds() + "'" +
            "}";
    }
}
