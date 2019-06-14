package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MModelUniformBottomResource} entity.
 */
public class MModelUniformBottomResourceDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer uniformTypeId;

    @NotNull
    private Integer dressingType;

    @NotNull
    private Integer width;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUniformTypeId() {
        return uniformTypeId;
    }

    public void setUniformTypeId(Integer uniformTypeId) {
        this.uniformTypeId = uniformTypeId;
    }

    public Integer getDressingType() {
        return dressingType;
    }

    public void setDressingType(Integer dressingType) {
        this.dressingType = dressingType;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO = (MModelUniformBottomResourceDTO) o;
        if (mModelUniformBottomResourceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mModelUniformBottomResourceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MModelUniformBottomResourceDTO{" +
            "id=" + getId() +
            ", uniformTypeId=" + getUniformTypeId() +
            ", dressingType=" + getDressingType() +
            ", width=" + getWidth() +
            "}";
    }
}
