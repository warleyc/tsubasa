package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MFullPowerPoint} entity.
 */
public class MFullPowerPointDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer pointType;

    @NotNull
    private Integer value;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPointType() {
        return pointType;
    }

    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MFullPowerPointDTO mFullPowerPointDTO = (MFullPowerPointDTO) o;
        if (mFullPowerPointDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mFullPowerPointDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MFullPowerPointDTO{" +
            "id=" + getId() +
            ", pointType=" + getPointType() +
            ", value=" + getValue() +
            "}";
    }
}
