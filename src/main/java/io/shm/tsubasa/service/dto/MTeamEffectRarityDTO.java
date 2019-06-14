package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTeamEffectRarity} entity.
 */
public class MTeamEffectRarityDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer rarity;

    
    @Lob
    private String name;

    @NotNull
    private Integer maxLevel;


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

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTeamEffectRarityDTO mTeamEffectRarityDTO = (MTeamEffectRarityDTO) o;
        if (mTeamEffectRarityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTeamEffectRarityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTeamEffectRarityDTO{" +
            "id=" + getId() +
            ", rarity=" + getRarity() +
            ", name='" + getName() + "'" +
            ", maxLevel=" + getMaxLevel() +
            "}";
    }
}
