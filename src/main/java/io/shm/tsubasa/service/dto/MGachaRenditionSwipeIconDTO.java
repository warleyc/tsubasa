package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRenditionSwipeIcon} entity.
 */
public class MGachaRenditionSwipeIconDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer isSsr;

    @NotNull
    private Integer isROrLess;

    @NotNull
    private Integer weight;

    
    @Lob
    private String swipeIconPrefabName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getIsROrLess() {
        return isROrLess;
    }

    public void setIsROrLess(Integer isROrLess) {
        this.isROrLess = isROrLess;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSwipeIconPrefabName() {
        return swipeIconPrefabName;
    }

    public void setSwipeIconPrefabName(String swipeIconPrefabName) {
        this.swipeIconPrefabName = swipeIconPrefabName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO = (MGachaRenditionSwipeIconDTO) o;
        if (mGachaRenditionSwipeIconDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionSwipeIconDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionSwipeIconDTO{" +
            "id=" + getId() +
            ", isSsr=" + getIsSsr() +
            ", isROrLess=" + getIsROrLess() +
            ", weight=" + getWeight() +
            ", swipeIconPrefabName='" + getSwipeIconPrefabName() + "'" +
            "}";
    }
}
