package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRenditionAfterInputCutInTextColor} entity.
 */
public class MGachaRenditionAfterInputCutInTextColorDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer isSsr;

    @NotNull
    private Integer weight;

    
    @Lob
    private String color;


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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionAfterInputCutInTextColorDTO mGachaRenditionAfterInputCutInTextColorDTO = (MGachaRenditionAfterInputCutInTextColorDTO) o;
        if (mGachaRenditionAfterInputCutInTextColorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionAfterInputCutInTextColorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionAfterInputCutInTextColorDTO{" +
            "id=" + getId() +
            ", isSsr=" + getIsSsr() +
            ", weight=" + getWeight() +
            ", color='" + getColor() + "'" +
            "}";
    }
}
