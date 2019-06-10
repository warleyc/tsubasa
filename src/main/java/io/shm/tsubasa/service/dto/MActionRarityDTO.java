package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MActionRarity} entity.
 */
public class MActionRarityDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer rarity;

    
    @Lob
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
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

        MActionRarityDTO mActionRarityDTO = (MActionRarityDTO) o;
        if (mActionRarityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mActionRarityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MActionRarityDTO{" +
            "id=" + getId() +
            ", rarity=" + getRarity() +
            ", name='" + getName() + "'" +
            "}";
    }
}
