package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MExtensionSale} entity.
 */
public class MExtensionSaleDTO implements Serializable {

    private Long id;

    @Lob
    private String menuMessage;

    @NotNull
    private Integer startAt;

    @NotNull
    private Integer endAt;

    @NotNull
    private Integer type;

    @NotNull
    private Integer addExtension;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuMessage() {
        return menuMessage;
    }

    public void setMenuMessage(String menuMessage) {
        this.menuMessage = menuMessage;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAddExtension() {
        return addExtension;
    }

    public void setAddExtension(Integer addExtension) {
        this.addExtension = addExtension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MExtensionSaleDTO mExtensionSaleDTO = (MExtensionSaleDTO) o;
        if (mExtensionSaleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mExtensionSaleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MExtensionSaleDTO{" +
            "id=" + getId() +
            ", menuMessage='" + getMenuMessage() + "'" +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            ", type=" + getType() +
            ", addExtension=" + getAddExtension() +
            "}";
    }
}
