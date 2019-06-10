package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MColorPalette} entity.
 */
public class MColorPaletteDTO implements Serializable {

    private Long id;

    
    @Lob
    private String color;

    @NotNull
    private Integer orderNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MColorPaletteDTO mColorPaletteDTO = (MColorPaletteDTO) o;
        if (mColorPaletteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mColorPaletteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MColorPaletteDTO{" +
            "id=" + getId() +
            ", color='" + getColor() + "'" +
            ", orderNum=" + getOrderNum() +
            "}";
    }
}
