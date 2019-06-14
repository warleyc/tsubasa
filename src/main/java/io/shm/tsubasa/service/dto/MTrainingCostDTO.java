package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTrainingCost} entity.
 */
public class MTrainingCostDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer rarity;

    @NotNull
    private Integer level;

    @NotNull
    private Integer cost;


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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTrainingCostDTO mTrainingCostDTO = (MTrainingCostDTO) o;
        if (mTrainingCostDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTrainingCostDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTrainingCostDTO{" +
            "id=" + getId() +
            ", rarity=" + getRarity() +
            ", level=" + getLevel() +
            ", cost=" + getCost() +
            "}";
    }
}
