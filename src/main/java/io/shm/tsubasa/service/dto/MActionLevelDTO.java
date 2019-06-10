package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MActionLevel} entity.
 */
public class MActionLevelDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer rarity;

    @NotNull
    private Integer level;

    @NotNull
    private Integer exp;


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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MActionLevelDTO mActionLevelDTO = (MActionLevelDTO) o;
        if (mActionLevelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mActionLevelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MActionLevelDTO{" +
            "id=" + getId() +
            ", rarity=" + getRarity() +
            ", level=" + getLevel() +
            ", exp=" + getExp() +
            "}";
    }
}
