package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MDetachActionSkillCost} entity.
 */
public class MDetachActionSkillCostDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer rarity;

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

        MDetachActionSkillCostDTO mDetachActionSkillCostDTO = (MDetachActionSkillCostDTO) o;
        if (mDetachActionSkillCostDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mDetachActionSkillCostDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MDetachActionSkillCostDTO{" +
            "id=" + getId() +
            ", rarity=" + getRarity() +
            ", cost=" + getCost() +
            "}";
    }
}
