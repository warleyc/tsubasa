package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MRecommendFormationFilterElement} entity.
 */
public class MRecommendFormationFilterElementDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer filterType;

    @NotNull
    private Integer elementId;

    
    @Lob
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MRecommendFormationFilterElementDTO mRecommendFormationFilterElementDTO = (MRecommendFormationFilterElementDTO) o;
        if (mRecommendFormationFilterElementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mRecommendFormationFilterElementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MRecommendFormationFilterElementDTO{" +
            "id=" + getId() +
            ", filterType=" + getFilterType() +
            ", elementId=" + getElementId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
