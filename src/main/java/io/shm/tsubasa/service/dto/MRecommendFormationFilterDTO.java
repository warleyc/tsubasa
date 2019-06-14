package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MRecommendFormationFilter} entity.
 */
public class MRecommendFormationFilterDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer filterType;

    
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

        MRecommendFormationFilterDTO mRecommendFormationFilterDTO = (MRecommendFormationFilterDTO) o;
        if (mRecommendFormationFilterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mRecommendFormationFilterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MRecommendFormationFilterDTO{" +
            "id=" + getId() +
            ", filterType=" + getFilterType() +
            ", name='" + getName() + "'" +
            "}";
    }
}
